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

import java.util.Set;

import com.hack23.cia.model.internal.application.data.rules.impl.ResourceType;
import com.hack23.cia.service.api.action.kpi.ComplianceCheck;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

/**
 * The Class RuleViolationPageItemRendererClickListener.
 *
 * @see ComplianceCheckPageItemRendererClickEvent
 */

public final class ComplianceCheckPageItemRendererClickListener implements PageItemRendererClickListener<ComplianceCheck> {

	/** The Constant PAGE_SEPARATOR. */
	private static final Character PAGE_SEPARATOR = '/';

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/**
	 * Instantiates a new compliance check page item renderer click listener.
	 */
	public ComplianceCheckPageItemRendererClickListener() {
		super();
	}

	/**
	 * Navigate to page.
	 *
	 * @param violation
	 *            the violation
	 */
	private static void navigateToPage(final ComplianceCheck violation) {
		if (violation.getResourceType() == ResourceType.PARTY) {
			UI.getCurrent().getNavigator().navigateTo(UserViews.PARTY_VIEW_NAME + PAGE_SEPARATOR + violation.getId());
		} else {
			UI.getCurrent().getNavigator().navigateTo(UserViews.POLITICIAN_VIEW_NAME + PAGE_SEPARATOR + violation.getId());
		}
	}

	@Override
	public void click(final RendererClickEvent<ComplianceCheck> event) {
		navigateToPage(event.getItem());

	}


	@Override
	public void selectionChange(final SelectionEvent<ComplianceCheck> event) {
		final Set<ComplianceCheck> added =event.getAllSelectedItems();

		if (!added.isEmpty()) {
			navigateToPage(added.iterator().next());
		}
	}
}