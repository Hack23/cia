/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.service.external.worldbank.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorsElement;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldbankIndicatorApiImpl.
 */
@Component
final class WorldbankIndicatorApiImpl extends BaseWorldBankApiImpl implements WorldBankIndicatorApi {

	private static final int IGNORE_TOP_HEADERS_LINE = 4;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldbankIndicatorApiImpl.class);

	/** The Constant INDICATORS. */
	private static final String INDICATORS = "https://api.worldbank.org/v2/indicators?per_page=5000";

	private static final String PAGE_NUMBER = "&page=";

	/** The Constant PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST. */
	private static final String PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST = "Problem getting worldbank indicator list";

	private static final int SECOND_PAGE = 2;

	/**
	 * The Constant
	 * XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "xmlns:wb=\"http://indicators.worldbank.external.model.cia.hack23.com/impl\"";


	/** The indicators unmarshaller. */
	@Autowired
	@Qualifier("worldbankOrgIndicatorsMarshaller")
	private Unmarshaller indicatorsUnmarshaller;

	/**
	 * Instantiates a new worldbank indicator api impl.
	 */
	@Autowired
	public WorldbankIndicatorApiImpl(final XmlAgent xmlAgent) {
		super(xmlAgent);
	}

	@Override
	public List<IndicatorElement> getIndicators() throws DataFailureException {
		final List<IndicatorElement> result = new ArrayList<>();

		try {
			final IndicatorsElement firstPage = (IndicatorsElement) getXmlAgent().unmarshallXml(indicatorsUnmarshaller,
					INDICATORS, null, XMLNS_WB_HTTP_WWW_WORLDBANK_ORG,
					XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL);
			result.addAll(firstPage.getIndicator());

			for (int pageNumber = SECOND_PAGE; pageNumber < firstPage.getPages().intValue(); pageNumber++) {
				final IndicatorsElement otherPageResult = (IndicatorsElement) getXmlAgent().unmarshallXml(indicatorsUnmarshaller,
						INDICATORS + PAGE_NUMBER + pageNumber, null, XMLNS_WB_HTTP_WWW_WORLDBANK_ORG,
						XMLNS_WB_HTTP_INDICATORS_WORLDBANK_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL);
				result.addAll(otherPageResult.getIndicator());
			}

		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_WORLDBANK_INDICATOR_LIST);
			throw new DataFailureException(e);
		}

		return result;
	}

	@Override
	public List<String> getIndicatorsWithSwedishData() throws DataFailureException {
		try {
			return Collections.unmodifiableList(readUsingZipInputStream(Request.Get("http://api.worldbank.org/v2/en/country/SWE?downloadformat=csv").execute().returnContent().asStream()));
		} catch (final IOException e) {
			throw new DataFailureException(e);
		}
	}

	/**
	 * Read using zip input stream.
	 *
	 * @param inputStream
	 *            the input stream
	 * @param specificFields
	 *            the specific fields
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static List<String> readUsingZipInputStream(final InputStream inputStream) throws IOException {
		final BufferedInputStream bis = new BufferedInputStream(inputStream);
		final ZipInputStream is = new ZipInputStream(bis);

		final List<String> list = new ArrayList<>();
		try {
			ZipEntry entry;

			while ((entry = is.getNextEntry()) != null) {
				if (entry.getName().startsWith("API_SWE_")) {
					list.addAll(readCsvContent(is));
				}
			}
		} finally {
			is.close();
		}
		return list;
	}

	/**
	 * Read csv content.
	 *
	 * @param is
	 *            the is
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static List<String> readCsvContent(final InputStream is) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
		for (int i = 0; i < IGNORE_TOP_HEADERS_LINE; i++) {
			final String ignoreFirstLinesWithHeaders = reader.readLine();
		}

		final CSVParser parser = CSVParser.parse(reader, CSVFormat.EXCEL.builder().setHeader().setDelimiter(',').build());
		final List<CSVRecord> records = parser.getRecords();
		records.remove(0);

		final List<String> list = new ArrayList<>();

		for (final CSVRecord csvRecord : records) {
			list.add(csvRecord.get("Indicator Code"));
		}

		return list;
	}



}
