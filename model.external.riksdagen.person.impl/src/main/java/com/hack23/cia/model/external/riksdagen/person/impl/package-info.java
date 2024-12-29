/**
 * This package contains implementations for handling person data from the Swedish Parliament (Riksdagen).
 *
 * Key classes:
 * - AssignmentData: Represents assignment data for a person.
 * - DetailData: Represents detailed data for a person.
 * - PersonAssignmentData: Represents assignment data for a person.
 * - PersonContainerData: Represents container data for a person.
 * - PersonData: Represents person data.
 * - PersonDetailData: Represents detailed data for a person.
 * - SexType: Enum representing the gender of a person.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.model.common.api for ModelObject interface.
 * - Depends on com.hack23.cia.model.common.impl.xml for XmlDateTypeAdapter.
 * - Uses javax.persistence for JPA annotations.
 * - Uses javax.xml.bind.annotation for XML binding annotations.
 * - Uses org.apache.commons.lang3.builder for EqualsBuilder, HashCodeBuilder, and ToStringBuilder.
 * - Depends on com.hack23.cia.model.external.riksdagen.personlista.impl for handling person list data.
 * - Depends on com.hack23.cia.model.external.riksdagen.utskottsforslag.impl for handling committee proposal data.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://person.riksdagen.external.model.cia.hack23.com/impl")
package com.hack23.cia.model.external.riksdagen.person.impl;
