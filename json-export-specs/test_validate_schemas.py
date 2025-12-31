#!/usr/bin/env python3
"""
Unit tests for JSON Schema Validation Script

Tests the SchemaValidator class methods to ensure correct functionality
for field parsing, type inference, field matching, and error handling.

Author: Citizen Intelligence Agency Development Team
License: Apache-2.0
Version: 1.0.0
"""

import unittest
import tempfile
import csv
import json
from pathlib import Path
from validate_schemas import SchemaValidator


class TestSchemaValidator(unittest.TestCase):
    """Test cases for SchemaValidator class."""
    
    def setUp(self):
        """Set up test fixtures."""
        self.temp_dir = tempfile.mkdtemp()
        self.schema_dir = Path(self.temp_dir) / "schemas"
        self.data_dir = Path(self.temp_dir) / "data"
        self.schema_dir.mkdir()
        self.data_dir.mkdir()
        
    def tearDown(self):
        """Clean up test fixtures."""
        import shutil
        shutil.rmtree(self.temp_dir)
    
    def test_initialization(self):
        """Test SchemaValidator initialization."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        self.assertEqual(validator.schema_dir, self.schema_dir)
        self.assertEqual(validator.sample_data_dir, self.data_dir)
        self.assertIsInstance(validator.schemas, dict)
        self.assertIsInstance(validator.sample_data, dict)
        self.assertIn("timestamp", validator.validation_results)
        self.assertEqual(validator.validation_results["schemas_validated"], 0)
    
    def test_camel_to_snake_conversion(self):
        """Test camelCase to snake_case conversion."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        # Test various camelCase patterns
        self.assertEqual(validator._camel_to_snake("firstName"), "first_name")
        self.assertEqual(validator._camel_to_snake("lastName"), "last_name")
        self.assertEqual(validator._camel_to_snake("totalDaysServed"), "total_days_served")
        self.assertEqual(validator._camel_to_snake("riskScore"), "risk_score")
        self.assertEqual(validator._camel_to_snake("ID"), "id")
        self.assertEqual(validator._camel_to_snake("HTTPResponse"), "http_response")
    
    def test_is_integer(self):
        """Test integer detection."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        self.assertTrue(validator._is_integer("123"))
        self.assertTrue(validator._is_integer("0"))
        self.assertTrue(validator._is_integer("-456"))
        self.assertFalse(validator._is_integer("123.45"))
        self.assertFalse(validator._is_integer("abc"))
        self.assertFalse(validator._is_integer(""))
    
    def test_is_float(self):
        """Test float detection."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        self.assertTrue(validator._is_float("123.45"))
        self.assertTrue(validator._is_float("0.0"))
        self.assertTrue(validator._is_float("-456.78"))
        self.assertTrue(validator._is_float("123"))  # integers are also valid floats
        self.assertFalse(validator._is_float("abc"))
        self.assertFalse(validator._is_float(""))
    
    def test_infer_column_types_boolean(self):
        """Test type inference for boolean columns."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        rows = [
            {"active": "t", "enabled": "true"},
            {"active": "f", "enabled": "false"},
            {"active": "t", "enabled": "true"}
        ]
        columns = ["active", "enabled"]
        
        types = validator._infer_column_types(rows, columns)
        
        self.assertEqual(types["active"], "boolean")
        self.assertEqual(types["enabled"], "boolean")
    
    def test_infer_column_types_integer(self):
        """Test type inference for integer columns."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        rows = [
            {"count": "123", "total": "456"},
            {"count": "0", "total": "789"},
            {"count": "999", "total": "0"}
        ]
        columns = ["count", "total"]
        
        types = validator._infer_column_types(rows, columns)
        
        self.assertEqual(types["count"], "integer")
        self.assertEqual(types["total"], "integer")
    
    def test_infer_column_types_float(self):
        """Test type inference for float columns."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        rows = [
            {"score": "123.45", "rate": "0.75"},
            {"score": "67.89", "rate": "0.25"},
            {"score": "100.0", "rate": "1.0"}
        ]
        columns = ["score", "rate"]
        
        types = validator._infer_column_types(rows, columns)
        
        self.assertEqual(types["score"], "float")
        self.assertEqual(types["rate"], "float")
    
    def test_infer_column_types_date(self):
        """Test type inference for date columns."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        rows = [
            {"created_date": "2024-01-01", "updated_time": "2024-01-02"},
            {"created_date": "2024-01-03", "updated_time": "2024-01-04"}
        ]
        columns = ["created_date", "updated_time"]
        
        types = validator._infer_column_types(rows, columns)
        
        self.assertEqual(types["created_date"], "date")
        self.assertEqual(types["updated_time"], "date")
    
    def test_infer_column_types_string(self):
        """Test type inference for string columns."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        rows = [
            {"name": "John", "description": "Test user"},
            {"name": "Jane", "description": "Another test"}
        ]
        columns = ["name", "description"]
        
        types = validator._infer_column_types(rows, columns)
        
        self.assertEqual(types["name"], "string")
        self.assertEqual(types["description"], "string")
    
    def test_infer_column_types_empty(self):
        """Test type inference for empty columns."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        rows = [
            {"empty_col": "", "another_empty": ""},
            {"empty_col": "", "another_empty": ""}
        ]
        columns = ["empty_col", "another_empty"]
        
        types = validator._infer_column_types(rows, columns)
        
        self.assertEqual(types["empty_col"], "empty")
        self.assertEqual(types["another_empty"], "empty")
    
    def test_parse_schema_field_validation(self):
        """Test schema parsing with field name validation."""
        # Create a test schema file with mermaid diagram
        schema_file = self.schema_dir / "test-schema.md"
        schema_content = """
# Test Schema

## Diagram

```mermaid
classDiagram
    class TestClass {
        +String firstName
        +Integer age
        +Float score
        +Integer 8
    }
```
"""
        schema_file.write_text(schema_content)
        
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        schema_info = validator._parse_schema_markdown(schema_file)
        
        # Valid fields should be extracted
        self.assertIn("firstName", schema_info["fields"])
        self.assertIn("age", schema_info["fields"])
        self.assertIn("score", schema_info["fields"])
        
        # Invalid field (numeric only) should be filtered out
        self.assertNotIn("8", schema_info["fields"])
    
    def test_load_csv_data(self):
        """Test loading CSV sample data."""
        # Create a test CSV file
        csv_file = self.data_dir / "view_test_data_sample.csv"
        with open(csv_file, 'w', newline='') as f:
            writer = csv.DictWriter(f, fieldnames=["id", "name", "active"])
            writer.writeheader()
            writer.writerow({"id": "1", "name": "Test", "active": "t"})
            writer.writerow({"id": "2", "name": "Sample", "active": "f"})
        
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        validator.load_sample_data()
        
        self.assertEqual(len(validator.sample_data), 1)
        self.assertIn("view_test_data_sample", validator.sample_data)
        
        data = validator.sample_data["view_test_data_sample"]
        self.assertEqual(data["file"], "view_test_data_sample.csv")
        self.assertEqual(data["columns"], ["id", "name", "active"])
        self.assertEqual(len(data["sample_rows"]), 2)
    
    def test_error_handling_invalid_csv(self):
        """Test error handling for invalid CSV files."""
        # Create a CSV file with invalid content
        csv_file = self.data_dir / "view_invalid_sample.csv"
        csv_file.write_text("This is not a valid CSV file\nwith random content")
        
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        # Should not raise exception, but handle gracefully
        try:
            validator.load_sample_data()
            # If we get here, error was handled gracefully
            self.assertTrue(True)
        except (IOError, csv.Error, UnicodeDecodeError):
            self.fail("Exception should have been caught by error handler")
    
    def test_extract_paths_dict(self):
        """Test extracting paths from nested dictionaries."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        data = {
            "name": "test",
            "details": {
                "age": 25,
                "address": {
                    "city": "Stockholm"
                }
            }
        }
        
        paths = validator._extract_paths(data)
        
        self.assertIn("name", paths)
        self.assertIn("details", paths)
        self.assertIn("details.age", paths)
        self.assertIn("details.address", paths)
        self.assertIn("details.address.city", paths)
    
    def test_extract_paths_list(self):
        """Test extracting paths from lists."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        data = [{"id": 1, "name": "test"}]
        
        paths = validator._extract_paths(data)
        
        self.assertIn("id", paths)
        self.assertIn("name", paths)
    
    def test_field_matching_flexible(self):
        """Test flexible field name matching."""
        # Create test data with snake_case columns
        csv_file = self.data_dir / "view_test_sample.csv"
        with open(csv_file, 'w', newline='') as f:
            writer = csv.DictWriter(f, fieldnames=["first_name", "last_name", "born_year"])
            writer.writeheader()
            writer.writerow({"first_name": "John", "last_name": "Doe", "born_year": "1990"})
        
        # Create test schema with camelCase fields
        schema_file = self.schema_dir / "test-schema.md"
        schema_content = """
# Test Schema

```mermaid
classDiagram
    class TestClass {
        +String firstName
        +String lastName
        +Integer bornYear
    }
```
"""
        schema_file.write_text(schema_content)
        
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        validator.load_sample_data()
        
        schema_info = validator._parse_schema_markdown(schema_file)
        
        # Verify camelCase fields are extracted
        self.assertIn("firstName", schema_info["fields"])
        self.assertIn("lastName", schema_info["fields"])
        self.assertIn("bornYear", schema_info["fields"])
        
        # Verify snake_case columns are loaded
        self.assertIn("view_test_sample", validator.sample_data)
        columns = validator.sample_data["view_test_sample"]["columns"]
        self.assertIn("first_name", columns)
        self.assertIn("last_name", columns)
        self.assertIn("born_year", columns)


