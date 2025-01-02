/*
 * Copyright 2010-2025 James Pether Sörling
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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.common.impl.test.SimpleXml;

/**
 * The Class XmlAgentImplITest.
 */
public final class XmlAgentImplITest extends AbstractServiceExternalCommonFunctionalIntegrationTest {

	/** The xml agent. */
	@Autowired
	private XmlAgent xmlAgent;

	/**
	 * Retrive content success test.
	 *
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	@Test
	public void retriveContentSuccessTest() throws XmlAgentException  {
		final String retriveContent = xmlAgent.retriveContent(XmlAgentImplITest.class.getResource("/simplexml-missing-namespace.xml").toString());
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><simpleXml><description>abc123</description></simpleXml>", retriveContent);
	}

	/**
	 * Unmarshall xml success test.
	 *
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	@Test
	public void unmarshallXmlSuccessTest() throws XmlAgentException {
		final Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(SimpleXml.class);
		final SimpleXml simpleXml = (SimpleXml) xmlAgent.unmarshallXml(jaxb2Marshaller, XmlAgentImplITest.class.getResource("/simplexml.xml").toString());
		assertEquals(new SimpleXml("abc123"), simpleXml);
	}

	/**
	 * Unmarshall xml missing namespace success test.
	 *
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	@Test
	public void unmarshallXmlMissingNamespaceSuccessTest() throws XmlAgentException {
		final Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(SimpleXml.class);
		final SimpleXml simpleXml = (SimpleXml) xmlAgent.unmarshallXml(jaxb2Marshaller, XmlAgentImplITest.class.getResource("/simplexml-missing-namespace.xml").toString(),"com.hack23.cia.service.external.common.impl.test",null,null);
		assertEquals(new SimpleXml("abc123"), simpleXml);
	}


	/**
	 * Unmarshall xml missing namespace and replace success test.
	 *
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	@Test
	public void unmarshallXmlMissingNamespaceAndReplaceSuccessTest() throws XmlAgentException {
		final Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(SimpleXml.class);
		final SimpleXml simpleXml = (SimpleXml) xmlAgent.unmarshallXml(jaxb2Marshaller, XmlAgentImplITest.class.getResource("/simplexml-missing-namespace.xml").toString(),"com.hack23.cia.service.external.common.impl.test","abc123","ABC123");
		assertEquals(new SimpleXml("ABC123"), simpleXml);
	}

}
