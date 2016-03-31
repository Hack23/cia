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
package com.hack23.cia.web.impl.ui.application.views.user.politician;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

import com.hack23.cia.model.external.riksdagen.person.impl.AssignmentData;
import com.hack23.cia.model.external.riksdagen.person.impl.DetailData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.GenericChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractPersonView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PoliticianView.
 */
@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(value = PoliticianView.NAME, cached = true)
public final class PoliticianView extends AbstractPersonView {


	private static final String DEPARTEMENT = "Departement";

	private static final String LEDIG = "LEDIG";

	private static final String GOVERNMENT_EXPERIENCE = "Government experience:";

	private static final String EU_EXPERIENCE = "EU experience:";

	private static final String TOTAL_ASSIGNMENTS = "Total Assignments:";

	private static final String SPEAKER_EXPERIENCE = "Speaker experience:";

	private static final String POLITICIAN = "Politician:";

	private static final String PARTY_EXPERIENCE = "Party experience:";

	private static final String KAMMARUPPDRAG = "kammaruppdrag";

	private static final String COMMITTEE_EXPERIENCE = "Committee experience:";

	private static final String PARLIAMENT_EXPERIENCE = "Parliament experience:";

	private static final String OVERVIEW = "overview";

	private static final String INDICATORS = "Indicators";

	private static final String CURRENT_PAGE_VISIT_HISTORY = "Current Page Visit History";

	private static final String GENERAL_PAGE_MODE_PAGE_VISIT = "General Page Mode Page Visit";

	private static final String CHARTS_NOT_IMPLEMENTED = "Charts Not Implemented";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_VIEW_NAME;

	/** The application manager. */
	@Autowired
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

	@Autowired
	private transient GenericChartDataManager<ViewRiksdagenVoteDataBallotPoliticianSummary> viewRiksdagenVoteDataBallotPoliticianSummaryChartDataManager;

	/**
	 * Instantiates a new politician view.
	 */
	public PoliticianView() {
		super();
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(NAME);
	}

