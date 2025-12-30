#!/usr/bin/env python3
"""
CIA JSON Export Schema Field Completeness Validator

Validates that sample CSV files contain sufficient data to support JSON export schemas.
"""

import csv
import argparse
from pathlib import Path
from typing import Dict, List, Set
from dataclasses import dataclass, field

@dataclass
class SchemaMapping:
    schema_name: str
    primary_csv: str
    required_fields: List[str]
    additional_csvs: List[str]
    description: str = ""

@dataclass
class CSVAnalysis:
    filepath: Path
    exists: bool
    columns: List[str] = field(default_factory=list)
    row_count: int = 0
    
    @property
    def has_data(self) -> bool:
        return self.exists and self.row_count > 0

@dataclass
class ValidationResult:
    schema: SchemaMapping
    primary_csv_analysis: CSVAnalysis
    additional_csv_analyses: Dict[str, CSVAnalysis]
    missing_primary_fields: Set[str]
    
    @property
    def is_complete(self) -> bool:
        return (self.primary_csv_analysis.has_data and 
                len(self.missing_primary_fields) == 0)
    
    @property
    def completeness_percentage(self) -> float:
        if not self.schema.required_fields:
            return 100.0
        present = len(self.schema.required_fields) - len(self.missing_primary_fields)
        return (present / len(self.schema.required_fields)) * 100

class SchemaCSVMappings:
    @staticmethod
    def get_mappings() -> List[SchemaMapping]:
        return [
            SchemaMapping(
                schema_name="politician-schema.md",
                primary_csv="view_riksdagen_politician_sample.csv",
                required_fields=[
                    "person_id", "first_name", "last_name", "party", "born_year", "gender",
                    "total_days_served", "active", "first_assignment_date", "last_assignment_date",
                    "total_assignments", "current_assignments",
                    "total_committee_assignments", "current_committee_assignments",
                    "total_documents", "documents_last_year", "average_docs_per_year",
                    "total_days_served_parliament", "total_days_served_committee",
                    "total_days_served_government", "total_days_served_party"
                ],
                additional_csvs=[
                    "view_riksdagen_politician_summary_sample.csv",
                    "view_riksdagen_vote_data_ballot_politician_summary_sample.csv",
                    "view_riksdagen_politician_document_summary_sample.csv",
                    "view_riksdagen_politician_ranking_sample.csv"
                ],
                description="Politician profiles with voting records, activity metrics, and rankings"
            ),
            SchemaMapping(
                schema_name="party-schema.md",
                primary_csv="view_riksdagen_party_sample.csv",
                required_fields=[
                    # Core attributes - using actual CSV column names
                    "party_id", "party_name",
                    # Membership (head_count = total members)
                    "head_count",
                    # Activity metrics
                    "total_documents", "documents_last_year",
                    # Date ranges
                    "registered_date"
                ],
                additional_csvs=[
                    "view_riksdagen_party_summary_sample.csv",
                    "view_riksdagen_party_ballot_support_annual_summary_sample.csv",
                    "view_riksdagen_party_coalition_sample.csv"
                ],
                description="Party performance, coalitions, and voting patterns"
            ),
            SchemaMapping(
                schema_name="committee-schema.md",
                primary_csv="view_riksdagen_committee_sample.csv",
                required_fields=[
                    # Core attributes - using actual CSV column names
                    "embedded_id_org_code",  # committee ID
                    # Membership
                    "current_member_size", "active",
                    # Activity (documents is proxy for proposals)
                    "total_documents", "total_assignments",
                    # Date ranges
                    "first_assignment_date", "last_assignment_date"
                ],
                additional_csvs=[
                    "view_riksdagen_committee_proposal_summary_sample.csv",
                    "view_committee_productivity_sample.csv"
                ],
                description="Committee composition, proposals, and effectiveness"
            ),
            SchemaMapping(
                schema_name="ministry-schema.md",
                primary_csv="view_riksdagen_goverment_sample.csv",
                required_fields=[
                    # Core attributes - using actual CSV column names
                    "name_id",  # ministry identifier
                    # Membership and activity
                    "active", "current_member_size",
                    # Activity metrics
                    "total_documents", "total_propositions", "total_government_bills",
                    # Date ranges
                    "first_assignment_date", "last_assignment_date"
                ],
                additional_csvs=[
                    "view_ministry_decision_impact_sample.csv",
                    "view_ministry_decision_flow_sample.csv"
                ],
                description="Government ministries, decisions, and performance"
            ),
            SchemaMapping(
                schema_name="intelligence-schema.md",
                primary_csv="table_rule_violation_sample.csv",
                required_fields=[
                    # Rule violation tracking - using actual CSV column names
                    "rule_name", "reference_id", "resource_type", "status",
                    # Descriptive fields
                    "name", "rule_description", "rule_group",
                    # Timestamps
                    "detected_date", "positive"
                ],
                additional_csvs=[
                    "view_riksdagen_politician_summary_sample.csv",
                    "view_riksdagen_party_summary_sample.csv",
                    "view_decision_kpi_dashboard_sample.csv",
                    "view_party_decision_flow_sample.csv",
                    "view_politician_decision_flow_sample.csv",
                    "view_ministry_decision_flow_sample.csv"
                ],
                description="Risk assessment, analytics, predictions, and decision intelligence"
            )
        ]

