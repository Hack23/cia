/*
 * Copyright 2010 James Pether Sörling
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
 *	$Id: MockWebContextLoader.java 6055 2015-05-08 20:50:29Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/testfoundation/src/main/java/com/hack23/cia/testfoundation/MockWebContextLoader.java $
 */
package com.hack23.cia.testfoundation;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * The Class MockWebContextLoader.
 */
public final class MockWebContextLoader extends AbstractContextLoader {

	/** The Constant SERVLET_CONTEXT. */
	public static final ServletContext SERVLET_CONTEXT = new MockServletContext(
			"/WebContent", new FileSystemResourceLoader());

	/** The Constant WEB_CONTEXT. */
	private static final GenericWebApplicationContext WEB_CONTEXT = new GenericWebApplicationContext();

	/**
	 * Gets the single instance of MockWebContextLoader.
	 *
	 * @return single instance of MockWebContextLoader
	 */
	public static WebApplicationContext getInstance() {
		return WEB_CONTEXT;
	}

	/**
	 * Creates the bean definition reader.
	 *
	 * @param context
	 *            the context
	 * @return the bean definition reader
	 */
	protected BeanDefinitionReader createBeanDefinitionReader(
			final GenericApplicationContext context) {
		return new XmlBeanDefinitionReader(context);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.springframework.test.context.support.AbstractContextLoader#
	 * getResourceSuffix()
	 */
	@Override
	protected String getResourceSuffix() {
		return "-context.xml";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.test.context.ContextLoader#loadContext(java.lang.
	 * String[])
	 */
	@Override
	public ConfigurableApplicationContext loadContext(
			final String... locations) throws Exception {

		SERVLET_CONTEXT.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				WEB_CONTEXT);
		WEB_CONTEXT.setServletContext(SERVLET_CONTEXT);
		createBeanDefinitionReader(WEB_CONTEXT).loadBeanDefinitions(locations);
		AnnotationConfigUtils.registerAnnotationConfigProcessors(WEB_CONTEXT);
		if (!WEB_CONTEXT.isActive()) {
			WEB_CONTEXT.refresh();
			WEB_CONTEXT.registerShutdownHook();
		}
		return WEB_CONTEXT;
	}

	@Override
	public ApplicationContext loadContext(
			final MergedContextConfiguration mergedContextConfiguration)
					throws Exception {
		throw new RuntimeException("Not Impl");
	}

}