class TestValidationReports(unittest.TestCase):
    """Test cases for validation report generation."""
    
    def setUp(self):
        """Set up test fixtures."""
        self.temp_dir = tempfile.mkdtemp()
        self.schema_dir = Path(self.temp_dir) / "schemas"
        self.data_dir = Path(self.temp_dir) / "data"
        self.schema_dir.mkdir()
        self.data_dir.mkdir()
    
    def tearDown(self):
        """Clean up test fixtures."""
        import shutil
        shutil.rmtree(self.temp_dir)
    
    def test_generate_json_report(self):
        """Test JSON report generation."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        output_path = Path(self.temp_dir) / "test-results.json"
        validator.generate_json_report(str(output_path))
        
        self.assertTrue(output_path.exists())
        
        with open(output_path) as f:
            results = json.load(f)
        
        self.assertIn("timestamp", results)
        self.assertIn("schemas_validated", results)
        self.assertIn("files_analyzed", results)
        self.assertIn("total_mismatches", results)
        self.assertIn("schemas", results)
    
    def test_generate_markdown_report(self):
        """Test Markdown report generation."""
        validator = SchemaValidator(str(self.schema_dir), str(self.data_dir))
        
        output_path = Path(self.temp_dir) / "test-report.md"
        validator.generate_report(str(output_path))
        
        self.assertTrue(output_path.exists())
        
        content = output_path.read_text()
        
        # Check for expected sections
        self.assertIn("# JSON Schema Validation Report", content)
        self.assertIn("## Executive Summary", content)
        self.assertIn("## Detailed Findings", content)
        self.assertIn("**Schemas Validated:**", content)
        self.assertIn("**Sample Files Analyzed:**", content)


if __name__ == '__main__':
    unittest.main()
