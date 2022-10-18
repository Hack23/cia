open module com.hack23.cia.service.external.worldbank {
	exports com.hack23.cia.service.external.worldbank.api;
	exports com.hack23.cia.service.external.worldbank.impl;
	
	requires java.xml.bind;
	requires spring.beans;
	requires org.slf4j;
	requires org.apache.commons.codec;
	requires org.apache.commons.csv;


	requires com.hack23.cia.service.external.common;

	requires com.hack23.cia.model.external.worldbank.topic.impl;
	requires com.hack23.cia.model.external.worldbank.indicators.impl;
	requires com.hack23.cia.model.external.worldbank.data.impl;
	requires com.hack23.cia.model.external.worldbank.countries.impl;

}