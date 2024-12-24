/**
 * CIA (Citizen Intelligence Agency) Riksdagen (Swedish Parliament) Service Module.
 *
 * <p>This module provides integration with the Swedish Parliament's (Riksdagen) data services,
 * enabling comprehensive access to parliamentary information, voting records, and political data.
 * It implements the service layer for retrieving and processing Riksdagen's open data.</p>
 *
 * <p>The module exports two packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.external.riksdagen.api} - Public interfaces for Riksdagen data access</li>
 *   <li>{@code com.hack23.cia.service.external.riksdagen.impl} - Implementation of Riksdagen service interfaces</li>
 * </ul>
 *
 * <p>Data Categories:</p>
 * <ul>
 *   <li>Parliamentary Documents
 *     <ul>
 *       <li>Document content and metadata</li>
 *       <li>Document status tracking</li>
 *       <li>Committee proposals</li>
 *     </ul>
 *   </li>
 *   <li>Voting Records
 *     <ul>
 *       <li>Individual votes</li>
 *       <li>Voting lists and summaries</li>
 *       <li>Voting patterns analysis</li>
 *     </ul>
 *   </li>
 *   <li>Member Information
 *     <ul>
 *       <li>Personal details</li>
 *       <li>Parliamentary roles</li>
 *       <li>Political history</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>This module integrates with the common external service framework and implements
 * XML processing for Riksdagen's data formats. It provides comprehensive access to
 * parliamentary data for political monitoring and analysis.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Real-time data access to Riksdagen's open data APIs</li>
 *   <li>Complete parliamentary document processing</li>
 *   <li>Comprehensive voting record analysis</li>
 *   <li>Member activity tracking</li>
 * </ul>
 *
 * @see com.hack23.cia.service.external.common
 * @see com.hack23.cia.model.external.riksdagen
 */
open module com.hack23.cia.service.external.riksdagen {
	exports com.hack23.cia.service.external.riksdagen.api;
	exports com.hack23.cia.service.external.riksdagen.impl;
	
	requires java.xml.bind;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;


	requires com.hack23.cia.service.external.common;

	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;

}