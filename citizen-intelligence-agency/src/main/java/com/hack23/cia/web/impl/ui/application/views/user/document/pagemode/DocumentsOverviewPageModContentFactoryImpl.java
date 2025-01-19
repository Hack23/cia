/*
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import org.springframework.stereotype.Component;

@Component
public final class DocumentsOverviewPageModContentFactoryImpl extends AbstractPageModContentFactoryImpl {

    @Override
    public Component createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
        final VerticalLayout panelContent = createPanelContent();
        panel.setContent(panelContent);

        getGridFactory().createBasicChart(panelContent,
                DocumentViewConstants.ACTIVITY_TITLE,
                DocumentViewConstants.ACTIVITY_DESC,
                createDocumentActivityChart(documentChartDataManager));

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();

        final Chart documentTypeChart = createDocumentTypeChart(documentChartDataManager);
        getGridFactory().createBasicChart(horizontalLayout,
                DocumentViewConstants.OVERVIEW_SECTION_DOC_PROFILE,
                DocumentViewConstants.OVERVIEW_DESC,
                documentTypeChart);

        final Chart statusChart = createDocumentStatusChart(documentChartDataManager);
        getGridFactory().createBasicChart(horizontalLayout,
                DocumentViewConstants.OVERVIEW_SECTION_METADATA,
                DocumentViewConstants.OVERVIEW_DESC,
                statusChart);

        panelContent.addComponent(horizontalLayout);

        return panelContent;
    }

    private Chart createDocumentTypeChart(final DocumentChartDataManager documentChartDataManager) {
        final Chart chart = new Chart(ChartType.PIE);
        final Configuration conf = chart.getConfiguration();
        conf.setTitle(DocumentViewConstants.FIELD_DOC_TYPE);

        final DataSeries series = new DataSeries();
        series.setName(DocumentViewConstants.FIELD_DOC_TYPE);
        series.setData(documentChartDataManager.getDocumentTypeData());
        conf.addSeries(series);

        final PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setDataLabels(new DataLabels(true));
        conf.setPlotOptions(plotOptions);

        return chart;
    }

    private Chart createDocumentStatusChart(final DocumentChartDataManager documentChartDataManager) {
        final Chart chart = new Chart(ChartType.PIE);
        final Configuration conf = chart.getConfiguration();
        conf.setTitle(DocumentViewConstants.FIELD_STATUS);

        final DataSeries series = new DataSeries();
        series.setName(DocumentViewConstants.FIELD_STATUS);
        series.setData(documentChartDataManager.getDocumentStatusData());
        conf.addSeries(series);

        final PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setDataLabels(new DataLabels(true));
        conf.setPlotOptions(plotOptions);

        return chart;
    }
}
 * Copyright 2010-2025 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.paging.PagingUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * The Class DocumentOverviewPageModContentFactoryImpl.
 */
@Component
public final class DocumentsOverviewPageModContentFactoryImpl extends AbstractDocumentsPageModContentFactoryImpl {

	private static final String[] COLUMN_ORDER = { "rm", "createdDate", "documentName", "subType", "title", "subTitle", "status" };
	private static final String DOCUMENT = "Document";
	private static final String[] HIDE_COLUMNS = { "rm", "lang", "noteTitle", "origin", "subType", "note", "subTitle", "status", "label",
			"id", "hit", "madePublicDate", "databaseSource", "domainOrg", "relatedId", "org",
			"documentType", "docType", "debateName", "tempLabel", "numberValue", "systemDate", "kallId",
			"documentFormat", "documentUrlText", "documentUrlHtml", "documentStatusUrlXml",
			"committeeReportUrlXml" };
	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "id",true);

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
		final int pageNr= getPageNr(parameters);

		getDocumentMenuItemFactory().createDocumentsMenuBar(menuBar);
		CardInfoRowUtil.createPageHeader(panel, panelContent, "Documents Overview", "Documents Repository", "Comprehensive repository of Swedish Parliament(Riksdagen) documents.");

		final DataContainer<DocumentElement, String> documentElementDataContainer = getApplicationManager()
				.getDataContainer(DocumentElement.class);

		final List<DocumentElement> pageOrderBy = documentElementDataContainer.getPageOrderBy(pageNr,DEFAULT_RESULTS_PER_PAGE, DocumentElement_.createdDate);

		pagingUtil.createPagingControls(panelContent,NAME,pageId, documentElementDataContainer.getSize(), pageNr, DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(panelContent, DocumentElement.class, pageOrderBy, DOCUMENT,
				COLUMN_ORDER,
				HIDE_COLUMNS,
				LISTENER, null, null);

		panel.setContent(panelContent);
		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_DOCUMENTS_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;

	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page);
	}

}
