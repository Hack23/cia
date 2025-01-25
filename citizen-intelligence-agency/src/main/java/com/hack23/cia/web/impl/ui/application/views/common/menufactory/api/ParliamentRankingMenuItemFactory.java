package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * A factory for creating ParliamentRankingMenuItem objects.
 */
public interface ParliamentRankingMenuItemFactory {

    /**
     * Creates a new ParliamentRankingMenuItem object.
     *
     * @param panelContent the panel content
     */
    void createOverviewPage(VerticalLayout panelContent);

    /**
     * Creates a new ParliamentRankingMenuItem object.
     *
     * @param parliamentMenuItem the parliament menu item
     */
    void createParliamentRankingTopics(MenuItem parliamentMenuItem);
}
