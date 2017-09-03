module com.hack23.cia.service.external.common {
	exports com.hack23.cia.service.external.common.api;
	exports com.hack23.cia.service.external.common.impl;
	requires slf4j.api;
	requires java.xml.bind;
	requires jdom2;
	requires fluent.hc;
	requires spring.oxm;
	requires spring.context;
	requires httpclient;
}