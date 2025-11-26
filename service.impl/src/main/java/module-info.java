/**
 * CIA (Citizen Intelligence Agency) Service Implementation Module.
 *
 * This module provides the core service implementation for the CIA application.
 * It integrates various external services and data models, including:
 * <ul>
 *   <li>Data processing and business logic implementation</li>
 *   <li>Integration with Riksdagen (Swedish Parliament) data</li>
 *   <li>Integration with Val (Election) data</li>
 *   <li>Integration with World Bank data</li>
 *   <li>Security and authentication services</li>
 * </ul>
 *
 * The module exports service implementation packages while consuming various
 * external dependencies including Spring Framework, security components,
 * and data access modules.
 *
 * @provides com.hack23.cia.service.api Service implementations for CIA
 * @uses com.hack23.cia.service.component.agent.api Agent API services
 * @uses com.hack23.cia.service.data.api Data access services
 */
open module com.hack23.cia.service.impl {


	exports com.hack23.cia.service.impl;
	exports com.hack23.cia.service.impl.rules;
	exports com.hack23.cia.service.impl.performance;

	requires java.validation;
	requires java.xml.bind;
	requires java.annotation;
	requires org.slf4j;
	requires transitive spring.context;
	requires transitive spring.beans;
	requires transitive spring.tx;
	requires transitive spring.jms;
	requires transitive spring.messaging;
	requires transitive spring.context.support;
	requires quartz;
	requires com.google.common;
	requires org.jsoup;

	requires googleauth;

	requires transitive spring.security.core;
	requires transitive spring.security.crypto;
	requires transitive spring.core;
	requires org.apache.commons.lang3;
	requires transitive org.hibernate.orm.jpamodelgen;

	requires passay;

	requires org.joda.time;

	requires jms;
	requires java.mail;
	requires org.kie.api;
	requires org.drools.core;
	requires org.bouncycastle.pkix;
	requires org.bouncycastle.provider;

	requires transitive com.hack23.cia.service.api;

	requires transitive com.hack23.cia.service.component.agent.api;
	requires com.hack23.cia.service.component.agent.impl;

	requires transitive com.hack23.cia.service.external.common;
    requires transitive com.hack23.cia.service.data.api;
	requires com.hack23.cia.service.external.riksdagen;
	requires com.hack23.cia.service.external.val;
	requires com.hack23.cia.service.external.worldbank;

	requires com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires com.hack23.cia.model.external.worldbank.topic.impl;
	requires com.hack23.cia.model.external.worldbank.indicators.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.worldbank.data.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.val.partier.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;
}
