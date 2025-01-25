package com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;

import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public interface ParliamentRankingMenuItemFactory {
    void createOverviewPage(VerticalLayout panelContent);
    void createParliamentRankingTopics(MenuItem parliamentMenuItem);
}