	//@Secured({ "ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
	@Override
	public void enter(final ViewChangeEvent event) {
		final String parameters = event.getParameters();

		if (parameters != null) {

			final String pageId = parameters.substring(parameters.lastIndexOf('/') + "/".length(), parameters.length());

			final DataContainer<PersonData, String> dataContainer = applicationManager
					.getDataContainer(PersonData.class);

			final PersonData personData = dataContainer.load(pageId);
			if (personData != null) {

				menuItemFactory.createPoliticianMenuBar(getBarmenu(), pageId);
				final VerticalLayout panelContent = new VerticalLayout();
				panelContent.setSizeFull();
				panelContent.setMargin(true);

				if (StringUtils.isEmpty(parameters) || parameters.equals(pageId)
						|| parameters.contains(PageMode.OVERVIEW.toString())) {

					createOverviewContent(panelContent,personData);

				} else if (parameters.contains(PageMode.CHARTS.toString())) {

					panelContent.addComponent(LabelFactory.createHeader2Label(CHARTS_NOT_IMPLEMENTED));

				} else if (parameters.contains(PageMode.INDICATORS.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(INDICATORS);
					panelContent.addComponent(createHeader2Label);

					final DCharts createPersonLineChart = chartDataManager.createPersonLineChart(personData.getId());
					panelContent.addComponent(createPersonLineChart);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(createPersonLineChart, ContentRatio.GRID);

				} else if (parameters.contains(PoliticianPageMode.ROLESUMMARY.toString())) {

					final Label createHeader2Label = LabelFactory
							.createHeader2Label(PoliticianPageMode.ROLESUMMARY.toString());
					panelContent.addComponent(createHeader2Label);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);

					final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPolitician.class);

					final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer
							.load(personData.getId());

					final List<AssignmentData> assignmentList = personData.getPersonAssignmentData()
							.getAssignmentList();

					createRoleSummary(panelContent, assignmentList, viewRiksdagenPolitician);

				} else if (parameters.contains(PoliticianPageMode.ROLELIST.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(PoliticianPageMode.ROLELIST.toString());
					panelContent.addComponent(createHeader2Label);
					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);

					final List<AssignmentData> assignmentList = personData.getPersonAssignmentData()
							.getAssignmentList();

					createRoleList(panelContent, assignmentList);

				} else if (parameters.contains(PoliticianPageMode.VOTEHISTORY.toString())) {

					final Label createHeader2Label = LabelFactory
							.createHeader2Label(PoliticianPageMode.VOTEHISTORY.toString());
					panelContent.addComponent(createHeader2Label);

					final BeanItemContainer<ViewRiksdagenVoteDataBallotPoliticianSummary> politicianBallotDataSource = new BeanItemContainer<>(
							ViewRiksdagenVoteDataBallotPoliticianSummary.class,
							viewRiksdagenVoteDataBallotPoliticianSummaryChartDataManager.findByValue(personData.getId()));

					final Grid politicianBallotsBeanItemGrid = gridFactory.createBasicBeanItemNestedPropertiesGrid(
							politicianBallotDataSource, "Ballots",
							new String[] { "embeddedId.ballotId", "embeddedId.concern", "embeddedId.issue" },
							new String[] { "voteDate", "rm", "label", "embeddedId.concern", "embeddedId.issue", "vote",
									"won", "partyWon", "rebel", "noWinner", "approved", "partyApproved", "totalVotes",
									"partyTotalVotes", "yesVotes", "partyYesVotes", "noVotes", "partyNoVotes",
									"partyAbstainVotes", "abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear",
									"partyAvgBornYear", "avgBornYear", "gender", "partyPercentageMale",
									"percentageMale", "ballotType", "embeddedId.ballotId" },
							new String[] { "embeddedId", "partyNoWinner", "partyPercentageYes", "partyPercentageNo",
									"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes", "percentageNo",
									"percentageAbsent", "percentageAbstain", "firstName", "lastName", "party" },
							null, null, null);

					panelContent.addComponent(politicianBallotsBeanItemGrid);
					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(politicianBallotsBeanItemGrid, ContentRatio.GRID);

				} else if (parameters.contains(PoliticianPageMode.BALLOTDECISIONSUMMARY.toString())) {

					final Label createHeader2Label = LabelFactory
							.createHeader2Label(PoliticianPageMode.BALLOTDECISIONSUMMARY.toString());
					panelContent.addComponent(createHeader2Label);

					final DataContainer<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary, ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class);

					final List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> decisionPartySummaryList = committeeBallotDecisionPartyDataContainer
							.findOrderedByPropertyListByEmbeddedProperty(
									ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class,
									ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_.embeddedId,
									ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId.class,
									ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId_.intressentId, pageId,
									ViewRiksdagenCommitteeBallotDecisionPoliticianSummary_.voteDate);

					final BeanItemContainer<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary> committeeBallotDecisionPartyDataSource = new BeanItemContainer<>(
							ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class, decisionPartySummaryList);

					final Grid committeeBallotDecisionPartyBeanItemGrid = gridFactory
							.createBasicBeanItemNestedPropertiesGrid(committeeBallotDecisionPartyDataSource,
									"Committee Ballot Decision Politician Summary",
									new String[] { "embeddedId.concern", "embeddedId.issue" },
									new String[] { "voteDate", "rm", "org", "committeeReport", "title", "subTitle",
											"winner", "embeddedId.concern", "embeddedId.issue", "vote", "won", "rebel",
											"noWinner", "approved", "partyApproved", "againstProposalNumber",
											"againstProposalParties", "totalVotes", "partyTotalVotes", "yesVotes",
											"partyYesVotes", "noVotes", "partyNoVotes", "partyAbstainVotes",
											"abstainVotes", "partyAbsentVotes", "absentVotes", "bornYear",
											"partyAvgBornYear", "avgBornYear", "ballotType", "decisionType",
											"ballotId" },
									new String[] { "label", "endNumber", "publicDate", "createdDate", "embeddedId",
											"partyNoWinner", "partyPercentageYes", "partyPercentageNo",
											"partyPercentageAbsent", "partyPercentageAbstain", "percentageYes",
											"percentageNo", "percentageAbsent", "percentageAbstain", "firstName",
											"lastName", "party" },
									null, null, null);

					panelContent.addComponent(committeeBallotDecisionPartyBeanItemGrid);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(committeeBallotDecisionPartyBeanItemGrid, ContentRatio.GRID);

				} else if (parameters.contains(PoliticianPageMode.DOCUMENTHISTORY.toString())) {

					final Label createHeader2Label = LabelFactory
							.createHeader2Label(PoliticianPageMode.DOCUMENTHISTORY.toString());
					panelContent.addComponent(createHeader2Label);

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<>(
							ViewRiksdagenPoliticianDocument.class,
							politicianDocumentDataContainer.findOrderedListByProperty(
									ViewRiksdagenPoliticianDocument_.personReferenceId, personData.getId(),
									ViewRiksdagenPoliticianDocument_.madePublicDate));

					final Grid politicianDocumentBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDocumentDataSource, "Documents",
							new String[] { "referenceName", "partyShortCode", "personReferenceId", "roleDescription",
									"documentType", "subType", "title", "subTitle", "org", "rm", "madePublicDate", "id",
									"docId", "tempLabel", "label", "numberValue", "orderNumber", "status" },
							new String[] { "id", "partyShortCode", "personReferenceId", "numberValue", "orderNumber",
									"tempLabel", "referenceName" },
							null, new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME, "docId"), null);

