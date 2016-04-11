/*
 * Copyright 2010 James Pether Sörling
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
package com.hack23.cia.model.common.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class XmlTimeTypeAdapter.
 */
public final class XmlTimeTypeAdapter {

	private static final String YYYY_MM_DD_HH_MM_SS = "2001-01-01 00:00:00";

	/** The Constant YYYY_MM_DD. */
	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(XmlTimeTypeAdapter.class);


	/**
	 * Instantiates a new xml time type adapter.
	 */
	private XmlTimeTypeAdapter() {
		super();
	}

	/**
	 * Parses the date.
	 *
	 * @param s
	 *            the s
	 * @return the date
	 */
	public static Date parseDate(final String s) {
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
			}
		}
		return DatatypeConverter.parseDate(s).getTime();
	}

	/**
	 * Prints the date.
	 *
	 * @param dt
	 *            the dt
	 * @return the string
	 */
	public static String printDate(final Date dt) {
		if (dt == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return DatatypeConverter.printDate(c);
	}

	/**
	 * Parses the time.
	 *
	 * @param s
	 *            the s
	 * @return the date
	 */
	public static Date parseTime(final String s) {
		if (s == null) {
			return null;
		}
		return DatatypeConverter.parseTime(s).getTime();
	}

	/**
	 * Prints the time.
	 *
	 * @param dt
	 *            the dt
	 * @return the string
	 */
	public static String printTime(final Date dt) {
		if (dt == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return DatatypeConverter.printTime(c);
	}

	/**
	 * Parses the date time.
	 *
	 * @param s
	 *            the s
	 * @return the date
	 */
	public static Date parseDateTime(final String s) {
		if (s == null) {
			return null;
		}
		return DatatypeConverter.parseDateTime(s).getTime();
	}

	/**
	 * Prints the date time.
	 *
	 * @param dt
	 *            the dt
	 * @return the string
	 */
	public static String printDateTime(final Date dt) {
		if (dt == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return DatatypeConverter.printDateTime(c);
	}
}
