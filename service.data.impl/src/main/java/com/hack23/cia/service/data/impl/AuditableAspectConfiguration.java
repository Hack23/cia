/*
 * Copyright 2010 James Pether Sörling
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.data.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.javers.core.Javers;
import org.javers.core.MappingStyle;
import org.javers.hibernate.integration.HibernateUnproxyObjectAccessHook;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.CommitPropertiesProvider;
import org.javers.spring.auditable.aspect.JaversAuditableAspect;
import org.javers.spring.jpa.TransactionalJaversBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.google.common.collect.ImmutableMap;

/**
 * The Class AuditableAspectConfiguration.
 */
@Configuration
public class AuditableAspectConfiguration {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The tx manager. */
	@Autowired
	private JtaTransactionManager txManager;

	/**
	 * Gets the javers.
	 *
	 * @return the javers
	 */
	@Bean
	public Javers getJavers() {
		final JaversSqlRepository sqlRepository = SqlRepositoryBuilder.sqlRepository()
				.withConnectionProvider(new ConnectionProvider() {

					@Override
					public Connection getConnection() throws SQLException {
						final SessionImpl session = (SessionImpl) entityManager.unwrap(Session.class);

						return session.connection();
					}
				}).withDialect(DialectName.POSTGRES).build();

		return TransactionalJaversBuilder.javers().withTxManager(txManager)
				.withObjectAccessHook(new HibernateUnproxyObjectAccessHook()).registerJaversRepository(sqlRepository)
				.withMappingStyle(MappingStyle.BEAN).build();
	}

	/**
	 * Javers auditable aspect.
	 *
	 * @return the javers auditable aspect
	 */
	@Bean
	public JaversAuditableAspect javersAuditableAspect() {
		return new JaversAuditableAspect(getJavers(), authorProvider(), commitPropertiesProvider());
	}

	/**
	 * Author provider.
	 *
	 * @return the author provider
	 */
	@Bean
	public AuthorProvider authorProvider() {
		return () -> {
			final SecurityContext context = SecurityContextHolder.getContext();
			if (context != null && context.getAuthentication() != null) {
				return context.getAuthentication().getPrincipal().toString();			
			} else {
				return "system";
			}			
		};
	}
	
	
	/**
	 * Commit properties provider.
	 *
	 * @return the commit properties provider
	 */
	@Bean
	public CommitPropertiesProvider commitPropertiesProvider() {
		return () -> ImmutableMap.of("key", "ok");
	}
}
