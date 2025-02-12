package com.hack23.cia.web.impl.ui.application.views.user.documents.pagemode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandDocumentConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.paging.PagingUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.document.pagemode.DocumentPageTitleConstants;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class DocumentOverviewPageModContentFactoryImpl.
 */
@Component
public final class DocumentsOverviewPageModContentFactoryImpl extends AbstractDocumentsPageModContentFactoryImpl {

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "rm", "createdDate", "documentName", "subType", "title", "subTitle",
			"status" };

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "rm", "lang", "noteTitle", "origin", "subType", "note", "subTitle",
			"status", "label", "id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId", "org",
			"documentType", "docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
			"documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml", "committeeReportUrlXml" };

	/** The Constant LISTENER. */
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			UserViews.DOCUMENT_VIEW_NAME, "id", true);

	/** The paging util. */
	@Autowired
	private PagingUtil pagingUtil;

	/**
	 * Instantiates a new documents overview page mod content factory impl.
	 */
	public DocumentsOverviewPageModContentFactoryImpl() {
		super();
	}

	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr = getPageNr(parameters);

		getDocumentMenuItemFactory().createDocumentsMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent, DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_TITLE,
				DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_HEADER,
				DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_DESC);

		final DataContainer<DocumentElement, String> documentElementDataContainer = getApplicationManager()
				.getDataContainer(DocumentElement.class);

		final List<DocumentElement> pageOrderBy = documentElementDataContainer.getPageOrderBy(pageNr,
				DEFAULT_RESULTS_PER_PAGE, DocumentElement_.createdDate);

		pagingUtil.createPagingControls(panelContent, NAME + "/" + PageMode.OVERVIEW, pageId, documentElementDataContainer.getSize(), pageNr,
				DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(panelContent, DocumentElement.class, pageOrderBy, DocumentPageTitleConstants.DOCUMENTS_OVERVIEW_HEADER,
				COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENTS_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandDocumentConstants.COMMAND_DOCUMENTS_OVERVIEW.matches(page, parameters);
	}

}
