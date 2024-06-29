/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.common.converters;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.testfoundation.AbstractUnitTest;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

/**
 * The Class ListPropertyConverterTest.
 */
public class ListPropertyConverterTest extends AbstractUnitTest {

	/**
	 * Apply no such method ignore failure test.
	 */
	@Test
	public void applyNoSuchMethodIgnoreFailureTest() {
		final String emptyString = new ListPropertyConverter("party", "party").apply(new ViewRiksdagenCommittee());
		assertEquals("",emptyString);
	}

	/**
	 * Convert to model verify dummy impl test.
	 */
	@Test
	public void convertToModelVerifyDummyImplTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final Result<List<?>> convertToModel = new ListPropertyConverter("", null).convertToModel("value", context);
		assertNotNull(convertToModel);
		assertFalse(convertToModel.isError());
	}

	/**
	 * Convert to presentation fallback no value test.
	 */
	@Test
	public void convertToPresentationFallbackNoValueTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final String emptyString = new ListPropertyConverter("partyId", "partyId","partyName").convertToPresentation(Arrays.asList(new ViewRiksdagenParty()), context);
		assertEquals("[ ]",emptyString);
	}

	/**
	 * Convert to presentation fallback test.
	 */
	@Test
	public void convertToPresentationFallbackTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final String emptyString = new ListPropertyConverter("partyId", "partyId","partyName").convertToPresentation(Arrays.asList(new ViewRiksdagenParty().withPartyName("PartyName")), context);
		assertEquals("[PartyName ]",emptyString);
	}

	/**
	 * Convert to presentation no value test.
	 */
	@Test
	public void convertToPresentationNoValueTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final String emptyString = new ListPropertyConverter("partyId", "partyId").convertToPresentation(Arrays.asList(new ViewRiksdagenParty()), context);
		assertEquals("[ ]",emptyString);
	}


	/**
	 * Convert to presentation null value failure test.
	 */
	@Test
	public void convertToPresentationNullValueFailureTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final String emptyString = new ListPropertyConverter("", null).convertToPresentation(null, context);
		assertEquals("",emptyString);
	}

	/**
	 * Convert to presentation test.
	 */
	@Test
	public void convertToPresentationTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final String emptyString = new ListPropertyConverter("partyId", "partyId").convertToPresentation(Arrays.asList(new ViewRiksdagenParty().withPartyId("partyId")), context);
		assertEquals("[partyId ]",emptyString);
	}


	/**
	 * Convert to presentation wrong path method value failure test.
	 */
	@Test
	public void convertToPresentationWrongPathMethodValueFailureTest() {
		final ValueContext context = new ValueContext(Locale.ENGLISH);
		final String emptyString = new ListPropertyConverter("party", "party").convertToPresentation(Arrays.asList(new ViewRiksdagenCommittee()), context);
		assertEquals("[ ]",emptyString);
	}

}
