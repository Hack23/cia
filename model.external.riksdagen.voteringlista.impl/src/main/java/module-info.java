/**
 * CIA (Citizen Intelligence Agency) Riksdagen Voteringlista Model Module.
 *
 * <p>This module models the voting list data from the Swedish Parliament,
 * covering relevant entities and structures for parliamentary voting records.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Representation of detailed vote lists</li>
 *   <li>JPA mappings for voteringlista entities</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate-based persistence</li>
 *   <li>XML parsing with JAXB for data exchange</li>
 * </ul>
 */
open module com.hack23.cia.model.external.riksdagen.voteringlista.impl {
	exports com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}