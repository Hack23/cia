#!/usr/bin/env python3
"""
Edge Case Generator for Framework Validation Enhancement

Purpose: Generate edge cases and extreme value test data to improve
         Drools risk rule calibration accuracy

Focus Areas:
1. P1-P10 and P90-P99 percentile coverage
2. Historical extremes (2014 crisis, 2018 deadlock)
3. Election cycle variations (2002 vs 2022)
4. Seasonal variations (Q4 pre-election surges)

Output: Enhanced CSV test data files for each framework
"""

import pandas as pd
from pathlib import Path

class EdgeCaseGenerator:
    def __init__(self, base_path):
        self.base_path = Path(base_path)
        self.edge_cases = {
            'temporal': [],
            'comparative': [],
            'predictive': [],
            'pattern': [],
            'network': [],
            'decision': []
        }
        
    def generate_extreme_absence_rates(self):
        """Generate P1 and P99 absence rate edge cases"""
        # Based on Swedish Riksdag statistics:
        # P1 ≈ 0-2%, P10 ≈ 5-8%, P90 ≈ 18-22%, P99 ≈ 50-95%
        
        edge_cases = []
        
        # P1 cases - Nearly perfect attendance (0-2%)
        edge_cases.append({
            'person_id': '9999000001',
            'first_name': 'Perfect',
            'last_name': 'Attendance',
            'party': 'S',
            'measurement_month': '2024-12-01',
            'baseline_absence_rate': 1.0,
            'current_absence_rate': 0.5,
            'change_magnitude': -0.5,
            'expected_detection': 'EXCEPTIONAL_ATTENDANCE',
            'test_case': 'edge_case_p1_attendance',
            'validation_label': 'PASS',
            'percentile': 'P1',
            'notes': 'Exceptional attendance - P1 percentile'
        })
        
        # P99 cases - Extreme absence (50-95%)
        edge_cases.append({
            'person_id': '9999000099',
            'first_name': 'Extreme',
            'last_name': 'Absence',
            'party': 'M',
            'measurement_month': '2024-12-01',
            'baseline_absence_rate': 45.0,
            'current_absence_rate': 85.0,
            'change_magnitude': 40.0,
            'expected_detection': 'CRITICAL_ABSENCE_CRISIS',
            'test_case': 'edge_case_p99_absence',
            'validation_label': 'PASS',
            'percentile': 'P99',
            'notes': 'Extreme absence rate - P99 percentile, potential resignation'
        })
        
        # Historical extreme: 2014 government crisis
        edge_cases.append({
            'person_id': '9999002014',
            'first_name': 'Crisis',
            'last_name': 'Period2014',
            'party': 'MP',
            'measurement_month': '2014-12-01',
            'baseline_absence_rate': 12.0,
            'current_absence_rate': 65.0,
            'change_magnitude': 53.0,
            'expected_detection': 'HISTORICAL_CRISIS_PATTERN',
            'test_case': 'historical_2014_crisis',
            'validation_label': 'PASS',
            'percentile': 'P99+',
            'notes': '2014 government crisis - extreme absence spike during political turmoil'
        })
        
        # Election cycle variation: Pre-election surge (2022)
        edge_cases.append({
            'person_id': '9999002022',
            'first_name': 'Election',
            'last_name': 'Cycle2022',
            'party': 'SD',
            'measurement_month': '2022-08-01',
            'baseline_absence_rate': 15.0,
            'current_absence_rate': 3.0,
            'change_magnitude': -12.0,
            'expected_detection': 'PRE_ELECTION_ENGAGEMENT',
            'test_case': 'election_cycle_2022',
            'validation_label': 'PASS',
            'percentile': 'P5',
            'notes': '2022 election - increased attendance before election'
        })
        
        return edge_cases
    
    def generate_extreme_productivity_cases(self):
        """Generate P1 and P99 document productivity edge cases"""
        # P1 ≈ 0 docs/year, P99 ≈ 80-100+ docs/year
        
        edge_cases = []
        
        # P99 high productivity
        edge_cases.append({
            'person_id': '9999100099',
            'first_name': 'Super',
            'last_name': 'Productive',
            'party': 'V',
            'avg_absence_rate': 2.0,
            'absence_trend': -1.5,
            'effectiveness_trend': 5.0,
            'smoothed_3month_absence': 1.8,
            'months_tracked': 12,
            'documents_last_year': 95,
            'expected_detection': 'EXCEPTIONAL_PRODUCTIVITY',
            'test_case': 'edge_case_p99_productivity',
            'validation_label': 'BASELINE',
            'percentile': 'P99',
            'notes': 'Exceptional productivity - P99 percentile, 95 docs/year'
        })
        
        # P1 low productivity (but still active)
        edge_cases.append({
            'person_id': '9999100001',
            'first_name': 'Minimal',
            'last_name': 'Output',
            'party': 'C',
            'avg_absence_rate': 18.0,
            'absence_trend': 2.0,
            'effectiveness_trend': -3.0,
            'smoothed_3month_absence': 20.0,
            'months_tracked': 12,
            'documents_last_year': 1,
            'expected_detection': 'CRITICAL_LOW_PRODUCTIVITY',
            'test_case': 'edge_case_p1_productivity',
            'validation_label': 'PASS',
            'percentile': 'P1',
            'notes': 'Critical low productivity - P1 percentile, only 1 doc/year'
        })
        
        return edge_cases
    
    def generate_coalition_stress_extremes(self):
        """Generate extreme coalition alignment cases"""
        
        edge_cases = []
        
        # Historical: 2018 election deadlock
        edge_cases.append({
            'party_1': 'S',
            'party_2': 'M',
            'coalition_type': 'ATTEMPTED_GRAND_COALITION',
            'baseline_alignment': 25.0,
            'current_alignment': 15.0,
            'alignment_trend': -10.0,
            'stress_level': 'CRITICAL',
            'year': 2018,
            'quarter': 4,
            'expected_detection': 'DEADLOCK_PATTERN_2018',
            'test_case': 'historical_2018_deadlock',
            'validation_label': 'PASS',
            'notes': '2018 election deadlock - 134 days to form government'
        })
        
        # Extreme coalition stress (P99)
        edge_cases.append({
            'party_1': 'MP',
            'party_2': 'C',
            'coalition_type': 'UNSTABLE_COALITION',
            'baseline_alignment': 80.0,
            'current_alignment': 25.0,
            'alignment_trend': -55.0,
            'stress_level': 'CRITICAL',
            'year': 2024,
            'quarter': 3,
            'expected_detection': 'HIGH_COALITION_STRESS',
            'test_case': 'edge_case_p99_coalition_stress',
            'validation_label': 'PASS',
            'percentile': 'P99',
            'notes': 'Extreme coalition stress - 55 point alignment drop'
        })
        
        # Exceptional coalition stability (P1)
        edge_cases.append({
            'party_1': 'S',
            'party_2': 'V',
            'coalition_type': 'STABLE_LEFT_BLOC',
            'baseline_alignment': 92.0,
            'current_alignment': 94.0,
            'alignment_trend': 2.0,
            'stress_level': 'LOW',
            'year': 2024,
            'quarter': 4,
            'expected_detection': 'STABLE_COALITION',
            'test_case': 'edge_case_p1_coalition_stable',
            'validation_label': 'BASELINE',
            'percentile': 'P1',
            'notes': 'Exceptional coalition stability - 94% alignment'
        })
        
        return edge_cases
    
    def generate_ministry_decline_extremes(self):
        """Generate extreme ministry approval decline cases"""
        
        edge_cases = []
        
        # P99 catastrophic ministry decline
        edge_cases.append({
            'org_code': 'KB',
            'ministry_name': 'Kulturdepartementet',
            'year': 2024,
            'quarter': 4,
            'baseline_approval_rate': 75.0,
            'current_approval_rate': 25.0,
            'decline_magnitude': -50.0,
            'quarters_declining': 4,
            'expected_detection': 'CATASTROPHIC_MINISTRY_DECLINE',
            'test_case': 'edge_case_p99_ministry_decline',
            'validation_label': 'PASS',
            'percentile': 'P99',
            'notes': 'Catastrophic 50-point approval decline over 4 quarters'
        })
        
        # Rapid ministry recovery (reverse pattern)
        edge_cases.append({
            'org_code': 'FI',
            'ministry_name': 'Finansdepartementet',
            'year': 2024,
            'quarter': 4,
            'baseline_approval_rate': 40.0,
            'current_approval_rate': 85.0,
            'decline_magnitude': 45.0,
            'quarters_declining': -4,  # Negative = improvement
            'expected_detection': 'EXCEPTIONAL_MINISTRY_RECOVERY',
            'test_case': 'edge_case_ministry_recovery',
            'validation_label': 'BASELINE',
            'percentile': 'P1',
            'notes': 'Exceptional ministry recovery - 45-point approval increase'
        })
        
        return edge_cases
    
    def generate_seasonal_variations(self):
        """Generate seasonal pattern edge cases"""
        
        edge_cases = []
        
        # Q4 pre-election surge (high activity)
        edge_cases.append({
            'measurement_period': '2022-Q4',
            'season': 'PRE_ELECTION_AUTUMN',
            'ballot_count': 450,
            'document_count': 180,
            'committee_meetings': 95,
            'seasonal_factor': 1.85,  # 85% above baseline
            'expected_detection': 'EXTREME_PRE_ELECTION_SURGE',
            'test_case': 'seasonal_q4_election_surge',
            'validation_label': 'PASS',
            'notes': 'Q4 2022 - extreme activity surge before election'
        })
        
        # Summer recess (low activity)
        edge_cases.append({
            'measurement_period': '2024-Q3',
            'season': 'SUMMER_RECESS',
            'ballot_count': 15,
            'document_count': 8,
            'committee_meetings': 3,
            'seasonal_factor': 0.12,  # 88% below baseline
            'expected_detection': 'TYPICAL_SUMMER_RECESS',
            'test_case': 'seasonal_q3_summer_low',
            'validation_label': 'BASELINE',
            'notes': 'Q3 2024 - typical summer parliamentary recess'
        })
        
        return edge_cases
    
    def generate_multi_dimensional_risk_extremes(self):
        """Generate extreme multi-dimensional risk profiles"""
        
        edge_cases = []
        
        # P99 extreme multi-dimensional failure
        edge_cases.append({
            'person_id': '9999300099',
            'first_name': 'Critical',
            'last_name': 'MultiRisk',
            'party': 'L',
            'total_violations': 12,
            'violation_dimension_count': 5,
            'absenteeism_violations': 3,
            'effectiveness_violations': 2,
            'discipline_violations': 3,
            'productivity_violations': 2,
            'collaboration_violations': 2,
            'annual_absence_rate': 75.0,
            'annual_rebel_rate': 2.5,
            'annual_vote_count': 50,
            'documents_last_year': 0,
            'risk_score': 95,
            'risk_level': 'CRITICAL',
            'expected_detection': 'EXTREME_MULTI_DIMENSION_RISK',
            'test_case': 'edge_case_p99_multi_risk',
            'validation_label': 'PASS',
            'percentile': 'P99',
            'notes': 'Extreme multi-dimensional failure - 12 violations across all 5 dimensions'
        })
        
        # Perfect performance (P1)
        edge_cases.append({
            'person_id': '9999300001',
            'first_name': 'Exemplary',
            'last_name': 'Performance',
            'party': 'S',
            'total_violations': 0,
            'violation_dimension_count': 0,
            'absenteeism_violations': 0,
            'effectiveness_violations': 0,
            'discipline_violations': 0,
            'productivity_violations': 0,
            'collaboration_violations': 0,
            'annual_absence_rate': 1.5,
            'annual_rebel_rate': 0.0,
            'annual_vote_count': 485,
            'documents_last_year': 85,
            'risk_score': 5,
            'risk_level': 'LOW',
            'expected_detection': 'EXEMPLARY_PERFORMANCE',
            'test_case': 'edge_case_p1_perfect',
            'validation_label': 'BASELINE',
            'percentile': 'P1',
            'notes': 'Exemplary performance - zero violations, top productivity'
        })
        
        return edge_cases
    
    def save_edge_cases_to_csv(self, framework, test_name, edge_cases, append=True):
        """Append edge cases to existing test CSV file"""
        file_path = self.base_path / framework / test_name
        
        if not file_path.exists():
            print(f"⚠️  File not found: {file_path}")
            return 0  # Return 0 cases added when file doesn't exist
        
        # Read existing file to get columns
        existing_df = pd.read_csv(file_path)
        existing_columns = existing_df.columns.tolist()
        
        # Filter edge case fields to match existing columns
        filtered_cases = []
        for case in edge_cases:
            filtered_case = {k: v for k, v in case.items() if k in existing_columns}
            # Add any missing columns with None
            for col in existing_columns:
                if col not in filtered_case:
                    filtered_case[col] = None
            filtered_cases.append(filtered_case)
        
        if append and file_path.exists():
            # Append to existing file
            edge_df = pd.DataFrame(filtered_cases)
            edge_df.to_csv(file_path, mode='a', header=False, index=False)
            print(f"✅ Appended {len(filtered_cases)} edge cases to {file_path.name}")
        else:
            # Create new file
            edge_df = pd.DataFrame(filtered_cases)
            edge_df.to_csv(file_path, index=False)
            print(f"✅ Created {file_path.name} with {len(filtered_cases)} edge cases")
        
        return len(filtered_cases)
    
    def generate_all_edge_cases(self):
        """Generate all edge cases across frameworks"""
        print("Generating Edge Cases for Framework Validation...")
        print("="*80 + "\n")
        
        total_added = 0
        
        # Temporal framework edge cases
        print("📊 Temporal Analysis Edge Cases")
        absence_cases = self.generate_extreme_absence_rates()
        count = self.save_edge_cases_to_csv('temporal', 'test_1_1_upward_trend_attendance.csv', absence_cases)
        total_added += count
        
        ministry_cases = self.generate_ministry_decline_extremes()
        count = self.save_edge_cases_to_csv('temporal', 'test_1_2_downward_trend_ministry.csv', ministry_cases)
        total_added += count
        
        seasonal_cases = self.generate_seasonal_variations()
        count = self.save_edge_cases_to_csv('temporal', 'test_1_3_cyclical_patterns.csv', seasonal_cases)
        total_added += count
        
        # Predictive framework edge cases
        print("\n🔮 Predictive Intelligence Edge Cases")
        productivity_cases = self.generate_extreme_productivity_cases()
        count = self.save_edge_cases_to_csv('predictive', 'test_4_1_resignation_prediction.csv', productivity_cases)
        total_added += count
        
        coalition_cases = self.generate_coalition_stress_extremes()
        count = self.save_edge_cases_to_csv('predictive', 'test_4_2_coalition_stress.csv', coalition_cases)
        total_added += count
        
        multi_risk_cases = self.generate_multi_dimensional_risk_extremes()
        count = self.save_edge_cases_to_csv('predictive', 'test_4_1b_politician_risk_profiles.csv', multi_risk_cases)
        total_added += count
        
        print("\n" + "="*80)
        print(f"✅ Total Edge Cases Added: {total_added}")
        print("="*80)
        
        return total_added


if __name__ == '__main__':
    base_path = Path(__file__).parent
    generator = EdgeCaseGenerator(base_path)
    total = generator.generate_all_edge_cases()
    print(f"\n🎯 Edge case generation complete: {total} cases added")
