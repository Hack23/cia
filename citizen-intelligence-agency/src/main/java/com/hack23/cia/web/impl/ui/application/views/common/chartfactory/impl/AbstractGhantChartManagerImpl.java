/*
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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.AbstractStep.CaptionMode;
import org.tltv.gantt.client.shared.Resolution;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 * The Class AbstractGhantChartManagerImpl.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractGhantChartManagerImpl<T extends Object> {

	/** The Constant CONTENT_SEPARATOR. */
	private static final char CONTENT_SEPARATOR = ' ';

	/** The Constant FILTER_DATA_BEFORE_YEAR. */
	private static final int FILTER_DATA_BEFORE_YEAR = 2000;

	/** The Constant PARTY_END_TAG. */
	private static final char PARTY_END_TAG = ')';

	/** The Constant PARTY_START_TAG. */
	private static final String PARTY_START_TAG = " (";

	/**
	 * Instantiates a new abstract ghant chart manager impl.
	 */
	public AbstractGhantChartManagerImpl() {
		super();
	}

	/**
	 * Creates the gantt.
	 *
	 * @return the gantt
	 */
	private static final Gantt createGantt() {
		final Gantt gantt = new Gantt();
		gantt.setSizeFull();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(100, Unit.PERCENTAGE);
		gantt.setResizableSteps(false);
		gantt.setMovableSteps(false);
		gantt.setResolution(Resolution.Week);
		return gantt;
	}

	/**
	 * Strip dates after current date.
	 *
	 * @param toDate
	 *            the to date
	 * @return the date
	 */
	private static final Date stripDatesAfterCurrentDate(final Date toDate) {
		return DateUtils.stripDatesAfterCurrentDate(toDate);
	}

	/**
	 * Adds the view generic role member to step.
	 *
	 * @param roleName
	 *            the role name
	 * @param step
	 *            the step
	 * @param roleAssignments
	 *            the role assignments
	 * @param stepMapping
	 *            the step mapping
	 */
	private void addViewGenericRoleMemberToStep(final String roleName, final Step step, final List<T> roleAssignments,
			final StepMapping<T> stepMapping) {

		for (final T roleMember : roleAssignments) {

			String subStepName = "";

			if (stepMapping.getRoleCode(roleMember) != null) {
				subStepName = new StringBuilder().append(stepMapping.getFirstName(roleMember))
						.append(CONTENT_SEPARATOR).append(stepMapping.getLastName(roleMember))
						.append(PARTY_START_TAG).append(stepMapping.getParty(roleMember)).append(PARTY_END_TAG)
						.toString();
			}

			final SubStep sameRoleSubStep = new SubStep(roleName + '.' + subStepName,CaptionMode.HTML);
			sameRoleSubStep.setDescription(roleName + '.' + subStepName);
			sameRoleSubStep.setBackgroundColor(stepMapping.getBackgroundColor(roleMember));

			sameRoleSubStep.setStartDate(stepMapping.getFromDate(roleMember).getTime());
			sameRoleSubStep.setEndDate(stripDatesAfterCurrentDate(stepMapping.getToDate(roleMember)).getTime());

			step.addSubStep(sameRoleSubStep);
		}
	}

	/**
	 * Creates the generic gantt.
	 *
	 * @param assignmentList
	 *            the assignment list
	 * @param roleMapping
	 *            the role mapping
	 * @param stepMapping
	 *            the step mapping
	 * @return the gantt
	 */
	private Gantt createGenericGantt(final List<T> assignmentList, final Function<T, String> roleMapping,
			final StepMapping<T> stepMapping) {

		final Map<String, List<T>> assignmentListMap = assignmentList.stream()
				.collect(Collectors.groupingBy(roleMapping, TreeMap::new, Collectors.toList()));

		final Gantt gantt = createGantt();

		if (!assignmentList.isEmpty()) {

			gantt.setStartDate(stepMapping.getFromDate(assignmentList.get(0)));
			gantt.setEndDate(
					stripDatesAfterCurrentDate(stepMapping.getToDate(assignmentList.get(assignmentList.size() - 1))));

			for (final Entry<String, List<T>> entry : entriesSortedByValues(assignmentListMap, stepMapping)) {

				final String stepName = entry.getKey();

				final Step step = new Step(stepName,CaptionMode.HTML);
				step.setDescription(stepName);

				final List<T> assignments = entry.getValue();

				Collections.sort(assignments, getComparator());

				addViewGenericRoleMemberToStep(stepName, step, assignments, stepMapping);

				gantt.addStep(step);
			}
		}

		return gantt;
	}


	/**
	 * Creates the role ghant.
	 *
	 * @param roleSummaryLayoutTabsheet the role summary layout tabsheet
	 * @param assignmentList            the assignment list
	 */
	public final void createRoleGhant(final AbstractOrderedLayout roleSummaryLayoutTabsheet, final Collection<T> assignmentList) {

		final List<T> list = filterAndSortAssignments(assignmentList);

		final Gantt createGantt = createGenericGantt(list, getRoleMapping(), getStepMapping());
		roleSummaryLayoutTabsheet.addComponent(createGantt);
		roleSummaryLayoutTabsheet.setExpandRatio(createGantt, ContentRatio.GRID);

	}

	/**
	 * Filter and sort assignments.
	 *
	 * @param assignmentList
	 *            the assignment list
	 * @return the list
	 */
	private List<T> filterAndSortAssignments(final Collection<T> assignmentList) {
		final Comparator<T> compare = getComparator();

		final List<T> list = assignmentList.stream().filter(
				(final T x) -> new DateTime(getStepMapping().getFromDate(x).getTime()).getYear() > FILTER_DATA_BEFORE_YEAR)
				.collect(Collectors.toList());

		Collections.sort(list, compare);
		return list;
	}

	/**
	 * Entries sorted by values.
	 *
	 * @param map
	 *            the map
	 * @param stepMapping
	 *            the step mapping
	 * @return the sorted set
	 */
	private SortedSet<Map.Entry<String, List<T>>> entriesSortedByValues(final Map<String, List<T>> map,
			final StepMapping<T> stepMapping) {
		final Comparator<? super Entry<String, List<T>>> compare = (o1, o2) -> {

			final Comparator<T> compare1 = (o11, o21) -> {
				final int compareDate = stepMapping.getFromDate(o11).compareTo(stepMapping.getFromDate(o21));
				if (compareDate == 0) {
					final int compareType = stepMapping.getRoleCode(o11).compareTo(stepMapping.getRoleCode(o21));
					if (compareType == 0) {
						return stepMapping.getOrg(o11).compareTo(stepMapping.getOrg(o21));
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

		final SortedSet<Map.Entry<String, List<T>>> sortedEntries = new TreeSet<>(compare);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	/**
	 * Gets the comparator.
	 *
	 * @return the comparator
	 */
	protected abstract Comparator<T> getComparator();

	/**
	 * Gets the role mapping.
	 *
	 * @return the role mapping
	 */
	protected abstract Function<T, String> getRoleMapping();

	/**
	 * Gets the step mapping.
	 *
	 * @return the step mapping
	 */
	protected abstract StepMapping<T> getStepMapping();

	/**
	 * The Interface StepMapping.
	 *
	 * @param <T>
	 *            the generic type
	 */
	public interface StepMapping<T> {

		/**
		 * Gets the background color.
		 *
		 * @param t
		 *            the t
		 * @return the background color
		 */
		String getBackgroundColor(T t);

		/**
		 * Gets the first name.
		 *
		 * @param assignmentData
		 *            the assignment data
		 * @return the first name
		 */
		Object getFirstName(T assignmentData);

		/**
		 * Gets the from date.
		 *
		 * @param t
		 *            the t
		 * @return the from date
		 */
		Date getFromDate(T t);

		/**
		 * Gets the last name.
		 *
		 * @param assignmentData
		 *            the assignment data
		 * @return the last name
		 */
		Object getLastName(T assignmentData);

		/**
		 * Gets the org.
		 *
		 * @param t
		 *            the t
		 * @return the org
		 */
		String getOrg(T t);

		/**
		 * Gets the party.
		 *
		 * @param t
		 *            the t
		 * @return the party
		 */
		String getParty(T t);

		/**
		 * Gets the role code.
		 *
		 * @param t
		 *            the t
		 * @return the role code
		 */
		String getRoleCode(T t);

		/**
		 * Gets the to date.
		 *
		 * @param t
		 * @return the to date
		 */
		Date getToDate(T t);

	}

}
