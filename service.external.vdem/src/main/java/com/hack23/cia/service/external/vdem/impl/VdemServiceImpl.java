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
package com.hack23.cia.service.external.vdem.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hack23.cia.model.external.vdem.indicators.impl.CountryQuestionData;
import com.hack23.cia.model.external.vdem.indicators.impl.CountryQuestionDataEmbeddedId;
import com.hack23.cia.model.external.vdem.indicators.impl.Question;
import com.hack23.cia.service.external.vdem.api.VdemService;

/**
 * The Class VdemServiceImpl.
 */
@Service
final class VdemServiceImpl implements VdemService {

	/** The Constant VDEM_DATA_DOWNLOAD_URL. */
	private static final String VDEM_DATA_DOWNLOAD_URL = "https://s3-eu-west-1.amazonaws.com/vdemdata/V-Dem-DS-CY-v5.csv";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(VdemServiceImpl.class);

	@Override
	public List<Question> getQuestions() {

		final List<Question> list = new ArrayList<>();
		try {
			final XSSFWorkbook myWorkBook = new XSSFWorkbook(VdemServiceImpl.class.getResourceAsStream("/V-DemQuestionIDsv5(2016).xlsx"));
			final XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			final Iterator<Row> rowIterator = mySheet.iterator();

			rowIterator.next();

			while (rowIterator.hasNext()) {
				final Row row = rowIterator.next();

				final Question question = new Question();

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

			myWorkBook.close();
		} catch (final IOException e) {
			LOGGER.warn("Problem loading", e);
		}

		return list;

	}

	/**
	 * Gets the country question data.
	 *
	 * @return the country question data
	 */
	@Override
	public List<CountryQuestionData> getCountryQuestionData() {
		final List<CountryQuestionData> list = new ArrayList<>();

		final List<Question> questions = getQuestions();

		try {
			final Reader in = new InputStreamReader(new URL(VDEM_DATA_DOWNLOAD_URL).openStream(),StandardCharsets.UTF_8);

			final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader().withDelimiter(','));

			for (final CSVRecord record : parser) {
				final String countryName = record.get("country_name");
				final String countryId = record.get("country_id");
				final String countryTextId = record.get("country_text_id");
				final String year = record.get("year");
				final String gapStart = record.get("gapstart");
				final String gapEnd = record.get("gapend");
				final String codingEnd = record.get("codingend");
				final String cowCode = record.get("COWcode");

				final int currentSize = list.size();
				LOGGER.info("Loading vdem data for country:{} year {} ",countryName,year);

				for (final Question question : questions) {
					addQuestionDataToList(list, record, countryName, countryId, countryTextId, year, gapStart, gapEnd,
							codingEnd, cowCode, question);
				}

				final int afterSize = list.size();
				LOGGER.info("Found vdem data for country:{} year:{} data points:{}",countryName,year,afterSize -currentSize);

			}
			parser.close();

		} catch (final IOException e) {

			LOGGER.warn("Problem loading vdem data", e);
		}

		return list;
	}

	/**
	 * Adds the question data to list.
	 *
	 * @param list
	 *            the list
	 * @param record
	 *            the record
	 * @param countryName
	 *            the country name
	 * @param countryId
	 *            the country id
	 * @param countryTextId
	 *            the country text id
	 * @param year
	 *            the year
	 * @param gapStart
	 *            the gap start
	 * @param gapEnd
	 *            the gap end
	 * @param codingEnd
	 *            the coding end
	 * @param cowCode
	 *            the cow code
	 * @param question
	 *            the question
	 */
	private static void addQuestionDataToList(final List<CountryQuestionData> list, final CSVRecord record,
			final String countryName, final String countryId, final String countryTextId, final String year,
			final String gapStart, final String gapEnd, final String codingEnd, final String cowCode,
			final Question question) {
		
		if (question.getQuestionId() != null && record.isMapped(question.getTag())) {

				try {
					final String questionValue = record.get(question.getTag());

					if (questionValue != null && StringUtils.hasText(questionValue)) {
						final CountryQuestionData countryQuestionData = new CountryQuestionData();
						final CountryQuestionDataEmbeddedId embeddedId = new CountryQuestionDataEmbeddedId();

						embeddedId.setCountryName(countryName);
						embeddedId.setCountryId(countryId);
						embeddedId.setIndicatorId(question.getTag());
						embeddedId.setYear(Integer.parseInt(year));
						embeddedId.setCountryId(countryId);

						countryQuestionData.setEmbeddedId(embeddedId);

						countryQuestionData.setCountryTextId(countryTextId);
						countryQuestionData.setGapstart(gapStart);
						countryQuestionData.setGapend(gapEnd);
						countryQuestionData.setCodingend(codingEnd);
						countryQuestionData.setCowCode(cowCode);
						countryQuestionData.setQuestionValue(questionValue.trim());

						list.add(countryQuestionData);
					}
				} catch (final RuntimeException e) {
					LOGGER.warn("Missing value for:"+ question.getTag(), e);
				}
			}
	}

}
