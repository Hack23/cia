open module com.hack23.cia.service.data.api {
	exports com.hack23.cia.service.data.api;
	
	requires java.persistence;

	requires transitive com.hack23.cia.model.internal.application.user.impl;

	requires transitive com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires transitive com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires transitive com.hack23.cia.model.external.worldbank.topic.impl;
	requires transitive com.hack23.cia.model.external.worldbank.indicators.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires transitive com.hack23.cia.model.external.worldbank.data.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.person.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires transitive com.hack23.cia.model.external.val.partier.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires transitive com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.votering.impl;
	requires transitive com.hack23.cia.model.external.worldbank.countries.impl;
}