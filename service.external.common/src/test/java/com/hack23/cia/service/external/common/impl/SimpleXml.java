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
package com.hack23.cia.service.external.common.impl;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class SimpleXml.
 */
@XmlRootElement( namespace = "com.hack23.cia.service.external.common.impl")
@XmlType(propOrder = { "description"})
public class SimpleXml {

	/** The description. */
	private String description;

    /**
	 * Instantiates a new simple xml.
	 */
    public SimpleXml() {
    }

    /**
	 * Instantiates a new simple xml.
	 *
	 * @param description
	 *            the description
	 */
    public SimpleXml(final String description) {
        this.description = description;
    }

    /**
	 * Gets the description.
	 *
	 * @return the description
	 */
    @XmlElement
    public String getDescription() {
        return description;
    }

    /**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
    public void setDescription(final String description) {
        this.description = description;
    }

    /** (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		final int result = 1;
		return prime * result
				+ ((description == null) ? 0 : description.hashCode());
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SimpleXml other = (SimpleXml) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		return true;
	}

}