#!/usr/bin/env python3
"""
extract-sample-data-fast.py
Fast Sample Data Extraction with Timeout Protection
Citizen Intelligence Agency - Open Source Intelligence Platform

Purpose: Quickly extracts sample data from all views and tables with
         timeout protection for complex views

Usage:
  cd service.data.impl/sample-data
  python3 ../src/main/resources/extract-sample-data-fast.py
  
Environment Variables:
  PGHOST - PostgreSQL host (default: localhost)
  PGPORT - PostgreSQL port (default: 5432)
  PGDATABASE - Database name (default: cia_dev)
  PGUSER - Database user (default: eris)
  PGPASSWORD - Database password
"""

import os
import sys
import subprocess
import time
from datetime import datetime
import csv

# Configuration
PGHOST = os.environ.get('PGHOST', 'localhost')
PGPORT = os.environ.get('PGPORT', '5432')
PGDATABASE = os.environ.get('PGDATABASE', 'cia_dev')
PGUSER = os.environ.get('PGUSER', 'eris')
TIMEOUT_SECONDS = 10

class ExtractionStats:
    def __init__(self):
        self.log = []
        
    def add_result(self, object_name, object_type, status, rows, duration_ms, error_msg=''):
        self.log.append({
            'object_name': object_name,
            'object_type': object_type,
            'status': status,
            'rows_extracted': rows,
            'duration_ms': duration_ms,
            'error_message': error_msg
        })
        
    def get_summary(self):
        total = len(self.log)
        success = len([r for r in self.log if r['status'] == 'SUCCESS'])
        timeout = len([r for r in self.log if r['status'] == 'TIMEOUT'])
        error = len([r for r in self.log if r['status'] == 'ERROR'])
        total_rows = sum(r['rows_extracted'] for r in self.log)
        return total, success, timeout, error, total_rows
        
    def write_log(self, filename='extraction_fast_log.csv'):
        with open(filename, 'w', newline='') as f:
            writer = csv.DictWriter(f, fieldnames=[
                'object_name', 'object_type', 'status', 
                'rows_extracted', 'duration_ms', 'error_message'
            ])
            writer.writeheader()
            writer.writerows(self.log)

def run_psql(query, timeout=TIMEOUT_SECONDS):
    """Run a psql query with timeout"""
    cmd = [
        'psql',
        '-h', PGHOST,
        '-p', PGPORT,
        '-U', PGUSER,
        '-d', PGDATABASE,
        '-t',  # tuples only
        '-c', query
    ]
    
    try:
        result = subprocess.run(
            cmd,
            capture_output=True,
            text=True,
            timeout=timeout,
            check=True
        )
        return result.stdout.strip(), None
    except subprocess.TimeoutExpired:
        return None, "TIMEOUT"
    except subprocess.CalledProcessError as e:
        return None, f"ERROR: {e.stderr}"
    except Exception as e:
        return None, f"ERROR: {str(e)}"

def extract_to_csv(object_name, object_type, sample_size, output_file, stats):
    """Extract data from a table or view to CSV"""
    start_time = time.time()
    
    query = f"\\COPY (SELECT * FROM {object_name} ORDER BY random() LIMIT {sample_size}) TO '{output_file}' WITH CSV HEADER"
    
    cmd = [
        'psql',
        '-h', PGHOST,
        '-p', PGPORT,
        '-U', PGUSER,
        '-d', PGDATABASE,
        '-c', query
    ]
    
    try:
        result = subprocess.run(
            cmd,
            capture_output=True,
            text=True,
            timeout=TIMEOUT_SECONDS,
            check=True
        )
        
        duration_ms = int((time.time() - start_time) * 1000)
        
        # Count rows in output file
        rows = 0
        if os.path.exists(output_file):
            with open(output_file, 'r') as f:
                rows = sum(1 for _ in f) - 1  # Subtract header
                
        stats.add_result(object_name, object_type, 'SUCCESS', rows, duration_ms)
        print(f"✓ {object_type}_{object_name}: {rows} rows")
        return True
        
    except subprocess.TimeoutExpired:
        duration_ms = int(TIMEOUT_SECONDS * 1000)
        stats.add_result(object_name, object_type, 'TIMEOUT', 0, duration_ms, f'Exceeded {TIMEOUT_SECONDS}s timeout')
        print(f"⏱ TIMEOUT: {object_type}_{object_name}")
        return False
        
    except subprocess.CalledProcessError as e:
        duration_ms = int((time.time() - start_time) * 1000)
        error_msg = e.stderr if e.stderr else str(e)
        stats.add_result(object_name, object_type, 'ERROR', 0, duration_ms, error_msg[:100])
        print(f"✗ ERROR: {object_type}_{object_name}")
        return False
        
    except Exception as e:
        duration_ms = int((time.time() - start_time) * 1000)
        stats.add_result(object_name, object_type, 'ERROR', 0, duration_ms, str(e)[:100])
        print(f"✗ ERROR: {object_type}_{object_name}: {str(e)}")
        return False

