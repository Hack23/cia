package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ParliamentRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandParliamentRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class ParliamentRankingMenuItemFactoryImpl.
 */
@Service
public final class ParliamentRankingMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements ParliamentRankingMenuItemFactory {

    @Override
    public void createOverviewPage(final VerticalLayout panelContent) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);
        createButtonLink(grid, RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY, RANKING_PAGE_VISIT_DESC);
    }

    @Override
    public void createParliamentRankingTopics(final MenuItem parliamentMenuItem) {
        parliamentMenuItem.addItem(RANKING_OVERVIEW_TEXT, VaadinIcons.DASHBOARD,
        		PageCommandParliamentRankingConstants.COMMAND_PARLIAMENT_RANKING_OVERVIEW);
        parliamentMenuItem.addItem(RANKING_PAGE_VISIT_TEXT, VaadinIcons.CHART,
                PARLIAMENT_RANKING_COMMAND_PAGEVISIT_HISTORY);
    }
}
