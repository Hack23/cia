#!/usr/bin/env python3
"""
Collect Enhanced Temporal Analysis Statistics
Using new capabilities from PR #8271 and #8274

Usage: python3 collect_enhanced_temporal_stats.py [--db DATABASE] [--user USER]
"""

import subprocess
import json
import re
import argparse
from datetime import datetime
from typing import Dict, List, Tuple

# Constants
EXPECTED_PG_STAT_FIELDS = 8  # Expected fields in pg_stat_statements query result
EXPECTED_STAT_STATUS_FIELDS = 6  # Expected fields in statistics status query result

def run_psql(query: str, db_name: str = 'cia_dev', db_user: str = 'postgres') -> str:
    """Execute a PostgreSQL query and return output"""
    cmd = [
        'sudo', '-u', db_user, 'psql', '-d', db_name,
        '-t', '-A', '-c', query
    ]
    result = subprocess.run(cmd, capture_output=True, text=True)
    return result.stdout.strip()

def get_pg_stat_statements_data(db_name: str, db_user: str) -> List[Dict]:
    """Get pg_stat_statements data with planning and execution breakdown"""
    query = """
    SELECT 
        SUBSTRING(query FROM 'view_[a-z_]+') as view_name,
        calls,
        ROUND(mean_plan_time::numeric, 3) as mean_plan_ms,
        ROUND(mean_exec_time::numeric, 3) as mean_exec_ms,
        ROUND((mean_plan_time + mean_exec_time)::numeric, 3) as total_ms,
        ROUND((100.0 * mean_plan_time / NULLIF(mean_plan_time + mean_exec_time, 0))::numeric, 1) as plan_pct,
        ROUND(stddev_plan_time::numeric, 3) as stddev_plan_ms,
        ROUND(stddev_exec_time::numeric, 3) as stddev_exec_ms
    FROM pg_stat_statements 
    WHERE query LIKE '%view_%'
      AND query LIKE '%COUNT%'
      AND query NOT LIKE '%pg_stat_statements%'
    ORDER BY total_ms DESC;
    """
    output = run_psql(query, db_name, db_user)
    results = []
    for line in output.split('\n'):
        if line:
            parts = line.split('|')
            if len(parts) >= EXPECTED_PG_STAT_FIELDS:
                results.append({
                    'view_name': parts[0],
                    'calls': int(parts[1]),
                    'mean_plan_ms': float(parts[2]),
                    'mean_exec_ms': float(parts[3]),
                    'total_ms': float(parts[4]),
                    'plan_pct': float(parts[5]),
                    'stddev_plan_ms': float(parts[6]) if parts[6] else 0,
                    'stddev_exec_ms': float(parts[7]) if parts[7] else 0
                })
    return results

def get_explain_with_buffers(view_name: str, db_name: str, db_user: str) -> Tuple[str, Dict]:
    """Get EXPLAIN (ANALYZE, BUFFERS, TIMING) for a view"""
    query = f"""
    EXPLAIN (ANALYZE, BUFFERS, TIMING, VERBOSE, FORMAT JSON) 
    SELECT COUNT(*) FROM {view_name};
    """
    output = run_psql(query, db_name, db_user)
    
    try:
        # Parse JSON output
        explain_data = json.loads(output)
        plan = explain_data[0]['Plan']
        execution_time = explain_data[0].get('Execution Time', 0)
        planning_time = explain_data[0].get('Planning Time', 0)
        
        # Extract buffer statistics
        buffers = {
            'shared_hit': plan.get('Shared Hit Blocks', 0),
            'shared_read': plan.get('Shared Read Blocks', 0),
            'shared_dirtied': plan.get('Shared Dirtied Blocks', 0),
            'shared_written': plan.get('Shared Written Blocks', 0),
            'local_hit': plan.get('Local Hit Blocks', 0),
            'local_read': plan.get('Local Read Blocks', 0),
            'temp_read': plan.get('Temp Read Blocks', 0),
            'temp_written': plan.get('Temp Written Blocks', 0)
        }
        
        # Calculate buffer hit ratio
        total_blocks = buffers['shared_hit'] + buffers['shared_read']
        if total_blocks > 0:
            buffers['hit_ratio'] = round(100.0 * buffers['shared_hit'] / total_blocks, 2)
        else:
            buffers['hit_ratio'] = 0.0
        
        stats = {
            'execution_time': execution_time,
            'planning_time': planning_time,
            'total_cost': plan.get('Total Cost', 0),
            'actual_rows': plan.get('Actual Rows', 0),
            'plan_rows': plan.get('Plan Rows', 0),
            'buffers': buffers
        }
        
        # Calculate row estimate accuracy
        if stats['actual_rows'] > 0:
            stats['estimate_accuracy'] = round(
                100.0 * (1 - abs(stats['plan_rows'] - stats['actual_rows']) / stats['actual_rows']), 
                2
            )
        else:
            stats['estimate_accuracy'] = 100.0 if stats['plan_rows'] == 0 else 0.0
        
        return output, stats
    except Exception as e:
        return output, {}

