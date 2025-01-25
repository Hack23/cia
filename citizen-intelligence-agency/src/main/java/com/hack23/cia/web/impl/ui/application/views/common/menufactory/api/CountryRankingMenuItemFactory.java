package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * A factory for creating CountryRankingMenuItem objects.
 */
public interface CountryRankingMenuItemFactory {

    /**
     * Creates a new CountryRankingMenuItem object.
     *
     * @param panelContent the panel content
     */
    void createOverviewPage(VerticalLayout panelContent);

    /**
     * Creates a new CountryRankingMenuItem object.
     *
     * @param countryMenuItem the country menu item
     */
    void createCountryRankingTopics(MenuItem countryMenuItem);
}
