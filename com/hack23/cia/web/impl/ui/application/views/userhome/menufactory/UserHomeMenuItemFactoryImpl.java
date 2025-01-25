import static com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserHomeConstants.*;

public class UserHomeMenuItemFactoryImpl {

    @Override
    public void createOverviewPage(final VerticalLayout overviewLayout) {
        final ResponsiveRow grid = RowUtil.createGridLayout(overviewLayout);

        createButtonLink(grid, SECURITY_SETTING_TEXT, VaadinIcons.SHIELD,
                COMMAND_USERHOME_SECURITY_SETTINGS,
                SECURITY_SETTINGS_DESCRIPTION);

        createButtonLink(grid, USER_VISITS, VaadinIcons.EYE,
                COMMAND_USERHOME_USER_VISITS,
                USER_VISITS_DESCRIPTION);

        createButtonLink(grid, USER_EVENTS, VaadinIcons.CALENDAR,
                COMMAND_USERHOME_USER_EVENTS,
                USER_EVENTS_DESCRIPTION);
    }

    @Override
    public void createUserHomeMenuBar(final MenuBar menuBar, final String pageId) {
        initApplicationMenuBar(menuBar);

        applicationMenuItemFactory.addRankingMenu(menuBar);
        final MenuItem accountItem = menuBar.addItem(USERACCOUNT, VaadinIcons.USER, null);

        accountItem.addItem(USER_HOME_OVERVIEW_TEXT, VaadinIcons.USER,
                COMMAND_USERHOME_OVERVIEW);

        accountItem.addItem(SECURITY_SETTING_TEXT, VaadinIcons.SHIELD,
                COMMAND_USERHOME_SECURITY_SETTINGS);

        accountItem.addItem(USER_VISITS, VaadinIcons.EYE,
                COMMAND_USERHOME_USER_VISITS); 

        accountItem.addItem(USER_EVENTS, VaadinIcons.CALENDAR,
                COMMAND_USERHOME_USER_EVENTS);
    }
}
