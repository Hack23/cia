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
package com.hack23.cia.service.external.esv.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.Charsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.junit.Ignore;
import org.junit.Test;

import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;

/**
 * The Class EsvGovernmentOperationIncomeReaderTest.
 */
public final class EsvGovernmentOperationIncomeReaderTest extends AbstractEsvFunctionalIntegrationTest {

	@Test
	@Ignore
	public void downloadIncomeCsvZipTest() throws IOException {
		Content returnContent = Request.Get(
				"https://www.esv.se/psidata/manadsutfall/GetFile/?documentType=Inkomst&fileType=Zip&fileName=M%C3%A5nadsutfall%20inkomster%20januari%202006%20-%20februari%202018,%20definitivt.zip&year=2018&month=2&status=Definitiv")
				.execute().returnContent();
		//readUsingZipInputStream(returnContent.asStream());

	}

	@Test
	public void readIncomeCsvZipTest() throws IOException {
		String[] specificFields = new String[] { "Inkomsttyp", "Inkomsttypsnamn", "Inkomsthuvudgrupp", "Inkomsthuvudgruppsnamn", "Inkomsttitelgrupp", "Inkomsttitelgruppsnamn", "Inkomsttitel", "Inkomsttitelsnamn", "Inkomstundertitel", "Inkomstundertitelsnamn"};
		List<GovernmentBodyAnnualOutcomeSummary> list = readUsingZipInputStream(EsvGovernmentOperationIncomeReaderTest.class.getResourceAsStream("/Månadsutfall inkomster januari 2006 - februari 2018%2c definitivt.zip"),specificFields);
		assertNotNull(list);
		assertFalse(list.isEmpty());		
	}

	private static String[] incomeFields = new String[] {"Inkomsttyp", "Inkomsttypsnamn", "Inkomsthuvudgrupp", "Inkomsthuvudgruppsnamn", "Inkomsttitelgrupp", "Inkomsttitelgruppsnamn", "Inkomsttitel", "Inkomsttitelsnamn", "Inkomstundertitel", "Inkomstundertitelsnamn", "Myndighet", "Organisationsnummer", "År", "Utfall januari", "Utfall februari", "Utfall mars", "Utfall april", "Utfall maj", "Utfall juni", "Utfall juli", "Utfall augusti", "Utfall september", "Utfall oktober", "Utfall november", "Utfall december", "Inkomsttyp utfallsår", "Inkomsttypsnamn utfallsår", "Inkomsthuvudgrupp utfallsår", "Inkomsthuvudgruppsnamn utfallsår", "Inkomsttitelgrupp utfallsår", "Inkomsttitelgruppsnamn utfallsår", "Inkomsttitel utfallsår", "Inkomsttitelsnamn utfallsår", "Inkomstundertitel utfallsår", "Inkomstundertitelsnamn utfallsår"};
	private static String[] outgoingFields = new String[] {"Utgiftsområde", "Utgiftsområdesnamn", "Anslag", "Anslagsnamn", "Anslagspost", "Anslagspostsnamn", "Anslagsdelpost", "Anslagsdelpostsnamn", "Myndighet", "Organisationsnummer", "År", "Utfall januari", "Utfall februari", "Utfall mars", "Utfall april", "Utfall maj", "Utfall juni", "Utfall juli", "Utfall augusti", "Utfall september", "Utfall oktober", "Utfall november", "Utfall december", "Utgiftsområde utfallsår", "Utgiftsområdesnamn utfallsår", "Anslag utfallsår", "Anslagsnamn utfallsår", "Anslagspost utfallsår", "Anslagspostsnamn utfallsår", "Anslagsdelpost utfallsår", "Anslagsdelpostsnamn utfallsår"};
	
	@Test
	public void readOutgoingCsvZipTest() throws IOException {		
		String[] specificFields = new String[] { "Utgiftsområde", "Utgiftsområdesnamn", "Anslag", "Anslagsnamn", "Anslagspost", "Anslagspostsnamn", "Anslagsdelpost", "Anslagsdelpostsnamn"};
		List<GovernmentBodyAnnualOutcomeSummary> list = readUsingZipInputStream(EsvGovernmentOperationIncomeReaderTest.class.getResourceAsStream("/Månadsutfall utgifter januari 2006 - februari 2018%2c definitivt.zip"),specificFields);
		assertNotNull(list);
		assertFalse(list.isEmpty());		
	}

	private static List<GovernmentBodyAnnualOutcomeSummary> readUsingZipInputStream(InputStream inputStream,String[] specificFields) throws IOException {
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		final ZipInputStream is = new ZipInputStream(bis);

		List<GovernmentBodyAnnualOutcomeSummary> list = new ArrayList<>();
		try {
			ZipEntry entry;
			while ((entry = is.getNextEntry()) != null) {
				list.addAll(readCsvContent(entry, is,specificFields));
			}
		} finally {
			is.close();
		}

		return list;
	}

	private static List<GovernmentBodyAnnualOutcomeSummary> readCsvContent(final ZipEntry entry, InputStream is,String[] specificFields) throws IOException {
		CSVParser parser = CSVParser.parse(new InputStreamReader(is,Charsets.UTF_8), CSVFormat.EXCEL.withHeader().withDelimiter(';'));
		List<CSVRecord> records = parser.getRecords();
		records.remove(0);
		
		List<GovernmentBodyAnnualOutcomeSummary> list = new ArrayList<>();
		
		for (CSVRecord csvRecord : records) {
			GovernmentBodyAnnualOutcomeSummary governmentBodyAnnualOutcomeSummary = new GovernmentBodyAnnualOutcomeSummary(csvRecord.get("Myndighet"), csvRecord.get("Organisationsnummer"), Integer.valueOf(csvRecord.get("År")));
			
			for (String field : specificFields) {				
				governmentBodyAnnualOutcomeSummary.addDescriptionField(field,csvRecord.get(field));
			}
			
			addResultForMonth(governmentBodyAnnualOutcomeSummary,1,csvRecord.get("Utfall januari"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,2,csvRecord.get("Utfall februari"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,3,csvRecord.get("Utfall mars"));
			
			addResultForMonth(governmentBodyAnnualOutcomeSummary,4,csvRecord.get("Utfall april"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,5,csvRecord.get("Utfall maj"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,6,csvRecord.get("Utfall juni"));
			
			addResultForMonth(governmentBodyAnnualOutcomeSummary,7,csvRecord.get("Utfall juli"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,8,csvRecord.get("Utfall augusti"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,9,csvRecord.get("Utfall september"));
			
			addResultForMonth(governmentBodyAnnualOutcomeSummary,10,csvRecord.get("Utfall oktober"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,11,csvRecord.get("Utfall november"));
			addResultForMonth(governmentBodyAnnualOutcomeSummary,12,csvRecord.get("Utfall december"));
			
			list.add(governmentBodyAnnualOutcomeSummary);
		}
		
		return list;
	}

	private static void addResultForMonth(GovernmentBodyAnnualOutcomeSummary governmentBodyAnnualOutcomeSummary, int month,
			String value) {
		if (value != null && value.length() >0 ) {
			governmentBodyAnnualOutcomeSummary.addData(month,Double.valueOf(value.replaceAll(",", ".")));
		}
	}

}