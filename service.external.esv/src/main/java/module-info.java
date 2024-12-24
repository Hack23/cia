/**
 * CIA (Citizen Intelligence Agency) ESV (Swedish National Financial Management Authority) Service Module.
 *
 * <p>This module provides integration with ESV (Ekonomistyrningsverket) data services,
 * enabling access to Swedish government financial data and statistics. The module handles
 * data retrieval and processing from ESV's various data formats including Excel and CSV.</p>
 *
 * <p>The module exports two packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.external.esv.api} - Public interfaces for ESV data access</li>
 *   <li>{@code com.hack23.cia.service.external.esv.impl} - Implementation of ESV service interfaces</li>
 * </ul>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Financial Data Processing
 *     <ul>
 *       <li>Excel file parsing (POI)</li>
 *       <li>CSV data processing</li>
 *       <li>XML data handling</li>
 *     </ul>
 *   </li>
 *   <li>Government Financial Statistics
 *     <ul>
 *       <li>Budget execution data</li>
 *       <li>Financial statements</li>
 *       <li>Economic indicators</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>This module integrates with the common external service framework and provides
 * standardized access to ESV's financial data as part of the CIA's comprehensive
 * monitoring of government financial performance.</p>
 *
 * <p>Technical Implementation:</p>
 * <ul>
 *   <li>Uses Apache POI for Excel file processing</li>
 *   <li>Implements CSV parsing for data files</li>
 *   <li>Provides Spring-based configuration</li>
 *   <li>Includes comprehensive logging</li>
 * </ul>
 *
 * @see com.hack23.cia.service.external.common
 */
open module com.hack23.cia.service.external.esv {
	exports com.hack23.cia.service.external.esv.api;
	exports com.hack23.cia.service.external.esv.impl;
	
	requires org.slf4j;
	requires java.xml.bind;
	requires spring.context;
	requires spring.beans;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	

	requires org.apache.commons.lang3;
	requires org.apache.commons.codec;
	requires org.apache.commons.csv;
	
	requires com.hack23.cia.service.external.common;
}