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
package com.hack23.cia.service.external.esv.api;

import java.util.List;
import java.util.Map;

/**
 * The Interface EsvApi.
 */
public interface EsvApi {

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	Map<Integer, List<GovernmentBodyAnnualSummary>> getData();

	/**
	 * Gets the data per ministry.
	 *
	 * @param name
	 *            the name
	 * @return the data per ministry
	 */
	Map<Integer, List<GovernmentBodyAnnualSummary>> getDataPerMinistry(String name);

	/**
	 * Gets the data per government body.
	 *
	 * @param name
	 *            the name
	 * @return the data per government body
	 */
	Map<Integer, GovernmentBodyAnnualSummary> getDataPerGovernmentBody(String name);

	/**
	 * Gets the data per ministry and year.
	 *
	 * @param name
	 *            the name
	 * @param year
	 *            the year
	 * @return the data per ministry and year
	 */
	List<GovernmentBodyAnnualSummary> getDataPerMinistryAndYear(String name, int year);

	/**
	 * Gets the government body names.
	 *
	 * @return the government body names
	 */
	List<String> getGovernmentBodyNames();

	/**
	 * Gets the government body names.
	 *
	 * @param ministry
	 *            the ministry
	 * @return the government body names
	 */
	List<String> getGovernmentBodyNames(String ministry);


	/**
	 * Gets the ministry names.
	 *
	 * @return the ministry names
	 */
	List<String> getMinistryNames();

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	Map<String, List<GovernmentOperationPeriodOutcome>> getReport();

	/**
	 * Gets the government body report.
	 *
	 * @return the government body report
	 */
	Map<String, List<GovernmentBodyAnnualOutcomeSummary>> getGovernmentBodyReport();

	/**
	 * Gets the government body report by field.
	 *
	 * @param string
	 *            the string
	 * @return the government body report by field
	 */
	Map<String, List<GovernmentBodyAnnualOutcomeSummary>> getGovernmentBodyReportByField(String string);

	/**
	 * Gets the government body report by field and ministry.
	 *
	 * @param string   the string
	 * @param ministry the ministry
	 * @return the government body report by field and ministry
	 */
	Map<String, List<GovernmentBodyAnnualOutcomeSummary>> getGovernmentBodyReportByFieldAndMinistry(String string, String ministry);

	/**
	 * Gets the government body report by ministry.
	 *
	 * @return the government body report by ministry
	 */
	Map<String, List<GovernmentBodyAnnualOutcomeSummary>> getGovernmentBodyReportByMinistry();

}