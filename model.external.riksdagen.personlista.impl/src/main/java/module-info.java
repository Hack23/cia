open module com.hack23.cia.model.external.riksdagen.personlista.impl {
	exports com.hack23.cia.model.external.riksdagen.personlista.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;
	
	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}