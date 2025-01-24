/**
 * This package contains implementations for handling document lists from the Swedish Parliament.
 *
 * Key classes:
 * - DefaultStringIdentifier: Represents a default string identifier.
 * - DocumentContainerElement: Represents a container for document elements.
 * - DocumentElement: Represents a document element.
 * - DocumentSubType: Represents a document sub-type.
 * - DocumentType: Represents a document type.
 * - ObjectFactory: Factory class for creating instances of document list data.
 */
@XmlSchema(
    elementFormDefault = XmlNsForm.QUALIFIED,
    namespace = ""
)
package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
