package com.hack23.cia.web.impl.ui.application;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.servlet.DefaultServlet;

@WebServlet(urlPatterns = {"/favicon.ico", "/robots.txt", "/sitemap.xml"}, loadOnStartup = 1, asyncSupported = true)
public class ResourceServlet extends DefaultServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
