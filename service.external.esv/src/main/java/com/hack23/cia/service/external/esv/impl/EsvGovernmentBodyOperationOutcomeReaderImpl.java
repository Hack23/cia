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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;

/**
 * The Class EsvGovernmentBodyOperationOutcomeReaderImpl.
 */
@Component
final class EsvGovernmentBodyOperationOutcomeReaderImpl implements EsvGovernmentBodyOperationOutcomeReader {

    /** The Constant ESV_BASE_URL. */
    private static final String ESV_BASE_URL = "https://www.esv.se/OpenDataManadsUtfallPage/GetFile";
    
    /** The Constant CSV_DELIMITER. */
    private static final String CSV_DELIMITER = ";";

    /** The Constant CONNECT_TIMEOUT. */
    private static final int CONNECT_TIMEOUT = 30_000;
    
    /** The Constant CHARSET. */
    private static final String CHARSET = StandardCharsets.UTF_8.name();

    /** The Constant SWEDISH_MONTH_NAMES. */
    private static final Map<Month, String> SWEDISH_MONTH_NAMES = Map.ofEntries(
    	    Map.entry(Month.JANUARY, "januari"),
    	    Map.entry(Month.FEBRUARY, "februari"),
    	    Map.entry(Month.MARCH, "mars"),
    	    Map.entry(Month.APRIL, "april"),
    	    Map.entry(Month.MAY, "maj"),
    	    Map.entry(Month.JUNE, "juni"),
    	    Map.entry(Month.JULY, "juli"),
    	    Map.entry(Month.AUGUST, "augusti"),
    	    Map.entry(Month.SEPTEMBER, "september"),
    	    Map.entry(Month.OCTOBER, "oktober"),
    	    Map.entry(Month.NOVEMBER, "november"),
    	    Map.entry(Month.DECEMBER, "december")
    	);

    /**
     * The Record MonthColumn.
     *
     * @param month the month
     * @param columnName the column name
     */
    private record MonthColumn(Month month, String columnName) {}

    /** The Constant MONTH_COLUMNS. */
    private static final List<MonthColumn> MONTH_COLUMNS = Arrays.stream(Month.values())
    	    .map(month -> new MonthColumn(month,
    	        String.format(Locale.ENGLISH,"Utfall %s", SWEDISH_MONTH_NAMES.get(month))))
    	    .collect(Collectors.toUnmodifiableList());

    /** The Constant SPECIFIC_FIELDS. */
    private static final Map<DataType, String[]> SPECIFIC_FIELDS = Map.of(
        DataType.INCOME, new String[] {
            "Inkomsttyp", "Inkomsttypsnamn", "Inkomsthuvudgrupp",
            "Inkomsthuvudgruppsnamn", "Inkomsttitelgrupp", "Inkomsttitelgruppsnamn",
            "Inkomsttitel", "Inkomsttitelsnamn", "Inkomstundertitel", "Inkomstundertitelsnamn"
        },
        DataType.OUTGOING, new String[] {
            "Utgiftsområde", "Utgiftsområdesnamn", "Anslag", "Anslagsnamn",
            "Anslagspost", "Anslagspostsnamn", "Anslagsdelpost", "Anslagsdelpostsnamn"
        }
    );

    /** The esv excel reader. */
    private final EsvExcelReader esvExcelReader;

    /**
     * Instantiates a new esv government body operation outcome reader impl.
     *
     * @param esvExcelReader the esv excel reader
     */
    @Autowired
    public EsvGovernmentBodyOperationOutcomeReaderImpl(EsvExcelReader esvExcelReader) {
        this.esvExcelReader = esvExcelReader;
    }

    /**
     * Read income csv.
     *
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public List<GovernmentBodyAnnualOutcomeSummary> readIncomeCsv() throws IOException {
        return fetchData(DataType.INCOME);
    }

    /**
     * Read outgoing csv.
     *
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public List<GovernmentBodyAnnualOutcomeSummary> readOutgoingCsv() throws IOException {
        return fetchData(DataType.OUTGOING);
    }

    /**
     * Fetch data.
     *
     * @param type the type
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private List<GovernmentBodyAnnualOutcomeSummary> fetchData(DataType type) throws IOException {
        final String url = buildUrl(type);
        try (InputStream is = executeRequest(url)) {
            return processZipStream(is, type);
        }
    }

    /**
     * Builds the url.
     *
     * @param type the type
     * @return the string
     */
    private String buildUrl(DataType type) {
        return String.format(Locale.ENGLISH,"%s?documentType=%s&fileType=Zip&fileName=M%%C3%%A5nadsutfall%%20%s%%20januari%%202006%%20-%%20november%%202024,%%20definitivt.zip&Year=2024&month=11&status=Definitiv",
            ESV_BASE_URL,
            type.getDocumentType(),
            type.getUrlName());
    }