					panelContent.addComponent(politicianDocumentBeanItemGrid);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(politicianDocumentBeanItemGrid, ContentRatio.GRID);

				} else if (parameters.contains(PoliticianPageMode.DOCUMENTACTIVITY.toString())) {

					final Label createHeader2Label = LabelFactory
							.createHeader2Label(PoliticianPageMode.DOCUMENTACTIVITY.toString());
					panelContent.addComponent(createHeader2Label);

					final DCharts documentHistoryChart = chartDataManager
							.createPersonDocumentHistoryChart(personData.getId());

					panelContent.addComponent(documentHistoryChart);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(documentHistoryChart, ContentRatio.GRID);

				} else if (parameters.contains(PoliticianPageMode.ROLEGHANT.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(PoliticianPageMode.ROLEGHANT.toString());
					panelContent.addComponent(createHeader2Label);
					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);

					final List<AssignmentData> assignmentList = personData.getPersonAssignmentData()
							.getAssignmentList();

					createRoleGhant(panelContent, assignmentList);

				} else if (parameters.contains(PageMode.PAGEVISITHISTORY.toString())) {

					final Label createHeader2Label = LabelFactory.createHeader2Label(CURRENT_PAGE_VISIT_HISTORY);
					panelContent.addComponent(createHeader2Label);
					final DCharts createApplicationActionEventPageElementDailySummaryChart = chartDataManager
							.createApplicationActionEventPageElementDailySummaryChart(NAME, pageId);
					panelContent.addComponent(createApplicationActionEventPageElementDailySummaryChart);

					final Label createHeader2Label2 = LabelFactory.createHeader2Label(GENERAL_PAGE_MODE_PAGE_VISIT);
					panelContent.addComponent(createHeader2Label2);
					final DCharts createApplicationActionEventPageModeDailySummaryChart = chartDataManager
							.createApplicationActionEventPageModeDailySummaryChart(NAME);
					panelContent.addComponent(createApplicationActionEventPageModeDailySummaryChart);

					panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
					panelContent.setExpandRatio(createApplicationActionEventPageElementDailySummaryChart,ContentRatio.GRID);
					panelContent.setExpandRatio(createHeader2Label2,ContentRatio.SMALL);
					panelContent.setExpandRatio(createApplicationActionEventPageModeDailySummaryChart,ContentRatio.GRID);

				}

				getPanel().setContent(panelContent);

				pageActionEventHelper.createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER,
						NAME, parameters, pageId);

				final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
						.getDataContainer(ViewRiksdagenPolitician.class);

				final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer
						.load(personData.getId());

				if (viewRiksdagenPolitician != null) {

					getPanel().setCaption(POLITICIAN + viewRiksdagenPolitician.getFirstName() + " "
							+ viewRiksdagenPolitician.getLastName() + '(' + viewRiksdagenPolitician.getParty() + ')');
				}

			}

		}

	}

	/**
	 * Creates the overview content.
	 *
	 * @param panelContent
	 *            the panel content
	 * @param personData
	 *            the person data
	 */
	private void createOverviewContent(final VerticalLayout panelContent,final PersonData personData) {
		final Label createHeader2Label = LabelFactory.createHeader2Label(OVERVIEW);
		panelContent.addComponent(createHeader2Label);

		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPolitician.class);

		final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer.load(personData.getId());

		final Link createPoliticianPageLink = pageLinkFactory.createPoliticianPageLink(personData);
		panelContent.addComponent(createPoliticianPageLink);

		final Image image = new Image("", new ExternalResource(personData.getImageUrl192()));

		final HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		panelContent.addComponent(horizontalLayout);

		final Panel formPanel = new Panel();
		formPanel.setSizeFull();

		horizontalLayout.addComponent(formPanel);
		horizontalLayout.addComponent(image);

		final FormLayout formContent = new FormLayout();
		formPanel.setContent(formContent);

		formFactory.addTextFields(formContent, new BeanItem<>(viewRiksdagenPolitician), ViewRiksdagenPolitician.class,
				Arrays.asList(new String[] { "firstName", "lastName", "gender", "bornYear", "party", "active",
						"firstAssignmentDate", "lastAssignmentDate", "currentAssignments", "currentMinistryAssignments",
						"currentSpeakerAssignments", "currentCommitteeAssignments", "currentPartyAssignments",
						"totalMinistryAssignments", "totalCommitteeAssignments", "totalSpeakerAssignments",
						"totalPartyAssignments", "totalAssignments", "totalDaysServed", "activeEu", "totalDaysServedEu",
						"activeGovernment", "totalDaysServedGovernment", "activeSpeaker", "totalDaysServedSpeaker",
						"activeCommittee", "totalDaysServedCommittee", "activeParliament", "totalDaysServedParliament",
						"activeParty", "totalDaysServedParty" }));

		final Grid createBasicBeanItemGrid = gridFactory.createBasicBeanItemGrid(
				new BeanItemContainer<>(DetailData.class, personData.getPersonDetailData().getDetailList()), "Detail",
				new String[] { "detailType", "detail", "code" }, new String[] { "hjid", "intressentId" }, null, null,
				null);
		panelContent.addComponent(createBasicBeanItemGrid);


		panelContent.setExpandRatio(createHeader2Label,ContentRatio.SMALL);
		panelContent.setExpandRatio(createPoliticianPageLink, ContentRatio.SMALL);
		panelContent.setExpandRatio(horizontalLayout, ContentRatio.GRID);

		panelContent.setExpandRatio(createBasicBeanItemGrid, ContentRatio.SMALL_GRID);


	}

	/**
	 * Creates the role summary.
	 *
	 * @param roleSummaryLayoutTabsheet
	 *            the role summary layout tabsheet
	 * @param assignmentList
	 *            the assignment list
	 * @param viewRiksdagenPolitician
	 *            the view riksdagen politician
	 */
	private void createRoleSummary(final VerticalLayout roleSummaryLayoutTabsheet, final List<AssignmentData> assignmentList,
			final ViewRiksdagenPolitician viewRiksdagenPolitician) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		layout.addComponent(new Label(TOTAL_ASSIGNMENTS + assignmentList.size()));

		if (viewRiksdagenPolitician != null) {

			layout.addComponent(new Label(GOVERNMENT_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedGovernment())));
			layout.addComponent(new Label(
					SPEAKER_EXPERIENCE + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedSpeaker())));
			layout.addComponent(new Label(COMMITTEE_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedCommittee())));
			layout.addComponent(
					new Label(EU_EXPERIENCE + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedEu())));
			layout.addComponent(new Label(PARLIAMENT_EXPERIENCE
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedParliament())));
			layout.addComponent(new Label(
					PARTY_EXPERIENCE + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedParty())));
		}

		roleSummaryLayoutTabsheet.addComponent(layout);
		roleSummaryLayoutTabsheet.setExpandRatio(layout, ContentRatio.GRID);



	}

	/**
	 * Creates the role list.
	 *
	 * @param roleSummaryLayoutTabsheet
	 *            the role summary layout tabsheet
	 * @param assignmentList
	 *            the assignment list
	 */
	private void createRoleList(final VerticalLayout roleSummaryLayoutTabsheet, final List<AssignmentData> assignmentList) {

		final Comparator<AssignmentData> compare = (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());

		Collections.sort(assignmentList, compare);

		final Grid createBasicBeanItemGrid = gridFactory
				.createBasicBeanItemGrid(new BeanItemContainer<>(AssignmentData.class, assignmentList), "Assignments",
						new String[] { "roleCode", "assignmentType", "status", "detail", "orgCode", "fromDate",
								"toDate" },
						new String[] { "hjid", "intressentId", "orderNumber", "orgCode" }, null, null, null);
		roleSummaryLayoutTabsheet.addComponent(createBasicBeanItemGrid);

		roleSummaryLayoutTabsheet.setExpandRatio(createBasicBeanItemGrid, ContentRatio.GRID);

	}

	/**
	 * Creates the role ghant.
	 *
	 * @param roleSummaryLayoutTabsheet
	 *            the role summary layout tabsheet
	 * @param assignmentList
	 *            the assignment list
	 */
	private void createRoleGhant(final VerticalLayout roleSummaryLayoutTabsheet,
			final List<AssignmentData> assignmentList) {

		final Comparator<AssignmentData> compare = (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());

		Collections.sort(assignmentList, compare);

		final Gantt createGantt = createGantt(assignmentList);
		roleSummaryLayoutTabsheet.addComponent(createGantt);
		roleSummaryLayoutTabsheet.setExpandRatio(createGantt, ContentRatio.GRID);

	}

	/**
	 * Entries sorted by values.
	 *
	 * @param map
	 *            the map
	 * @return the sorted set
	 */
	private static SortedSet<Map.Entry<String, List<AssignmentData>>> entriesSortedByValues(
			final Map<String, List<AssignmentData>> map) {
		final Comparator<? super Entry<String, List<AssignmentData>>> compare = (o1, o2) -> {

			final Comparator<AssignmentData> compare1 = (o11, o21) -> {
				final int compareDate = o11.getFromDate().compareTo(o21.getFromDate());
				if (compareDate == 0) {
					final int compareType = o11.getAssignmentType().compareTo(o21.getAssignmentType());
					if (compareType == 0) {
						return o11.getDetail().compareTo(o21.getDetail());
					} else {
						return compareType;
					}
				}

				return compareDate;
			};

			Collections.sort(o1.getValue(), compare1);
			Collections.sort(o2.getValue(), compare1);

			return compare1.compare(o1.getValue().get(0), o2.getValue().get(0));
		};

		final SortedSet<Map.Entry<String, List<AssignmentData>>> sortedEntries = new TreeSet<>(compare);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	/**
	 * Creates the gantt.
	 *
	 * @param assignmentList
	 *            the assignment list
	 * @return the gantt
	 */
	private Gantt createGantt(final List<AssignmentData> assignmentList) {

		final Function<AssignmentData, String> role = new RoleMapping();

		final Map<String, List<AssignmentData>> assignmentListMap = assignmentList.stream()
				.collect(Collectors.groupingBy(role, TreeMap::new, Collectors.toList()));

		final Gantt gantt = new Gantt();
		gantt.setSizeFull();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(100, Unit.PERCENTAGE);
		gantt.setResizableSteps(false);
		gantt.setMovableSteps(false);
		gantt.setResolution(Resolution.Week);

		if (!assignmentList.isEmpty()) {

			gantt.setStartDate(assignmentList.get(0).getFromDate());
			gantt.setEndDate(assignmentList.get(assignmentList.size() - 1).getToDate());

			for (final Entry<String, List<AssignmentData>> entry : entriesSortedByValues(assignmentListMap)) {

				final String stepName = entry.getKey();

				final Step step = new Step();
				step.setDescription(stepName);

				final List<AssignmentData> assignments = entry.getValue();

				final Comparator<AssignmentData> compare = (o1, o2) -> o1.getFromDate().compareTo(o2.getFromDate());

				Collections.sort(assignments, compare);

				final String parliamentType = KAMMARUPPDRAG;
				for (final AssignmentData assignmentData : assignments) {

					String subStepName = "";

					if (assignmentData.getStatus() != null) {
						subStepName = assignmentData.getStatus();

					} else if (assignmentData.getRoleCode() != null) {
						subStepName = assignmentData.getRoleCode();
					}

					final SubStep sameRoleSubStep = new SubStep(stepName + '.' + subStepName);

					sameRoleSubStep.setBackgroundColor("A8D999");

					if (LEDIG.equalsIgnoreCase(assignmentData.getStatus())) {
						sameRoleSubStep.setBackgroundColor("e3e3e3");
					} else if (parliamentType.equalsIgnoreCase(assignmentData.getAssignmentType())) {
						sameRoleSubStep.setBackgroundColor("0eab76");
					} else if (DEPARTEMENT.equalsIgnoreCase(assignmentData.getAssignmentType())) {

						sameRoleSubStep.setBackgroundColor("ded858");
					} else {
						sameRoleSubStep.setBackgroundColor("3271c8");
					}

					sameRoleSubStep.setStartDate(assignmentData.getFromDate().getTime());
					sameRoleSubStep.setEndDate(assignmentData.getToDate().getTime());

					step.addSubStep(sameRoleSubStep);
				}

				gantt.addStep(step);
			}
		}

		return gantt;
	}

	/**
	 * The Class RoleMapping.
	 */
	private static final class RoleMapping implements Function<AssignmentData, String> {
		private static final String RIKSDAGSLEDAMOT = "Riksdagsledamot";

		@Override
		public String apply(final AssignmentData t) {
			if (KAMMARUPPDRAG.equalsIgnoreCase(t.getAssignmentType())) {
				return RIKSDAGSLEDAMOT;
			} else {
				return t.getAssignmentType() + '.' + t.getDetail() + " " + t.getRoleCode();
			}

		}
	}

}