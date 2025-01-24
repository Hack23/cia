package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ParliamentRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@Service
public final class ParliamentRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements ParliamentRankingMenuItemFactory {
    
    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);
        createButtonLink(grid, PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART,
                PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY, PAGE_VISIT_HISTORY_DESCRIPTION);
    }
    
    @Override
    public void createParliamentRankingTopics(final MenuItem parliamentMenuItem) {
        parliamentMenuItem.addItem(OVERVIEW_TEXT, VaadinIcons.DASHBOARD, COMMAND_OVERVIEW);
        parliamentMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, 
                PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}
