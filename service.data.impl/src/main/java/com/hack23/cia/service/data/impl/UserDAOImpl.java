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
 *	$Id: UserDAOImpl.java 6046 2015-05-06 20:42:53Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.data.impl/src/main/java/com/hack23/cia/service/data/impl/UserDAOImpl.java $
 */
package com.hack23.cia.service.data.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.data.api.UserDAO;

/**
 * The Class UserDAOImpl.
 */
@Repository(value="UserDAO")
public final class UserDAOImpl extends AbstractGenericDAOImpl<UserAccount, Long> implements UserDAO {


	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new user dao impl.
	 */
	public UserDAOImpl() {
		super(UserAccount.class);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.impl.AbstractGenericDAOImpl#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {
		return 0L;
	}

}
