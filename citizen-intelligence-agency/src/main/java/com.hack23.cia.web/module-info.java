module com.hack23.cia.web {
	exports com.hack23.cia.web.impl.ui.application;

	requires vaadin.compatibility.shared;
	requires vaadin.compatibility.server;
	requires vaadin.shared;
	requires vaadin.server;
	requires responsive.layout;
	
	requires java.xml.bind;
	requires java.annotation;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;
	requires spring.tx;
	requires spring.messaging;
	requires spring.context.support;
	requires quartz;
	requires commons.lang;

	requires spring.security.core;
	requires spring.security.web;
	requires org.apache.commons.lang3;

	requires weka.dev;
	requires passay;
	requires wt.pdf.viewer;
	requires spring.aop;
	requires statistics.card;
	requires org.eclipse.jetty.servlet;
	requires vaadin.grid.util;
	requires yauaa;
	requires qrcode;

	requires java.desktop;
	requires org.apache.commons.text;
	requires gantt.addon;
//	requires joda.time;

//	requires jms;
//	requires java.bean;

	requires com.hack23.cia.web.widgets;
	requires dcharts;
	requires commons.beanutils;
	requires org.jsoup;
	requires javax.servlet.api;
	requires vaadin.spring;
	requires spring.web;
	requires java.logging;
	requires java.management;
	
	requires com.hack23.cia.service.api;
	requires com.hack23.cia.service.impl;
	requires com.hack23.cia.service.external.esv;

	requires com.hack23.cia.service.component.agent.api;
	requires com.hack23.cia.service.component.agent.impl;

	requires com.hack23.cia.service.external.common;
    requires com.hack23.cia.service.data.api;
	requires com.hack23.cia.service.external.riksdagen;
	requires com.hack23.cia.service.external.val;
	requires com.hack23.cia.service.external.worldbank;

	requires com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires com.hack23.cia.model.external.worldbank.topic.impl;
	requires com.hack23.cia.model.external.worldbank.indicators.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.worldbank.data.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.val.partier.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;

}