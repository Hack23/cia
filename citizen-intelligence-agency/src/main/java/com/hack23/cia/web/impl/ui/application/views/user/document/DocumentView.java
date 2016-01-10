/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.user.document;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentActivityData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentAttachment;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentDetailData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentPersonReferenceData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentProposalData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentReferenceData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractUserView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PartyView.
 */
@Service
@Scope("prototype")
@VaadinView(value = DocumentView.NAME, cached = true)
//@Secured({ "ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class DocumentView extends AbstractUserView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.DOCUMENT_VIEW_NAME;

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The chart data manager. */
	@Autowired
	private transient ChartDataManager chartDataManager;

	/** The menu item factory. */
	@Autowired
	private transient MenuItemFactory menuItemFactory;

	/** The grid factory. */
	@Autowired
	private transient GridFactory gridFactory;

	/** The form factory. */
	@Autowired
	private transient FormFactory formFactory;


	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

		final String parameters = event.getParameters();

		if (parameters != null) {

			final String pageId = event.getParameters().substring(parameters.lastIndexOf('/') + "/".length(),
					parameters.length());

			final DataContainer<DocumentElement, String> documentElementDataContainer = applicationManager
					.getDataContainer(DocumentElement.class);

			final DataContainer<DocumentContentData, String> documentContentDataDataContainer = applicationManager
					.getDataContainer(DocumentContentData.class);

			final DataContainer<DocumentStatusContainer, String> documentStatusContainerDataContainer = applicationManager
					.getDataContainer(DocumentStatusContainer.class);

			final DataContainer<CommitteeProposalComponentData, String> committeeProposalComponentDataContainer = applicationManager
					.getDataContainer(CommitteeProposalComponentData.class);

			final DocumentElement documentElement = documentElementDataContainer.load(pageId);

			if (documentElement != null) {

				menuItemFactory.createDocumentMenuBar(getBarmenu(), pageId);
				final VerticalLayout panelContent = new VerticalLayout();
				panelContent.setSizeFull();
				panelContent.setMargin(true);

				final DocumentStatusContainer documentStatusContainer = documentStatusContainerDataContainer
						.findByQueryProperty(DocumentStatusContainer.class, DocumentStatusContainer_.document,
								DocumentData.class, DocumentData_.id, pageId);

				if (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
						|| parameters.contains(PageMode.Overview.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Overview"));

					formFactory.addTextFields(panelContent, new BeanItem<DocumentElement>(documentElement), DocumentElement.class,
							Arrays.asList(new String[] { "id", "org", "documentType", "subType", "rm", "status",
									"title", "subTitle", "madePublicDate", "createdDate", "systemDate", "relatedId",
									"label", "tempLabel", "numberValue", "kallId", "documentFormat" }));

					if (documentStatusContainer != null) {
						formFactory.addTextFields(panelContent, new BeanItem<DocumentStatusContainer>(documentStatusContainer),
								DocumentStatusContainer.class, Arrays.asList(new String[] { "documentCategory" }));

						formFactory.addTextFields(panelContent, new BeanItem<DocumentData>(documentStatusContainer.getDocument()),
								DocumentData.class,
								Arrays.asList(new String[] { "id", "org", "documentType", "subType", "rm", "status",
										"title", "subTitle", "madePublicDate", "label", "tempLabel", "numberValue",
										"hangarId", }));

					}

				} else if (parameters.contains(DocumentPageMode.DocumenDecision.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document Decision"));

					if (documentStatusContainer != null && documentStatusContainer.getDocumentProposal() != null
							&& documentStatusContainer.getDocumentProposal().getProposal() != null) {

						formFactory.addTextFields(panelContent,
								new BeanItem<DocumentProposalData>(
										documentStatusContainer.getDocumentProposal().getProposal()),
								DocumentProposalData.class,
								Arrays.asList(new String[] { "committee", "chamber", "processedIn", "decisionType",
										"proposalNumber", "designation", "wording", "wording2", "wording3",
										"wording4" }));

					}

				} else if (parameters.contains(DocumentPageMode.DocumentActivity.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document Activity"));

					if (documentStatusContainer != null
							&& documentStatusContainer.getDocumentActivityContainer() != null
							&& documentStatusContainer.getDocumentActivityContainer().getDocumentActivities() != null) {
						final BeanItemContainer<DocumentActivityData> documentActivityDataDataDataSource = new BeanItemContainer<DocumentActivityData>(
								DocumentActivityData.class,
								documentStatusContainer.getDocumentActivityContainer().getDocumentActivities());

						final Grid documentActivityDataItemGrid = gridFactory.createBasicBeanItemGrid(
								documentActivityDataDataDataSource, "Document activities", new String[] { "createdDate",
										"code", "activityName", "orderNumber", "process", "status" },
								new String[] { "hjid" }, null, null, null);
						panelContent.addComponent(documentActivityDataItemGrid);
					}

				} else if (parameters.contains(DocumentPageMode.DocumentData.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document Data"));

					final List<DocumentContentData> documentContentlist = documentContentDataDataContainer
							.getAllBy(DocumentContentData_.id, pageId);

					if (!documentContentlist.isEmpty()) {

						final Label htmlContent = new Label(documentContentlist.get(0).getContent(), ContentMode.HTML);

						panelContent.addComponent(htmlContent);

					}

				} else if (parameters.contains(DocumentPageMode.DocumentDetails.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document Details"));

					if (documentStatusContainer != null && documentStatusContainer.getDocumentDetailContainer() != null
							&& documentStatusContainer.getDocumentDetailContainer().getDocumentDetailList() != null) {
						final BeanItemContainer<DocumentDetailData> documentDetailDataDataDataSource = new BeanItemContainer<DocumentDetailData>(
								DocumentDetailData.class,
								documentStatusContainer.getDocumentDetailContainer().getDocumentDetailList());

						final Grid documentDetailDataItemGrid = gridFactory.createBasicBeanItemGrid(
								documentDetailDataDataDataSource, "Document details",
								new String[] { "code", "detailName", "text" }, new String[] { "hjid" }, null, null, null);
						panelContent.addComponent(documentDetailDataItemGrid);
					}

				} else if (parameters.contains(DocumentPageMode.DocumentReferences.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document References"));

					if (documentStatusContainer != null
							&& documentStatusContainer.getDocumentReferenceContainer() != null
							&& documentStatusContainer.getDocumentReferenceContainer()
									.getDocumentReferenceList() != null) {
						final BeanItemContainer<DocumentReferenceData> documentReferenceDataDataSource = new BeanItemContainer<DocumentReferenceData>(
								DocumentReferenceData.class,
								documentStatusContainer.getDocumentReferenceContainer().getDocumentReferenceList());

						final Grid documentReferenceDataItemGrid = gridFactory.createBasicBeanItemGrid(
								documentReferenceDataDataSource, "Document references",
								new String[] { "referenceType", "referenceDocumentId", "detail" },
								new String[] { "hjid" }, null, null, null);
						panelContent.addComponent(documentReferenceDataItemGrid);

					}

				} else if (parameters.contains(DocumentPageMode.PersonReferences.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Person References"));

					if (documentStatusContainer != null
							&& documentStatusContainer.getDocumentPersonReferenceContainer() != null
							&& documentStatusContainer.getDocumentPersonReferenceContainer()
									.getDocumentPersonReferenceList() != null) {
						final BeanItemContainer<DocumentPersonReferenceData> documentPersonReferenceDataDataSource = new BeanItemContainer<DocumentPersonReferenceData>(
								DocumentPersonReferenceData.class, documentStatusContainer
										.getDocumentPersonReferenceContainer().getDocumentPersonReferenceList());

						final Grid documentPersonReferenceDataItemGrid = gridFactory.createBasicBeanItemGrid(
								documentPersonReferenceDataDataSource, "Document person references",
								new String[] { "personReferenceId", "referenceName", "partyShortCode", "orderNumber",
										"roleDescription" },
								new String[] { "hjid" }, "personReferenceId",
								new PageItemPropertyClickListener(UserViews.POLITICIAN_VIEW_NAME,"personReferenceId"), null);
						panelContent.addComponent(documentPersonReferenceDataItemGrid);
					}

				} else if (parameters.contains(DocumentPageMode.DocumentAttachments.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Document Attachments"));

					if (documentStatusContainer != null
							&& documentStatusContainer.getDocumentAttachmentContainer() != null
							&& documentStatusContainer.getDocumentAttachmentContainer()
									.getDocumentAttachmentList() != null) {
						final BeanItemContainer<DocumentAttachment> documentAttachmentDataSource = new BeanItemContainer<DocumentAttachment>(
								DocumentAttachment.class,
								documentStatusContainer.getDocumentAttachmentContainer().getDocumentAttachmentList());

						final Grid documentAttachmentDataItemGrid = gridFactory.createBasicBeanItemGrid(
								documentAttachmentDataSource, "Document attachements",
								new String[] { "fileName", "fileSize", "fileType", "fileUrl" }, new String[] { "hjid" },
								null, null, null);
						panelContent.addComponent(documentAttachmentDataItemGrid);
					}

				} else if (parameters.contains(PageMode.PageVisitHistory.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label("Current Page Visit History"));
					panelContent.addComponent(chartDataManager.createApplicationActionEventPageElementDailySummaryChart(NAME,pageId));

					panelContent.addComponent(LabelFactory.createHeader2Label("General Page Mode Page Visit"));
					panelContent.addComponent(chartDataManager.createApplicationActionEventPageModeDailySummaryChart(NAME));

				}

				getPanel().setContent(panelContent);
				pageActionEventHelper.createPageEvent(ViewAction.VISIT_DOCUMENT_VIEW, ApplicationEventGroup.ADMIN, NAME, parameters, pageId);
			}

		}
	}

}