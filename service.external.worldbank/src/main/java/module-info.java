/**
 * CIA (Citizen Intelligence Agency) World Bank Service Module.
 *
 * <p>
 * This module provides integration with the World Bank's data services,
 * enabling access to global economic indicators, country data, and development
 * metrics.
 * It serves as a crucial component for international financial and economic
 * analysis.
 * </p>
 *
 * <p>
 * The module exports two packages:
 * </p>
 * <ul>
 * <li>{@code com.hack23.cia.service.external.worldbank.api} - Public interfaces
 * for World Bank data access</li>
 * <li>{@code com.hack23.cia.service.external.worldbank.impl} - Implementation
 * of World Bank service interfaces</li>
 * </ul>
 *
 * <p>
 * Data Categories:
 * </p>
 * <ul>
 * <li>Economic Indicators
 * <ul>
 * <li>Development metrics</li>
 * <li>Financial indicators</li>
 * <li>Economic trends</li>
 * </ul>
 * </li>
 * <li>Country Information
 * <ul>
 * <li>Country profiles</li>
 * <li>Regional data</li>
 * <li>Development status</li>
 * </ul>
 * </li>
 * <li>Topic Analysis
 * <ul>
 * <li>Development topics</li>
 * <li>Global challenges</li>
 * <li>Economic sectors</li>
 * </ul>
 * </li>
 * </ul>
 *
 * <p>
 * Key Features:
 * </p>
 * <ul>
 * <li>Data Processing
 * <ul>
 * <li>XML and CSV data handling</li>
 * <li>Indicator processing</li>
 * <li>Time series analysis</li>
 * </ul>
 * </li>
 * <li>Integration
 * <ul>
 * <li>World Bank API connectivity</li>
 * <li>Data synchronization</li>
 * <li>Error handling and logging</li>
 * </ul>
 * </li>
 * </ul>
 *
 * <p>
 * This module integrates with the common external service framework and
 * provides
 * standardized access to World Bank data as part of the CIA's comprehensive
 * financial and economic monitoring system.
 * </p>
 *
 * @see com.hack23.cia.service.external.common
 * @see com.hack23.cia.model.external.worldbank
 */
open module com.hack23.cia.service.external.worldbank {
	exports com.hack23.cia.service.external.worldbank.api;
	exports com.hack23.cia.service.external.worldbank.impl;

	requires org.slf4j;
	requires java.xml.bind;
	requires spring.context;
	requires spring.beans;
	requires jakarta.activation;
	requires org.apache.commons.lang3;
	requires org.apache.commons.csv;
	requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpclient.fluent;


	requires transitive com.hack23.cia.service.external.common;
	requires transitive com.hack23.cia.model.external.worldbank.countries.impl;
	requires transitive com.hack23.cia.model.external.worldbank.data.impl;
	requires transitive com.hack23.cia.model.external.worldbank.indicators.impl;
	requires transitive com.hack23.cia.model.external.worldbank.topic.impl;
}