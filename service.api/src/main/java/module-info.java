open module com.hack23.cia.service.api {

	exports com.hack23.cia.service.api;
	exports com.hack23.cia.service.api.action.admin;
	exports com.hack23.cia.service.api.action.application;
	exports com.hack23.cia.service.api.action.common;
	exports com.hack23.cia.service.api.action.user;
	exports com.hack23.cia.service.api.action.kpi;

	requires java.validation;
	requires org.apache.commons.lang3;
	requires org.apache.commons.collections4;

	requires com.hack23.cia.service.data.api;

	requires com.hack23.cia.model.internal.application.user.impl;

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