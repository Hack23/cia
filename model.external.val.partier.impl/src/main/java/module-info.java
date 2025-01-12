/**
 * CIA (Citizen Intelligence Agency) Val Partier Model Module.
 *
 * <p>This module models Swedish political parties' data, covering official
 * registrations and other party-related information.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Party entity definitions</li>
 *   <li>Integration with the CIA model framework</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate-based persistence</li>
 *   <li>JAXB for data import</li>
 * </ul>
 */
open module com.hack23.cia.model.external.val.partier.impl {
	exports com.hack23.cia.model.external.val.partier.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}