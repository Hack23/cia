#!/usr/bin/env python3
"""
Framework Validation Results Computation Script

This script automates the computation of accuracy results by comparing
framework predictions against expected outcomes in validation datasets.

Usage:
    python compute_validation_results.py

Prerequisites:
    - Validation CSV files generated from extract-framework-validation-data.sql
    - Framework analytics predictions available as 'predicted_detection' column
    - pip install pandas

Output:
    - validation-results-computed.csv with filled accuracy values
"""

import csv
import re
from pathlib import Path
from typing import Dict, Tuple

try:
    import pandas as pd
    HAS_PANDAS = True
except ImportError:
    HAS_PANDAS = False
    print("Warning: pandas not installed. Install with: pip install pandas")
    print("Falling back to CSV-only mode (requires predicted_detection column in test files)")


def compute_accuracy_pandas(csv_file: Path) -> Tuple[int, int, float]:
    """
    Compute accuracy using pandas (if available).
    
    Args:
        csv_file: Path to validation CSV with expected_detection and predicted_detection columns
        
    Returns:
        Tuple of (sample_size, correct_predictions, accuracy_percentage)
    """
    df = pd.read_csv(csv_file)
    
    if 'predicted_detection' not in df.columns:
        print(f"Warning: {csv_file.name} missing 'predicted_detection' column - skipping")
        return 0, 0, 0.0
    
    sample_size = len(df)
    correct = (df['expected_detection'] == df['predicted_detection']).sum()
    accuracy = (correct / sample_size * 100) if sample_size > 0 else 0.0
    
    return sample_size, correct, accuracy


def compute_accuracy_csv(csv_file: Path) -> Tuple[int, int, float]:
    """
    Compute accuracy using standard library CSV module.
    
    Args:
        csv_file: Path to validation CSV with expected_detection and predicted_detection columns
        
    Returns:
        Tuple of (sample_size, correct_predictions, accuracy_percentage)
    """
    with open(csv_file, 'r', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        rows = list(reader)
    
    if not rows or 'predicted_detection' not in rows[0]:
        print(f"Warning: {csv_file.name} missing 'predicted_detection' column - skipping")
        return 0, 0, 0.0
    
    sample_size = len(rows)
    correct = sum(1 for row in rows if row['expected_detection'] == row['predicted_detection'])
    accuracy = (correct / sample_size * 100) if sample_size > 0 else 0.0
    
    return sample_size, correct, accuracy


def get_test_file_mapping() -> Dict[str, str]:
    """
    Map test IDs to their CSV file paths.
    
    Returns:
        Dictionary mapping test_id to relative file path
    """
    return {
        'Test 1.1': 'temporal/test_1_1_upward_trend_attendance.csv',
        'Test 1.2': 'temporal/test_1_2_downward_trend_ministry.csv',
        'Test 1.2b': 'temporal/test_1_2b_ministry_risk_evolution.csv',
        'Test 1.3': 'temporal/test_1_3_cyclical_patterns.csv',
        'Test 1.4': 'temporal/test_1_4_anomaly_detection.csv',
        'Test 2.1': 'comparative/test_2_1_party_rankings.csv',
        'Test 2.2': 'comparative/test_2_2_peer_comparison.csv',
        'Test 2.3': 'comparative/test_2_3_party_momentum.csv',
        'Test 3.1': 'pattern/test_3_1_behavioral_clustering.csv',
        'Test 3.2': 'pattern/test_3_2_rebellion_patterns.csv',
        'Test 4.1': 'predictive/test_4_1_resignation_prediction.csv',
        'Test 4.1b': 'predictive/test_4_1b_politician_risk_profiles.csv',
        'Test 4.2': 'predictive/test_4_2_coalition_stress.csv',
        'Test 5.1': 'network/test_5_1_power_brokers.csv',
        'Test 5.2': 'network/test_5_2_coalition_facilitators.csv',
        'Test 6.1': 'decision/test_6_1_effectiveness_patterns.csv',
        'Test 6.2': 'decision/test_6_2_coalition_misalignment.csv',
    }


def process_validation_results(base_dir: Path, output_file: Path):
    """
    Process validation results template and compute accuracy metrics.
    
    Args:
        base_dir: Base directory containing framework-validation folder
        output_file: Output path for computed results CSV
    """
    template_file = base_dir / 'validation-results-template.csv'
    
    if not template_file.exists():
        print(f"Error: Template file not found: {template_file}")
        return
    
    # Read template
    with open(template_file, 'r', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        results = list(reader)
    
    if not results:
        print(f"Error: Template file is empty: {template_file}")
        return
    
    # Get test file mapping
    file_mapping = get_test_file_mapping()
    
    # Compute accuracy for each test
    compute_fn = compute_accuracy_pandas if HAS_PANDAS else compute_accuracy_csv
    
    for row in results:
        test_id = row['test_id']
        
        if test_id not in file_mapping:
            print(f"Warning: No file mapping for {test_id}")
            continue
        
        csv_path = base_dir / file_mapping[test_id]
        
        if not csv_path.exists():
            print(f"Warning: Test file not found: {csv_path}")
            continue
        
        try:
            sample_size, correct, accuracy = compute_fn(csv_path)
            
            # Update row with computed values
            if sample_size > 0:
                row['sample_size'] = str(sample_size)
                row['correct_predictions'] = str(correct)
                row['accuracy'] = f"{accuracy:.1f}%"
                
                # Determine status based on accuracy vs expected
                expected_str = row['expected_accuracy']
                try:
                    # Extract first numeric value from expected_accuracy (e.g., "87%" or "87% (6-8 months)" -> 87)
                    match = re.search(r'(\d+(?:\.\d+)?)', expected_str)
                    if match:
                        expected_val = float(match.group(1))
                        status = 'PASS' if accuracy >= expected_val else 'FAIL'
                    else:
                        status = 'COMPUTED'
                except (ValueError, AttributeError):
                    status = 'COMPUTED'
                
                row['status'] = status
                row['notes'] = f"Computed from {file_mapping[test_id]}"
                
                print(f"✓ {test_id}: {correct}/{sample_size} = {accuracy:.1f}% (expected: {row['expected_accuracy']})")
            else:
                print(f"✗ {test_id}: No data available")
                
        except Exception as e:
            print(f"Error processing {test_id}: {e}")
    
    # Write computed results
    with open(output_file, 'w', newline='', encoding='utf-8') as f:
        fieldnames = results[0].keys() if results else []
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(results)
    
    print(f"\n✓ Results written to: {output_file}")


def main():
    """Main entry point for validation computation."""
    # Determine base directory
    script_dir = Path(__file__).parent
    base_dir = script_dir
    
    # If script is in framework-validation/, that's our base
    if base_dir.name != 'framework-validation':
        # Try to find framework-validation directory
        potential_paths = [
            Path('service.data.impl/sample-data/framework-validation'),
            script_dir / 'service.data.impl/sample-data/framework-validation',
            script_dir.parent / 'service.data.impl/sample-data/framework-validation',
        ]
        
        for path in potential_paths:
            if path.exists():
                base_dir = path
                break
        else:
            print("Error: Could not locate framework-validation directory")
            print("Please run this script from the project root or framework-validation directory")
            return
    
    output_file = base_dir / 'validation-results-computed.csv'
    
    print("Framework Validation Results Computation")
    print("=" * 60)
    print(f"Base directory: {base_dir}")
    print(f"Output file: {output_file}")
    print("=" * 60)
    print()
    
    process_validation_results(base_dir, output_file)


if __name__ == '__main__':
    main()
