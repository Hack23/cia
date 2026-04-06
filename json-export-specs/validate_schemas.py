#!/usr/bin/env python3
"""
JSON Schema Validation Against Sample Data

This script validates the 5 JSON export schemas against CSV sample data files
to ensure correctness and identify mismatches between schema definitions and actual data.

Author: Citizen Intelligence Agency Development Team
License: Apache-2.0
Version: 1.1.0
"""

import csv
import json
import re
import sys
from collections import defaultdict
from datetime import datetime, timezone
from pathlib import Path
from typing import Dict, List, Tuple, Any


class SchemaValidator:
    """Validates JSON schemas against CSV sample data files."""
    
    # Structural JSON grouping fields that are not direct database columns
    STRUCTURAL_FIELDS = {
        "labels", "attributes", "relationships", "intelligence", "activity",
        "voting", "documents", "committees", "descriptions", "category",
        "subcategories", "intelligenceTags", "short", "detailed", "long",
        "breakdown", "period", "byType", "electoral", "parliamentary",
        "members", "coalition", "policy", "predictions", "membership",
        "productivity", "decisions", "budget", "personnel", "performance",
        "trend", "alignment"
    }
    
    # Computed fields that can be derived from existing database columns
    COMPUTED_FIELDS = {
        # Politician computed fields
        "fullName", "partyLoyalty", "rebellions", "influenceScore",
        "rankingPosition", "trendDirection", "absences", "activeDays",
        "amendments", "questions", "motions",
        # Party computed fields
        "totalMembers", "currentSupport", "seats", "votePercentage",
        "cohesionScore", "activityRate", "disciplineRate", "stability",
        "legislativeSuccess", "committeeChairs", "strengthScore",
        # Committee computed fields
        "code", "name", "regularMembers", "deputyMembers",
        "established", "reports", "performanceScore", "attendanceRate",
        "hearings",
        # Ministry computed fields
        "id", "ministers", "stateSecretaries", "civilServants",
        "effectiveness", "decisionsImplemented", "efficiency",
        "executionRate",
    }
    
    def __init__(self, schema_dir: str, sample_data_dir: str):
        self.schema_dir = Path(schema_dir)
        self.sample_data_dir = Path(sample_data_dir)
        self.schemas = {}
        self.sample_data = {}
        self.validation_results = {
            "timestamp": datetime.now(timezone.utc).isoformat(),
            "schemas_validated": 0,
            "files_analyzed": 0,
            "total_mismatches": 0,
            "field_status_summary": {
                "implemented": 0,
                "structural": 0,
                "computed": 0,
                "planned": 0
            },
            "schemas": {}
        }
        
    def load_schemas(self) -> None:
        """Load and parse all JSON schema markdown files."""
        schema_files = {
            "politician": "politician-schema.md",
            "party": "party-schema.md",
            "committee": "committee-schema.md",
            "ministry": "ministry-schema.md",
            "intelligence": "intelligence-schema.md"
        }
        
        for schema_name, filename in schema_files.items():
            schema_path = self.schema_dir / filename
            if schema_path.exists():
                self.schemas[schema_name] = self._parse_schema_markdown(schema_path)
                print(f"✓ Loaded schema: {schema_name}")
            else:
                print(f"✗ Schema not found: {schema_path}")
    
    def _parse_schema_markdown(self, schema_path: Path) -> Dict[str, Any]:
        """Extract field definitions from markdown schema file."""
        with open(schema_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        schema_info = {
            "name": schema_path.stem.replace("-schema", ""),
            "path": str(schema_path),
            "fields": {},
            "database_views": [],
            "example_json_paths": []
        }
        
        # Extract database view references
        view_pattern = r'`view_(\w+)`'
        schema_info["database_views"] = list(set(re.findall(view_pattern, content)))
        
        # Extract field definitions from mermaid diagrams
        # Pattern matches: +Type fieldName
        mermaid_pattern = r'\+(\w+)\s+(\w+)'
        for match in re.finditer(mermaid_pattern, content):
            field_type = match.group(1)
            field_name = match.group(2)
            # Only add field if it starts with a letter and is not numeric-only
            if re.match(r'^[A-Za-z]\w*$', field_name):
                schema_info["fields"][field_name] = {
                    "type": field_type,
                    "required": True  # Default assumption
                }
        
        # Extract JSON examples to identify field paths
        json_code_pattern = r'```json\s*(.*?)\s*```'
        json_examples = re.findall(json_code_pattern, content, re.DOTALL)
        for example in json_examples:
            try:
                parsed = json.loads(example)
                if isinstance(parsed, dict) and "data" in parsed:
                    # This is a data structure example
                    schema_info["example_json_paths"].append(self._extract_paths(parsed.get("data", [])))
            except json.JSONDecodeError:
                # Skip invalid JSON examples in markdown
                pass
        
        return schema_info
    
    def _extract_paths(self, data: Any, prefix: str = "") -> List[str]:
        """Recursively extract all field paths from JSON structure."""
        paths = []
        if isinstance(data, dict):
            for key, value in data.items():
                current_path = f"{prefix}.{key}" if prefix else key
                paths.append(current_path)
                paths.extend(self._extract_paths(value, current_path))
        elif isinstance(data, list) and data:
            paths.extend(self._extract_paths(data[0], prefix))
        return paths
    
    def load_sample_data(self) -> None:
        """Load all CSV sample data files and analyze their structure."""
        csv_files = list(self.sample_data_dir.glob("*.csv"))
        print(f"\nFound {len(csv_files)} CSV files in {self.sample_data_dir}")
        
        # Focus on view and table files, excluding distinct values and stats
        relevant_files = [
            f for f in csv_files 
            if (f.name.startswith("view_") or f.name.startswith("table_"))
            and not f.name.startswith("distinct_")
            and f.name not in ["extraction_statistics.csv", "materialized_view_statistics.csv", 
                              "sample_data_manifest.csv", "view_column_mapping.csv"]
        ]
        
        print(f"Analyzing {len(relevant_files)} relevant data files...")
        
        for csv_file in relevant_files:
            try:
                with open(csv_file, 'r', encoding='utf-8') as f:
                    reader = csv.DictReader(f)
                    columns = reader.fieldnames or []
                    
                    # Read first few rows to analyze data types
                    rows = []
                    for i, row in enumerate(reader):
                        if i < 10:  # Sample first 10 rows
                            rows.append(row)
                        else:
                            break
                    
                    self.sample_data[csv_file.stem] = {
                        "file": csv_file.name,
                        "columns": columns,
                        "sample_rows": rows,
                        "column_types": self._infer_column_types(rows, columns)
                    }
                    
            except (IOError, csv.Error, UnicodeDecodeError) as e:
                print(f"  ✗ Error reading {csv_file.name}: {e}")
        
        print(f"✓ Loaded {len(self.sample_data)} sample data files")
        self.validation_results["files_analyzed"] = len(self.sample_data)
    
    def _infer_column_types(self, rows: List[Dict], columns: List[str]) -> Dict[str, str]:
        """Infer data types for each column based on sample data."""
        types = {}
        
        for column in columns:
            # Collect non-empty values
            values = [row.get(column, "") for row in rows if row.get(column, "").strip()]
            
            if not values:
                types[column] = "empty"
                continue
            
            # Check for date/timestamp
            if any(keyword in column.lower() for keyword in ["date", "time", "timestamp"]):
                types[column] = "date"
            # Check for boolean
            elif all(v.lower() in ['t', 'f', 'true', 'false', '0', '1'] for v in values):
                types[column] = "boolean"
            # Check for integer
            elif all(self._is_integer(v) for v in values):
                types[column] = "integer"
            # Check for float
            elif all(self._is_float(v) for v in values):
                types[column] = "float"
            else:
                types[column] = "string"
        
        return types
    
    @staticmethod
    def _is_integer(value: str) -> bool:
        """Check if string represents an integer."""
        try:
            int(value)
            return True
        except (ValueError, TypeError):
            return False
    
    @staticmethod
    def _is_float(value: str) -> bool:
        """Check if string represents a float."""
        try:
            float(value)
            return True
        except (ValueError, TypeError):
            return False
    
    def validate_schema_mappings(self) -> None:
        """Validate each schema against corresponding sample data files."""
        
        # Define mappings between schemas and database views
        schema_to_views = {
            "politician": ["riksdagen_politician", "riksdagen_politician_summary", 
                          "riksdagen_politician_document", "riksdagen_politician_ballot",
                          "riksdagen_politician_document_summary"],
            "party": ["riksdagen_party", "riksdagen_party_summary", 
                     "riksdagen_party_document_summary", "riksdagen_party_ballot"],
            "committee": ["riksdagen_committee", "riksdagen_committee_decisions",
                         "riksdagen_committee_roles", "riksdagen_committee_ballot_decision"],
            "ministry": ["riksdagen_government", "riksdagen_government_roles",
                        "ministry_decision_impact", "ministry_effectiveness_trends"],
            "intelligence": ["politician_risk_summary", "party_performance_metrics",
                           "decision_temporal_trends", "committee_productivity"]
        }
        
        for schema_name, schema_info in self.schemas.items():
            print(f"\n{'='*80}")
            print(f"Validating Schema: {schema_name.upper()}")
            print(f"{'='*80}")
            
            schema_result = {
                "views_referenced": len(schema_info["database_views"]),
                "fields_defined": len(schema_info["fields"]),
                "matched_views": [],
                "missing_views": [],
                "field_mismatches": [],
                "recommendations": []
            }
            
            # Find relevant sample data files
            relevant_views = schema_to_views.get(schema_name, [])
            matched_files = []
            
            for file_key, file_data in self.sample_data.items():
                for view_pattern in relevant_views:
                    if view_pattern in file_key:
                        matched_files.append((file_key, file_data))
                        schema_result["matched_views"].append(file_data["file"])
                        break
            
            print(f"\nFound {len(matched_files)} matching data files:")
            for file_key, _ in matched_files:
                print(f"  • {file_key}")
            
            # Validate fields against data
            if matched_files:
                self._validate_fields(schema_name, schema_info, matched_files, schema_result)
            else:
                schema_result["recommendations"].append(
                    "No matching sample data files found. Verify view name mappings."
                )
            
            # Check for database views mentioned in schema
            for view_name in schema_info["database_views"]:
                found = False
                for file_key in self.sample_data.keys():
                    if view_name in file_key:
                        found = True
                        break
                
                if not found:
                    schema_result["missing_views"].append(view_name)
            
            self.validation_results["schemas"][schema_name] = schema_result
            self.validation_results["schemas_validated"] += 1
            
            # Print summary
            print(f"\n{'─'*80}")
            print(f"Schema: {schema_name}")
            print(f"  Fields defined: {schema_result['fields_defined']}")
            print(f"  Views matched: {len(schema_result['matched_views'])}")
            print(f"  Missing views: {len(schema_result['missing_views'])}")
            print(f"  Field mismatches: {len(schema_result['field_mismatches'])}")
            print(f"  Recommendations: {len(schema_result['recommendations'])}")
    
    def _validate_fields(self, schema_name: str, schema_info: Dict, 
                        matched_files: List[Tuple[str, Dict]], 
                        schema_result: Dict) -> None:
        """Validate schema fields against actual data columns."""
        
        # Collect all columns from matched files
        all_columns = set()
        column_to_files = defaultdict(list)
        
        for file_key, file_data in matched_files:
            for column in file_data["columns"]:
                all_columns.add(column)
                column_to_files[column].append(file_key)
        
        print(f"\nTotal unique columns in data: {len(all_columns)}")
        
        # Map schema fields to database columns (convert camelCase to snake_case)
        # Sort for deterministic output order
        schema_fields = sorted(schema_info["fields"].keys())
        
        # Initialize field status tracking
        field_status = {
            "implemented": [],
            "structural": [],
            "computed": [],
            "planned": []
        }
        
        # Check if schema fields exist in data (with flexible matching)
        unmapped_schema_fields = []
        unmapped_data_columns = sorted(all_columns)
        
        for field in schema_fields:
            # Try various naming conventions (deterministic order, deduplicated)
            seen = set()
            possible_names = []
            for candidate in [field, self._camel_to_snake(field), field.lower(), field.upper()]:
                if candidate not in seen:
                    seen.add(candidate)
                    possible_names.append(candidate)
            
            matched = False
            for possible_name in possible_names:
                if possible_name in all_columns:
                    matched = True
                    if possible_name in unmapped_data_columns:
                        unmapped_data_columns.remove(possible_name)
                    field_status["implemented"].append(field)
                    break
            
            if not matched:
                # Classify the mismatch: structural > computed > planned
                if field in self.STRUCTURAL_FIELDS:
                    status = "STRUCTURAL"
                    field_status["structural"].append(field)
                elif field in self.COMPUTED_FIELDS:
                    status = "COMPUTED"
                    field_status["computed"].append(field)
                else:
                    status = "PLANNED"
                    field_status["planned"].append(field)
                
                unmapped_schema_fields.append(field)
                schema_result["field_mismatches"].append({
                    "field": field,
                    "issue": "Field defined in schema but not found in data",
                    "status": status,
                    "suggestions": possible_names
                })
        
        # Store field status in schema result
        schema_result["field_status"] = field_status
        
        # Update global status summary
        for status_key in field_status:
            self.validation_results["field_status_summary"][status_key] += len(field_status[status_key])
        
        # Report field implementation status
        print(f"\n📊 Field Implementation Status:")
        print(f"  ✅ Implemented: {len(field_status['implemented'])}")
        print(f"  ❌ Structural (JSON grouping): {len(field_status['structural'])}")
        print(f"  🔀 Computed (derivable): {len(field_status['computed'])}")
        print(f"  🔄 Planned (not yet in data): {len(field_status['planned'])}")
        
        # Report unmapped fields by category
        if field_status["computed"]:
            print(f"\n🔀 Computed fields (derivable from DB) ({len(field_status['computed'])}):")
            for field in field_status["computed"][:10]:
                print(f"  • {field}")
            if len(field_status["computed"]) > 10:
                print(f"  ... and {len(field_status['computed']) - 10} more")
        
        if field_status["planned"]:
            print(f"\n🔄 Planned fields not yet in data ({len(field_status['planned'])}):")
            for field in field_status["planned"][:10]:
                print(f"  • {field}")
            if len(field_status["planned"]) > 10:
                print(f"  ... and {len(field_status['planned']) - 10} more")
        
        if field_status["structural"]:
            print(f"\n❌ Structural grouping fields ({len(field_status['structural'])}):")
            for field in field_status["structural"][:5]:
                print(f"  • {field}")
            if len(field_status["structural"]) > 5:
                print(f"  ... and {len(field_status['structural']) - 5} more")
        
        # Report key data columns not in schema
        if unmapped_data_columns:
            important_columns = [
                col for col in unmapped_data_columns 
                if not col.startswith('hjid') and col not in ['created_date', 'modified_date']
            ][:20]  # Show top 20
            
            if important_columns:
                print(f"\n💡 Key data columns not in schema ({len(important_columns)}):")
                for col in important_columns:
                    print(f"  • {col} (in {len(column_to_files[col])} files)")
                
                schema_result["recommendations"].append(
                    f"Consider adding {len(important_columns)} data columns to schema: {', '.join(important_columns[:5])}"
                )
        
        self.validation_results["total_mismatches"] += len(schema_result["field_mismatches"])
    
    @staticmethod
    def _camel_to_snake(name: str) -> str:
        """Convert camelCase to snake_case."""
        s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', name)
        return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()
    
    @staticmethod
    def _format_field_status_row(icon: str, label: str, fields: list, max_display: int = 8) -> str:
        """Format a field status table row for the validation report."""
        count = len(fields)
        field_list = ', '.join(f'`{f}`' for f in fields[:max_display])
        if count > max_display:
            field_list += '...'
        return f"| {icon} {label} | {count} | {field_list} |"

    def generate_report(self, output_path: str = None) -> None:
        """Generate comprehensive validation report."""
        
        if output_path is None:
            output_path = self.schema_dir / "SCHEMA_VALIDATION_REPORT.md"
        
        report_lines = [
            "# JSON Schema Validation Report",
            "",
            f"**Generated:** {self.validation_results['timestamp']}",
            f"**Schemas Validated:** {self.validation_results['schemas_validated']}",
            f"**Sample Files Analyzed:** {self.validation_results['files_analyzed']}",
            f"**Total Mismatches:** {self.validation_results['total_mismatches']}",
            "",
            "---",
            "",
            "## Executive Summary",
            "",
            f"This report validates the 5 JSON export schemas against "
            f"{self.validation_results['files_analyzed']} relevant CSV sample data files "
            "from the CIA database to ensure schema correctness and identify gaps between "
            "schema definitions and actual data structure.",
            "",
            "### Field Implementation Status Summary",
            "",
            f"| Category | Count | Description |",
            f"|----------|-------|-------------|",
            f"| ✅ Implemented | {self.validation_results['field_status_summary']['implemented']} | Fields found in database sample data |",
            f"| ❌ Structural | {self.validation_results['field_status_summary']['structural']} | JSON grouping objects (not direct DB columns) |",
            f"| 🔀 Computed | {self.validation_results['field_status_summary']['computed']} | Derivable from existing database columns |",
            f"| 🔄 Planned | {self.validation_results['field_status_summary']['planned']} | Fields not yet available in data |",
            "",
            "### Validation Scope",
            "",
            "| Schema | Fields Defined | Views Matched | Missing Views | Field Mismatches | Implemented | Status |",
            "|--------|---------------|---------------|---------------|------------------|-------------|--------|"
        ]
        
        for schema_name, result in self.validation_results["schemas"].items():
            status = "✅ PASS" if len(result["field_mismatches"]) == 0 else "⚠️ REVIEW"
            implemented = len(result.get("field_status", {}).get("implemented", []))
            report_lines.append(
                f"| {schema_name.capitalize()} | {result['fields_defined']} | "
                f"{len(result['matched_views'])} | {len(result['missing_views'])} | "
                f"{len(result['field_mismatches'])} | {implemented} | {status} |"
            )
        
        report_lines.extend([
            "",
            "---",
            "",
            "## Detailed Findings",
            ""
        ])
        
        for schema_name, result in self.validation_results["schemas"].items():
            field_status = result.get("field_status", {})
            report_lines.extend([
                f"### {schema_name.capitalize()} Schema",
                "",
                f"**Fields Defined:** {result['fields_defined']}  ",
                f"**Database Views Referenced:** {result['views_referenced']}  ",
                f"**Sample Files Matched:** {len(result['matched_views'])}",
                "",
                "#### Field Implementation Status",
                "",
                f"| Category | Count | Fields |",
                f"|----------|-------|--------|",
                self._format_field_status_row("✅", "Implemented", field_status.get('implemented', [])),
                self._format_field_status_row("❌", "Structural", field_status.get('structural', [])),
                self._format_field_status_row("🔀", "Computed", field_status.get('computed', [])),
                self._format_field_status_row("🔄", "Planned", field_status.get('planned', [])),
                ""
            ])
            
            if result["matched_views"]:
                report_lines.extend([
                    "#### Matched Data Files",
                    ""
                ])
                for view_file in result["matched_views"][:10]:
                    report_lines.append(f"- `{view_file}`")
                if len(result["matched_views"]) > 10:
                    report_lines.append(f"- ... and {len(result['matched_views']) - 10} more")
                report_lines.append("")
            
            if result["missing_views"]:
                report_lines.extend([
                    "#### Missing Views",
                    "",
                    "The following database views are referenced in the schema but not found in sample data:",
                    ""
                ])
                for view in result["missing_views"]:
                    report_lines.append(f"- `{view}`")
                report_lines.append("")
            
            if result["field_mismatches"]:
                report_lines.extend([
                    "#### Field Mismatches",
                    ""
                ])
                for mismatch in result["field_mismatches"][:10]:
                    report_lines.extend([
                        f"**Field:** `{mismatch['field']}`",
                        f"- **Issue:** {mismatch['issue']}",
                        f"- **Suggested columns:** {', '.join([f'`{s}`' for s in mismatch['suggestions']])}",
                        ""
                    ])
                if len(result["field_mismatches"]) > 10:
                    report_lines.append(f"*... and {len(result['field_mismatches']) - 10} more mismatches*")
                    report_lines.append("")
            
            if result["recommendations"]:
                report_lines.extend([
                    "#### Recommendations",
                    ""
                ])
                for rec in result["recommendations"]:
                    report_lines.append(f"- {rec}")
                report_lines.append("")
            
            report_lines.append("---")
            report_lines.append("")
        
        report_lines.extend([
            "## Next Steps",
            "",
            "1. **Review Field Mismatches**: Update schemas to match actual database column names",
            "2. **Add Missing Fields**: Consider adding important data columns to schemas",
            "3. **Document Mappings**: Create explicit field mapping documentation (JSON ↔ Database)",
            "4. **Update Examples**: Ensure JSON examples use actual field names from data",
            "5. **Continuous Validation**: Integrate this validation into CI/CD pipeline",
            "",
            "## Validation Methodology",
            "",
            "This validation compares:",
            "- Field definitions in JSON schema markdown files",
            f"- Column names and data types from {self.validation_results['files_analyzed']} relevant CSV sample files",
            "- Database view references in schema documentation",
            "",
            "**Validation includes:**",
            "- Field name matching (with camelCase/snake_case conversion)",
            "- Data type inference from sample data",
            "- Coverage analysis (schema fields vs. actual columns)",
            "- Missing view detection",
            "",
            f"**Report Generated:** {datetime.now(timezone.utc).strftime('%Y-%m-%d %H:%M:%S')} UTC",
            ""
        ])
        
        # Write report
        with open(output_path, 'w', encoding='utf-8') as f:
            f.write('\n'.join(report_lines))
        
        print(f"\n{'='*80}")
        print(f"✓ Validation report generated: {output_path}")
        print(f"{'='*80}")
    
    def generate_json_report(self, output_path: str = None) -> None:
        """Generate JSON format validation report for programmatic use."""
        
        if output_path is None:
            output_path = self.schema_dir / "validation-results.json"
        
        with open(output_path, 'w', encoding='utf-8') as f:
            json.dump(self.validation_results, f, indent=2)
        
        print(f"✓ JSON validation results saved: {output_path}")


def main():
    """Main execution function."""
    print("="*80)
    print("JSON Schema Validation Against Sample Data")
    print("Citizen Intelligence Agency")
    print("="*80)
    print()
    
    # Determine paths
    script_dir = Path(__file__).parent
    schema_dir = script_dir / "schemas"
    sample_data_dir = script_dir.parent / "service.data.impl" / "sample-data"
    
    # Verify directories exist
    if not schema_dir.exists():
        print(f"✗ Schema directory not found: {schema_dir}")
        sys.exit(1)
    
    if not sample_data_dir.exists():
        print(f"✗ Sample data directory not found: {sample_data_dir}")
        sys.exit(1)
    
    print(f"Schema directory: {schema_dir}")
    print(f"Sample data directory: {sample_data_dir}")
    print()
    
    # Initialize validator
    validator = SchemaValidator(str(schema_dir), str(sample_data_dir))
    
    # Load schemas
    print("Loading JSON schemas...")
    validator.load_schemas()
    
    # Load sample data
    print("\nLoading sample data...")
    validator.load_sample_data()
    
    # Validate mappings
    print("\nValidating schema mappings...")
    validator.validate_schema_mappings()
    
    # Generate reports
    print("\nGenerating reports...")
    validator.generate_report()
    validator.generate_json_report()
    
    print("\n" + "="*80)
    print("Validation Complete!")
    print(f"Total Mismatches: {validator.validation_results['total_mismatches']}")
    summary = validator.validation_results.get('field_status_summary', {})
    print(f"\nField Implementation Status:")
    print(f"  ✅ Implemented: {summary.get('implemented', 0)}")
    print(f"  ❌ Structural (JSON grouping): {summary.get('structural', 0)}")
    print(f"  🔀 Computed (derivable): {summary.get('computed', 0)}")
    print(f"  🔄 Planned (not yet in data): {summary.get('planned', 0)}")
    print("="*80)
    
    # Return exit code based on results
    return 0 if validator.validation_results['total_mismatches'] == 0 else 1


if __name__ == "__main__":
    sys.exit(main())
