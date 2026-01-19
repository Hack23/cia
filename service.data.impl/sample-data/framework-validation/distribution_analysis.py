#!/usr/bin/env python3
"""
Distribution Analysis Script for Framework Validation Enhancement

Purpose: Analyze statistical distributions of key behavioral metrics to identify
         gaps and inform Drools risk rule threshold calibration

Analysis Scope:
- Absence rates (distribution_annual_ballots.csv)
- Document productivity (multiple distribution files)
- Coalition alignment patterns
- Ministry approval rates
- Committee effectiveness metrics

Output: Statistical metrics (mean, median, std dev, P1, P10, P25, P75, P90, P99)
"""

import pandas as pd
import numpy as np
from pathlib import Path
import json

class DistributionAnalyzer:
    def __init__(self, base_path):
        self.base_path = Path(base_path)
        self.results = {}
        
    def calculate_percentiles(self, series, metric_name):
        """Calculate comprehensive percentile distribution"""
        if series.empty or series.isna().all():
            return {
                'metric': metric_name,
                'count': 0,
                'mean': None,
                'median': None,
                'std_dev': None,
                'P1': None,
                'P10': None,
                'P25': None,
                'P75': None,
                'P90': None,
                'P99': None
            }
        
        return {
            'metric': metric_name,
            'count': len(series),
            'mean': float(series.mean()),
            'median': float(series.median()),
            'std_dev': float(series.std()),
            'min': float(series.min()),
            'max': float(series.max()),
            'P1': float(series.quantile(0.01)),
            'P10': float(series.quantile(0.10)),
            'P25': float(series.quantile(0.25)),
            'P75': float(series.quantile(0.75)),
            'P90': float(series.quantile(0.90)),
            'P95': float(series.quantile(0.95)),
            'P99': float(series.quantile(0.99))
        }
    
    def analyze_absence_rates(self):
        """Analyze absence rate distribution from ballot data"""
        file_path = self.base_path.parent / 'distribution_annual_ballots.csv'
        if not file_path.exists():
            print(f"⚠️  File not found: {file_path}")
            return
        
        df = pd.read_csv(file_path)
        print(f"✅ Loaded {file_path.name}: {len(df)} rows")
        
        # Note: This file has aggregate annual ballot statistics
        # We'll need to compute absence rates from other sources
        self.results['absence_rates'] = {
            'note': 'Absence rates need to be calculated from politician-level ballot data',
            'annual_ballot_years': sorted(df['year'].unique().tolist()) if 'year' in df.columns else []
        }
    
    def analyze_document_productivity(self):
        """Analyze document productivity distributions"""
        distributions = {}
        
        # Committee productivity
        file_path = self.base_path.parent / 'distribution_committee_productivity.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            if 'docs_per_member' in df.columns:
                # Filter out inactive committees
                active = df[df['docs_per_member'] > 0]
                distributions['committee_docs_per_member'] = self.calculate_percentiles(
                    active['docs_per_member'], 
                    'Committee Documents per Member'
                )
                distributions['committee_docs_per_member']['inactive_committees'] = len(df) - len(active)
        
        # Ministry productivity
        file_path = self.base_path.parent / 'distribution_ministry_productivity_matrix.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            if 'avg_approval_rate' in df.columns:
                active = df[df['avg_approval_rate'].notna()]
                distributions['ministry_approval_rate'] = self.calculate_percentiles(
                    active['avg_approval_rate'], 
                    'Ministry Approval Rate (%)'
                )
        
        # Committee productivity matrix (for more detailed metrics)
        file_path = self.base_path.parent / 'distribution_committee_productivity_matrix.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            if 'total_documents' in df.columns:
                active = df[df['total_documents'] > 0]
                distributions['committee_total_documents'] = self.calculate_percentiles(
                    active['total_documents'], 
                    'Committee Total Documents'
                )
        
        self.results['document_productivity'] = distributions
    
    def analyze_ministry_effectiveness(self):
        """Analyze ministry effectiveness metrics"""
        distributions = {}
        
        # Ministry effectiveness trends
        file_path = self.base_path.parent / 'distribution_ministry_effectiveness.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            # Typically this would have approval rates and decision counts
            for col in ['approval_rate', 'decision_count', 'avg_decisions']:
                if col in df.columns:
                    active = df[df[col].notna() & (df[col] > 0)]
                    if not active.empty:
                        distributions[col] = self.calculate_percentiles(
                            active[col], 
                            f'Ministry {col.replace("_", " ").title()}'
                        )
        
        self.results['ministry_effectiveness'] = distributions
    
    def analyze_coalition_alignment(self):
        """Analyze coalition alignment patterns"""
        file_path = self.base_path.parent / 'distribution_coalition_alignment.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            distributions = {}
            for col in df.columns:
                if col not in ['party', 'coalition', 'bloc']:
                    if df[col].dtype in ['int64', 'float64']:
                        distributions[col] = self.calculate_percentiles(
                            df[col].dropna(), 
                            f'Coalition {col.replace("_", " ").title()}'
                        )
            
            self.results['coalition_alignment'] = distributions
        else:
            print(f"⚠️  File not found: {file_path}")
    
    def analyze_party_effectiveness(self):
        """Analyze party effectiveness trends"""
        file_path = self.base_path.parent / 'distribution_party_effectiveness_trends.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            distributions = {}
            for col in ['win_rate', 'discipline_rate', 'productivity_score', 'effectiveness_score']:
                if col in df.columns:
                    distributions[col] = self.calculate_percentiles(
                        df[col].dropna(), 
                        f'Party {col.replace("_", " ").title()}'
                    )
            
            self.results['party_effectiveness'] = distributions
        else:
            print(f"⚠️  File not found: {file_path}")
    
    def analyze_politician_risk(self):
        """Analyze politician risk distributions"""
        file_path = self.base_path.parent / 'distribution_politician_risk_levels.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            distributions = {}
            
            # Risk level counts
            if 'risk_level' in df.columns:
                risk_counts = df['risk_level'].value_counts().to_dict()
                distributions['risk_level_distribution'] = risk_counts
            
            # Risk score distribution
            if 'risk_score' in df.columns:
                distributions['risk_score'] = self.calculate_percentiles(
                    df['risk_score'].dropna(), 
                    'Politician Risk Score'
                )
            
            self.results['politician_risk'] = distributions
        else:
            print(f"⚠️  File not found: {file_path}")
    
    def analyze_behavioral_patterns(self):
        """Analyze behavioral pattern distributions"""
        file_path = self.base_path.parent / 'distribution_behavioral_patterns_by_party.csv'
        if file_path.exists():
            df = pd.read_csv(file_path)
            print(f"✅ Loaded {file_path.name}: {len(df)} rows")
            
            distributions = {}
            for col in df.columns:
                if col not in ['party', 'pattern_type'] and df[col].dtype in ['int64', 'float64']:
                    distributions[col] = self.calculate_percentiles(
                        df[col].dropna(), 
                        f'Behavioral {col.replace("_", " ").title()}'
                    )
            
            self.results['behavioral_patterns'] = distributions
        else:
            print(f"⚠️  File not found: {file_path}")
    
    def identify_distribution_gaps(self):
        """Identify areas where P1-P10 and P90-P99 are underrepresented"""
        gaps = []
        
        for category, metrics in self.results.items():
            if isinstance(metrics, dict):
                for metric_name, stats in metrics.items():
                    if isinstance(stats, dict) and 'P10' in stats and 'P90' in stats:
                        # Check if we have sufficient coverage at extremes
                        if stats['count'] < 50:
                            gaps.append({
                                'category': category,
                                'metric': metric_name,
                                'issue': 'Low sample size',
                                'count': stats['count'],
                                'recommendation': 'Add more test cases'
                            })
                        
                        # Check for distribution skew
                        # Use robust skew detection that handles near-zero medians
                        MEDIAN_THRESHOLD = 0.01  # Named constant for near-zero median detection
                        if stats['P10'] is not None and stats['P90'] is not None:
                            if abs(stats['median']) < MEDIAN_THRESHOLD:
                                # For near-zero medians, use absolute differences instead of ratios
                                p10_to_median_ratio = abs(stats['P10'] - stats['median'])
                                p90_to_median_ratio = abs(stats['P90'] - stats['median'])
                            else:
                                # Standard ratio calculation for non-zero medians
                                p10_to_median_ratio = abs(stats['P10'] - stats['median']) / abs(stats['median'])
                                p90_to_median_ratio = abs(stats['P90'] - stats['median']) / abs(stats['median'])
                            
                            if p10_to_median_ratio < 0.1 or p90_to_median_ratio < 0.1:
                                gaps.append({
                                    'category': category,
                                    'metric': metric_name,
                                    'issue': 'Insufficient extreme values',
                                    'P10': stats['P10'],
                                    'P90': stats['P90'],
                                    'median': stats['median'],
                                    'recommendation': 'Add edge cases at P1-P10 and P90-P99'
                                })
        
        self.results['distribution_gaps'] = gaps
        return gaps
    
    def generate_report(self):
        """Generate comprehensive distribution analysis report"""
        print("\n" + "="*80)
        print("DISTRIBUTION ANALYSIS REPORT")
        print("="*80 + "\n")
        
        for category, data in self.results.items():
            if category == 'distribution_gaps':
                continue
                
            print(f"\n{'='*80}")
            print(f"Category: {category.replace('_', ' ').title()}")
            print(f"{'='*80}")
            
            if isinstance(data, dict):
                for metric_name, stats in data.items():
                    if isinstance(stats, dict) and 'mean' in stats:
                        print(f"\n  Metric: {stats['metric']}")
                        print(f"  Count: {stats['count']}")
                        if stats['mean'] is not None:
                            print(f"  Mean: {stats['mean']:.2f}")
                            print(f"  Median: {stats['median']:.2f}")
                            print(f"  Std Dev: {stats['std_dev']:.2f}")
                            print(f"  Min: {stats['min']:.2f}")
                            print(f"  Max: {stats['max']:.2f}")
                            print(f"  P1:  {stats['P1']:.2f}")
                            print(f"  P10: {stats['P10']:.2f}")
                            print(f"  P25: {stats['P25']:.2f}")
                            print(f"  P75: {stats['P75']:.2f}")
                            print(f"  P90: {stats['P90']:.2f}")
                            print(f"  P95: {stats['P95']:.2f}")
                            print(f"  P99: {stats['P99']:.2f}")
                    elif isinstance(stats, dict):
                        print(f"\n  {metric_name}: {stats}")
        
        # Distribution gaps
        if 'distribution_gaps' in self.results:
            print(f"\n{'='*80}")
            print("DISTRIBUTION GAPS IDENTIFIED")
            print(f"{'='*80}")
            
            gaps = self.results['distribution_gaps']
            if gaps:
                for i, gap in enumerate(gaps, 1):
                    print(f"\n  Gap {i}:")
                    print(f"    Category: {gap['category']}")
                    print(f"    Metric: {gap['metric']}")
                    print(f"    Issue: {gap['issue']}")
                    print(f"    Recommendation: {gap['recommendation']}")
                    if 'count' in gap:
                        print(f"    Sample Size: {gap['count']}")
            else:
                print("\n  ✅ No significant distribution gaps identified")
        
        print("\n" + "="*80)
        print("END OF REPORT")
        print("="*80 + "\n")
    
    def save_results(self, output_file='distribution_analysis_results.json'):
        """Save analysis results to JSON"""
        output_path = self.base_path / output_file
        with open(output_path, 'w') as f:
            json.dump(self.results, f, indent=2)
        print(f"✅ Results saved to: {output_path}")
    
    def run_full_analysis(self):
        """Execute complete distribution analysis"""
        print("Starting Distribution Analysis...")
        print("="*80 + "\n")
        
        self.analyze_absence_rates()
        self.analyze_document_productivity()
        self.analyze_ministry_effectiveness()
        self.analyze_coalition_alignment()
        self.analyze_party_effectiveness()
        self.analyze_politician_risk()
        self.analyze_behavioral_patterns()
        
        print("\n" + "="*80)
        print("Identifying Distribution Gaps...")
        gaps = self.identify_distribution_gaps()
        print(f"Found {len(gaps)} potential gaps")
        
        self.generate_report()
        self.save_results()


if __name__ == '__main__':
    base_path = Path(__file__).parent
    analyzer = DistributionAnalyzer(base_path)
    analyzer.run_full_analysis()