class CSVValidator:
    def __init__(self, csv_dir: Path):
        self.csv_dir = csv_dir
        self._cache: Dict[str, CSVAnalysis] = {}
    
    def analyze_csv(self, csv_filename: str) -> CSVAnalysis:
        if csv_filename in self._cache:
            return self._cache[csv_filename]
        
        csv_path = self.csv_dir / csv_filename
        
        if not csv_path.exists():
            analysis = CSVAnalysis(filepath=csv_path, exists=False)
            self._cache[csv_filename] = analysis
            return analysis
        
        try:
            with open(csv_path, 'r') as f:
                reader = csv.reader(f)
                headers = next(reader, [])
                row_count = sum(1 for _ in reader)
            
            analysis = CSVAnalysis(
                filepath=csv_path,
                exists=True,
                columns=headers,
                row_count=row_count
            )
        except Exception:
            analysis = CSVAnalysis(filepath=csv_path, exists=True, columns=[], row_count=0)
        
        self._cache[csv_filename] = analysis
        return analysis
    
    def validate_schema(self, mapping: SchemaMapping) -> ValidationResult:
        primary_analysis = self.analyze_csv(mapping.primary_csv)
        
        if primary_analysis.exists:
            csv_columns = set(primary_analysis.columns)
            missing_fields = set(mapping.required_fields) - csv_columns
        else:
            missing_fields = set(mapping.required_fields)
        
        additional_analyses = {}
        for csv_name in mapping.additional_csvs:
            additional_analyses[csv_name] = self.analyze_csv(csv_name)
        
        return ValidationResult(
            schema=mapping,
            primary_csv_analysis=primary_analysis,
            additional_csv_analyses=additional_analyses,
            missing_primary_fields=missing_fields
        )

