/*
 * Copyright 2010-2024 James Pether Sörling
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
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
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
import org.javers.spring.jpa.TransactionalJpaJaversBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * The Class AuditableAspectConfiguration.
 */
@Configuration
public class AuditableAspectConfiguration {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Instantiates a new auditable aspect configuration.
	 */
	public AuditableAspectConfiguration() {
		super();
	}

	/**
	 * Gets the javers.
	 *
	 * @param txManager the tx manager
	 * @return the javers
	 */
	@Bean
	public Javers getJavers(final PlatformTransactionManager txManager) {
		final JaversSqlRepository sqlRepository = SqlRepositoryBuilder.sqlRepository()
				.withConnectionProvider(new ConnectionProvider() {

					@Override
					public Connection getConnection() {
						final SharedSessionContractImplementor session = entityManager.unwrap(SharedSessionContractImplementor.class);
						return session.connection();
					}
				}).withDialect(DialectName.POSTGRES).build();

		return TransactionalJpaJaversBuilder.javers().withTxManager(txManager)
				.withObjectAccessHook(new HibernateUnproxyObjectAccessHook()).registerJaversRepository(sqlRepository)
				.withMappingStyle(MappingStyle.BEAN).build();
	}

	/**
	 * Javers auditable aspect.
	 *
	 * @param javers                   the javers
	 * @param authorProvider           the author provider
	 * @param commitPropertiesProvider the commit properties provider
	 * @return the javers auditable aspect
	 */
	@Bean
	public JaversAuditableAspect javersAuditableAspect(final Javers javers, final AuthorProvider authorProvider,
			final CommitPropertiesProvider commitPropertiesProvider) {
		return new JaversAuditableAspect(javers, authorProvider, commitPropertiesProvider);
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
		return new CommitPropertiesProvider() {
            @Override
            public Map<String, String>  provideForCommittedObject(final Object domainObject) {
                final Map<String, String> props = new HashMap<>();
                props.put("key", "ok");
                return props;
            }
        };
	}
}
