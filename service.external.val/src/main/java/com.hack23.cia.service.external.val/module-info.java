module com.hack23.cia.service.external.val {
	exports com.hack23.cia.service.external.val.api;
	exports com.hack23.cia.service.external.val.impl;

	requires java.xml.bind;
	requires spring.beans;
	requires org.slf4j;
	
	requires com.hack23.cia.service.external.common;

	requires model.external.val.riksdagsvalkrets.impl;
	requires model.external.val.kommunvalkrets.impl;
	requires model.external.val.partier.impl;
	requires model.external.val.landstingvalkrets.impl;
}