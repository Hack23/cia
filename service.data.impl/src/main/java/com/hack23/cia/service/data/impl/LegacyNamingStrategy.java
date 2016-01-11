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



import org.apache.commons.lang.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * The Class LegacyNamingStrategy.
 */
public class LegacyNamingStrategy implements PhysicalNamingStrategy {

    /** (non-Javadoc)
     * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalCatalogName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
     */
    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    /** (non-Javadoc)
     * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalColumnName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
     */
    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    /** (non-Javadoc)
     * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalSchemaName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
     */
    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    /** (non-Javadoc)
     * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalSequenceName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
     */
    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    /** (non-Javadoc)
     * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalTableName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
     */
    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convert(identifier);
    }

    /**
	 * Convert.
	 *
	 * @param identifier
	 *            the identifier
	 * @return the identifier
	 */
    private Identifier convert(final Identifier identifier) {
        if (identifier == null || StringUtils.isBlank(identifier.getText())) {
            return identifier;
        }

        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
        return Identifier.toIdentifier(newName);
    }
}