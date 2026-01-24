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

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hack23.cia.service.data.api.ViewDataManager;

/**
 * The Class ViewDataManagerImpl.
 * 
 * Note: REFRESH MATERIALIZED VIEW (without CONCURRENTLY) in PostgreSQL takes an
 * ACCESS EXCLUSIVE lock on the materialized view for the entire duration of the
 * refresh, blocking both reads and writes. If minimal read disruption is required,
 * use REFRESH MATERIALIZED VIEW CONCURRENTLY (which requires a unique index on the
 * materialized view). See PostgreSQL documentation for details on concurrent refresh.
 */
@Service
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
		
		// Set statement timeout to 60 minutes (3600 seconds) for long-running refreshes
		// This provides a safety limit when running outside a Spring transaction
		jdbcTemplate.setQueryTimeout(3600);

		// Handle FP changed to L for folkpartiet name changed to liberalerna.
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
		
		// Dynamically refresh materialized views in dependency order
		// Query uses same logic as refresh-all-views.sql to calculate dependency levels
		// Returns schema-qualified, properly quoted refresh statements
		final String dependencyOrderQuery = """
			WITH RECURSIVE 
			view_deps AS (
			    SELECT DISTINCT
			        dependent_view.relname AS view_name,
			        source_table.relname AS depends_on,
			        CASE WHEN source_table.relkind = 'm' THEN TRUE ELSE FALSE END AS depends_on_matview
			    FROM pg_depend
			    JOIN pg_rewrite ON pg_depend.objid = pg_rewrite.oid
			    JOIN pg_class AS dependent_view ON pg_rewrite.ev_class = dependent_view.oid
			    JOIN pg_class AS source_table ON pg_depend.refobjid = source_table.oid
			    JOIN pg_namespace dependent_ns ON dependent_ns.oid = dependent_view.relnamespace
			    JOIN pg_namespace source_ns ON source_ns.oid = source_table.relnamespace
			    WHERE dependent_view.relkind = 'm'
			      AND source_table.relkind IN ('v', 'm', 'r')
			      AND pg_depend.deptype = 'n'
			      AND dependent_ns.nspname = 'public'
			      AND source_ns.nspname = 'public'
			),
			mv_dependencies AS (
			    SELECT view_name, depends_on FROM view_deps WHERE depends_on_matview = TRUE
			),
			all_mvs AS (
			    SELECT schemaname, matviewname FROM pg_matviews WHERE schemaname = 'public'
			),
			dependency_depth AS (
			    SELECT am.schemaname, am.matviewname AS view_name, 0 AS depth, ARRAY[am.matviewname] AS path
			    FROM all_mvs am
			    WHERE NOT EXISTS (SELECT 1 FROM mv_dependencies md WHERE md.view_name = am.matviewname)
			    UNION ALL
			    SELECT am.schemaname, md.view_name, dd.depth + 1, dd.path || md.view_name
			    FROM mv_dependencies md
			    JOIN dependency_depth dd ON md.depends_on = dd.view_name
			    JOIN all_mvs am ON am.matviewname = md.view_name
			    WHERE NOT (md.view_name = ANY(dd.path))
			),
			max_depths AS (
			    SELECT schemaname, view_name, MAX(depth) as max_depth 
			    FROM dependency_depth 
			    GROUP BY schemaname, view_name
			)
			SELECT format('REFRESH MATERIALIZED VIEW %I.%I', schemaname, view_name) AS refresh_stmt
			FROM max_depths 
			ORDER BY max_depth, schemaname, view_name
			""";
		
		// Get ordered list of properly-quoted refresh statements
		final List<String> refreshStatements = jdbcTemplate.queryForList(dependencyOrderQuery, String.class);
		
		// Refresh each view in dependency order
		for (final String refreshStatement : refreshStatements) {
			jdbcTemplate.execute(refreshStatement);
		}
	}

}
