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
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatformException;

/**
 * The Class NarayanaJtaPlatform.
 */
public class NarayanaJtaPlatform extends AbstractJtaPlatform {
	
	/** The Constant JBOSS_TM_CLASS_NAME. */
	public static final String JBOSS_TM_CLASS_NAME = "com.arjuna.ats.jta.TransactionManager";
	
	/** The Constant JBOSS_UT_CLASS_NAME. */
	public static final String JBOSS_UT_CLASS_NAME = "com.arjuna.ats.jta.UserTransaction";

	/* (non-Javadoc)
	 * @see org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform#locateTransactionManager()
	 */
	@Override
	protected TransactionManager locateTransactionManager() {
		try {
			final Class jbossTmClass = serviceRegistry()
					.getService( ClassLoaderService.class )
					.classForName( JBOSS_TM_CLASS_NAME );
			return (TransactionManager) jbossTmClass.getMethod( "transactionManager" ).invoke( null );
		}
		catch ( Exception e ) {
			throw new JtaPlatformException( "Could not obtain JBoss Transactions transaction manager instance", e );
		}
	}

	/* (non-Javadoc)
	 * @see org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform#locateUserTransaction()
	 */
	@Override
	protected UserTransaction locateUserTransaction() {
		try {
			final Class jbossUtClass = serviceRegistry()
					.getService( ClassLoaderService.class )
					.classForName( JBOSS_UT_CLASS_NAME );
			return (UserTransaction) jbossUtClass.getMethod( "userTransaction" ).invoke( null );
		}
		catch ( Exception e ) {
			throw new JtaPlatformException( "Could not obtain JBoss Transactions user transaction instance", e );
		}
	}
}