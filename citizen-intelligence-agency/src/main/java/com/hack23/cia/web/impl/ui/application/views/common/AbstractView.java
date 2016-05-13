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
package com.hack23.cia.web.impl.ui.application.views.common;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractView.
 */
public abstract class AbstractView extends Panel implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page mode content factory map. */
	private transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/** The page name. */
	private final String pageName;

	/** The barmenu. */
	private final MenuBar barmenu = new MenuBar();

	/** The panel. */
	private Panel panel;

	/** The page link factory. */
	@Autowired
	protected transient PageLinkFactory pageLinkFactory;

	/** The page action event helper. */
	@Autowired
	protected transient PageActionEventHelper pageActionEventHelper;

	/**
	 * Instantiates a new abstract view.
	 */
	protected AbstractView(final Map<String, PageModeContentFactory> pageModeContentFactoryMap, final String pageName) {
		super();
		this.pageModeContentFactoryMap = pageModeContentFactoryMap;
		this.pageName = pageName;
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public final void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(pageName);
	}


	@Override
	public final void enter(final ViewChangeEvent event) {
		try {

			final String parameters = event.getParameters();
			for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {
				if (pageModeContentFactory.matches(pageName, parameters)) {
					getPanel().setContent(pageModeContentFactory.createContent(parameters, getBarmenu(), getPanel()));
					return;
				}
			}
		} catch (final AccessDeniedException e ) {
			final VerticalLayout panelContent = new VerticalLayout();
			panelContent.setMargin(true);
			panelContent.setWidth(100, Unit.PERCENTAGE);
			panelContent.setHeight(100, Unit.PERCENTAGE);
			panelContent.addComponent(LabelFactory.createHeader2Label("Access denided:" +pageName));
			getPanel().setContent(panelContent);
			getPanel().setCaption("Access denied");
		}
	}

	/**
	 * Creates the basic layout with panel and footer.
	 *
	 * @param panelName
	 *            the panel name
	 */
	protected final void createBasicLayoutWithPanelAndFooter(final String panelName) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		final VerticalLayout pageModeContent = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(pageModeContent);

		pageModeContent.addComponent(barmenu);

		panel = new Panel(panelName);

		panel.setSizeFull();
		pageModeContent.addComponent(panel);
		pageModeContent.setExpandRatio(panel, ContentRatio.FULL_SIZE);

		pageModeContent.addComponent(pageLinkFactory.createMainViewPageLink());
		setContent(layout);

		pageModeContent.setWidth(100, Unit.PERCENTAGE);
		pageModeContent.setHeight(100, Unit.PERCENTAGE);

		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);

	}

	/**
	 * Gets the barmenu.
	 *
	 * @return the barmenu
	 */
	public final MenuBar getBarmenu() {
		return barmenu;
	}

	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	protected final Panel getPanel() {
		return panel;
	}

}
