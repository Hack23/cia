module com.hack23.cia.service.api {

	exports com.hack23.cia.service.api;
	exports com.hack23.cia.service.api.action.admin;
	exports com.hack23.cia.service.api.action.application;
	exports com.hack23.cia.service.api.action.common;
	exports com.hack23.cia.service.api.action.user;


	requires org.apache.commons.lang3;

	requires com.hack23.cia.service.data.api;

	requires model.internal.application.user.impl;

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