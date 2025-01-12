/**
 * CIA (Citizen Intelligence Agency) Riksdagen Votering Model Module.
 *
 * <p>This module defines data structures for individual voting records (votering)
 * in the Swedish Parliament, allowing analysis of how members voted.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>JPA entities for votering data</li>
 *   <li>Integration with the CIA model framework</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate-based persistence</li>
 *   <li>JAXB for XML handling</li>
 * </ul>
 */
open module com.hack23.cia.model.external.riksdagen.votering.impl {
	exports com.hack23.cia.model.external.riksdagen.votering.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}