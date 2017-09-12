module com.hack23.cia.service.impl {


	exports com.hack23.cia.service.impl;


	requires java.xml.bind;
	requires java.xml.ws.annotation;
	requires slf4j.api;
	requires spring.context;
	requires spring.beans;
	requires spring.tx;
	requires spring.jms;
	requires spring.messaging;
	requires spring.context.support;
	requires quartz;
	requires commons.lang;

	requires google.api.client;
	requires google.oauth.client;
	requires google.http.client.jackson2;
	requires google.http.client;
	requires googleauth;
	requires google.api.services.translate.v2.rev51;

	requires spring.security.core;
	requires org.apache.commons.lang3;

	requires weka.dev;
	requires passay;

	requires joda.time;

	requires jms;
	requires javax.mail;

	requires com.hack23.cia.service.api;

	requires com.hack23.cia.service.component.agent.api;
	requires com.hack23.cia.service.component.agent.impl;

	requires com.hack23.cia.service.external.common;
    requires com.hack23.cia.service.data.api;
	requires com.hack23.cia.service.external.riksdagen;
	requires com.hack23.cia.service.external.val;
	requires com.hack23.cia.service.external.worldbank;

	requires model.external.val.riksdagsvalkrets.impl;
	requires model.external.riksdagen.voteringlista.impl;
	requires model.external.val.kommunvalkrets.impl;
	requires model.external.worldbank.topic.impl;
	requires model.external.worldbank.indicators.impl;
	requires model.external.riksdagen.documentcontent.impl;
	requires model.external.worldbank.data.impl;
	requires model.external.riksdagen.person.impl;
	requires model.external.riksdagen.dokumentstatus.impl;
	requires model.external.riksdagen.dokumentlista.impl;
	requires model.external.riksdagen.utskottsforslag.impl;
	requires model.external.val.partier.impl;
	requires model.external.riksdagen.personlista.impl;
	requires model.external.val.landstingvalkrets.impl;
	requires model.external.riksdagen.votering.impl;
}