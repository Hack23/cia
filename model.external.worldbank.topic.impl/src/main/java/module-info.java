/**
 * CIA (Citizen Intelligence Agency) World Bank Topic Data Model Module.
 *
 * <p>This module defines the data structures for handling World Bank topic
 * classifications, including metadata and domain objects.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Topic-based categorization of World Bank data</li>
 *   <li>JPA persistence for topic entities</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate ORM for data mapping</li>
 *   <li>Java XML Binding for XML-based data</li>
 * </ul>
 */
open module com.hack23.cia.model.external.worldbank.topic.impl {
    exports com.hack23.cia.model.external.worldbank.topic.impl;

	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;
    requires java.xml.bind;
    requires java.persistence;
    requires org.hibernate.orm.jpamodelgen;
    requires org.slf4j;
    requires org.apache.commons.lang3;
    requires jaxb2.basics.runtime;
}
