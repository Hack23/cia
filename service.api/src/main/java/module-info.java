/**
 * CIA (Citizen Intelligence Agency) Service API Module.
 *
 * <p>This module defines the core service API for the CIA application, providing
 * interfaces for all business operations and actions. It acts as the primary
 * interface layer between the presentation tier and the business logic.</p>
 *
 * <p>The module exports the following packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.api} - Core service interfaces</li>
 *   <li>{@code com.hack23.cia.service.api.action.admin} - Administrative operations</li>
 *   <li>{@code com.hack23.cia.service.api.action.application} - Application-wide functionality</li>
 *   <li>{@code com.hack23.cia.service.api.action.common} - Shared action definitions</li>
 *   <li>{@code com.hack23.cia.service.api.action.user} - User-specific operations</li>
 *   <li>{@code com.hack23.cia.service.api.action.kpi} - Key Performance Indicators</li>
 * </ul>
 *
 * <p>Functional Areas:</p>
 * <ul>
 *   <li>Administrative Operations
 *     <ul>
 *       <li>System configuration and management</li>
 *       <li>User administration</li>
 *       <li>Security controls</li>
 *     </ul>
 *   </li>
 *   <li>Data Analysis
 *     <ul>
 *       <li>Political performance tracking</li>
 *       <li>Financial metrics analysis</li>
 *       <li>Institutional monitoring</li>
 *     </ul>
 *   </li>
 *   <li>Key Performance Indicators
 *     <ul>
 *       <li>Political efficiency metrics</li>
 *       <li>Financial performance indicators</li>
 *       <li>Trend analysis</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>The API utilizes validation constraints and supports operations across various
 * data models including Swedish Parliament (Riksdagen), Election Authority (Val),
 * and internal application data.</p>
 *
 * @see com.hack23.cia.service.data.api
 * @see com.hack23.cia.model.internal.application.user.impl
 */
open module com.hack23.cia.service.api {

	exports com.hack23.cia.service.api;
	exports com.hack23.cia.service.api.action.admin;
	exports com.hack23.cia.service.api.action.application;
	exports com.hack23.cia.service.api.action.common;
	exports com.hack23.cia.service.api.action.user;
	exports com.hack23.cia.service.api.action.kpi;

	requires java.validation;
	requires org.apache.commons.lang3;
	requires org.apache.commons.collections4;

	requires com.hack23.cia.service.data.api;

	requires com.hack23.cia.model.internal.application.user.impl;

	requires com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires com.hack23.cia.model.external.worldbank.topic.impl;
	requires com.hack23.cia.model.external.worldbank.indicators.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.worldbank.data.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.val.partier.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;

}