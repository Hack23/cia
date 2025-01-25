package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.GovernmentBodyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandGovernmentBodyConstants;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class GovernmentBodyMenuItemFactoryImpl.
 */
@Service
public final class GovernmentBodyMenuItemFactoryImpl extends AbstractMenuItemFactoryImpl
        implements GovernmentBodyMenuItemFactory {

    /** The application menu item factory. */
    @Autowired
    private ApplicationMenuItemFactory applicationMenuItemFactory;

    /** The government body ranking menu item factory. */
    @Autowired
    private GovernmentBodyRankingMenuItemFactory governmentBodyRankingMenuItemFactory;

    /**
     * Instantiates a new government body menu item factory impl.
     */
    public GovernmentBodyMenuItemFactoryImpl() {
        super();
    }

    @Override
    public void createGovernmentBodyMenuBar(final MenuBar menuBar, final String pageId, final String title) {
        initApplicationMenuBar(menuBar);

        applicationMenuItemFactory.addRankingMenu(menuBar);

        governmentBodyRankingMenuItemFactory.createGovernmentBodyRankingTopics(
                menuBar.addItem(GOVERNMENT_BODY_RANKING, VaadinIcons.BUILDING_O, null));

        final MenuItem governmentBodyItem = menuBar.addItem(title, VaadinIcons.BUILDING_O, null);

        governmentBodyItem.addItem(GOVERNMENT_BODY_OVERVIEW_TEXT, VaadinIcons.FILE_TEXT,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_OVERVIEW);

        governmentBodyItem.addItem(HEADCOUNT_CHART, VaadinIcons.USER,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_HEADCOUNT);

        governmentBodyItem.addItem(INCOME, VaadinIcons.MONEY,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_INCOME);

        governmentBodyItem.addItem(EXPENDITURE, VaadinIcons.CREDIT_CARD,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_EXPENDITURE);
    }

    @Override
    public void createOverviewPage(final VerticalLayout panelContent, final String pageId) {
        final ResponsiveRow grid = RowUtil.createGridLayout(panelContent);

        createButtonLink(grid, HEADCOUNT_CHART, VaadinIcons.USER,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_HEADCOUNT,
                HEADCOUNT_DESCRIPTION);

        createButtonLink(grid, INCOME, VaadinIcons.MONEY,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_INCOME,
                INCOME_DESCRIPTION);

        createButtonLink(grid, EXPENDITURE, VaadinIcons.CREDIT_CARD,
                PageCommandGovernmentBodyConstants.COMMAND_GOVERNMENT_BODY_EXPENDITURE,
                EXPENDITURE_DESCRIPTION);
    }
}