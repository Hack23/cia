module com.hack23.cia.service.data.impl {
	exports com.hack23.cia.service.data.impl;

	requires java.xml.bind;
	requires java.sql;
	requires java.desktop;
	requires java.xml.ws.annotation;
	requires commons.beanutils;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;
	requires spring.jdbc;
	requires spring.tx;
	requires hibernate.jpa;
	requires hibernate.core;

	requires hibernate.search.orm;
	requires hibernate.search.engine;

	requires liquibase.core;
	requires commons.lang;
	requires org.apache.commons.lang3;
	requires lucene.core;
	requires lucene.analyzers.common;


    requires com.hack23.cia.service.data.api;
}