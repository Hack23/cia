package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CountryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CountryRankingMenuItemFactoryImpl.
 */
@Service
public final class CountryRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements CountryRankingMenuItemFactory {

    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

        createButtonLink(grid, OVERVIEW_TEXT, VaadinIcons.DASHBOARD,
                COMMAND_COUNTRY_RANKING_OVERVIEW, COUNTRY_RANKING_DESCRIPTION);

        createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY,
                RANKING_PAGE_VISIT_DESC);
    }

    @Override
    public void createCountryRankingTopics(final MenuItem countryMenuItem) {
        countryMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD,
                COMMAND_COUNTRY_RANKING_OVERVIEW);
        countryMenuItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                COUNTRY_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}
