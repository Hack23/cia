module com.hack23.cia.service.external.worldbank {
	exports com.hack23.cia.service.external.worldbank.api;
	exports com.hack23.cia.service.external.worldbank.impl;

	requires java.xml.bind;
	requires spring.beans;

	requires com.hack23.cia.service.external.common;

	requires model.external.worldbank.topic.impl;
	requires model.external.worldbank.indicators.impl;
	requires model.external.worldbank.data.impl;
	requires model.external.worldbank.countries.impl;

}