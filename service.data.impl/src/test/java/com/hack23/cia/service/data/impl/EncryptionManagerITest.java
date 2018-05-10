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

import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue;
import com.hack23.cia.service.data.api.EncryptedValueDAO;
import com.hack23.cia.service.data.api.EncryptionManager;

/**
 * The Class EncryptionManagerITest.
 */
@Transactional
public class EncryptionManagerITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The encryption manager. */
	@Autowired
	private EncryptionManager encryptionManager;

	/** The data source. */
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private EncryptedValueDAO encryptedValueDAO;


	/**
	 * Refresh views test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void setEncryptionKeyTest() {
		String encryptionKey = UUID.randomUUID().toString();
		encryptionManager.setEncryptionKey(encryptionKey);
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		SqlRowSet srs = jdbcTemplate.queryForRowSet("select current_setting from current_setting('cia.encrypt.key')");
		srs.next();		
		assertEquals(encryptionKey, srs.getString("current_setting"));
	}

	@Test
	public void EncryptionValueSuccessTest() {
		String encryptionKey = UUID.randomUUID().toString();
		encryptionManager.setEncryptionKey(encryptionKey);
		EncryptedValue entity = new EncryptedValue();
		entity.setStorage("somesecret");
		encryptedValueDAO.persist(entity);
		
		EncryptedValue load = encryptedValueDAO.load(entity.getId());
		assertEquals(entity.getStorage(), load.getStorage());
	}


	@Test
	@Ignore
	public void setEncryptionKeyFailureTest() throws InterruptedException {
		String encryptionKey = UUID.randomUUID().toString();
		encryptionManager.setEncryptionKey("");
		
		EncryptedValue entity = new EncryptedValue();
		entity.setStorage("somesecret");
		encryptedValueDAO.persist(entity);
						
		encryptionManager.setEncryptionKey("wrong "+ encryptionKey);
		
		EncryptedValue load = encryptedValueDAO.load(entity.getId());
		assertNotEquals(entity.getStorage(), load.getStorage());
	}

}
