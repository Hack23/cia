package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.junit.Test;
import org.xml.sax.InputSource;

public class DocumentContainerElementTest {

    @Test
    public void testUnmarshalXmlToDocumentContainerElement() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(DocumentContainerElement.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        
        // Create parser that ignores namespaces
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(false);
        
        // Create SAX source with namespace unaware parser
        InputSource inputSource = new InputSource(new File("src/test/samples/documentlista-2025b.xml").toURI().toString());
        SAXSource source = new SAXSource(saxParserFactory.newSAXParser().getXMLReader(), inputSource);
        
        DocumentContainerElement documentContainerElement = (DocumentContainerElement) unmarshaller.unmarshal(source);
        
        assertNotNull(documentContainerElement);
        assertTrue(documentContainerElement.getDokument().size() > 0);
    }
}
