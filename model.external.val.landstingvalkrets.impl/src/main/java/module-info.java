/**
 * CIA (Citizen Intelligence Agency) Val Landstingvalkrets Model Module.
 *
 * <p>This module contains data structures and JPA entities for representing
 * Swedish county council electoral districts.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Mapping of landstingvalkrets (county council regions)</li>
 *   <li>Integration with the CIA model framework</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate-based entity persistence</li>
 *   <li>Java XML Binding for data import</li>
 * </ul>
 */
open module com.hack23.cia.model.external.val.landstingvalkrets.impl {
	exports com.hack23.cia.model.external.val.landstingvalkrets.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}