module com.hack23.cia.service.external.riksdagen {
	exports com.hack23.cia.service.external.riksdagen.api;
	exports com.hack23.cia.service.external.riksdagen.impl;

	requires java.xml.bind;
	requires slf4j.api;
	requires spring.context;
	requires spring.beans;


	requires com.hack23.cia.service.external.common;

	requires model.external.riksdagen.voteringlista.impl;
	requires model.external.riksdagen.documentcontent.impl;
	requires model.external.riksdagen.person.impl;
	requires model.external.riksdagen.dokumentstatus.impl;
	requires model.external.riksdagen.dokumentlista.impl;
	requires model.external.riksdagen.utskottsforslag.impl;
	requires model.external.riksdagen.personlista.impl;
	requires model.external.riksdagen.votering.impl;

}