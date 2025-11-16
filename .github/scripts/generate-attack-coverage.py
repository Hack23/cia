#!/usr/bin/env python3
"""
MITRE ATT&CK Coverage Analysis Script for Citizen Intelligence Agency

This script extracts MITRE ATT&CK techniques from THREAT_MODEL.md and generates:
1. Coverage analysis and metrics
2. ATT&CK Navigator layer JSON for visualization
3. Security control to mitigation mapping
4. CISA KEV (Known Exploited Vulnerabilities) integration

Aligned with Hack23 AB ISMS Threat Modeling Policy:
https://github.com/Hack23/ISMS/blob/main/Threat_Modeling.md

Author: Hack23 AB Security Team
License: Apache 2.0
"""

import re
import json
import sys
from pathlib import Path
from typing import Dict, List, Set, Tuple
from datetime import datetime, timezone

try:
    import requests
except ImportError:
    print("⚠️  requests library not found. Installing...")
    import subprocess
    subprocess.check_call([sys.executable, "-m", "pip", "install", "requests"])
    import requests


class AttackCoverageAnalyzer:
    """Analyzes MITRE ATT&CK technique coverage in threat model documentation."""
    
    ATTACK_FRAMEWORK_URL = "https://raw.githubusercontent.com/mitre/cti/master/enterprise-attack/enterprise-attack.json"
    CISA_KEV_URL = "https://www.cisa.gov/sites/default/files/feeds/known_exploited_vulnerabilities.json"
    
    # Map tactic machine names to display names
    TACTIC_DISPLAY_NAMES = {
        'initial-access': 'Initial Access',
        'execution': 'Execution',
        'persistence': 'Persistence',
        'privilege-escalation': 'Privilege Escalation',
        'defense-evasion': 'Defense Evasion',
        'credential-access': 'Credential Access',
        'discovery': 'Discovery',
        'lateral-movement': 'Lateral Movement',
        'collection': 'Collection',
        'command-and-control': 'Command and Control',
        'exfiltration': 'Exfiltration',
        'impact': 'Impact'
    }
    
    def __init__(self, threat_model_path: str):
        """Initialize analyzer with threat model file path."""
        self.threat_model_path = Path(threat_model_path)
        self.techniques = set()
        self.framework = None
        self.all_techniques = {}
        
    def extract_attack_techniques(self) -> Set[str]:
        """Parse THREAT_MODEL.md for ATT&CK technique IDs."""
        print("🔍 Extracting ATT&CK techniques from THREAT_MODEL.md...")
        
        if not self.threat_model_path.exists():
            raise FileNotFoundError(f"Threat model not found: {self.threat_model_path}")
        
        with open(self.threat_model_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Extract all T#### and T####.### technique IDs
        technique_pattern = r'T\d{4}(?:\.\d{3})?'
        techniques = re.findall(technique_pattern, content)
        
        # Remove duplicates and sort
        self.techniques = set(techniques)
        print(f"✅ Found {len(self.techniques)} unique ATT&CK techniques")
        
        return self.techniques
    
    def fetch_attack_framework(self) -> Dict:
        """Download latest MITRE ATT&CK framework from GitHub."""
        print("📥 Fetching latest MITRE ATT&CK Enterprise framework...")
        
        try:
            response = requests.get(self.ATTACK_FRAMEWORK_URL, timeout=30)
            response.raise_for_status()
            self.framework = response.json()
            
            # Build technique lookup dictionary
            for obj in self.framework['objects']:
                if obj['type'] == 'attack-pattern' and not obj.get('revoked', False):
                    external_refs = obj.get('external_references', [])
                    if external_refs:
                        tech_id = external_refs[0].get('external_id', '')
                        if tech_id.startswith('T'):
                            self.all_techniques[tech_id] = obj
            
            print(f"✅ Loaded {len(self.all_techniques)} ATT&CK techniques from framework")
            return self.framework
            
        except requests.RequestException as e:
            print(f"❌ Failed to fetch ATT&CK framework: {e}")
            raise
    
    def generate_coverage_matrix(self) -> Dict:
        """Generate technique coverage analysis by tactic."""
        print("📊 Generating coverage matrix...")
        
        if not self.framework:
            raise ValueError("ATT&CK framework not loaded. Call fetch_attack_framework() first.")
        
        # Count techniques by tactic
        tactics_coverage = {}
        tactics_total = {}
        covered_by_tactic = {}
        
        # Initialize tactics
        for tactic_key in self.TACTIC_DISPLAY_NAMES.keys():
            tactics_total[tactic_key] = 0
            tactics_coverage[tactic_key] = 0
            covered_by_tactic[tactic_key] = []
        
        # Count all techniques by tactic
        for tech_id, tech_obj in self.all_techniques.items():
            kill_chains = tech_obj.get('kill_chain_phases', [])
            for phase in kill_chains:
                if phase['kill_chain_name'] == 'mitre-attack':
                    tactic = phase['phase_name']
                    if tactic in tactics_total:
                        tactics_total[tactic] += 1
        
        # Count covered techniques by tactic
        for tech_id in self.techniques:
            if tech_id in self.all_techniques:
                tech_obj = self.all_techniques[tech_id]
                kill_chains = tech_obj.get('kill_chain_phases', [])
                for phase in kill_chains:
                    if phase['kill_chain_name'] == 'mitre-attack':
                        tactic = phase['phase_name']
                        if tactic in tactics_coverage:
                            tactics_coverage[tactic] += 1
                            covered_by_tactic[tactic].append(tech_id)
        
        total_techniques = len(self.all_techniques)
        covered_techniques = len(self.techniques)
        coverage_percentage = (covered_techniques / total_techniques * 100) if total_techniques > 0 else 0
        
        coverage = {
            'total_techniques': total_techniques,
            'covered_techniques': covered_techniques,
            'coverage_percentage': round(coverage_percentage, 2),
            'timestamp': datetime.now(timezone.utc).isoformat(),
            'tactics': {}
        }
        
        for tactic_key, display_name in self.TACTIC_DISPLAY_NAMES.items():
            total = tactics_total.get(tactic_key, 0)
            covered = tactics_coverage.get(tactic_key, 0)
            tactic_percentage = (covered / total * 100) if total > 0 else 0
            
            coverage['tactics'][tactic_key] = {
                'name': display_name,
                'total': total,
                'covered': covered,
                'percentage': round(tactic_percentage, 2),
                'techniques': sorted(covered_by_tactic.get(tactic_key, []))
            }
        
        print(f"✅ Coverage: {coverage['coverage_percentage']:.1f}% ({covered_techniques}/{total_techniques} techniques)")
        
        return coverage
    
    def generate_navigator_layer(self) -> Dict:
        """Generate ATT&CK Navigator layer JSON for visualization."""
        print("🗺️  Generating ATT&CK Navigator layer...")
        
        layer = {
            "name": "CIA Platform Threat Coverage",
            "versions": {
                "attack": "15",
                "navigator": "5.0.0",
                "layer": "4.5"
            },
            "domain": "enterprise-attack",
            "description": "MITRE ATT&CK techniques covered by Citizen Intelligence Agency threat model. Aligned with Hack23 AB ISMS Threat Modeling Policy.",
            "filters": {
                "platforms": ["Windows", "Linux", "macOS", "AWS", "Azure", "GCP", "SaaS"]
            },
            "sorting": 0,
            "layout": {
                "layout": "side",
                "aggregateFunction": "average",
                "showID": True,
                "showName": True,
                "showAggregateScores": False,
                "countUnscored": False
            },
            "hideDisabled": False,
            "techniques": [],
            "gradient": {
                "colors": [
                    "#ff6666",
                    "#ffe766",
                    "#8ec843"
                ],
                "minValue": 0,
                "maxValue": 100
            },
            "legendItems": [
                {
                    "label": "Covered in Threat Model",
                    "color": "#00ff00"
                }
            ],
            "metadata": [
                {
                    "name": "Source",
                    "value": "Citizen Intelligence Agency THREAT_MODEL.md"
                },
                {
                    "name": "Generated",
                    "value": datetime.now(timezone.utc).strftime("%Y-%m-%d %H:%M:%S UTC")
                },
                {
                    "name": "Policy",
                    "value": "Hack23 AB ISMS Threat Modeling"
                }
            ],
            "showTacticRowBackground": True,
            "tacticRowBackground": "#dddddd",
            "selectTechniquesAcrossTactics": True,
            "selectSubtechniquesWithParent": False
        }
        
        # Add covered techniques to layer
        for tech_id in sorted(self.techniques):
            if tech_id in self.all_techniques:
                tech_obj = self.all_techniques[tech_id]
                layer['techniques'].append({
                    "techniqueID": tech_id,
                    "tactic": "",  # Navigator will populate from technique data
                    "color": "#00ff00",
                    "comment": f"Covered in THREAT_MODEL.md - {tech_obj.get('name', 'Unknown')}",
                    "enabled": True,
                    "score": 1,
                    "showSubtechniques": False
                })
        
        print(f"✅ Generated Navigator layer with {len(layer['techniques'])} techniques")
        
        return layer
    
    def check_cisa_kev(self) -> Dict:
        """Check CISA Known Exploited Vulnerabilities catalog."""
        print("🔍 Checking CISA Known Exploited Vulnerabilities (KEV)...")
        
        try:
            response = requests.get(self.CISA_KEV_URL, timeout=30)
            response.raise_for_status()
            kev_data = response.json()
            
            vulnerabilities = kev_data.get('vulnerabilities', [])
            
            kev_summary = {
                'total_kev': len(vulnerabilities),
                'catalog_version': kev_data.get('catalogVersion', 'unknown'),
                'date_released': kev_data.get('dateReleased', 'unknown'),
                'recent_vulnerabilities': []
            }
            
            # Get 10 most recent KEV entries
            sorted_vulns = sorted(
                vulnerabilities,
                key=lambda x: x.get('dateAdded', ''),
                reverse=True
            )[:10]
            
            for vuln in sorted_vulns:
                kev_summary['recent_vulnerabilities'].append({
                    'cve_id': vuln.get('cveID', 'N/A'),
                    'vendor': vuln.get('vendorProject', 'N/A'),
                    'product': vuln.get('product', 'N/A'),
                    'vulnerability_name': vuln.get('vulnerabilityName', 'N/A'),
                    'date_added': vuln.get('dateAdded', 'N/A'),
                    'due_date': vuln.get('dueDate', 'N/A')
                })
            
            print(f"✅ CISA KEV catalog contains {kev_summary['total_kev']} vulnerabilities")
            
            return kev_summary
            
        except requests.RequestException as e:
            print(f"⚠️  Failed to fetch CISA KEV (non-critical): {e}")
            return {
                'total_kev': 0,
                'catalog_version': 'unavailable',
                'date_released': 'unavailable',
                'recent_vulnerabilities': []
            }
    
    def generate_control_mapping(self) -> List[Dict]:
        """Generate security control to ATT&CK mitigation mapping."""
        print("🛡️  Generating security control mappings...")
        
        # Define CIA platform security controls and their ATT&CK mitigations
        control_mappings = [
            {
                'control': 'AWS WAF',
                'mitigation': 'M1050: Exploit Protection',
                'techniques': ['T1190', 'T1133'],
                'description': 'Web Application Firewall protecting public endpoints'
            },
            {
                'control': 'Multi-Factor Authentication',
                'mitigation': 'M1032: Multi-factor Authentication',
                'techniques': ['T1078', 'T1110', 'T1556'],
                'description': 'MFA for admin and privileged user accounts'
            },
            {
                'control': 'AWS CloudTrail',
                'mitigation': 'M1047: Audit',
                'techniques': ['T1562', 'T1070', 'T1098'],
                'description': 'Immutable audit logging of all AWS API calls'
            },
            {
                'control': 'VPC Security Groups',
                'mitigation': 'M1030: Network Segmentation',
                'techniques': ['T1021', 'T1563', 'T1041'],
                'description': 'Network-level access controls and segmentation'
            },
            {
                'control': 'Spring Security',
                'mitigation': 'M1035: Limit Access to Resource Over Network',
                'techniques': ['T1548', 'T1068', 'T1078'],
                'description': 'Application-layer authentication and authorization'
            },
            {
                'control': 'AWS GuardDuty',
                'mitigation': 'M1047: Audit',
                'techniques': ['T1190', 'T1078', 'T1048'],
                'description': 'Threat detection and continuous monitoring'
            },
            {
                'control': 'Input Validation',
                'mitigation': 'M1021: Restrict Web-Based Content',
                'techniques': ['T1190', 'T1059', 'T1565'],
                'description': 'Comprehensive input sanitization and validation'
            },
            {
                'control': 'AWS KMS Encryption',
                'mitigation': 'M1041: Encrypt Sensitive Information',
                'techniques': ['T1020', 'T1041', 'T1048'],
                'description': 'Data encryption at rest using AWS KMS'
            }
        ]
        
        # Filter to only include techniques we have in our threat model
        filtered_mappings = []
        for mapping in control_mappings:
            covered_techs = [t for t in mapping['techniques'] if t in self.techniques]
            if covered_techs:
                filtered_mapping = mapping.copy()
                filtered_mapping['techniques'] = covered_techs
                filtered_mappings.append(filtered_mapping)
        
        print(f"✅ Generated {len(filtered_mappings)} control mappings")
        
        return filtered_mappings
    
    def save_results(self, output_dir: Path):
        """Save all analysis results to files."""
        print(f"💾 Saving results to {output_dir}...")
        
        output_dir.mkdir(parents=True, exist_ok=True)
        
        # Generate all analyses
        coverage = self.generate_coverage_matrix()
        navigator_layer = self.generate_navigator_layer()
        kev_data = self.check_cisa_kev()
        control_mappings = self.generate_control_mapping()
        
        # Save coverage analysis
        coverage_file = output_dir / 'attack-coverage.json'
        with open(coverage_file, 'w', encoding='utf-8') as f:
            json.dump(coverage, f, indent=2)
        print(f"✅ Saved coverage analysis: {coverage_file}")
        
        # Save Navigator layer
        navigator_file = output_dir / 'attack-navigator-layer.json'
        with open(navigator_file, 'w', encoding='utf-8') as f:
            json.dump(navigator_layer, f, indent=2)
        print(f"✅ Saved Navigator layer: {navigator_file}")
        
        # Save CISA KEV data
        kev_file = output_dir / 'cisa-kev-summary.json'
        with open(kev_file, 'w', encoding='utf-8') as f:
            json.dump(kev_data, f, indent=2)
        print(f"✅ Saved CISA KEV summary: {kev_file}")
        
        # Save control mappings
        mappings_file = output_dir / 'control-mappings.json'
        with open(mappings_file, 'w', encoding='utf-8') as f:
            json.dump(control_mappings, f, indent=2)
        print(f"✅ Saved control mappings: {mappings_file}")
        
        # Generate markdown summary
        self._generate_markdown_summary(output_dir, coverage, control_mappings)
        
        return {
            'coverage': coverage,
            'navigator_layer': navigator_layer,
            'kev_data': kev_data,
            'control_mappings': control_mappings
        }
    
    def _generate_markdown_summary(self, output_dir: Path, coverage: Dict, control_mappings: List[Dict]):
        """Generate a markdown summary of the coverage analysis."""
        summary_file = output_dir / 'coverage-summary.md'
        
        with open(summary_file, 'w', encoding='utf-8') as f:
            f.write("# 🎖️ MITRE ATT&CK Coverage Summary\n\n")
            f.write(f"**Generated:** {datetime.now(timezone.utc).strftime('%Y-%m-%d %H:%M:%S UTC')}\n\n")
            
            f.write(f"## 📊 Overall Coverage\n\n")
            f.write(f"- **Total Techniques:** {coverage['total_techniques']}\n")
            f.write(f"- **Covered Techniques:** {coverage['covered_techniques']}\n")
            f.write(f"- **Coverage Percentage:** {coverage['coverage_percentage']:.1f}%\n\n")
            
            f.write("## 🎯 Coverage by Tactic\n\n")
            f.write("| Tactic | Covered | Total | Percentage |\n")
            f.write("|--------|---------|-------|------------|\n")
            
            for tactic_key in self.TACTIC_DISPLAY_NAMES.keys():
                tactic_data = coverage['tactics'][tactic_key]
                f.write(f"| {tactic_data['name']} | ")
                f.write(f"{tactic_data['covered']} | ")
                f.write(f"{tactic_data['total']} | ")
                f.write(f"{tactic_data['percentage']:.1f}% |\n")
            
            f.write("\n## 🛡️ Security Control Mappings\n\n")
            for mapping in control_mappings:
                f.write(f"### {mapping['control']}\n")
                f.write(f"- **Mitigation:** {mapping['mitigation']}\n")
                f.write(f"- **Techniques:** {', '.join(mapping['techniques'])}\n")
                f.write(f"- **Description:** {mapping['description']}\n\n")
        
        print(f"✅ Saved markdown summary: {summary_file}")


def main():
    """Main execution function."""
    print("=" * 70)
    print("🎖️  MITRE ATT&CK Coverage Analysis - Citizen Intelligence Agency")
    print("=" * 70)
    print()
    
    # Determine repository root
    repo_root = Path(__file__).parent.parent.parent
    threat_model_path = repo_root / 'THREAT_MODEL.md'
    output_dir = repo_root / 'cia-dist-cloudformation'
    
    try:
        # Initialize analyzer
        analyzer = AttackCoverageAnalyzer(str(threat_model_path))
        
        # Extract techniques from threat model
        analyzer.extract_attack_techniques()
        
        # Fetch ATT&CK framework
        analyzer.fetch_attack_framework()
        
        # Generate and save all analyses
        results = analyzer.save_results(output_dir)
        
        print()
        print("=" * 70)
        print("✅ ATT&CK Coverage Analysis Complete!")
        print("=" * 70)
        print(f"📊 Coverage: {results['coverage']['coverage_percentage']:.1f}%")
        print(f"🎯 Techniques: {results['coverage']['covered_techniques']}/{results['coverage']['total_techniques']}")
        print(f"📁 Output directory: {output_dir}")
        print()
        
        # Exit with appropriate code based on coverage threshold
        coverage_pct = results['coverage']['coverage_percentage']
        if coverage_pct < 30.0:
            print(f"⚠️  WARNING: Coverage below 30% threshold ({coverage_pct:.1f}%)")
            print("   Consider expanding threat model coverage.")
            sys.exit(1)
        else:
            print(f"✅ Coverage meets 30% threshold ({coverage_pct:.1f}%)")
            sys.exit(0)
        
    except Exception as e:
        print(f"\n❌ Error: {e}")
        import traceback
        traceback.print_exc()
        sys.exit(1)


if __name__ == '__main__':
    main()
