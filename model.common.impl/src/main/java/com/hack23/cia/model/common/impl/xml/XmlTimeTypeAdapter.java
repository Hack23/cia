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
package com.hack23.cia.model.common.impl.xml;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The Class XmlTimeTypeAdapter.
 */
public final class XmlTimeTypeAdapter extends XmlAdapter<String,Date> {


	/**
	 * Instantiates a new xml time type adapter.
	 */
	public XmlTimeTypeAdapter() {
		super();
	}


	@Override
	public Date unmarshal(final String s) {
		if (s == null) {
			return null;
		}
		return DatatypeConverter.parseTime(s).getTime();
	}

	@Override
	public String marshal(final Date dt) {
		if (dt == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return DatatypeConverter.printTime(c);
	}

}
