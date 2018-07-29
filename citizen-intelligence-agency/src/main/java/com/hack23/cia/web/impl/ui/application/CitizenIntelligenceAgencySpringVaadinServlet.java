/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.internal.SpringViewDisplayRegistrationBean;
import com.vaadin.spring.internal.UIID;
import com.vaadin.spring.internal.UIScopeImpl;
import com.vaadin.spring.internal.VaadinSessionScope;
import com.vaadin.spring.server.SpringUIProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;

/**
 * The Class CitizenIntelligenceAgencySpringVaadinServlet.
 */
@WebServlet(value = "/*", loadOnStartup=2, asyncSupported = true)
@VaadinServletConfiguration(productionMode = true, ui = CitizenIntelligenceAgencyUI.class, widgetset = "com.hack23.cia.web.widgets.WebWidgetSet")
public final class CitizenIntelligenceAgencySpringVaadinServlet extends SpringVaadinServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void servletInitialized() throws ServletException {
        VaadinServletService service = getService();
        service.addSessionInitListener(new SessionInitListener() {

            private static final long serialVersionUID = -6307820453486668084L;

            @Override
            public void sessionInit(SessionInitEvent sessionInitEvent)
                    throws ServiceException {
                WebApplicationContext webApplicationContext = WebApplicationContextUtils
                        .getWebApplicationContext(getServletContext());

                // remove DefaultUIProvider instances to avoid mapping
                // extraneous UIs if e.g. a servlet is declared as a nested
                // class in a UI class
                VaadinSession session = sessionInitEvent.getSession();
                List<UIProvider> uiProviders = new ArrayList<UIProvider>(
                        session.getUIProviders());
                for (UIProvider provider : uiProviders) {
                    // use canonical names as these may have been loaded with
                    // different classloaders
                    if (DefaultUIProvider.class.getCanonicalName().equals(
                            provider.getClass().getCanonicalName())) {
                        session.removeUIProvider(provider);
                    }
                }

                // add Spring UI provider
                SpringUIProvider uiProvider = new CustomSpringUIProvider(session);
                session.addUIProvider(uiProvider);
            }
        });
        service.addSessionDestroyListener(new SessionDestroyListener() {
            @Override
            public void sessionDestroy(SessionDestroyEvent event) {
                VaadinSession session = event.getSession();

                UIScopeImpl.cleanupSession(session);
                VaadinSessionScope.cleanupSession(session);
            }
        });
    }
    
    public class CustomSpringUIProvider extends SpringUIProvider {
    	
    	private final SpringViewDisplayRegistrationBean springViewDisplayRegistrationBean = new SpringViewDisplayRegistrationBean();
    	
        public CustomSpringUIProvider(VaadinSession vaadinSession) {
            super(vaadinSession);
        }

        @Override
        public UI createInstance(UICreateEvent event) {
            final Class<UIID> key = UIID.class;
            final UIID identifier = new UIID(event);
            CurrentInstance.set(key, identifier);
            try {
                logger.debug(
                        "Creating a new UI bean of class [{}] with identifier [{}]",
                        event.getUIClass().getCanonicalName(), identifier);
                UI ui = getWebApplicationContext().getBean(event.getUIClass());

                getSpringViewDisplayRegistrationBean().setBeanClass(event.getUIClass());

                configureNavigator(ui);
                return ui;
            } finally {
                CurrentInstance.set(key, null);
            }
        }
        
        protected Object findSpringViewDisplay(UI ui) {
            try {
                return getSpringViewDisplayRegistrationBean()
                        .getSpringViewDisplay(getWebApplicationContext());
            } catch (NoUniqueBeanDefinitionException e) {
                throw e;
            } catch (NoSuchBeanDefinitionException e) {
                // fallback with getBeanNamesForAnnotation()
                logger.debug(
                        "Looking for a SpringViewDisplay bean based on bean level annotations");
                final String[] springViewDisplayBeanNames = getWebApplicationContext()
                        .getBeanNamesForAnnotation(SpringViewDisplay.class);
                if (springViewDisplayBeanNames.length == 0) {
                    logger.debug(
                            "No view display defined for the UI " + ui.getId());
                    return null;
                }
                if (springViewDisplayBeanNames.length > 1) {
                    logger.error("Multiple view displays defined for the UI "
                            + ui.getId() + ": "
                            + Arrays.toString(springViewDisplayBeanNames));
                    throw new NoUniqueBeanDefinitionException(Object.class,
                            Arrays.asList(springViewDisplayBeanNames));
                }
                return getWebApplicationContext()
                        .getBean(springViewDisplayBeanNames[0]);
            }
        }


        private SpringViewDisplayRegistrationBean getSpringViewDisplayRegistrationBean() {
            return springViewDisplayRegistrationBean;

        }
    }
    
}