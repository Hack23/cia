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
package com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotSummary_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.BallotMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.AbstractItemPageModContentFactoryImpl;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The Class AbstractBallotPageModContentFactoryImpl.
 */
abstract class AbstractBallotPageModContentFactoryImpl extends AbstractItemPageModContentFactoryImpl<List<ViewRiksdagenVoteDataBallotSummary>> {

	/** The Constant NAME. */
	public static final String NAME = UserViews.BALLOT_VIEW_NAME;

	/** The ballot menu item factory. */
	@Autowired
	private BallotMenuItemFactory ballotMenuItemFactory;

	/**
	 * Instantiates a new abstract ballot page mod content factory impl.
	 */
	AbstractBallotPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Gets the ballot menu item factory.
	 *
	 * @return the ballot menu item factory
	 */
	protected final BallotMenuItemFactory getBallotMenuItemFactory() {
		return ballotMenuItemFactory;
	}

	@Override
	protected List<ViewRiksdagenVoteDataBallotSummary> getItem(final String parameters) {
		final String pageId = getPageId(parameters);

		final DataContainer<ViewRiksdagenVoteDataBallotSummary, RiksdagenVoteDataBallotEmbeddedId> dataContainer = getApplicationManager()
				.getDataContainer(ViewRiksdagenVoteDataBallotSummary.class);

		return dataContainer.findListByEmbeddedProperty(ViewRiksdagenVoteDataBallotSummary.class, ViewRiksdagenVoteDataBallotSummary_.embeddedId, RiksdagenVoteDataBallotEmbeddedId.class, RiksdagenVoteDataBallotEmbeddedId_.ballotId, pageId);
	}

}
