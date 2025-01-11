/**
 * CIA (Citizen Intelligence Agency) Data Service Implementation Module.
 * 
 * <p>This module provides the core data persistence and management implementation
 * for the CIA application. It handles database operations, caching, search functionality,
 * and audit tracking through various technology integrations.</p>
 * 
 * <p>Key features include:</p>
 * <ul>
 *   <li>Database persistence using Hibernate ORM and JPA</li>
 *   <li>Full-text search capabilities via Hibernate Search and Lucene</li>
 *   <li>Data auditing and versioning through Javers</li>
 *   <li>AWS Secrets Manager integration for secure credential management</li>
 *   <li>Caching support with EhCache</li>
 *   <li>Transaction management with Spring</li>
 * </ul>
 * 
 * <p>The module exports two packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.data.impl} - Main implementation classes</li>
 *   <li>{@code com.hack23.cia.service.data.impl.util} - Utility classes for data operations</li>
 * </ul>
 * 
 * <p>This module is part of the CIA (Citizen Intelligence Agency) project, which focuses on
 * monitoring political figures and institutions while providing insights into financial
 * performance, risk metrics, and political trends.</p>
 * 
 * @provides com.hack23.cia.service.data.api Implementation of CIA data service API
 * @see com.hack23.cia.service.data.api
 */
open module com.hack23.cia.service.data.impl {
	exports com.hack23.cia.service.data.impl;
	exports com.hack23.cia.service.data.impl.util;

	requires java.xml.bind;
	requires jakarta.activation;
	requires transitive java.sql;
	requires java.desktop;
	requires java.annotation;
	requires org.slf4j;
	requires spring.context;
	requires spring.context.support;
	requires com.fasterxml.jackson.core;
	
	requires spring.beans;
	requires spring.jdbc;
	requires spring.tx;
	requires spring.core;	
	requires org.postgresql.jdbc;
	requires transitive java.naming;
	
	requires transitive java.persistence;  // Changed from requires java.persistence
	requires transitive org.hibernate.orm.core;
	requires transitive java.transaction;
	requires ehcache;
	requires cache.api;

	requires spring.security.core;

	requires org.hibernate.search.engine;
	requires org.hibernate.search.backend.lucene;
	requires transitive org.hibernate.search.mapper.pojo; // Make this transitive
	requires org.hibernate.search.mapper.orm;	
	
	requires com.fasterxml.jackson.databind;	
	requires transitive aws.secretsmanager.caching.java;
	requires transitive aws.secretsmanager.jdbc;
	requires transitive aws.java.sdk.secretsmanager;
	requires transitive aws.java.sdk.core;
	
	requires org.apache.commons.lang3;
	requires lucene.analyzers.common;

	requires transitive javers.spring;
	requires transitive javers.spring.jpa;
	requires transitive javers.persistence.sql;
	requires transitive javers.core;


	requires com.google.common;

    requires com.hack23.cia.service.data.api;
}
