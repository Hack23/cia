#!/usr/bin/env python3
"""
Database View Validation Script for Framework Validation Enhancement

Purpose: Validate all database views referenced in the enhancement work against
         the PostgreSQL database to ensure queries are correct and views exist

Database Access: Requires PostgreSQL connection to cia_dev database
                 Configuration from .github/workflows/copilot-setup-steps.yml

Referenced Views:
1. view_ministry_risk_evolution - Ministry risk tracking
2. view_riksdagen_party_momentum_analysis - Party momentum metrics
3. view_politician_risk_summary - Politician risk profiles
"""

import psycopg2
import sys
import os

class DatabaseViewValidator:
    def __init__(self, dbname=None, user=None, password=None, host=None):
        """Initialize database connection"""
        # Resolve database configuration with environment variable fallbacks
        self.dbname = dbname or os.environ.get("CIA_DB_NAME", "cia_dev")
        self.user = user or os.environ.get("CIA_DB_USER", "eris")
        self.password = password or os.environ.get("CIA_DB_PASSWORD", "discord")
        self.host = host or os.environ.get("CIA_DB_HOST", "localhost")
        self.conn = None
        self.validation_results = []
        
    def connect(self):
        """Establish database connection"""
        try:
            self.conn = psycopg2.connect(
                dbname=self.dbname,
                user=self.user,
                password=self.password,
                host=self.host
            )
            print(f"✅ Connected to database: {self.dbname}")
            return True
        except Exception as e:
            print(f"❌ Database connection failed: {e}")
            return False
    
    def validate_view_exists(self, view_name):
        """Check if a view exists in the database"""
        try:
            cursor = self.conn.cursor()
            cursor.execute("""
                SELECT EXISTS (
                    SELECT 1 
                    FROM information_schema.views 
                    WHERE table_schema = 'public' 
                    AND table_name = %s
                )
            """, (view_name,))
            exists = cursor.fetchone()[0]
            cursor.close()
            
            if exists:
                print(f"  ✅ View exists: {view_name}")
                return True
            else:
                print(f"  ❌ View NOT found: {view_name}")
                return False
        except Exception as e:
            print(f"  ❌ Error checking view {view_name}: {e}")
            return False
    
    def validate_view_columns(self, view_name, expected_columns):
        """Validate that a view has expected columns"""
        try:
            cursor = self.conn.cursor()
            cursor.execute("""
                SELECT column_name 
                FROM information_schema.columns 
                WHERE table_schema = 'public' 
                AND table_name = %s
                ORDER BY ordinal_position
            """, (view_name,))
            actual_columns = [row[0] for row in cursor.fetchall()]
            cursor.close()
            
            missing_columns = set(expected_columns) - set(actual_columns)
            extra_columns = set(actual_columns) - set(expected_columns)
            
            if not missing_columns and not extra_columns:
                print(f"  ✅ All {len(expected_columns)} expected columns present")
                return True
            else:
                if missing_columns:
                    print(f"  ⚠️  Missing columns: {', '.join(missing_columns)}")
                if extra_columns:
                    print(f"  ℹ️  Extra columns (OK): {', '.join(extra_columns)}")
                return len(missing_columns) == 0  # Pass if all required columns exist
        except Exception as e:
            print(f"  ❌ Error validating columns for {view_name}: {e}")
            return False
    
    def test_view_query(self, view_name, test_query):
        """Test that a sample query against the view executes without error"""
        try:
            cursor = self.conn.cursor()
            cursor.execute(test_query)
            # Just check execution, don't fetch results (may be empty)
            cursor.close()
            print(f"  ✅ Sample query executed successfully")
            return True
        except Exception as e:
            print(f"  ❌ Query execution failed: {e}")
            return False
    
    def validate_ministry_risk_evolution(self):
        """Validate view_ministry_risk_evolution"""
        print("\n🔍 Validating: view_ministry_risk_evolution")
        view_name = 'view_ministry_risk_evolution'
        
        # Check existence
        if not self.validate_view_exists(view_name):
            return False
        
        # Expected columns based on enhancement-details.md
        expected_columns = [
            'org_code', 'ministry_name', 'year', 'quarter',
            'documents_produced', 'legislative_count',
            'document_trend', 'legislative_trend',
            'current_risk_level'
        ]
        
        column_check = self.validate_view_columns(view_name, expected_columns)
        
        # Test query
        test_query = f"""
            SELECT org_code, ministry_name, year, quarter, 
                   documents_produced, current_risk_level
            FROM {view_name}
            WHERE year >= 2020
            LIMIT 10
        """
        query_check = self.test_view_query(view_name, test_query)
        
        result = column_check and query_check
        self.validation_results.append({
            'view': view_name,
            'passed': result,
            'framework': 'Temporal Analysis'
        })
        return result
    
    def validate_party_momentum_analysis(self):
        """Validate view_riksdagen_party_momentum_analysis"""
        print("\n🔍 Validating: view_riksdagen_party_momentum_analysis")
        view_name = 'view_riksdagen_party_momentum_analysis'
        
        # Check existence
        if not self.validate_view_exists(view_name):
            return False
        
        # Expected columns based on enhancement-details.md
        expected_columns = [
            'party', 'year', 'quarter', 'period',
            'ballots_participated', 'participation_rate',
            'prev_quarter_rate', 'momentum',
            'moving_avg_4q', 'volatility', 'acceleration',
            'trend_direction', 'stability_classification'
        ]
        
        column_check = self.validate_view_columns(view_name, expected_columns)
        
        # Test query
        test_query = f"""
            SELECT party, year, quarter, participation_rate,
                   momentum, trend_direction
            FROM {view_name}
            WHERE year >= 2020
            LIMIT 10
        """
        query_check = self.test_view_query(view_name, test_query)
        
        result = column_check and query_check
        self.validation_results.append({
            'view': view_name,
            'passed': result,
            'framework': 'Comparative Analysis'
        })
        return result
    
    def validate_politician_risk_summary(self):
        """Validate view_politician_risk_summary"""
        print("\n🔍 Validating: view_politician_risk_summary")
        view_name = 'view_politician_risk_summary'
        
        # Check existence
        if not self.validate_view_exists(view_name):
            return False
        
        # Expected columns based on enhancement-details.md
        expected_columns = [
            'person_id', 'first_name', 'last_name', 'party',
            'total_violations', 'risk_score',
            'violation_dimension_count',
            'absenteeism_violations', 'effectiveness_violations',
            'discipline_violations', 'productivity_violations',
            'collaboration_violations'
        ]
        
        column_check = self.validate_view_columns(view_name, expected_columns)
        
        # Test query
        test_query = f"""
            SELECT person_id, first_name, last_name, party,
                   total_violations, risk_score, violation_dimension_count
            FROM {view_name}
            WHERE total_violations > 0
            LIMIT 10
        """
        query_check = self.test_view_query(view_name, test_query)
        
        result = column_check and query_check
        self.validation_results.append({
            'view': view_name,
            'passed': result,
            'framework': 'Predictive Intelligence'
        })
        return result
    
    def validate_additional_views(self):
        """Validate other views mentioned in the distribution analysis"""
        print("\n🔍 Validating: Additional distribution analysis views")
        
        additional_views = [
            'view_riksdagen_vote_data_ballot_politician_summary_annual',
            'view_committee_productivity',
            'view_ministry_effectiveness_trends',
            'view_party_performance_metrics'
        ]
        
        all_passed = True
        for view_name in additional_views:
            exists = self.validate_view_exists(view_name)
            if exists:
                # Test basic query
                test_query = f"SELECT * FROM {view_name} LIMIT 1"
                query_ok = self.test_view_query(view_name, test_query)
                all_passed = all_passed and query_ok
            else:
                all_passed = False
        
        self.validation_results.append({
            'view': 'additional_views',
            'passed': all_passed,
            'framework': 'Distribution Analysis'
        })
        return all_passed
    
    def run_all_validations(self):
        """Run all validation tests"""
        print("="*70)
        print("DATABASE VIEW VALIDATION FOR FRAMEWORK ENHANCEMENT")
        print("="*70)
        
        if not self.connect():
            print("\n❌ Cannot proceed without database connection")
            return False
        
        # Validate key views
        self.validate_ministry_risk_evolution()
        self.validate_party_momentum_analysis()
        self.validate_politician_risk_summary()
        self.validate_additional_views()
        
        # Summary
        print("\n" + "="*70)
        print("VALIDATION SUMMARY")
        print("="*70)
        
        passed_count = sum(1 for r in self.validation_results if r['passed'])
        total_count = len(self.validation_results)
        
        for result in self.validation_results:
            status = "✅ PASS" if result['passed'] else "❌ FAIL"
            print(f"{status} | {result['framework']}: {result['view']}")
        
        print(f"\nResult: {passed_count}/{total_count} validations passed")
        
        if passed_count == total_count:
            print("\n🎉 All database views validated successfully!")
            return True
        else:
            print(f"\n⚠️  {total_count - passed_count} validation(s) failed")
            return False
    
    def close(self):
        """Close database connection"""
        if self.conn:
            self.conn.close()
            print("\n✅ Database connection closed")

def main():
    """Main validation routine"""
    validator = DatabaseViewValidator()
    
    try:
        success = validator.run_all_validations()
        validator.close()
        
        # Exit with appropriate code
        sys.exit(0 if success else 1)
    except Exception as e:
        print(f"\n❌ Validation failed with error: {e}")
        validator.close()
        sys.exit(1)

if __name__ == "__main__":
    main()
