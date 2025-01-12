/**
 * CIA (Citizen Intelligence Agency) Val Riksdagsvalkrets Model Module.
 *
 * <p>This module provides data structures for Swedish parliamentary constituencies
 * (riksdagsvalkrets), enabling identification and mapping of these regions.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Riksdagsvalkrets entities and JPA mappings</li>
 *   <li>Integration with the CIA model framework</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate ORM for data persistence</li>
 *   <li>JAXB for XML-based data binding</li>
 * </ul>
 */
open module com.hack23.cia.model.external.val.riksdagsvalkrets.impl {
	exports com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}