def get_statistics_status(db_name: str, db_user: str) -> List[Dict]:
    """Get statistics collection status for base tables"""
    query = """
    SELECT 
        relname,
        last_analyze,
        last_autoanalyze,
        n_live_tup,
        n_dead_tup,
        n_mod_since_analyze
    FROM pg_stat_user_tables 
    WHERE relname IN ('vote_data', 'application_action_event', 'document_data', 'document_element')
    ORDER BY relname;
    """
    output = run_psql(query, db_name, db_user)
    results = []
    for line in output.split('\n'):
        if line:
            parts = line.split('|')
            if len(parts) >= EXPECTED_STAT_STATUS_FIELDS:
                results.append({
                    'table': parts[0],
                    'last_analyze': parts[1],
                    'last_autoanalyze': parts[2],
                    'n_live_tup': int(parts[3]),
                    'n_dead_tup': int(parts[4]),
                    'n_mod_since_analyze': int(parts[5]) if parts[5] else 0
                })
    return results

def get_all_temporal_views(db_name: str, db_user: str) -> List[str]:
    """Get list of all temporal views using documented naming conventions"""
    query = """
    SELECT c.relname
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE n.nspname = 'public'
      AND c.relkind IN ('v', 'm')
      AND (c.relname LIKE '%_summary_%' 
        OR c.relname LIKE '%_temporal_%'
        OR c.relname LIKE '%_daily%'
        OR c.relname LIKE '%_weekly%'
        OR c.relname LIKE '%_monthly%'
        OR c.relname LIKE '%_annual%')
    ORDER BY c.relname;
    """
    output = run_psql(query, db_name, db_user)
    return [line.strip() for line in output.split('\n') if line.strip()]

def get_view_type(view_name: str, db_name: str, db_user: str) -> str:
    """Determine if view is materialized or regular"""
    query = f"""
    SELECT CASE c.relkind 
        WHEN 'v' THEN 'VIEW'
        WHEN 'm' THEN 'MATVIEW'
    END as view_type
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE n.nspname = 'public'
      AND c.relname = '{view_name}';
    """
    return run_psql(query, db_name, db_user)

