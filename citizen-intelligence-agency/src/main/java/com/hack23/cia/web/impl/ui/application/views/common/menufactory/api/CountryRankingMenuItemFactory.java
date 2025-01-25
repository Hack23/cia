package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public interface CountryRankingMenuItemFactory {
    void createOverviewPage(VerticalLayout panelContent);
    void createCountryRankingTopics(MenuItem countryMenuItem);
}
