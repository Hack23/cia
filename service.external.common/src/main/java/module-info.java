open module com.hack23.cia.service.external.common {
	exports com.hack23.cia.service.external.common.api;
	exports com.hack23.cia.service.external.common.impl;
			
	requires org.slf4j;
	requires java.xml.bind;
	requires jakarta.activation;
	requires org.jdom2;
	requires spring.oxm;
	requires spring.context;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpclient.fluent;
}