module com.hack23.cia.service.component.agent.impl {


	exports com.hack23.cia.service.component.agent.impl.command;
	exports com.hack23.cia.service.component.agent.impl.common;
	exports com.hack23.cia.service.component.agent.impl.riksdagen;
	exports com.hack23.cia.service.component.agent.impl.val;
	exports com.hack23.cia.service.component.agent.impl.worldbank;

	requires java.xml.bind;
	requires java.xml.ws.annotation;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;
	requires spring.tx;
	requires spring.jms;
	requires org.apache.commons.lang3;


	requires joda.time;

	requires jms;

	requires com.hack23.cia.service.external.common;
    requires com.hack23.cia.service.data.api;
	requires com.hack23.cia.service.component.agent.api;
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