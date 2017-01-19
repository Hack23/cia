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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Resolution;

import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractGhantChartManagerImpl.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractGhantChartManagerImpl<T extends Object> {

	/**
	 * Instantiates a new abstract ghant chart manager impl.
	 */
	public AbstractGhantChartManagerImpl() {
		super();
	}

	/**
	 * Creates the role ghant.
	 *
	 * @param roleSummaryLayoutTabsheet
	 *            the role summary layout tabsheet
	 * @param assignmentList
	 *            the assignment list
	 */
	public final void createRoleGhant(final VerticalLayout roleSummaryLayoutTabsheet, final List<T> assignmentList) {

		final Comparator<T> compare = getComparator();

		Collections.sort(assignmentList, compare);

		final Gantt createGantt = createGantt(assignmentList);
		roleSummaryLayoutTabsheet.addComponent(createGantt);
		roleSummaryLayoutTabsheet.setExpandRatio(createGantt, ContentRatio.GRID);

	}

	protected abstract Gantt createGantt(List<T> assignmentList);

	protected abstract Comparator<T> getComparator();

	/**
	 * Strip dates after current date.
	 *
	 * @param toDate
	 *            the to date
	 * @return the date
	 */
	protected static final Date stripDatesAfterCurrentDate(final Date toDate) {
		final DateTime currentTime = new DateTime();

		if (currentTime.isBefore(toDate.getTime())) {
			return currentTime.plusDays(1).toDate();
		} else {
			return toDate;
		}
	}

	protected static final Gantt createGantt() {
		final Gantt gantt = new Gantt();
		gantt.setSizeFull();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(100, Unit.PERCENTAGE);
		gantt.setResizableSteps(false);
		gantt.setMovableSteps(false);
		gantt.setResolution(Resolution.Week);
		return gantt;
	}

}
