/**
 * CIA (Citizen Intelligence Agency) Agent Implementation Module.
 *
 * <p>This module implements the data collection and processing agents for the CIA application.
 * It provides implementations for retrieving and processing data from multiple sources including
 * the Swedish Parliament (Riksdagen), Election Authority (Val), and World Bank.</p>
 *
 * <p>The module exports the following key packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.component.agent.impl.command} - Command processing implementations</li>
 *   <li>{@code com.hack23.cia.service.component.agent.impl.riksdagen} - Swedish Parliament data agents</li>
 *   <li>{@code com.hack23.cia.service.component.agent.impl.val} - Election data agents</li>
 *   <li>{@code com.hack23.cia.service.component.agent.impl.worldbank} - World Bank data agents</li>
 * </ul>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Automated data collection from multiple sources</li>
 *   <li>JMS-based message processing</li>
 *   <li>Spring-managed transaction handling</li>
 *   <li>Secure data processing with Spring Security integration</li>
 *   <li>Comprehensive data model support for various external sources</li>
 * </ul>
 *
 * <p>Data Sources Integration:</p>
 * <ul>
 *   <li>Swedish Parliament (Riksdagen)
 *     <ul>
 *       <li>Political documents and status</li>
 *       <li>Voting records</li>
 *       <li>Member information</li>
 *       <li>Committee proposals</li>
 *     </ul>
 *   </li>
 *   <li>Election Authority (Val)
 *     <ul>
 *       <li>Electoral district data</li>
 *       <li>Political party information</li>
 *       <li>Regional election data</li>
 *     </ul>
 *   </li>
 *   <li>World Bank
 *     <ul>
 *       <li>Economic indicators</li>
 *       <li>Country data</li>
 *       <li>Topic classifications</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * @see com.hack23.cia.service.component.agent.api
 * @see com.hack23.cia.service.external.common
 */
open module com.hack23.cia.service.component.agent.impl {


	exports com.hack23.cia.service.component.agent.impl.command;
	exports com.hack23.cia.service.component.agent.impl.riksdagen;
	exports com.hack23.cia.service.component.agent.impl.val;
	exports com.hack23.cia.service.component.agent.impl.worldbank;
		
	requires java.xml.bind;
	requires java.annotation;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;
	requires spring.tx;
	requires spring.core;
	requires spring.jms;
	requires org.apache.commons.lang3;

	requires spring.security.core;

	requires org.joda.time;

	requires javax.jms;
	
	requires com.hack23.cia.service.external.common;
    requires com.hack23.cia.service.data.api;
	requires com.hack23.cia.service.component.agent.api;
	requires com.hack23.cia.service.external.riksdagen;
	requires com.hack23.cia.service.external.val;
	requires com.hack23.cia.service.external.worldbank;

	requires com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires com.hack23.cia.model.external.worldbank.topic.impl;
	requires com.hack23.cia.model.external.worldbank.indicators.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.worldbank.data.impl;
	requires com.hack23.cia.model.external.worldbank.countries.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.val.partier.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;
}