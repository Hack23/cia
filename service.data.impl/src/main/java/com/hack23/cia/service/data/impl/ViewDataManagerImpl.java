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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.data.api.ViewDataManager;

/**
 * The Class ViewDataManagerImpl.
 */
@Service
@Transactional(timeout=900)
final class ViewDataManagerImpl implements ViewDataManager {

	/** The data source. */
	@Autowired
	private DataSource dataSource;

	/**
	 * Instantiates a new view data manager impl.
	 */
	public ViewDataManagerImpl() {
		super();
	}

	@Override
	public void refreshViews() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


		//Handle FP changed to L for folkpartiet name changed to liberalerna.

		jdbcTemplate.execute("update vote_data set gender='MAN' where gender='M'");
		jdbcTemplate.execute("update vote_data set gender='KVINNA' where gender='K'");

		jdbcTemplate.execute("update vote_data set gender='MAN' where gender='man'");
		jdbcTemplate.execute("update vote_data set gender='KVINNA' where gender='kvinna'");


		jdbcTemplate.execute("update vote_data set party='L' where party='FP'");

		jdbcTemplate.execute("update person_data set party='L' where party='FP'");
		jdbcTemplate.execute("update document_element set org='L' where org='FP' or org='fp'");
		jdbcTemplate.execute("update document_data set org='L' where org='FP' or org='fp'");
		jdbcTemplate.execute("update committee_document_data set org='L' where org='FP' or org='fp'");
		jdbcTemplate.execute("update document_person_reference_da_0 set party_short_code='L' where party_short_code='FP' or party_short_code='fp'");

		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_worldbank_indicator_data_country_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_daily");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_annual");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_monthly");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_party_summary_weekly");

		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_daily");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_annual");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_monthly");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_summary_weekly");

		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_daily");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_annual");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_monthly");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_vote_data_ballot_politician_summary_weekly");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_committee_decisions");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_politician_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_party_summary");

		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_committee_ballot_decision_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_committee_decision_type_org_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_committee_decision_type_summary");


		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_politician_document");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_org_document_daily_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_party_document_daily_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_politician_document_daily_summary");
		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_riksdagen_document_type_daily_summary");

		jdbcTemplate.execute("REFRESH MATERIALIZED VIEW view_worldbank_indicator_data_country_summary");

	}

}
