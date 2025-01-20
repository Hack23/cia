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
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.UI;

/**
 * The Class DecisionFlowValueChangeListener.
 *
 * @see DecisionFlowValueChangeEvent
 */
public final class DecisionFlowValueChangeListener implements ValueChangeListener<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page id. */
	private final String pageId;

	/** The page name. */
	private final String pageName;


	/**
	 * Instantiates a new decision flow value change listener.
	 *
	 * @param pageName the page name
	 * @param pageId   the page id
	 */
	public DecisionFlowValueChangeListener(final String pageName, final String pageId) {
		super();
		this.pageName = pageName;
		this.pageId = pageId;
	}


	@Override
	public void valueChange(final ValueChangeEvent<String> event) {
		if (!event.getSource().isEmpty()) {
			UI.getCurrent().getNavigator().navigateTo(pageName + "/" + PageMode.CHARTS + "/"
					+ ChartIndicators.DECISION_FLOW_CHART + "/" + pageId + "[" + event.getValue() + "]");
		}
	}

}
