/**
 * CIA (Citizen Intelligence Agency) Web Module.
 *
 * <p>
 * This module delivers the Vaadin-based user interface, enabling users to
 * explore political data, perform analyses, and manage administrative
 * operations.
 * </p>
 *
 * <p>
 * Key Features:
 * </p>
 * <ul>
 * <li>Vaadin UI for interactive dashboards</li>
 * <li>Spring Security integration</li>
 * <li>Responsive layouts and charting</li>
 * </ul>
 *
 * <p>
 * Technologies / Integrations:
 * </p>
 * <ul>
 * <li>Vaadin for UI components</li>
 * <li>Spring Framework for security and backend integration</li>
 * <li>Third-party libraries for PDF viewing, charting, and user-agent
 * parsing</li>
 * </ul>
 *
 * <p>
 * This module implements the web interface for the CIA application using Vaadin
 * framework.
 * It provides a comprehensive user interface for monitoring political figures,
 * institutions,
 * and analyzing political/financial trends.
 * </p>
 *
 * <p>
 * The module contains views and components for:
 * </p>
 * <ul>
 * <li>Administrative operations and system management</li>
 * <li>Parliament data visualization and analysis</li>
 * <li>Political party information and statistics</li>
 * <li>Government body and committee tracking</li>
 * <li>Document management and ballot tracking</li>
 * <li>Politician profiles and activities</li>
 * </ul>
 *
 * <p>
 * Key Features:
 * </p>
 * <ul>
 * <li>Responsive web interface using Vaadin framework</li>
 * <li>Security integration with Spring Security</li>
 * <li>Data visualization with charts and grids</li>
 * <li>PDF viewing capabilities</li>
 * <li>QR code generation</li>
 * <li>User agent parsing and analytics</li>
 * </ul>
 *
 * <p>
 * The module is organized into the following main packages:
 * </p>
 * <ul>
 * <li>application - Core application infrastructure</li>
 * <li>views.admin.* - Administrative interface components</li>
 * <li>views.user.* - User-facing interface components</li>
 * <li>views.common.* - Shared UI components and utilities</li>
 * </ul>
 *
 * <p>
 * This module requires and integrates with various CIA service modules and
 * external
 * data sources including Riksdagen (Swedish Parliament), Val (Elections), and
 * World Bank data.
 * </p>
 *
 * @see com.hack23.cia.service.api
 * @see com.hack23.cia.web.widgets
 */
open module com.hack23.cia.web {
	exports com.hack23.cia.web.impl.ui.application;
	exports com.hack23.cia.web.impl.ui.application.action;
	exports com.hack23.cia.web.impl.ui.application.util;
	exports com.hack23.cia.web.impl.ui.application.web.listener;
	exports com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.admin.system;
	exports com.hack23.cia.web.impl.ui.application.views.admin.datasummary.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.admin.datasummary;
	exports com.hack23.cia.web.impl.ui.application.views.admin.agentoperations.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.admin.agentoperations;
	exports com.hack23.cia.web.impl.ui.application.views.admin.common;
	exports com.hack23.cia.web.impl.ui.application.views.pageclicklistener;
	exports com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.parliament;
	exports com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.home;
	exports com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.party;
	exports com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.ballot;
	exports com.hack23.cia.web.impl.ui.application.views.user.country.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.country;
	exports com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.goverment;
	exports com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.document;
	exports com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.committee;
	exports com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.politician;
	exports com.hack23.cia.web.impl.ui.application.views.user.common;
	exports com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.user.govermentbody;
	exports com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.impl;
	exports com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.api;
	exports com.hack23.cia.web.impl.ui.application.views.common.labelfactory;
	exports com.hack23.cia.web.impl.ui.application.views.common.formfactory.impl;
	exports com.hack23.cia.web.impl.ui.application.views.common.formfactory.api;
	exports com.hack23.cia.web.impl.ui.application.views.common.pagemode;
	exports com.hack23.cia.web.impl.ui.application.views.common.sizing;
	exports com.hack23.cia.web.impl.ui.application.views.common.viewnames;
	exports com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl;
	exports com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api;
	exports com.hack23.cia.web.impl.ui.application.views.common.converters;
	exports com.hack23.cia.web.impl.ui.application.views.common.rows;
	exports com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl;
	exports com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api;
	exports com.hack23.cia.web.impl.ui.application.views.common.gridfactory.impl;
	exports com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api;
	exports com.hack23.cia.web.impl.ui.application.views.common.paging;
	exports com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;
	exports com.hack23.cia.web.impl.ui.application.views.common.menufactory.api;
	exports com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;
	exports com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api;
	exports com.hack23.cia.web.impl.ui.application.views.common;

	// Non-modular dependencies (remove transitive)
	requires vaadin.compatibility.shared;
	requires vaadin.compatibility.server;
	requires responsive.layout;
	requires org.hibernate.orm.jpamodelgen;
	requires vaadin.shared;
	requires org.eclipse.jetty.servlet;
	requires jetty.servlet.api;
	requires vaadin.server;

	// Keep transitive for Spring and our own modules
	requires transitive spring.context;
	requires transitive spring.security.core;
	requires transitive spring.security.web;
	requires transitive spring.beans;
	requires transitive com.hack23.cia.web.widgets;

	requires java.persistence;
	requires org.hibernate.orm.core;
	requires java.transaction;
	requires ehcache;
	requires cache.api;

	requires java.annotation;
	requires org.slf4j;
	requires spring.tx;
	requires spring.messaging;
	requires spring.context.support;
	requires quartz;

	requires org.apache.commons.lang3;
	requires org.bouncycastle.provider;

	requires passay;
	requires wt.pdf.viewer;
	requires spring.aop;
	requires statistics.card;

	requires vaadin.grid.util;
	requires nl.basjes.parse.useragent;
	requires qrcode;

	requires java.desktop;
	requires org.apache.commons.text;
	requires gantt.addon;

	requires dcharts;
	requires org.jsoup;
	requires vaadin.spring;
	requires spring.web;
	requires java.management;
	requires java.logging;

	requires transitive com.hack23.cia.service.api;
	requires transitive com.hack23.cia.service.impl;
	requires transitive com.hack23.cia.service.external.esv;

	requires transitive com.hack23.cia.service.component.agent.api;
	requires transitive com.hack23.cia.service.component.agent.impl;

	requires transitive com.hack23.cia.service.external.common;
	requires transitive com.hack23.cia.service.data.api;
	requires transitive com.hack23.cia.service.external.riksdagen;
	requires transitive com.hack23.cia.service.external.val;
	requires transitive com.hack23.cia.service.external.worldbank;

	requires transitive com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires transitive com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires transitive com.hack23.cia.model.external.worldbank.topic.impl;
	requires transitive com.hack23.cia.model.external.worldbank.indicators.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires transitive com.hack23.cia.model.external.worldbank.data.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.person.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires transitive com.hack23.cia.model.external.val.partier.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires transitive com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires transitive com.hack23.cia.model.external.riksdagen.votering.impl;
	requires transitive com.hack23.cia.model.external.worldbank.countries.impl;
	requires org.apache.commons.beanutils;
	requires static org.eclipse.jetty.jmx;
}
