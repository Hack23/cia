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
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl;

import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;

/**
 * The Class PageLinkFactoryImpl.
 */
@Service
public final class PageLinkFactoryImpl implements PageLinkFactory {

	/** The Constant COMMITTEE. */
	private static final String COMMITTEE = "Committee ";

	/** The Constant PAGE_PREFIX. */
	private static final String PAGE_PREFIX = "#!";

	/** The Constant LINK_SEPARATOR. */
	private static final String LINK_SEPARATOR = PAGE_PREFIX;

	/** The Constant MAIN_VIEW_LINK_TEXT. */
	private static final String MAIN_VIEW_LINK_TEXT = "Main View";

	/** The Constant MINISTRY. */
	private static final String MINISTRY = "Ministry ";


	/** The Constant PAGE_SEPARATOR. */
	private static final Character PAGE_SEPARATOR = '/';

	/** The Constant PARTY. */
	private static final String PARTY = "Party ";

	/** The Constant POLITICIAN. */
	private static final String POLITICIAN = "Politician ";

	/**
	 * Default constructor for PageLinkFactoryImpl.
	 */
	public PageLinkFactoryImpl() {
		// Default constructor
	}

	@Override
	public Link addCommitteePageLink(final ViewRiksdagenCommittee data) {
		final Link pageLink = new Link(COMMITTEE
				+ data.getEmbeddedId().getDetail(), new ExternalResource(PAGE_PREFIX
						+ UserViews.COMMITTEE_VIEW_NAME + PageMode.OVERVIEW + PAGE_SEPARATOR + PAGE_SEPARATOR + data.getEmbeddedId().getOrgCode()));
		pageLink.setId(ViewAction.VISIT_COMMITTEE_VIEW.name() + PAGE_SEPARATOR
				+ data.getEmbeddedId().getOrgCode());
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

	@Override
	public Link addMinistryPageLink(final ViewRiksdagenMinistry data) {
		final Link pageLink = new Link(MINISTRY + data.getNameId(),
				new ExternalResource(PAGE_PREFIX + UserViews.MINISTRY_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW + PAGE_SEPARATOR
						+ data.getNameId()));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + PAGE_SEPARATOR
				+ data.getNameId());
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

	@Override
	public Link addPartyPageLink(final ViewRiksdagenParty data) {
		final Link pageLink = new Link(PARTY + data.getPartyName(),
				new ExternalResource(PAGE_PREFIX + UserViews.PARTY_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW + PAGE_SEPARATOR
						+ data.getPartyId()));
		pageLink.setId(ViewAction.VISIT_PARTY_VIEW.name() + PAGE_SEPARATOR
				+ data.getPartyId());
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}



	@Override
	public Link createAdminPagingLink(final String label,final String page, final String pageId, final String pageNr) {
		final Link pageLink = new Link(label,
				new ExternalResource(PAGE_PREFIX + page + PAGE_SEPARATOR
						+ "[" + pageNr + "]"));
		pageLink.setId(page +"ShowPage" + PAGE_SEPARATOR
				+ pageNr);
		pageLink.setIcon(VaadinIcons.SERVER);

		return pageLink;
	}

	@Override
	public Link createLoginPageLink() {
		final Link pageLink = new Link("Login", new ExternalResource(
				LINK_SEPARATOR + CommonsViews.MAIN_VIEW_NAME + PAGE_SEPARATOR + ApplicationPageMode.LOGIN));
		pageLink.setId(ViewAction.VISIT_LOGIN.name());
		pageLink.setIcon(VaadinIcons.SIGN_IN);
		return pageLink;
	}

	@Override
	public Link createMainViewPageLink() {
		final Link pageLink = new Link(MAIN_VIEW_LINK_TEXT, new ExternalResource(
				LINK_SEPARATOR + CommonsViews.MAIN_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW));
		pageLink.setId(ViewAction.VISIT_MAIN_VIEW.name());
		pageLink.setIcon(VaadinIcons.STAR);
		return pageLink;
	}

	@Override
	public Link createPoliticianPageLink(final PersonData personData) {
		final Link pageLink = new Link(POLITICIAN
				+ personData.getFirstName() + ' '
				+ personData.getLastName(), new ExternalResource(PAGE_PREFIX
						+ UserViews.POLITICIAN_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW + PAGE_SEPARATOR + personData.getId()));
		pageLink.setId(ViewAction.VISIT_POLITICIAN_VIEW.name() + PAGE_SEPARATOR
				+ personData.getId());
		pageLink.setIcon(VaadinIcons.BUG);
		return pageLink;
	}

	@Override
	public Link createPoliticianPageLink(final ViewRiksdagenPolitician personData) {
		final Link pageLink = new Link(POLITICIAN
				+ personData.getFirstName() + ' '
				+ personData.getLastName(), new ExternalResource(PAGE_PREFIX
						+ UserViews.POLITICIAN_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW + PAGE_SEPARATOR + personData.getPersonId()));
		pageLink.setId(ViewAction.VISIT_POLITICIAN_VIEW.name() + PAGE_SEPARATOR
				+ personData.getPersonId());
		pageLink.setIcon(VaadinIcons.BUG);
		return pageLink;
	}


	@Override
	public Link createRegisterPageLink() {
		final Link pageLink = new Link("Register", new ExternalResource(
				LINK_SEPARATOR + CommonsViews.MAIN_VIEW_NAME + PAGE_SEPARATOR + ApplicationPageMode.REGISTER));
		pageLink.setId(ViewAction.VISIT_REGISTER.name());
		pageLink.setIcon(VaadinIcons.RANDOM);
		return pageLink;
	}

	@Override
	public Link createUserHomeViewPageLink() {
		final Link pageLink = new Link("User account:", new ExternalResource(PAGE_PREFIX
				+ UserViews.USERHOME_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW));
			pageLink.setId(ViewAction.VISIT_USER_HOME_VIEW.name());
			pageLink.setIcon(VaadinIcons.USER);
			return pageLink;
	}

	@Override
	public Link addMinistryPageLink(final String name) {
		final Link pageLink = new Link(MINISTRY + name,
				new ExternalResource(PAGE_PREFIX + UserViews.MINISTRY_VIEW_NAME + PAGE_SEPARATOR + PageMode.OVERVIEW + PAGE_SEPARATOR
						+ name));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + PAGE_SEPARATOR
				+ name);
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

	@Override
	public Link addMinistryGovermentBodiesPageLink(final String name) {
		final Link pageLink = new Link("Government bodies:",
				new ExternalResource(PAGE_PREFIX + UserViews.MINISTRY_VIEW_NAME + PAGE_SEPARATOR + MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString() + PAGE_SEPARATOR +  name));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + PAGE_SEPARATOR
				+ name);
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

	@Override
	public Link addMinistryGovermentBodiesHeadcountPageLink(final String name) {
		final Link pageLink = new Link("Headcount:",
				new ExternalResource(PAGE_PREFIX + UserViews.MINISTRY_VIEW_NAME + PAGE_SEPARATOR + MinistryPageMode.GOVERNMENT_BODIES_HEADCOUNT.toString() + PAGE_SEPARATOR +  name));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + PAGE_SEPARATOR
				+ name);
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

	@Override
	public Link addMinistryGovermentBodiesIncomePageLink(final String name) {
		final Link pageLink = new Link("Income(B SEK):" ,
				new ExternalResource(PAGE_PREFIX + UserViews.MINISTRY_VIEW_NAME + PAGE_SEPARATOR + MinistryPageMode.GOVERNMENT_BODIES_INCOME.toString() + PAGE_SEPARATOR +  name));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + PAGE_SEPARATOR
				+ name);
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

	@Override
	public Link addMinistrGovermentBodiesSpendingPageLink(final String name) {
		final Link pageLink = new Link("Spending(B SEK):",
				new ExternalResource(PAGE_PREFIX + UserViews.MINISTRY_VIEW_NAME + PAGE_SEPARATOR + MinistryPageMode.GOVERNMENT_BODIES_EXPENDITURE.toString() + PAGE_SEPARATOR +  name));
		pageLink.setId(ViewAction.VISIT_MINISTRY_VIEW.name() + PAGE_SEPARATOR
				+ name);
		pageLink.setIcon(VaadinIcons.GROUP);
		return pageLink;
	}

}
