/**
 * CIA (Citizen Intelligence Agency) Data Service API Module.
 *
 * <p>This module defines the data service API for the CIA application, providing
 * interfaces for data persistence and retrieval operations. It serves as the primary
 * contract for database operations across the application.</p>
 *
 * <p>The module exports a single package:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.data.api} - Core data service interfaces and definitions</li>
 * </ul>
 *
 * <p>Data Model Integration:</p>
 * <ul>
 *   <li>Internal Models:
 *     <ul>
 *       <li>User management and authentication</li>
 *     </ul>
 *   </li>
 *   <li>Swedish Parliament (Riksdagen) Models:
 *     <ul>
 *       <li>Voting records and lists</li>
 *       <li>Document content and status</li>
 *       <li>Person and member information</li>
 *       <li>Committee proposals</li>
 *     </ul>
 *   </li>
 *   <li>Election Authority (Val) Models:
 *     <ul>
 *       <li>Electoral districts (Riksdag, Kommune, Landsting)</li>
 *       <li>Political party information</li>
 *     </ul>
 *   </li>
 *   <li>World Bank Models:
 *     <ul>
 *       <li>Country information</li>
 *       <li>Topics and indicators</li>
 *       <li>Economic data</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>All model dependencies are marked as transitive to ensure availability
 * throughout the application. The API integrates with JPA for persistence
 * operations.</p>
 *
 * @see javax.persistence
 * @see com.hack23.cia.model.internal.application.user.impl
 */
open module com.hack23.cia.service.data.api {
	exports com.hack23.cia.service.data.api;
	
	requires java.persistence;

	requires transitive com.hack23.cia.model.internal.application.user.impl;

	requires transitive com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires transitive com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires transitive com.hack23.cia.model.external.worldbank.topic.impl;
	requires transitive com.hack23.cia.model.external.worldbank.indicators.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires transitive com.hack23.cia.model.external.worldbank.data.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.person.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires transitive com.hack23.cia.model.external.val.partier.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires transitive com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.votering.impl;
	requires transitive com.hack23.cia.model.external.worldbank.countries.impl;
}