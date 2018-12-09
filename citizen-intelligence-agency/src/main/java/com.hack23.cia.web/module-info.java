module com.hack23.cia.web {
	exports com.hack23.cia.web.impl.ui.application;
	
	opens com.hack23.cia.web.impl.ui.application.action to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.util to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.web.listener to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.web to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.system to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.datasummary.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.datasummary to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.agentoperations.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.agentoperations to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin.common to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.admin to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.pageclicklistener to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.parliament.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.parliament to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.home.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.home to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.party.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.party to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.ballot.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.ballot to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.country.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.country to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.goverment.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.goverment to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.document.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.document to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.committee.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.committee to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.politician.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.politician to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.common to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.govermentbody.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user.govermentbody to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.user to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.pageclicklisteners.handlers to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.pageclicklisteners to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.labelfactory to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.formfactory.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.formfactory.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.formfactory to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.pagemode to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.sizing to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.viewnames to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.pagelinks.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.pagelinks to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.converters to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.rows to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.gridfactory.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.gridfactory.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.gridfactory to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.paging to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.menufactory.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.menufactory to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common.chartfactory to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.web.impl.ui.application.views.common to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	
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