def main():
    parser = argparse.ArgumentParser(
        description='Collect enhanced temporal analysis statistics from PostgreSQL'
    )
    parser.add_argument('--db', default='cia_dev', help='Database name (default: cia_dev)')
    parser.add_argument('--user', default='postgres', help='Database user (default: postgres)')
    args = parser.parse_args()
    
    db_name = args.db
    db_user = args.user
    
    print("=" * 80)
    print("COLLECTING ENHANCED TEMPORAL STATISTICS")
    print("=" * 80)
    print(f"Database: {db_name}")
    print(f"User: {db_user}")
    print(f"Timestamp: {datetime.now().isoformat()}")
    print()
    
    # Step 1: Reset pg_stat_statements
    print("Step 1: Resetting pg_stat_statements...")
    run_psql("SELECT pg_stat_statements_reset();", db_name, db_user)
    
    # Step 2: Execute queries on all temporal views
    print("Step 2: Executing queries on temporal views...")
    views = get_all_temporal_views(db_name, db_user)
    print(f"Found {len(views)} temporal views")
    
    for view in views:
        try:
            run_psql(f"SELECT COUNT(*) FROM {view};", db_name, db_user)
            print(f"  ✓ {view}")
        except Exception as e:
            print(f"  ✗ {view}: {str(e)}")
    
    print()
    
    # Step 3: Collect pg_stat_statements data
    print("Step 3: Collecting pg_stat_statements data...")
    pg_stats = get_pg_stat_statements_data(db_name, db_user)
    print(f"Collected statistics for {len(pg_stats)} views")
    print()
    
    # Step 4: Collect EXPLAIN with BUFFERS data for key views
    print("Step 4: Collecting EXPLAIN data with BUFFERS and TIMING...")
    explain_views = [
        'view_riksdagen_vote_data_ballot_party_summary_daily',
        'view_riksdagen_vote_data_ballot_party_summary_weekly',
        'view_riksdagen_vote_data_ballot_party_summary_monthly',
        'view_riksdagen_vote_data_ballot_party_summary_annual',
        'view_application_action_event_page_daily_summary',
    ]
    
    explain_data = {}
    for view in explain_views:
        print(f"  Analyzing {view}...")
        try:
            raw, stats = get_explain_with_buffers(view, db_name, db_user)
            explain_data[view] = stats
        except Exception as e:
            print(f"    Error: {str(e)}")
    
    print()
    
    # Step 5: Check statistics status
    print("Step 5: Checking statistics collection status...")
    stats_status = get_statistics_status(db_name, db_user)
    
    print()
    print("=" * 80)
    print("ENHANCED STATISTICS SUMMARY")
    print("=" * 80)
    print()
    
    # Print pg_stat_statements summary
    print("## Planning vs Execution Time Breakdown")
    print()
    print(f"{'View Name':<60} {'Total':<10} {'Plan':<10} {'Exec':<10} {'Plan %':<10}")
    print("-" * 100)
    for stat in sorted(pg_stats, key=lambda x: x['total_ms'], reverse=True):
        print(f"{stat['view_name']:<60} {stat['total_ms']:<10.3f} {stat['mean_plan_ms']:<10.3f} {stat['mean_exec_ms']:<10.3f} {stat['plan_pct']:<10.1f}")
    
    print()
    print()
    
    # Print EXPLAIN summary
    print("## Buffer Statistics and I/O Timing")
    print()
    for view_name, stats in explain_data.items():
        if stats:
            print(f"### {view_name}")
            print(f"  - Planning Time: {stats['planning_time']:.3f} ms")
            print(f"  - Execution Time: {stats['execution_time']:.3f} ms")
            print(f"  - Estimate Accuracy: {stats['estimate_accuracy']:.2f}%")
            print(f"  - Buffer Hit Ratio: {stats['buffers']['hit_ratio']:.2f}%")
            print(f"  - Shared Blocks Hit: {stats['buffers']['shared_hit']}")
            print(f"  - Shared Blocks Read: {stats['buffers']['shared_read']}")
            print()
    
    print()
    print("## Statistics Collection Status")
    print()
    for stat in stats_status:
        print(f"### {stat['table']}")
        print(f"  - Last Analyze: {stat['last_analyze']}")
        print(f"  - Live Tuples: {stat['n_live_tup']}")
        print(f"  - Dead Tuples: {stat['n_dead_tup']}")
        print(f"  - Modified Since Analyze: {stat['n_mod_since_analyze']}")
        print()
    
    print()
    print("=" * 80)
    print("Collection complete!")
    print("=" * 80)

if __name__ == '__main__':
    main()
