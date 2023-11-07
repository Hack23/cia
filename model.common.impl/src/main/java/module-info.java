open module com.hack23.cia.model.common.impl {
	requires com.hack23.model.common.api;
	requires java.xml.bind;
	requires org.slf4j;
	requires org.apache.commons.lang3;

	exports com.hack23.cia.model.common.impl;
	exports com.hack23.cia.model.common.impl.xml;
	
}