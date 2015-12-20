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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

import com.hack23.cia.model.external.riksdagen.person.impl.AssignmentData;
import com.hack23.cia.model.external.riksdagen.person.impl.DetailData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocument_;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager;
import com.hack23.cia.web.impl.ui.application.views.common.formfactory.FormFactory;
import com.hack23.cia.web.impl.ui.application.views.common.gridfactory.GridFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractPersonView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

/**
 * The Class PoliticianView.
 */
@Service
@Scope("prototype")
@VaadinView(value = PoliticianView.NAME, cached = true)
public final class PoliticianView extends AbstractPersonView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NAME. */
	public static final String NAME = UserViews.POLITICIAN_VIEW_NAME;

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
						|| parameters.contains(PageMode.Overview.toString())) {

					createOverviewContent(panelContent,pageId, personData);

				} else if (parameters.contains(PageMode.Charts.toString())) {

					panelContent.addComponent(new Label("Charts"));

				} else if (parameters.contains(PageMode.Indicators.toString())) {

					panelContent.addComponent(new Label("Indicators"));

					panelContent.addComponent(chartDataManager.createPersonLineChart(personData.getId()));

				} else if (parameters.contains(PoliticianPageMode.RoleSummary.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.RoleSummary.toString()));

					final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPolitician.class);

					final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer
							.load(personData.getId());

					final List<AssignmentData> assignmentList = personData.getPersonAssignmentData()
							.getAssignmentList();

					createRoleSummary(panelContent, assignmentList, viewRiksdagenPolitician);

				} else if (parameters.contains(PoliticianPageMode.RoleList.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.RoleList.toString()));

					final List<AssignmentData> assignmentList = personData.getPersonAssignmentData()
							.getAssignmentList();

					createRoleList(panelContent, assignmentList);

				} else if (parameters.contains(PoliticianPageMode.VoteHistory.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.VoteHistory.toString()));

					final BeanItemContainer<ViewRiksdagenVoteDataBallotPoliticianSummary> politicianBallotDataSource = new BeanItemContainer<ViewRiksdagenVoteDataBallotPoliticianSummary>(
							ViewRiksdagenVoteDataBallotPoliticianSummary.class,
							chartDataManager.getViewRiksdagenVoteDataBallotPoliticianSummary(personData.getId()));

					final Grid politicianBallotsBeanItemGrid = gridFactory
							.createBasicBeanItemGrid(politicianBallotDataSource, "Ballots", null, null, null, null, null);

					panelContent.addComponent(politicianBallotsBeanItemGrid);

				} else if (parameters.contains(PoliticianPageMode.BallotDecisionSummary.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.BallotDecisionSummary.toString()));

				} else if (parameters.contains(PoliticianPageMode.DocumentHistory.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.DocumentHistory.toString()));

					final DataContainer<ViewRiksdagenPoliticianDocument, String> politicianDocumentDataContainer = applicationManager
							.getDataContainer(ViewRiksdagenPoliticianDocument.class);

					final BeanItemContainer<ViewRiksdagenPoliticianDocument> politicianDocumentDataSource = new BeanItemContainer<ViewRiksdagenPoliticianDocument>(
							ViewRiksdagenPoliticianDocument.class, politicianDocumentDataContainer
									.findOrderedListByProperty(ViewRiksdagenPoliticianDocument_.personReferenceId, personData.getId(),ViewRiksdagenPoliticianDocument_.madePublicDate));

					final Grid politicianDocumentBeanItemGrid = gridFactory.createBasicBeanItemGrid(
							politicianDocumentDataSource, "Documents",
							new String[] { "id", "docId", "referenceName", "partyShortCode", "personReferenceId",
									"roleDescription", "documentType", "subType", "org", "label", "rm",
									"madePublicDate", "numberValue", "status", "title", "subTitle", "tempLabel",
									"orderNumber" },
							new String[] { "id", "partyShortCode", "personReferenceId", "numberValue", "orderNumber",
									"tempLabel", "referenceName" },
							"docId", new PageItemPropertyClickListener(UserViews.DOCUMENT_VIEW_NAME,"docId"), null);

					panelContent.addComponent(politicianDocumentBeanItemGrid);

				} else if (parameters.contains(PoliticianPageMode.DocumentActivity.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.DocumentActivity.toString()));

					final DCharts documentHistoryChart = chartDataManager
							.createPersonDocumentHistoryChart(personData.getId());

					panelContent.addComponent(documentHistoryChart);

				} else if (parameters.contains(PoliticianPageMode.RoleGhant.toString())) {

					panelContent.addComponent(new Label(PoliticianPageMode.RoleGhant.toString()));

					final List<AssignmentData> assignmentList = personData.getPersonAssignmentData()
							.getAssignmentList();

					createRoleGhant(panelContent, assignmentList);

				}

				getPanel().setContent(panelContent);

				pageActionEventHelper.createPageEvent(ViewAction.VISIT_POLITICIAN_VIEW, ApplicationEventGroup.USER, NAME, parameters, pageId);


				final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
						.getDataContainer(ViewRiksdagenPolitician.class);

				final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer
						.load(personData.getId());

				if (viewRiksdagenPolitician != null) {

					getPanel().setCaption("Politician:" + viewRiksdagenPolitician.getFirstName() + " "
							+ viewRiksdagenPolitician.getLastName() + "(" + viewRiksdagenPolitician.getParty() + ")");
				}

			}

		}

	}

	/**
	 * Creates the overview content.
	 *
	 * @param panelContent
	 *            the panel content
	 * @param pageId
	 *            the page id
	 * @param personData
	 *            the person data
	 */
	private void createOverviewContent(VerticalLayout panelContent, final String pageId, final PersonData personData) {
		panelContent.addComponent(new Label("overview"));

		final DataContainer<ViewRiksdagenPolitician, String> politicianDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPolitician.class);

		final ViewRiksdagenPolitician viewRiksdagenPolitician = politicianDataContainer.load(personData.getId());

		panelContent.addComponent(pageLinkFactory.createPoliticianPageLink(personData));

		panelContent.addComponent(new Image("", new ExternalResource(personData.getImageUrl192())));

		formFactory.addTextFields(panelContent, new BeanItem<ViewRiksdagenPolitician>(viewRiksdagenPolitician),
				ViewRiksdagenPolitician.class,
				Arrays.asList(new String[] { "firstName", "lastName", "gender", "bornYear", "party", "active",
						"firstAssignmentDate", "lastAssignmentDate", "currentAssignments", "currentMinistryAssignments",
						"currentSpeakerAssignments", "currentCommitteeAssignments", "currentPartyAssignments",
						"totalMinistryAssignments", "totalCommitteeAssignments", "totalSpeakerAssignments",
						"totalPartyAssignments", "totalAssignments", "totalDaysServed", "activeEu", "totalDaysServedEu",
						"activeGovernment", "totalDaysServedGovernment", "activeSpeaker", "totalDaysServedSpeaker",
						"activeCommittee", "totalDaysServedCommittee", "activeParliament", "totalDaysServedParliament",
						"activeParty", "totalDaysServedParty" }));

		panelContent.addComponent(gridFactory.createBasicBeanItemGrid(
				new BeanItemContainer<DetailData>(DetailData.class, personData.getPersonDetailData().getDetailList()),
				"Detail", new String[] { "detailType", "detail", "code" }, new String[] { "hjid", "intressentId" },
				null, null, null));

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
	private void createRoleSummary(final Layout roleSummaryLayoutTabsheet, final List<AssignmentData> assignmentList,
			final ViewRiksdagenPolitician viewRiksdagenPolitician) {

		roleSummaryLayoutTabsheet.addComponent(new Label("Assignments:" + assignmentList.size()));

		if (viewRiksdagenPolitician != null) {

			roleSummaryLayoutTabsheet.addComponent(new Label("Government experience:"
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedGovernment())));
			roleSummaryLayoutTabsheet.addComponent(new Label(
					"Speaker experience:" + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedSpeaker())));
			roleSummaryLayoutTabsheet.addComponent(new Label("Committee experience:"
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedCommittee())));
			roleSummaryLayoutTabsheet.addComponent(
					new Label("EU experience:" + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedEu())));
			roleSummaryLayoutTabsheet.addComponent(new Label("Parliament experience:"
					+ convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedParliament())));
			roleSummaryLayoutTabsheet.addComponent(new Label(
					"Party experience:" + convertToYearsString(viewRiksdagenPolitician.getTotalDaysServedParty())));
		}

	}

	/**
	 * Creates the role list.
	 *
	 * @param roleSummaryLayoutTabsheet
	 *            the role summary layout tabsheet
	 * @param assignmentList
	 *            the assignment list
	 */
	private void createRoleList(final Layout roleSummaryLayoutTabsheet, final List<AssignmentData> assignmentList) {

		final Comparator<AssignmentData> compare = new Comparator<AssignmentData>() {

			@Override
			public int compare(final AssignmentData o1, final AssignmentData o2) {
				return o1.getFromDate().compareTo(o2.getFromDate());
			}
		};

		Collections.sort(assignmentList, compare);

		roleSummaryLayoutTabsheet
				.addComponent(gridFactory.createBasicBeanItemGrid(
						new BeanItemContainer<AssignmentData>(AssignmentData.class, assignmentList), "Assignments",
						new String[] { "roleCode", "assignmentType", "status", "detail", "orgCode", "fromDate",
								"toDate" },
						new String[] { "hjid", "intressentId", "orderNumber", "orgCode" }, null, null, null));

	}

	/**
	 * Creates the role ghant.
	 *
	 * @param roleSummaryLayoutTabsheet
	 *            the role summary layout tabsheet
	 * @param assignmentList
	 *            the assignment list
	 */
	private void createRoleGhant(final Layout roleSummaryLayoutTabsheet, final List<AssignmentData> assignmentList) {

		final Comparator<AssignmentData> compare = new Comparator<AssignmentData>() {

			@Override
			public int compare(final AssignmentData o1, final AssignmentData o2) {
				return o1.getFromDate().compareTo(o2.getFromDate());
			}
		};

		Collections.sort(assignmentList, compare);

		roleSummaryLayoutTabsheet.addComponent(createGantt(assignmentList));
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
		final Comparator<? super Entry<String, List<AssignmentData>>> compare = new Comparator<Map.Entry<String, List<AssignmentData>>>() {

			@Override
			public int compare(final Entry<String, List<AssignmentData>> o1,
					final Entry<String, List<AssignmentData>> o2) {

				final Comparator<AssignmentData> compare = new Comparator<AssignmentData>() {

					@Override
					public int compare(final AssignmentData o1, final AssignmentData o2) {
						final int compareDate = o1.getFromDate().compareTo(o2.getFromDate());
						if (compareDate == 0) {
							final int compareType = o1.getAssignmentType().compareTo(o2.getAssignmentType());
							if (compareType == 0) {
								return o1.getDetail().compareTo(o2.getDetail());
							} else {
								return compareType;
							}
						} else {
						}
						return compareDate;

					}
				};

				Collections.sort(o1.getValue(), compare);
				Collections.sort(o2.getValue(), compare);

				return compare.compare(o1.getValue().get(0), o2.getValue().get(0));
			}
		};

		final SortedSet<Map.Entry<String, List<AssignmentData>>> sortedEntries = new TreeSet<Map.Entry<String, List<AssignmentData>>>(
				compare);
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

		final Function role = new Function<AssignmentData, String>() {

			@Override
			public String apply(final AssignmentData t) {
				if ("kammaruppdrag".equalsIgnoreCase(t.getAssignmentType())) {
					return "Riksdagsledamot";
				} else if ("Riksdagsorgan".equalsIgnoreCase(t.getAssignmentType())) {
					return t.getAssignmentType() + "." + t.getDetail();
				} else if ("uppdrag".equalsIgnoreCase(t.getAssignmentType())) {
					return t.getAssignmentType() + "." + t.getDetail();
				} else {
					return t.getAssignmentType() + "." + t.getDetail();
				}

			}

		};

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

				final Comparator<AssignmentData> compare = new Comparator<AssignmentData>() {

					@Override
					public int compare(final AssignmentData o1, final AssignmentData o2) {
						return o1.getFromDate().compareTo(o2.getFromDate());
					}
				};

				Collections.sort(assignments, compare);

				final String parliamentType = "kammaruppdrag";
				for (final AssignmentData assignmentData : assignments) {

					String subStepName = "";

					if (assignmentData.getStatus() != null) {
						subStepName = assignmentData.getStatus().toString();

					} else if (assignmentData.getRoleCode() != null) {
						subStepName = assignmentData.getRoleCode().toString();
					}

					final SubStep sameRoleSubStep = new SubStep(stepName + "." + subStepName);

					sameRoleSubStep.setBackgroundColor("A8D999");

					if ("LEDIG".equalsIgnoreCase(assignmentData.getStatus())) {
						sameRoleSubStep.setBackgroundColor("AA0000");
					} else {
						if (parliamentType.equalsIgnoreCase(assignmentData.getAssignmentType())) {
							sameRoleSubStep.setBackgroundColor("00AA00");
						} else {
							sameRoleSubStep.setBackgroundColor("0000AA");
						}

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

}