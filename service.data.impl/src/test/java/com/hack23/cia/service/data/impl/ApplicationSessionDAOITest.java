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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.data.api.ApplicationSessionDAO;

/**
 * The Class ApplicationSessionDAOITest.
 */
@Transactional
public class ApplicationSessionDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The application session DAO. */
	@Autowired
	private ApplicationSessionDAO applicationSessionDAO;

	/**
	 * Load test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void loadTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final List<ApplicationSession> all = applicationSessionDAO.getAll();
		final ApplicationSession firstValue = all.iterator().next();
		final ApplicationSession load = applicationSessionDAO.load(firstValue.getHjid());
		assertEquals(firstValue,load);
	}

	/**
	 * Persist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void persistTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();

		final ApplicationSession loaddedApplicationSession = applicationSessionDAO.load(applicationSession.getHjid());
		assertNotNull(loaddedApplicationSession);
		assertEquals(applicationSession,loaddedApplicationSession);
	}

	/**
	 * Merge test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void mergeTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();

		final ApplicationSession mergedApplicationSession = applicationSessionDAO.merge(applicationSession);
		mergedApplicationSession.setOperatingSystem("Merged");
		applicationSessionDAO.merge(mergedApplicationSession);

		final ApplicationSession loaddedApplicationSession = applicationSessionDAO.load(applicationSession.getHjid());
		assertNotNull(loaddedApplicationSession);
		assertEquals(applicationSession,loaddedApplicationSession);
	}

	private ApplicationSession createApplicationSession() {
		final ApplicationSession applicationSession = new ApplicationSession();
		applicationSession.withCreatedDate(new Date()).withIpInformation(UUID.randomUUID().toString()).withLocale("Locale").withSessionId(UUID.randomUUID().toString());
		applicationSessionDAO.persist(applicationSession);
		return applicationSession;
	}

	/**
	 * Delete test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void deleteTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final List<ApplicationSession> all = applicationSessionDAO.getAll();
		final ApplicationSession firstValue = all.iterator().next();
		final Long hjid = firstValue.getHjid();
		applicationSessionDAO.delete(firstValue);
		final ApplicationSession loadDeleted = applicationSessionDAO.load(hjid);
		assertNull(loadDeleted);
	}

	/**
	 * Find first by property test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void findFirstByPropertyTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final List<ApplicationSession> all = applicationSessionDAO.getAll();
		final ApplicationSession findFirstByProperty = applicationSessionDAO.findFirstByProperty(ApplicationSession_.ipInformation, all.iterator().next().getIpInformation());
		assertNotNull(findFirstByProperty);
	}

	/**
	 * Find list by property test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void findListByPropertyTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final List<ApplicationSession> all = applicationSessionDAO.getAll();

		final List<ApplicationSession> findListByProperty = applicationSessionDAO.findListByProperty(ApplicationSession_.ipInformation, all.iterator().next().getIpInformation());
		assertNotNull(findListByProperty);
		assertFalse(findListByProperty.isEmpty());
	}

	/**
	 * Find list by property in list test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void findListByPropertyInListTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();

		final List<ApplicationSession> list = applicationSessionDAO.findListByPropertyInList(ApplicationSession_.sessionId,
				new Object[] {applicationSession.getSessionId()});
		assertNotNull(list);
		assertEquals(applicationSession,list.iterator().next());
	}

	/**
	 * Gets the all test.
	 *
	 * @return the all test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getAllTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final List<ApplicationSession> all = applicationSessionDAO.getAll();
		assertNotNull(all);
		assertFalse(all.isEmpty());
	}

	/**
	 * Gets the page test.
	 *
	 * @return the page test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getPageTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final int resultPerPage=1;
		final List<ApplicationSession> pageList = applicationSessionDAO.getPage(1, resultPerPage);
		assertNotNull(pageList);
		assertFalse(pageList.isEmpty());
		assertEquals(resultPerPage,pageList.size());
	}

	/**
	 * Gets the page order by test.
	 *
	 * @return the page order by test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getPageOrderByTest() throws Exception {
		final ApplicationSession applicationSession = createApplicationSession();
		final int resultPerPage=1;
		final List<ApplicationSession> pageList = applicationSessionDAO.getPageOrderBy(1, resultPerPage,ApplicationSession_.createdDate);
		assertNotNull(pageList);
		assertFalse(pageList.isEmpty());
		assertEquals(resultPerPage,pageList.size());
	}

}
