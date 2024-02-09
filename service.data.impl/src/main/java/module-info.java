open module com.hack23.cia.service.data.impl {
	exports com.hack23.cia.service.data.impl;
	exports com.hack23.cia.service.data.impl.util;

	requires java.xml.bind;
	requires jakarta.activation;
	requires java.sql;
	requires java.desktop;
	requires java.annotation;
	requires commons.beanutils;
	requires org.slf4j;
	requires spring.context;
	requires spring.context.support;
	requires com.fasterxml.jackson.core;
	
	requires spring.beans;
	requires spring.jdbc;
	requires spring.tx;
	requires org.postgresql.jdbc;
	requires java.naming;
	
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires java.transaction;
	requires ehcache;
	requires cache.api;

	requires spring.security.core;

	requires org.hibernate.search.engine;
	requires org.hibernate.search.backend.lucene;
	requires org.hibernate.search.mapper.pojo;
	requires org.hibernate.search.mapper.orm;	
	
	requires com.fasterxml.jackson.databind;	
	requires aws.secretsmanager.caching.java;
	requires aws.secretsmanager.jdbc;
	requires aws.java.sdk.secretsmanager;
	requires aws.java.sdk.core;
	
	
	
	
	requires org.apache.commons.lang3;
	requires lucene.analyzers.common;

	requires javers.spring;
	requires javers.spring.jpa;
	requires javers.persistence.sql;
	requires javers.core;


	requires com.google.common;

    requires com.hack23.cia.service.data.api;
}