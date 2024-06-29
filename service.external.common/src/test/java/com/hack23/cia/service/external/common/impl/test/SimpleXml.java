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
package com.hack23.cia.service.external.common.impl.test;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class SimpleXml.
 */
@XmlRootElement(namespace = "com.hack23.cia.service.external.common.impl.test")
@XmlType(propOrder = {"description"})
public final class SimpleXml {

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

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_FIELD_NAMES_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}