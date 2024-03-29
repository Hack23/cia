//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.02.25 at 12:05:09 AM CET
//


package com.hack23.cia.model.internal.application.data.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RiksdagenDataSources.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RiksdagenDataSources"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Document_List"/&gt;
 *     &lt;enumeration value="Document_Status"/&gt;
 *     &lt;enumeration value="Document_Content"/&gt;
 *     &lt;enumeration value="Persons"/&gt;
 *     &lt;enumeration value="Votes"/&gt;
 *     &lt;enumeration value="Ballot_List"/&gt;
 *     &lt;enumeration value="Committee_Proposals"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 */
@XmlType(name = "RiksdagenDataSources")
@XmlEnum
public enum RiksdagenDataSources {

    @XmlEnumValue("Document_List")
    DOCUMENT_LIST("Document_List"),
    @XmlEnumValue("Document_Status")
    DOCUMENT_STATUS("Document_Status"),
    @XmlEnumValue("Document_Content")
    DOCUMENT_CONTENT("Document_Content"),
    @XmlEnumValue("Persons")
    PERSONS("Persons"),
    @XmlEnumValue("Votes")
    VOTES("Votes"),
    @XmlEnumValue("Ballot_List")
    BALLOT_LIST("Ballot_List"),
    @XmlEnumValue("Committee_Proposals")
    COMMITTEE_PROPOSALS("Committee_Proposals");
    private final String value;

    RiksdagenDataSources(final String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RiksdagenDataSources fromValue(final String v) {
        for (final RiksdagenDataSources c: RiksdagenDataSources.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
