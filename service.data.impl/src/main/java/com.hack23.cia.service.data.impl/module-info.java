module com.hack23.cia.service.data.impl {
	exports com.hack23.cia.service.data.impl;

	requires java.xml.bind;
	requires java.sql;
	requires java.desktop;
	requires java.xml.ws.annotation;
	requires commons.beanutils;
	requires org.slf4j;
	requires spring.context;
	requires spring.context.support;
	
	requires spring.beans;
	requires spring.jdbc;
	requires spring.tx;
	
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires java.transaction;
	requires ehcache;
	requires cache.api;

	requires spring.security.core;

	requires org.hibernate.search.orm;
	requires org.hibernate.search.engine;

	requires liquibase.core;
	requires commons.lang;
	requires org.apache.commons.lang3;
	requires lucene.core;
	requires lucene.analyzers.common;
	requires javers.spring;
	requires javers.spring.jpa;
	requires javers.persistence.sql;
	requires javers.core;


	requires com.google.common;

    requires com.hack23.cia.service.data.api;
}