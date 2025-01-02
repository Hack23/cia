/*
 * Copyright 2010-2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.external.esv.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class EsvExcelReaderImpl.
 */
@Component
final class EsvExcelReaderImpl implements EsvExcelReader {

    /** The Constant GOVERNMENT_BODY_EXCEL. */
    private static final String GOVERNMENT_BODY_EXCEL = "/Myndighetsinformation.xls";
    
    /** The Constant EXPECTED_COLUMN_LENGTH. */
    private static final int EXPECTED_COLUMN_LENGTH = 14;

    /**
     * The Record ExcelColumn.
     *
     * @param name the name
     * @param index the index
     */
    private record ExcelColumn(String name, int index) {
        
        /**
         * Gets the value.
         *
         * @param row the row
         * @return the value
         */
        public String getValue(Row row) {
            final Cell cell = row.getCell(index);
            return cell != null ? cell.toString() : "";
        }

        /**
         * Gets the int value.
         *
         * @param row the row
         * @return the int value
         */
        public int getIntValue(Row row) {
            final String value = getValue(row);
            return value.trim().isEmpty() ? 0 : Integer.parseInt(value);
        }
    }

    /** The Constant COLUMNS. */
    private static final ExcelColumn[] COLUMNS = {
        new ExcelColumn("NAME", 0),
        new ExcelColumn("CONSECUTIVE_NUMBER", 2),
        new ExcelColumn("GOVERNMENT_BODY_ID", 3),
        new ExcelColumn("MCODE", 3),
        new ExcelColumn("MINISTRY", 4),
        new ExcelColumn("ORG_NUMBER", 5),
        new ExcelColumn("HEADCOUNT", 6),
        new ExcelColumn("ANNUAL_HEADCOUNT", 7),
        new ExcelColumn("VAT", 8),
        new ExcelColumn("COMMENT", 14)
    };

    /**
     * Gets the data per ministry.
     *
     * @param name the name
     * @return the data per ministry
     */
    @Override
    public Map<Integer, List<GovernmentBodyAnnualSummary>> getDataPerMinistry(final String name) {
        final Map<Integer, List<GovernmentBodyAnnualSummary>> result = new TreeMap<>();

        try (HSSFWorkbook workbook = loadWorkbook()) {
            processSheets(workbook, (sheet, year) ->
                result.put(year, processMinistrySheet(sheet, name)));
        } catch (final IOException e) {
            throw new EsvExcelReaderException("Failed to read ministry data", e);
        }

        return result;
    }

    /**
     * Gets the data per government body.
     *
     * @param name the name
     * @return the data per government body
     */
    @Override
    public Map<Integer, GovernmentBodyAnnualSummary> getDataPerGovernmentBody(final String name) {
        final Map<Integer, GovernmentBodyAnnualSummary> result = new TreeMap<>();

        try (HSSFWorkbook workbook = loadWorkbook()) {
            processSheets(workbook, (sheet, year) -> {
                final Optional<GovernmentBodyAnnualSummary> summary = processGovernmentBodySheet(sheet, year, name);
                summary.ifPresent(s -> result.put(year, s));
            });
        } catch (final IOException e) {
            throw new EsvExcelReaderException("Failed to read government body data", e);
        }

        return result;
    }

    /**
     * Load workbook.
     *
     * @return the HSSF workbook
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private HSSFWorkbook loadWorkbook() throws IOException {
        try (InputStream is = EsvExcelReaderImpl.class.getResourceAsStream(GOVERNMENT_BODY_EXCEL)) {
            if (is == null) {
                throw new IOException("Failed to load resource: " + GOVERNMENT_BODY_EXCEL);
            }
            return new HSSFWorkbook(is);
        }
    }

    /**
     * The Interface SheetProcessor.
     */
    @FunctionalInterface
    private interface SheetProcessor {
        
        /**
         * Process.
         *
         * @param sheet the sheet
         * @param year the year
         */
        void process(HSSFSheet sheet, int year);
    }

    /**
     * Process sheets.
     *
     * @param workbook the workbook
     * @param processor the processor
     */
    private void processSheets(HSSFWorkbook workbook, SheetProcessor processor) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            final HSSFSheet sheet = workbook.getSheetAt(i);
            final String sheetName = sheet.getSheetName();

            if (sheetName.chars().allMatch(Character::isDigit)) {
                processor.process(sheet, Integer.parseInt(sheetName));
            }
        }
    }

    /**
     * Process ministry sheet.
     *
     * @param sheet the sheet
     * @param ministryName the ministry name
     * @return the list
     */
    private List<GovernmentBodyAnnualSummary> processMinistrySheet(HSSFSheet sheet, String ministryName) {
        return StreamSupport.stream(sheet.spliterator(), false)
            .skip(1) // Skip header row
            .filter(r -> r instanceof Row)
            .map(r -> r)
            .filter(row -> isValidRow(row))
            .map(row -> createSummary(row, Integer.parseInt(sheet.getSheetName())))
            .filter(summary -> ministryName == null ||
                             ministryName.equalsIgnoreCase(summary.getMinistry()))
            .collect(Collectors.toList());
    }

    /**
     * Process government body sheet.
     *
     * @param sheet the sheet
     * @param year the year
     * @param bodyName the body name
     * @return the optional
     */
    private Optional<GovernmentBodyAnnualSummary> processGovernmentBodySheet(
            HSSFSheet sheet, int year, String bodyName) {
        return StreamSupport.stream(sheet.spliterator(), false)
            .skip(1) // Skip header row
            .filter(row -> isValidRow(row))
            .map(row -> createSummary(row, year))
            .filter(summary -> bodyName == null ||
                             bodyName.equalsIgnoreCase(summary.getName()))
            .findFirst();
    }

    /**
     * Checks if is valid row.
     *
     * @param row the row
     * @return true, if is valid row
     */
    private boolean isValidRow(Row row) {
        return row != null && row.getLastCellNum() >= EXPECTED_COLUMN_LENGTH;
    }

    /**
     * Creates the summary.
     *
     * @param row the row
     * @param year the year
     * @return the government body annual summary
     */
    private GovernmentBodyAnnualSummary createSummary(Row row, int year) {
        return new GovernmentBodyAnnualSummary(
            year,
            COLUMNS[0].getValue(row),    // NAME
            COLUMNS[1].getIntValue(row), // CONSECUTIVE_NUMBER
            COLUMNS[2].getValue(row),    // GOVERNMENT_BODY_ID
            COLUMNS[3].getValue(row),    // MCODE
            COLUMNS[4].getValue(row),    // MINISTRY
            COLUMNS[5].getValue(row),    // ORG_NUMBER
            COLUMNS[6].getIntValue(row), // HEADCOUNT
            COLUMNS[7].getIntValue(row), // ANNUAL_HEADCOUNT
            COLUMNS[8].getValue(row),    // VAT
            COLUMNS[9].getValue(row)     // COMMENT
        );
    }

    /**
     * The Class EsvExcelReaderException.
     */
    public static class EsvExcelReaderException extends RuntimeException {
        
        /**
         * Instantiates a new esv excel reader exception.
         *
         * @param message the message
         * @param cause the cause
         */
        public EsvExcelReaderException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}