def get_view_complexity():
    """Get view complexity levels and sample sizes"""
    query = """
WITH RECURSIVE 
all_views AS (
    SELECT viewname AS view_name, 'view' AS view_type
    FROM pg_views WHERE schemaname = 'public'
    UNION ALL
    SELECT matviewname AS view_name, 'materialized_view' AS view_type
    FROM pg_matviews WHERE schemaname = 'public'
),
view_deps AS (
    SELECT DISTINCT
        dependent_view.relname AS view_name,
        source_table.relname AS depends_on
    FROM pg_depend
    JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
    JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
    JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
    JOIN pg_namespace dependent_ns ON dependent_ns.oid = dependent_view.relnamespace
    JOIN pg_namespace source_ns ON source_ns.oid = source_table.relnamespace
    WHERE dependent_view.relkind IN ('v', 'm')
      AND source_table.relkind IN ('v', 'm', 'r')
      AND pg_depend.deptype = 'n'
      AND dependent_ns.nspname = 'public'
      AND source_ns.nspname = 'public'
),
view_only_deps AS (
    SELECT vd.view_name, vd.depends_on
    FROM view_deps vd
    WHERE EXISTS (SELECT 1 FROM all_views av WHERE av.view_name = vd.depends_on)
),
dependency_depth AS (
    SELECT av.view_name, av.view_type, 0 AS depth
    FROM all_views av
    WHERE NOT EXISTS (SELECT 1 FROM view_only_deps vod WHERE vod.view_name = av.view_name)
    UNION ALL
    SELECT vod.view_name, av.view_type, dd.depth + 1
    FROM view_only_deps vod
    JOIN dependency_depth dd ON vod.depends_on = dd.view_name
    JOIN all_views av ON vod.view_name = av.view_name
)
SELECT 
    view_name,
    view_type,
    MAX(depth) as dependency_level,
    CASE 
        WHEN MAX(depth) <= 1 THEN 50
        WHEN MAX(depth) = 2 THEN 30
        ELSE 10
    END AS sample_size
FROM dependency_depth
GROUP BY view_name, view_type
ORDER BY MAX(depth), view_name;
    """
    
    result, error = run_psql(query, timeout=30)
    if error:
        print(f"ERROR getting view complexity: {error}")
        return []
    
    views = []
    for line in result.split('\n'):
        if line.strip():
            parts = [p.strip() for p in line.split('|')]
            if len(parts) >= 4:
                views.append({
                    'name': parts[0],
                    'type': parts[1],
                    'level': int(parts[2]) if parts[2].isdigit() else 0,
                    'sample_size': int(parts[3]) if parts[3].isdigit() else 50
                })
    return views

def main():
    print("=" * 60)
    print("Fast Sample Data Extraction")
    print(f"Started at: {datetime.now()}")
    print("=" * 60)
    print()
    print("Configuration:")
    print(f"  Host: {PGHOST}:{PGPORT}")
    print(f"  Database: {PGDATABASE}")
    print(f"  User: {PGUSER}")
    print(f"  Timeout: {TIMEOUT_SECONDS}s per view")
    print()
    
    stats = ExtractionStats()
    
    # Phase 1: Extract base tables
    print("--- PHASE 1: Extracting Base Tables ---")
    print()
    
    query = """
SELECT tablename 
FROM pg_tables 
WHERE schemaname = 'public' 
  AND tablename NOT LIKE 'qrtz_%'
  AND tablename NOT IN ('databasechangelog', 'databasechangeloglock')
ORDER BY tablename;
    """
    
    result, error = run_psql(query)
    if error:
        print(f"ERROR getting tables: {error}")
        return 1
        
    tables = [line.strip() for line in result.split('\n') if line.strip()]
    table_count = len(tables)
    table_success = 0
    
    for i, table in enumerate(tables, 1):
        output_file = f"table_{table}_sample.csv"
        if extract_to_csv(table, 'table', 50, output_file, stats):
            table_success += 1
    
    print()
    print(f"Tables: {table_success} successful out of {table_count}")
    print()
    
    # Phase 2: Analyze view complexity
    print("--- PHASE 2: Analyzing View Complexity ---")
    print()
    
    views = get_view_complexity()
    if not views:
        print("ERROR: Could not get view complexity")
        return 1
        
    print(f"Complexity analysis complete: {len(views)} views")
    print()
    
    # Phase 3: Extract views
    print("--- PHASE 3: Extracting Views (Simple to Complex) ---")
    print()
    
    view_success = 0
    view_timeout = 0
    
    for i, view in enumerate(views, 1):
        output_file = f"view_{view['name']}_sample.csv"
        result = extract_to_csv(view['name'], view['type'], view['sample_size'], output_file, stats)
        if result:
            view_success += 1
        elif stats.log[-1]['status'] == 'TIMEOUT':
            view_timeout += 1
    
    print()
    print(f"Views: {view_success} successful, {view_timeout} timeout out of {len(views)}")
    print()
    
    # Phase 4: Write log and summary
    print("--- PHASE 4: Summary ---")
    print()
    
    stats.write_log()
    
    total, success, timeout, error, total_rows = stats.get_summary()
    
    print("Overall Statistics:")
    print(f"  Total objects:      {total}")
    print(f"  Successful:         {success}")
    print(f"  Timeout:            {timeout}")
    print(f"  Errors:             {error}")
    print(f"  Total rows:         {total_rows}")
    print(f"  Success rate:       {success * 100 // total}%")
    print()
    
    print("=" * 60)
    print("Extraction Complete")
    print(f"Finished at: {datetime.now()}")
    print("=" * 60)
    print()
    print("Generated files:")
    print("  - table_*_sample.csv (base table samples)")
    print("  - view_*_sample.csv (view samples)")
    print("  - extraction_fast_log.csv (extraction status and timing)")
    print()
    
    return 0

if __name__ == '__main__':
    sys.exit(main())
