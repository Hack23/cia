package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class DocumentContainerElementTest {

    @Test
    public void testUnmarshalXmlToDocumentContainerElement() throws Exception {
            // Create a JAXB context for the DocumentContainerElement class
            final JAXBContext context = JAXBContext.newInstance(DocumentContainerElement.class,DocumentElement.class);

            // Create an unmarshaller
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            //unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());


            // Path to the XML file to be read
            final File file = new File("src/test/samples/documentlista-2025b.xml");

            // Unmarshal the XML file into a DocumentContainerElement object
            final DocumentContainerElement documentContainerElement = (DocumentContainerElement) unmarshaller.unmarshal(file);

            // Assert that the object is not null
            assertNotNull(documentContainerElement);
            assertNotNull(documentContainerElement.getDokument());
            assertTrue(documentContainerElement.getDokument().size() > 0);
    }
}