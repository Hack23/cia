/**
 * This package contains implementations for handling person lists from the Swedish Parliament (Riksdagen).
 *
 * Key classes:
 * - AssignmentElement: Represents an assignment element with details such as organization code, role code, status, and dates.
 * - DetailElement: Represents a detail element with information such as intressent ID, detail type, and code.
 * - PersonAssignmentElement: Represents a person assignment element containing a list of assignments.
 * - PersonContainerElement: Represents a container for person elements.
 * - PersonDetailElement: Represents a person detail element containing a detail list.
 * - PersonElement: Represents a person element with various attributes such as name, party, and role.
 *
 * Annotations:
 * - @XmlSchema: Defines the XML namespace and element form default for the package.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://personlista.riksdagen.external.model.cia.hack23.com/impl")
package com.hack23.cia.model.external.riksdagen.personlista.impl;
