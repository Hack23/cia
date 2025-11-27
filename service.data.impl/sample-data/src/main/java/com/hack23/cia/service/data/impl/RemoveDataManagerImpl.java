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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.data.api.RemoveDataManager;

/**
 * The Class RemoveDataManagerImpl.
 */
@Service
@Transactional(timeout=900)
final class RemoveDataManagerImpl implements RemoveDataManager {

	/** The data source. */
	private final DataSource dataSource;

	/**
	 * Instantiates a new removes the data manager impl.
	 *
	 * @param dataSource
	 *            the data source
	 */
	@Autowired
	public RemoveDataManagerImpl(final DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void removePersonData() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("delete from PERSON_DATA");
		jdbcTemplate.execute("delete from DETAIL_DATA");
		jdbcTemplate.execute("delete from PERSON_DETAIL_DATA");
		jdbcTemplate.execute("delete from ASSIGNMENT_DATA");
		jdbcTemplate.execute("delete from PERSON_ASSIGNMENT_DATA");
	}

	@Override
	public void removeDocuments() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("delete from document_element");
		jdbcTemplate.execute("delete from document_content_data");
	}

	@Override
	public void removeDocumentStatus() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("delete from document_activity_data");
		jdbcTemplate.execute("delete from document_activity_container");
		jdbcTemplate.execute("delete from document_person_reference_da_0");
		jdbcTemplate.execute("delete from document_person_reference_co_0");
		jdbcTemplate.execute("delete from document_attachment");
		jdbcTemplate.execute("delete from document_attachment_container");
		jdbcTemplate.execute("delete from document_reference_data");
		jdbcTemplate.execute("delete from document_reference_container");
		jdbcTemplate.execute("delete from document_proposal_container");
		jdbcTemplate.execute("delete from document_proposal_data");
		jdbcTemplate.execute("delete from document_detail_data");
		jdbcTemplate.execute("delete from document_detail_container");
		jdbcTemplate.execute("delete from document_data");
		jdbcTemplate.execute("delete from document_container_element");
		jdbcTemplate.execute("delete from document_status_container");
	}

	@Override
	public void removeCommitteeProposals() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("delete from committee_proposal_component_0");
		jdbcTemplate.execute("delete from committee_proposal_container");
		jdbcTemplate.execute("delete from committee_document_data");
		jdbcTemplate.execute("delete from against_proposal_container");
		jdbcTemplate.execute("delete from committee_proposal_data");
		jdbcTemplate.execute("delete from against_proposal_data");
	}

	@Override
	public void removeApplicationHistory() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("delete from application_action_event");
		jdbcTemplate.execute("delete from application_session");
	}

	@Override
	public void removeUserAccountApplicationHistory(final String userId) {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("delete from application_action_event WHERE EVENTS_APPLICATION_SESSION_H_0 IN (SELECT hjid FROM application_session WHERE user_id = ?)", userId);
		jdbcTemplate.update("delete from application_session WHERE user_id = ?", userId);
	}

}