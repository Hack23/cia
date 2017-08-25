module com.hack23.cia.service.external.worldbank {
	exports com.hack23.cia.service.external.worldbank;
	requires com.hack23.cia.service.external.common;	
    requires com.hack23.cia.model.external.worldbank.countries.impl;
    requires com.hack23.cia.model.external.worldbank.data.impl;
    requires com.hack23.cia.model.external.worldbank.indicators.impl;
    requires com.hack23.cia.model.external.worldbank.topic.impl;
}