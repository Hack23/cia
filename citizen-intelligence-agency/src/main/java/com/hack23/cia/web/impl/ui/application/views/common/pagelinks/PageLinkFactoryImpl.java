/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks;

import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;

@Service
public final class PageLinkFactoryImpl implements PageLinkFactory {

	/** The Constant ADMIN_AGENT_OPERATIONS_LINK_TEXT. */
	private static final String ADMIN_AGENT_OPERATIONS_LINK_TEXT = "Admin Agent Operations";

	/** The Constant ADMIN_DATA_SUMMARY_LINK_TEXT. */
	private static final String ADMIN_DATA_SUMMARY_LINK_TEXT = "Admin Data Summary";

	/** The Constant POLITICIAN_RANKING_LINK_TEXT. */
	private static final String POLITICIAN_RANKING_LINK_TEXT = "Politician Ranking";

	/** The Constant PARTY_RANKING_LINK_TEXT. */
	private static final String PARTY_RANKING_LINK_TEXT = "Party Ranking";

	/** The Constant COMMITTEE_RANKING_LINK_TEXT. */
	private static final String COMMITTEE_RANKING_LINK_TEXT = "Committee Ranking";

	/** The Constant MINISTRY_RANKING_LINK_TEXT. */
	private static final String MINISTRY_RANKING_LINK_TEXT = "Ministry Ranking";

	/** The Constant TEST_CHART_VIEW_LINK_TEXT. */
	private static final String TEST_CHART_VIEW_LINK_TEXT = "Test Chart View";

	/** The Constant LINK_SEPARATOR. */
	private static final String LINK_SEPARATOR = "#!";

	/** The Constant MAIN_VIEW_LINK_TEXT. */
	private static final String MAIN_VIEW_LINK_TEXT = "Main View";

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createMainViewPageLink()
	 */
	@Override
	public Link createMainViewPageLink() {
		final Link pageLink = new Link(MAIN_VIEW_LINK_TEXT, new ExternalResource(
				LINK_SEPARATOR + CommonsViews.MAIN_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_MAIN_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createTestChartViewPageLink()
	 */
	@Override
	public Link createTestChartViewPageLink() {
		final Link pageLink = new Link(TEST_CHART_VIEW_LINK_TEXT,
				new ExternalResource(LINK_SEPARATOR + UserViews.TEST_CHART_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_TEST_CHART_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createMinistryRankingViewPageLink()
	 */
	@Override
	public Link createMinistryRankingViewPageLink() {
		final Link pageLink = new Link(MINISTRY_RANKING_LINK_TEXT, new ExternalResource(
				LINK_SEPARATOR + UserViews.MINISTRY_RANKING_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_MINISTRY_RANKING_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createCommitteeRankingViewPageLink()
	 */
	@Override
	public Link createCommitteeRankingViewPageLink() {
		final Link pageLink = new Link(COMMITTEE_RANKING_LINK_TEXT, new ExternalResource(
				LINK_SEPARATOR + UserViews.COMMITTEE_RANKING_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_COMMITTEE_RANKING_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createPartyRankingViewPageLink()
	 */
	@Override
	public Link createPartyRankingViewPageLink() {
		final Link pageLink = new Link(PARTY_RANKING_LINK_TEXT, new ExternalResource(
				LINK_SEPARATOR + UserViews.PARTY_RANKING_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_PARTY_RANKING_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createPoliticianRankingViewPageLink()
	 */
	@Override
	public Link createPoliticianRankingViewPageLink() {
		final Link pageLink = new Link(POLITICIAN_RANKING_LINK_TEXT,
				new ExternalResource(LINK_SEPARATOR + UserViews.POLITICIAN_RANKING_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_POLITICIAN_RANKING_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createAdminDataSummaryViewPageLink()
	 */
	@Override
	public Link createAdminDataSummaryViewPageLink() {
		final Link pageLink = new Link(ADMIN_DATA_SUMMARY_LINK_TEXT,
				new ExternalResource(LINK_SEPARATOR
						+ AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_ADMIN_DATA_SUMMARY_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createAdminAgentOperationViewPageLink()
	 */
	@Override
	public Link createAdminAgentOperationViewPageLink() {
		final Link pageLink = new Link(ADMIN_AGENT_OPERATIONS_LINK_TEXT, new ExternalResource(
				LINK_SEPARATOR + AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME));
		pageLink.setId(ViewAction.VISIT_ADMIN_AGENT_OPERATION_VIEW.name());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#addCommitteePageLink(com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee)
	 */
	@Override
	public Link addCommitteePageLink(final ViewRiksdagenCommittee data) {
		final Link pageLink = new Link("Committee "
				+ data.getEmbeddedId().getDetail(), new ExternalResource("#!"
						+ UserViews.COMMITTEE_VIEW_NAME + "/" + data.getEmbeddedId().getOrgCode()));
		pageLink.setId(ViewAction.VISIT_COMMITTEE_VIEW.name() + "/"
				+ data.getEmbeddedId().getOrgCode());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#addMinistryPageLink(com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry)
	 */
	@Override
	public Link addMinistryPageLink(final ViewRiksdagenMinistry data) {
		final Link pageLink = new Link("Ministry " + data.getNameId(),
				new ExternalResource("#!" + UserViews.MINISTRY_VIEW_NAME + "/"
						+ data.getNameId()));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + "/"
				+ data.getNameId());
		return pageLink;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#addPartyPageLink(com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty)
	 */
	@Override
	public Link addPartyPageLink(final ViewRiksdagenParty data) {
		final Link pageLink = new Link("Party " + data.getPartyName(),
				new ExternalResource("#!" + UserViews.PARTY_VIEW_NAME + "/"
						+ data.getPartyId()));
		pageLink.setId(ViewAction.VISIT_PARTY_VIEW.name() + "/"
				+ data.getPartyId());
		return pageLink;
	}


	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.PageLinkFactory#createPoliticianPageLink(com.hack23.cia.model.external.riksdagen.person.impl.PersonData)
	 */
	@Override
	public Link createPoliticianPageLink(final PersonData personData) {
		final Link pageLink = new Link("Politician "
				+ personData.getFirstName() + " "
				+ personData.getLastName(), new ExternalResource("#!"
						+ UserViews.POLITICIAN_VIEW_NAME + "/" + personData.getId()));
		pageLink.setId(ViewAction.VISIT_POLITICIAN_VIEW.name() + "/"
				+ personData.getId());
		return pageLink;
	}

	@Override
	public Link createSearchDocumentViewPageLink() {
		final Link pageLink = new Link("Search", new ExternalResource("#!"
						+ UserViews.SEARCH_DOCUMENT_VIEW_NAME));
		pageLink.setId(ViewAction.VISIT_DOCUMENT_VIEW.name());
		return pageLink;
	}


}
