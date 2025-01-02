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
package com.hack23.cia.model.common.impl.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class XmlDateTypeAdapter.
 */
public final class XmlDateTypeAdapter extends XmlAdapter<String,Date> {

	private static final String YYYY_MM_DD_HH_MM_SS = "2001-01-01 00:00:00";

	/** The Constant YYYY_MM_DD. */
	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(XmlDateTypeAdapter.class);

	/**
	 * Instantiates a new xml time type adapter.
	 */
	public XmlDateTypeAdapter() {
		super();
	}


	@Override
	public Date unmarshal(final String s) {
		if (s == null) {
			return null;
		}
		// hack to supported swedish riksdags xml.
		final String dateStr = s.trim();
		if (YYYY_MM_DD_HH_MM_SS.length() == dateStr.length()
				|| YYYY_MM_DD.length() == dateStr.length()) {
			try {
				return new SimpleDateFormat(
						YYYY_MM_DD,Locale.ENGLISH).parse(dateStr.substring(0,YYYY_MM_DD.length()));
			} catch (final ParseException e) {
				LOGGER.warn("Problem parsing date from str:{}",s,e);
				return null;
			}
		} else if (dateStr.length() > YYYY_MM_DD.length()) {
			try {
				return DatatypeConverter.parseDate(s).getTime();
			} catch (final Exception e) {
				LOGGER.warn("Problem parsing date from str:{}",s,e);
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public String marshal(final Date dt) {
		if (dt == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return DatatypeConverter.printDate(c);
	}

}
