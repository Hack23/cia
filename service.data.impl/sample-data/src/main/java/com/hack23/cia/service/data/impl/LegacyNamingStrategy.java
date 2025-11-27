/*
 * Copyright 2010-2025 James Pether Sörling
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

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;


/**
 * The Class LegacyNamingStrategy.
 */
public final class LegacyNamingStrategy implements PhysicalNamingStrategy {

    private static final String REG_EXPR = "([a-z])([A-Z])";
    private static final String REPLACEMENT_PATTERN = "$1_$2";


    /**
	 * Instantiates a new legacy naming strategy.
	 */
    public LegacyNamingStrategy() {
		super();
	}

    /**
	 * Convert.
	 *
	 * @param identifier
	 *            the identifier
	 * @return the identifier
	 */
    private static Identifier convert(final Identifier identifier) {
    	if (identifier == null || StringUtils.isBlank(identifier.getText())) {
            return identifier;
        } else {
        	return Identifier.toIdentifier(identifier.getText().replaceAll(REG_EXPR, REPLACEMENT_PATTERN).toLowerCase(Locale.ENGLISH));
        }
    }

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

}