class ReportGenerator:
    @staticmethod
    def print_console_report(results: List[ValidationResult], verbose: bool = False):
        print("\n" + "="*80)
        print(" CIA JSON Export Schema - Field Completeness Validation Report")
        print("="*80 + "\n")
        
        print("This validation checks that sample CSV files contain the required fields")
        print("to support JSON export schemas defined in BUSINESS_PRODUCT_DOCUMENT.md\n")
        
        for result in results:
            status = "✅" if result.is_complete else "⚠️"
            print(f"\n{status} Schema: {result.schema.schema_name}")
            print(f"   Description: {result.schema.description}")
            print(f"   Primary CSV: {result.schema.primary_csv}")
            
            if result.primary_csv_analysis.exists:
                print(f"   Status: Found ({result.primary_csv_analysis.row_count} rows, "
                      f"{len(result.primary_csv_analysis.columns)} columns)")
                print(f"   Required Fields: {len(result.schema.required_fields)}")
                print(f"   Missing Fields: {len(result.missing_primary_fields)}")
                print(f"   Completeness: {result.completeness_percentage:.1f}%")
            else:
                print(f"   Status: ❌ CSV file not found!")
            
            if verbose and result.missing_primary_fields:
                print(f"\n   Missing required fields:")
                for field_name in sorted(result.missing_primary_fields):
                    print(f"      - {field_name}")
            
            if verbose:
                print(f"\n   Additional CSVs ({len(result.schema.additional_csvs)}):")
                for csv_name in result.schema.additional_csvs:
                    analysis = result.additional_csv_analyses.get(csv_name)
                    if analysis and analysis.exists:
                        print(f"      ✅ {csv_name} ({analysis.row_count} rows)")
                    else:
                        print(f"      ⚠️  {csv_name} (not found)")
        
        print("\n" + "="*80)
        print(" Summary")
        print("="*80)
        total = len(results)
        complete = sum(1 for r in results if r.is_complete)
        avg_completeness = sum(r.completeness_percentage for r in results) / total
        
        print(f"\n   Total Schemas Validated: {total}")
        print(f"   Complete Schemas: {complete}")
        print(f"   Incomplete Schemas: {total - complete}")
        print(f"   Average Completeness: {avg_completeness:.1f}%\n")
        
        if complete == total:
            print("   ✅ All schemas have complete field coverage!\n")
        else:
            print("   ⚠️  Some schemas have missing fields or CSVs.\n")
    
    @staticmethod
    def generate_markdown_report(results: List[ValidationResult], output_file: Path):
        with open(output_file, 'w') as f:
            f.write("# Field Completeness Validation Report\n\n")
            f.write("**Generated:** 2024-12-30\n\n")
            f.write("**Purpose:** Validate sample CSV data completeness for JSON export schemas\n\n")
            
            f.write("## Overview\n\n")
            f.write("This report validates that sample CSV files contain the required fields ")
            f.write("to support the 5 JSON export schemas.\n\n")
            
            f.write("### Summary Table\n\n")
            f.write("| Schema | Primary CSV | Rows | Required Fields | Missing | Completeness |\n")
            f.write("|--------|-------------|------|----------------|---------|-------------|\n")
            
            for result in results:
                status = "✅" if result.is_complete else "⚠️"
                rows = result.primary_csv_analysis.row_count if result.primary_csv_analysis.exists else 0
                f.write(f"| {status} {result.schema.schema_name} | `{result.schema.primary_csv}` | "
                       f"{rows} | {len(result.schema.required_fields)} | "
                       f"{len(result.missing_primary_fields)} | "
                       f"{result.completeness_percentage:.1f}% |\n")
            
            f.write("\n## Detailed Results\n\n")
            
            for result in results:
                f.write(f"### {result.schema.schema_name}\n\n")
                f.write(f"**Description:** {result.schema.description}\n\n")
                f.write(f"**Primary CSV:** `{result.schema.primary_csv}`\n\n")
                
                if result.primary_csv_analysis.exists:
                    f.write(f"- **Status:** ✅ Found\n")
                    f.write(f"- **Rows:** {result.primary_csv_analysis.row_count}\n")
                    f.write(f"- **Columns:** {len(result.primary_csv_analysis.columns)}\n")
                    f.write(f"- **Completeness:** {result.completeness_percentage:.1f}%\n\n")
                else:
                    f.write(f"- **Status:** ❌ NOT FOUND\n\n")
                
                if result.missing_primary_fields:
                    f.write(f"**Missing Required Fields ({len(result.missing_primary_fields)}):**\n\n")
                    for field in sorted(result.missing_primary_fields):
                        f.write(f"- `{field}`\n")
                    f.write("\n")
                else:
                    f.write("✅ All required fields present in primary CSV!\n\n")
                
                f.write(f"**Additional CSV Views ({len(result.schema.additional_csvs)}):**\n\n")
                for csv_name in result.schema.additional_csvs:
                    analysis = result.additional_csv_analyses.get(csv_name)
                    if analysis and analysis.exists:
                        f.write(f"- ✅ `{csv_name}` ({analysis.row_count} rows)\n")
                    else:
                        f.write(f"- ⚠️ `{csv_name}` (not found)\n")
                f.write("\n")

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--csv-dir', type=Path, default=Path('../service.data.impl/sample-data'))
    parser.add_argument('--output', type=Path, default=Path('FIELD_COMPLETENESS_REPORT.md'))
    parser.add_argument('--verbose', action='store_true')
    args = parser.parse_args()
    
    script_dir = Path(__file__).parent
    csv_dir = script_dir / args.csv_dir
    output_file = script_dir / args.output
    
    if not csv_dir.exists():
        print(f"Error: CSV directory not found: {csv_dir}")
        return 1
    
    mappings = SchemaCSVMappings.get_mappings()
    validator = CSVValidator(csv_dir)
    results = [validator.validate_schema(mapping) for mapping in mappings]
    
    ReportGenerator.print_console_report(results, verbose=args.verbose)
    ReportGenerator.generate_markdown_report(results, output_file)
    
    print(f"📄 Detailed report saved to: {output_file}\n")
    
    all_complete = all(r.is_complete for r in results)
    return 0 if all_complete else 1

if __name__ == '__main__':
    exit(main())
