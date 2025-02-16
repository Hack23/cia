/*
 * Copyright 2010 -2025 James Pether Sörling
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.24 at 11:26:40 PM CET
//


package com.hack23.cia.model.external.worldbank.countries.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * The Enum LendingTypeCategory.
 */
@XmlType(name = "LendingTypeCategory")
@XmlEnum
public enum LendingTypeCategory {

    /** The aggregates. */
    @XmlEnumValue("Aggregates")
    AGGREGATES("Aggregates"),

    /** The ibrd only. */
    @XmlEnumValue("IBRD only")
    IBRD_ONLY("IBRD only"),

    /** The ida blend. */
    @XmlEnumValue("IDA blend")
    IDA_BLEND("IDA blend"),

    /** The ida only. */
    @XmlEnumValue("IDA only")
    IDA_ONLY("IDA only"),

    /** The na. */
    NA("NA"),

    /** The not classified. */
    @XmlEnumValue("Not classified")
    NOT_CLASSIFIED("Not classified");

    /** The value. */
    private final String value;

    /**
	 * Instantiates a new lending type category.
	 *
	 * @param v the v
	 */
    LendingTypeCategory(final String v) {
        value = v;
    }

    /**
	 * Value.
	 *
	 * @return the string
	 */
    public String value() {
        return value;
    }

    /**
	 * From value.
	 *
	 * @param v the v
	 * @return the lending type category
	 */
    public static LendingTypeCategory fromValue(final String v) {
        for (final LendingTypeCategory c: LendingTypeCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
