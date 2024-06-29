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
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.vaadin.ui.Link;

/**
 * A factory for creating PageLink objects.
 */
public interface PageLinkFactory {

	/**
	 * Adds the committee page link.
	 *
	 * @param data
	 *            the data
	 * @return the link
	 */
	Link addCommitteePageLink(ViewRiksdagenCommittee data);

	/**
	 * Adds the ministry page link.
	 *
	 * @param data
	 *            the data
	 * @return the link
	 */
	Link addMinistryPageLink(ViewRiksdagenMinistry data);

	/**
	 * Adds the party page link.
	 *
	 * @param data
	 *            the data
	 * @return the link
	 */
	Link addPartyPageLink(ViewRiksdagenParty data);

	/**
	 * Creates a new PageLink object.
	 *
	 * @param label
	 *            the label
	 * @param page
	 *            the page
	 * @param pageId
	 *            the page id
	 * @param pageNr
	 *            the page nr
	 * @return the link
	 */
	Link createAdminPagingLink(String label,String page,String pageId,String pageNr);

	/**
	 * Creates a new PageLink object.
	 *
	 * @return the link
	 */
	Link createLoginPageLink();

	/**
	 * Creates a new PageLink object.
	 *
	 * @return the link
	 */
	Link createMainViewPageLink();

	/**
	 * Creates a new PageLink object.
	 *
	 * @param personData
	 *            the person data
	 * @return the link
	 */
	Link createPoliticianPageLink(PersonData personData);

	/**
	 * Creates a new PageLink object.
	 *
	 * @return the link
	 */
	Link createRegisterPageLink();

	/**
	 * Creates a new PageLink object.
	 *
	 * @return the link
	 */
	Link createUserHomeViewPageLink();

	/**
	 * Creates a new PageLink object.
	 *
	 * @param personData the person data
	 * @return the link
	 */
	Link createPoliticianPageLink(ViewRiksdagenPolitician personData);

	/**
	 * Adds the ministry page link.
	 *
	 * @param name the name
	 * @return the link
	 */
	Link addMinistryPageLink(String name);

	
	/**
	 * Adds the ministry goverment bodies page link.
	 *
	 * @param name the name
	 * @param size the size
	 * @return the link
	 */
	Link addMinistryGovermentBodiesPageLink(String name, int size);

	/**
	 * Adds the ministry goverment bodies headcount page link.
	 *
	 * @param name the name
	 * @param size the size
	 * @return the link
	 */
	Link addMinistryGovermentBodiesHeadcountPageLink(String name, int size);

	/**
	 * Adds the ministry goverment bodies income page link.
	 *
	 * @param name the name
	 * @param d the d
	 * @return the link
	 */
	Link addMinistryGovermentBodiesIncomePageLink(String name, double d);

	/**
	 * Adds the ministr goverment bodies spending page link.
	 *
	 * @param name the name
	 * @param d the d
	 * @return the link
	 */
	Link addMinistrGovermentBodiesSpendingPageLink(String name , double d);
	

	
}
