/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.service.data.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.vdem.indicators.impl.Question;

/**
 * The Class VdemQuestionDAO.
 */
@Service
public class VdemQuestionDAO {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Question> getAll() {

	List<Question> list = new ArrayList<>();

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook;
			try {
				myWorkBook = new XSSFWorkbook(VdemQuestionDAO.class.getResourceAsStream("/V-DemQuestionIDsv5(2016).xlsx"));
				XSSFSheet mySheet = myWorkBook.getSheetAt(0);
				Iterator<Row> rowIterator = mySheet.iterator();

				Row ignoreFirstRow = rowIterator.next();

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					Question question = new Question();


					if (row.getCell(0) == null) {
						question.setTag(row.getCell(1).toString());
						question.setName(row.getCell(2).toString());
					} else {
						question.setQuestionId(row.getCell(0).toString());
						question.setTag(row.getCell(1).toString());
						question.setName(row.getCell(2).toString());
					}
					list.add(question);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return list;

	}



}
