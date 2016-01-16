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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.vdem.indicators.impl.CountryQuestionData;
import com.hack23.cia.model.external.vdem.indicators.impl.CountryQuestionDataEmbeddedId;
import com.hack23.cia.model.external.vdem.indicators.impl.Question;

/**
 * The Class VdemQuestionDAO.
 */
@Service
public class VdemQuestionDAO {

	/** The Constant VDEM_DATA_DOWNLOAD_URL. */
	private static final String VDEM_DATA_DOWNLOAD_URL="https://s3-eu-west-1.amazonaws.com/vdemdata/V-Dem-DS-CY-v5.csv";

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

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<CountryQuestionData> getData() {
		List<CountryQuestionData> list = new ArrayList<>();

		List<Question> questions = getAll();

		Reader in;
		try {
			in =  new InputStreamReader(new URL(VDEM_DATA_DOWNLOAD_URL).openStream());

			final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader().withDelimiter(','));

			//Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
			for (CSVRecord record : parser) {
			    String countryName = record.get("country_name");
			    String countryId = record.get("country_id");
			    String countryTextId = record.get("country_text_id");
			    String year = record.get("year");
			    String gapStart = record.get("gapstart");
			    String gapEnd = record.get("gapend");
			    String codingEnd = record.get("codingend");
			    String cowCode = record.get("COWcode");

			    for (Question question : questions) {
			    	if (question.getQuestionId() != null) {

			    		if (record.isMapped(question.getTag())) {

			    			try {
				    		String questionValue = record.get(question.getTag());
				    		if (questionValue != null && questionValue.trim().length() >0) {
				    			CountryQuestionDataEmbeddedId CountryQuestionDataEmbeddedId = new CountryQuestionDataEmbeddedId();
				    			CountryQuestionData countryQuestionData = new CountryQuestionData();

				    			System.out.println( countryName +"."+ year+ " :" + question.getName() + " :" + questionValue);

				    			list.add(countryQuestionData);
				    		}
			    			} catch (Exception e) {
			    				System.out.println("Missing value for:"+ question.getTag());
			    			}

			    		}
			    	}
				}


			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

}
