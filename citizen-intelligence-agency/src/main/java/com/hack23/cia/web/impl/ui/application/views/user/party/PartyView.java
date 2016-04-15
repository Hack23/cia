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
package com.hack23.cia.web.impl.ui.application.views.user.party;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractGroupView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PartyView.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = PartyView.NAME, cached = true)
public final class PartyView extends AbstractGroupView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_VIEW_NAME;

	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/**
	 * Instantiates a new party view.
	 *
	 * @param context
	 *            the context
	 */
	public PartyView(final ApplicationContext context) {
		super();
		pageModeContentFactoryMap = context.getBeansOfType(PageModeContentFactory.class);

	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	@Override
	public void enter(final ViewChangeEvent event) {
		final String parameters = event.getParameters();

		for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {

			if (pageModeContentFactory.matches(NAME, parameters)) {



				getPanel().setContent(pageModeContentFactory.createContent(parameters, getBarmenu(), getPanel()));

				return;
			}
		}

		for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {
			if (pageModeContentFactory.matches(NAME, parameters)) {


				getPanel().setContent(pageModeContentFactory.createContent(parameters, getBarmenu(), getPanel()));
				return;
			}
		}
	}

}