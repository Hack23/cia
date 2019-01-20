module com.hack23.cia.service.external.common {
	exports com.hack23.cia.service.external.common.api;
	exports com.hack23.cia.service.external.common.impl;
	
	opens com.hack23.cia.service.external.common.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.external.common.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
		
	requires org.slf4j;
	requires java.xml.bind;
	requires jdom2;
	requires spring.oxm;
	requires spring.context;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpclient.fluent;
}