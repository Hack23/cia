package com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPoliticianConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PoliticianVisitHistoryPageModContentFactoryImpl.
 */
@Component
public final class PoliticianVisitHistoryPageModContentFactoryImpl extends AbstractPoliticianPageModContentFactoryImpl {

    /**
     * Instantiates a new politician visit history page mod content factory impl.
     */
    public PoliticianVisitHistoryPageModContentFactoryImpl() {
        super();
    }

    @Override
    @Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
    public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout panelContent = createPanelContent();
        final String pageId = getPageId(parameters);
        final ViewRiksdagenPolitician viewRiksdagenPolitician = getItem(parameters);

        getPoliticianMenuItemFactory().createPoliticianMenuBar(menuBar, pageId);

        CardInfoRowUtil.createPageHeader(panel, panelContent,
            PoliticianTitleFormatter.formatTitle(viewRiksdagenPolitician),
            PoliticianDescriptionConstants.VISIT_TRENDS_TITLE,
            PoliticianDescriptionConstants.VISIT_HISTORY_DESC);

        createPageVisitHistory(NAME, pageId, panelContent);

        getPageActionEventHelper().createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
            UserViews.POLITICIAN_VIEW_NAME, parameters, pageId);

        return panelContent;
    }

    @Override
    public boolean matches(final String page, final String parameters) {
    	return PageCommandPoliticianConstants.COMMAND_POLITICIAN_PAGEVISIT.matches(page, parameters);
    }
}
