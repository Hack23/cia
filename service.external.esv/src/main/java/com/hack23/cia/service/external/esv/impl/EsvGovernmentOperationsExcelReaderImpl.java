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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.external.esv.api.GovernmentOperationPeriodOutcome;

/**
 * The Class EsvGovernmentOperationsExcelReaderImpl.
 */
@Component
final class EsvGovernmentOperationsExcelReaderImpl implements EsvGovernmentOperationsExcelReader {

	/** The Constant STATUS_CELL. */
	private static final int STATUS_CELL = 5;

	/** The Constant PERCENTAGE_CHANGE_PREVIOUS_CELL. */
	private static final int PERCENTAGE_CHANGE_PREVIOUS_CELL = 3;

	/** The Constant PERCENTAGE_CHANGE_SAME_CELL. */
	private static final int PERCENTAGE_CHANGE_SAME_CELL = 4;

	/** The Constant PERIOD_CELL. */
	private static final int PERIOD_CELL = 1;

	/** The Constant VARIABLE_CELL. */
	private static final int VARIABLE_CELL = 0;

	/** The Constant VALUE_CELL. */
	private static final int VALUE_CELL = 2;

	/** The Constant ROW_LENGTH. */
	private static final int ROW_LENGTH = 6;

	/**
	 * Instantiates a new esv government operations excel reader impl.
	 */
	public EsvGovernmentOperationsExcelReaderImpl() {
		super();
	}

	private static XSSFWorkbook createGovermentOperationsWorkBook() throws IOException {
		return new XSSFWorkbook(EsvGovernmentOperationsExcelReaderImpl.class
				.getResourceAsStream("/sdds-plus-august-20202.xlsx"));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<GovernmentOperationPeriodOutcome> getReport() throws IOException {
		final List<GovernmentOperationPeriodOutcome> result = new ArrayList<>();
		final XSSFWorkbook wb = createGovermentOperationsWorkBook();

		final XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;

		final Iterator rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();

			if (row.getLastCellNum() == ROW_LENGTH && row.getCell(VALUE_CELL).getCellType() == CellType.NUMERIC) {
				final GovernmentOperationPeriodOutcome governmentOperationPeriodOutcome = new GovernmentOperationPeriodOutcome();

				governmentOperationPeriodOutcome.setVariableName(row.getCell(VARIABLE_CELL).getStringCellValue());
				governmentOperationPeriodOutcome.setPeriod(row.getCell(PERIOD_CELL).getStringCellValue());

				governmentOperationPeriodOutcome.setValue(row.getCell(VALUE_CELL).getNumericCellValue());
				governmentOperationPeriodOutcome
						.setPercentageChangeFromPreviousToLatest(row.getCell(PERCENTAGE_CHANGE_PREVIOUS_CELL).getNumericCellValue());
				governmentOperationPeriodOutcome
						.setPercentageChangeFromSamePeriodLastYearToLatest(row.getCell(PERCENTAGE_CHANGE_SAME_CELL).getNumericCellValue());

				governmentOperationPeriodOutcome.setObservationStatus(row.getCell(STATUS_CELL).getStringCellValue());

				result.add(governmentOperationPeriodOutcome);
			}
		}
		return result;
	}

}