    /**
     * Execute request.
     *
     * @param url the url
     * @return the input stream
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private InputStream executeRequest(String url) throws IOException {
        return Request.Get(url)
            .connectTimeout(CONNECT_TIMEOUT)
            .socketTimeout(CONNECT_TIMEOUT)
            .execute()
            .returnContent()
            .asStream();
    }

    /**
     * Process zip stream.
     *
     * @param input the input
     * @param type the type
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private List<GovernmentBodyAnnualOutcomeSummary> processZipStream(InputStream input, DataType type)
            throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(input);
             ZipInputStream zis = new ZipInputStream(bis)) {

            final List<GovernmentBodyAnnualOutcomeSummary> results = new ArrayList<>();
            while ((zis.getNextEntry()) != null) {
                results.addAll(processCsvContent(zis, type));
            }
            return Collections.unmodifiableList(results);
        }
    }

    /**
     * Process csv content.
     *
     * @param is the is
     * @param type the type
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private List<GovernmentBodyAnnualOutcomeSummary> processCsvContent(InputStream is, DataType type)
            throws IOException {
        final CSVParser parser = CSVParser.parse(
            new InputStreamReader(is, CHARSET),
            CSVFormat.EXCEL.builder()
                .setHeader()
                .setDelimiter(CSV_DELIMITER)
                .build()
        );

        final Map<Integer, Map<String, String>> ministryMap = createOrgMinistryMap(
            esvExcelReader.getDataPerMinistry(null)
        );

        return parser.getRecords().stream()
            .skip(1) // Skip header
            .filter(csvRecord -> csvRecord.get("Organisationsnummer") != null)
            .map(csvRecord -> createSummary(csvRecord, type, ministryMap))
            .filter(Objects::nonNull)
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Creates the summary.
     *
     * @param csvRecord the csvRecord
     * @param type the type
     * @param ministryMap the ministry map
     * @return the government body annual outcome summary
     */
    private GovernmentBodyAnnualOutcomeSummary createSummary(
            CSVRecord csvRecord,
            DataType type,
            Map<Integer, Map<String, String>> ministryMap) {
        try {
            final String orgNumber = csvRecord.get("Organisationsnummer");
            final int year = Integer.parseInt(csvRecord.get("År"));

            final GovernmentBodyAnnualOutcomeSummary summary = new GovernmentBodyAnnualOutcomeSummary(
                csvRecord.get("Myndighet"),
                orgNumber,
                getMinistry(ministryMap, year, orgNumber),
                year
            );

            addFields(summary, csvRecord, type);
            addMonthlyData(summary, csvRecord);

            return summary;
        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * Adds the fields.
     *
     * @param summary the summary
     * @param csvRecord the csvRecord
     * @param type the type
     */
    private void addFields(
            GovernmentBodyAnnualOutcomeSummary summary,
            CSVRecord csvRecord,
            DataType type) {
        for (final String field : SPECIFIC_FIELDS.get(type)) {
            final String value = csvRecord.get(field);
            if (value != null) {
                summary.addDescriptionField(field, value);
            }
        }
    }

    /**
     * Adds the monthly data.
     *
     * @param summary the summary
     * @param csvRecord the csvRecord
     */
    private void addMonthlyData(
            GovernmentBodyAnnualOutcomeSummary summary,
            CSVRecord csvRecord) {
        MONTH_COLUMNS.forEach(monthData -> {
            final String value = csvRecord.get(monthData.columnName());
            if (value != null && !value.isEmpty()) {
                try {
                    summary.addData(
                        monthData.month().getValue(),
                        Double.valueOf(value.replace(",", "."))
                    );
                } catch (final NumberFormatException ignored) {}
            }
        });
    }

    /**
     * Gets the ministry.
     *
     * @param ministryMap the ministry map
     * @param year the year
     * @param orgNumber the org number
     * @return the ministry
     */
    private String getMinistry(Map<Integer, Map<String, String>> ministryMap,
                              int year,
                              String orgNumber) {
        final Map<String, String> yearMap = ministryMap.get(year);
        return yearMap != null ? yearMap.get(orgNumber.replace("-", "")) : null;
    }

    /**
     * The Enum DataType.
     */
    private enum DataType {
        
        /** The income. */
        INCOME("Inkomst", "inkomster"),
        
        /** The outgoing. */
        OUTGOING("Utgift", "utgifter");

        /** The document type. */
        private final String documentType;
        
        /** The url name. */
        private final String urlName;

        /**
         * Instantiates a new data type.
         *
         * @param documentType the document type
         * @param urlName the url name
         */
        DataType(String documentType, String urlName) {
            this.documentType = documentType;
            this.urlName = urlName;
        }

        /**
         * Gets the document type.
         *
         * @return the document type
         */
        public String getDocumentType() {
            return documentType;
        }

        /**
         * Gets the url name.
         *
         * @return the url name
         */
        public String getUrlName() {
            return urlName;
        }
    }

    /**
     * Creates the org ministry map.
     *
     * @param data the data
     * @return the map
     */
    private static Map<Integer, Map<String, String>> createOrgMinistryMap(
            Map<Integer, List<GovernmentBodyAnnualSummary>> data) {
        return data.entrySet().stream()
            .collect(Collectors.toMap(
                Entry::getKey,
                e -> e.getValue().stream()
                    .collect(Collectors.toMap(
                        t -> t.getOrgNumber().replace("-", ""),
                        GovernmentBodyAnnualSummary::getMinistry,
                        (v1, v2) -> v1
                    ))
            ));
    }
}
