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
package com.hack23.cia.service.impl;

import com.hack23.cia.service.api.SystemConfiguration;

/**
 * The Class SystemConfigurationImpl.
 */
public final class SystemConfigurationImpl implements SystemConfiguration {

	/** The geoip data file location. */
	private String geoipDataFileLocation;

	/** The geoip data file location city. */
	private String geoipDataFileLocationCity;

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.api.Configuration#getGeoipDataFileLocation()
	 */
	@Override
	public String getGeoipDataFileLocation() {
		return geoipDataFileLocation;
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.api.Configuration#getGeoipDataFileLocationCity()
	 */
	@Override
	public String getGeoipDataFileLocationCity() {
		return geoipDataFileLocationCity;
	}

	/**
	 * Sets the geoip data file location.
	 *
	 * @param geoipDataFileLocation
	 *            the new geoip data file location
	 */
	public void setGeoipDataFileLocation(final String geoipDataFileLocation) {
		this.geoipDataFileLocation = geoipDataFileLocation;
	}

	/**
	 * Sets the geoip data file location city.
	 *
	 * @param geoipDataFileLocationCity
	 *            the new geoip data file location city
	 */
	public void setGeoipDataFileLocationCity(final String geoipDataFileLocationCity) {
		this.geoipDataFileLocationCity = geoipDataFileLocationCity;
	}
}
