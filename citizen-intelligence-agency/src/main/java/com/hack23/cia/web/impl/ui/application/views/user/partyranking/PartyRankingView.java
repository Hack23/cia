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
package com.hack23.cia.web.impl.ui.application.views.user.partyranking;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView;
import com.vaadin.spring.annotation.SpringView;

/**
 * The Class PartyRankingView.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = PartyRankingView.NAME)
public final class PartyRankingView extends AbstractRankingView {

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new party ranking view.
	 *
	 * @param context
	 *            the context
	 */
	public PartyRankingView(final ApplicationContext context) {
		super(context.getBeansOfType(PageModeContentFactory.class), NAME);
	}

}