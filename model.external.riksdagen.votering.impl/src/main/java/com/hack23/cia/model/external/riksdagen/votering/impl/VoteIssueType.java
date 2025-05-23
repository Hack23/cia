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
// Generated on: 2019.02.24 at 11:40:13 PM CET
//


package com.hack23.cia.model.external.riksdagen.votering.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * The Enum VoteIssueType.
 */
@XmlType(name = "VoteIssueType")
@XmlEnum
public enum VoteIssueType {

    /** The SAKFRÅGAN. */
    @XmlEnumValue("sakfr\u00e5gan")
    SAKFRÅGAN("sakfr\u00e5gan"),

    /** The MOTIVFRÅGAN. */
    @XmlEnumValue("motivfr\u00e5gan")
    MOTIVFRÅGAN("motivfr\u00e5gan");

    /** The value. */
    private final String value;

    /**
	 * Instantiates a new vote issue type.
	 *
	 * @param v the v
	 */
    VoteIssueType(final String v) {
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
	 * @return the vote issue type
	 */
    public static VoteIssueType fromValue(final String v) {
        for (final VoteIssueType c: VoteIssueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
