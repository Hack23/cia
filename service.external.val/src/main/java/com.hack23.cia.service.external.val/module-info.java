module com.hack23.cia.service.external.val {
	exports com.hack23.cia.service.external.val.api;
	exports com.hack23.cia.service.external.val.impl;
	
	opens com.hack23.cia.service.external.val.api to spring.aop, spring.core, spring.beans, spring.context;
	opens com.hack23.cia.service.external.val.impl to spring.aop, spring.core, spring.beans, spring.context;

	requires java.xml.bind;
	requires spring.beans;
	requires org.slf4j;
	
	requires com.hack23.cia.service.external.common;

	requires com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires com.hack23.cia.model.external.val.partier.impl;
	requires com.hack23.cia.model.external.val.landstingvalkrets.impl;
}