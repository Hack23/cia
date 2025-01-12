/**
 * CIA (Citizen Intelligence Agency) ESV (Swedish National Financial Management Authority) Service Module.
 *
 * <p>This module integrates with ESV’s data services to provide financial statistics,
 * budget data, and government spending information.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Excel and CSV file parsing for financial data</li>
 *   <li>Statistics for budget execution and economic indicators</li>
 *   <li>Comprehensive logging and Spring-based configuration</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Apache POI for Excel processing</li>
 *   <li>Commons CSV for data import</li>
 *   <li>Spring Framework for dependency injection</li>
 * </ul>
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