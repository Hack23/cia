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
package com.hack23.cia.web.widgets.charts;

import java.util.ArrayList;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * The Class SankeyChart.
 */
@JavaScript({ "https://www.gstatic.com/charts/loader.js", "SankeyChart.js" })
public class SankeyChart extends AbstractJavaScriptComponent {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The element id. */
    private static int elementId = 0;

    /** The my id. */
    private String myId;

    /**
	 * Instantiates a new bar chart.
	 *
	 * @param title
	 *            the title
	 * @param subtitle
	 *            the subtitle
	 */
    public SankeyChart() {
        myId = "gBarChartComponent" + (++elementId);
        callFunction("setId", myId);
        setSizeFull();
        setHeight("100%");
        setId(myId);
        getState().myId = myId;
    }

    @Override
    protected SankeyChartState getState() {
        return (SankeyChartState) super.getState();
    }


	/**
	 * Adds the data row.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param weight
	 *            the weight
	 */
	public void addDataRow(final String from, final String to, int weight) {
		final ArrayList<Object> valueData = new ArrayList<>();
        valueData.add(from);
        valueData.add(to);
        valueData.add(weight);
        getState().values.add(valueData);
	}

    /**
	 * Draw chart.
	 */
    public void drawChart() {
        callFunction("doDraw");
    }
}
