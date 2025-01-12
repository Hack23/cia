/**
 * CIA (Citizen Intelligence Agency) Riksdagen Dokumentstatus Model Module.
 *
 * <p>This module includes data structures for Swedish Parliament document statuses,
 * detailing the publication and progression of parliamentary documents.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Document status tracking</li>
 *   <li>JPA mappings for parliamentary document entities</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate for object-relational mapping</li>
 *   <li>XML-based data binding</li>
 * </ul>
 */
open module com.hack23.cia.model.external.riksdagen.dokumentstatus.impl {
	exports com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;


	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}