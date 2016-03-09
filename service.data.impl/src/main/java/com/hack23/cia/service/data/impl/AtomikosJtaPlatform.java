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

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform;

import com.atomikos.icatch.jta.J2eeTransactionManager;
import com.atomikos.icatch.jta.J2eeUserTransaction;

/**
 * The Class AtomikosJtaPlatform.
 */
public final class AtomikosJtaPlatform extends AbstractJtaPlatform implements JtaPlatform {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1L;

	/** {@inheritDoc}
	 * @see org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform#locateTransactionManager()
	 */
	@Override
	protected TransactionManager locateTransactionManager() {
		return new J2eeTransactionManager();
	}

	/** {@inheritDoc}
	 * @see org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform#locateUserTransaction()
	 */
	@Override
	protected UserTransaction locateUserTransaction() {
		return new J2eeUserTransaction();
	}

}