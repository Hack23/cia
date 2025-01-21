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

package com.hack23.cia.systemintegrationtest;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.security.Security;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * The Class CitizenIntelligenceAgencyServer.
 */
public final class CitizenIntelligenceAgencyServer {

	/** The Constant PORT. */
	public static final int PORT = 28443;

	/** The Constant ACCESS_URL. */
	public static final String ACCESS_URL = "https://localhost:" + PORT + "/";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CitizenIntelligenceAgencyServer.class);


	/** The server started. */
	private static int serverStarted = 0;

	/** The test server. */
	private static CitizenIntelligenceAgencyServer testServer;

	/** The initialised. */
	private boolean initialised;

	/** The server. */
	private Server server;

	static {
		System.setProperty("logback.configurationFile", "src/main/resources/logback.xml");
		System.setProperty("slf4j", "true");
		System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.Slf4jLog");
		System.setProperty("jetty.sslContext.sniRequired", "false");
		System.setProperty("jetty.ssl.sniRequired", "false");
		System.setProperty("java.util.logging.manage", "org.jboss.logmanager.LogManager");

		LogManager.getLogManager().reset();
		SLF4JBridgeHandler.install();
		java.util.logging.Logger.getLogger("global").setLevel(Level.FINEST);
	}


	/**
	 * Instantiates a new citizen intelligence agency server.
	 */
	public CitizenIntelligenceAgencyServer() {
		super();
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		CitizenIntelligenceAgencyServer.setEnv("CIA_APP_ENCRYPTION_PASSWORD", "allhaildiscordia");

		final CitizenIntelligenceAgencyServer testServer = new CitizenIntelligenceAgencyServer();
		testServer.startServer();
	}

	/**
	 * Sets the env.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public static void setEnv(final String key, final String value) {
		try {
			final Map<String, String> env = System.getenv();
			final Class<?> cl = env.getClass();
			final Field field = cl.getDeclaredField("m");
			field.setAccessible(true);
			@SuppressWarnings("unchecked")
			final Map<String, String> writableEnv = (Map<String, String>) field.get(env);
			writableEnv.put(key, value);
		} catch (final Exception e) {
			throw new IllegalStateException("Failed to set environment variable", e);
		}
	}

	/**
	 * Start test server.
	 *
	 * @throws Exception the exception
	 */
	public static synchronized void startTestServer() throws Exception {
		serverStarted++;
		if (testServer == null) {
			testServer = new CitizenIntelligenceAgencyServer();
			testServer.startServer();
		}
	}

	/**
	 * Stop test server.
	 *
	 * @throws Exception the exception
	 */
	public static synchronized void stopTestServer() throws Exception {
		serverStarted--;
		if (serverStarted == 0 && testServer != null) {
			testServer.stop();
			testServer = null;
		}
	}

	/**
	 * Inits the.
	 *
	 * @throws Exception the exception
	 */
	public void init() throws Exception {
		initialised = true;
		server = new Server();
		Security.addProvider(new BouncyCastleProvider());
		// Setup JMX
		final MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
		server.addBean(mbContainer);

		final HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(28443);
		http_config.setSendServerVersion(false);

		final HttpConfiguration https_config = new HttpConfiguration(http_config);
		https_config.addCustomizer(new SecureRequestCustomizer(false));

		final SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
		sslContextFactory.setKeyStoreType("PKCS12");
		sslContextFactory.setKeyStorePath("target/keystore.p12");
		sslContextFactory.setTrustStorePath("target/keystore.p12");
		sslContextFactory.setTrustStoreType("PKCS12");

		sslContextFactory.setKeyStorePassword("changeit");
		sslContextFactory.setTrustStorePassword("changeit");
		sslContextFactory.setKeyManagerPassword("changeit");
		sslContextFactory.setCertAlias("jetty");
		sslContextFactory.setIncludeCipherSuites("TLS_AES_256_GCM_SHA384", "TLS_CHACHA20_POLY1305_SHA256",
				"TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256",
				"TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
		sslContextFactory.setExcludeProtocols("SSL", "SSLv2", "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1");
		sslContextFactory.setIncludeProtocols("TLSv1.2", "TLSv1.3");

		final ServerConnector sslConnector = new ServerConnector(server,
				new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https_config),
				new HTTP2CServerConnectionFactory(https_config));
		sslConnector.setPort(PORT);

		server.setConnectors(new ServerConnector[] { sslConnector });
		final WebAppContext handler = new WebAppContext("src/main/webapp", "/");
		handler.setExtraClasspath("target/classes");
		handler.setParentLoaderPriority(true);
		handler.setConfigurationDiscovered(true);
		handler.setClassLoader(Thread.currentThread().getContextClassLoader());
		final HandlerList handlers = new HandlerList();

		handlers.setHandlers(new Handler[] { handler, new DefaultHandler() });

		server.setHandler(handlers);
	}

	/**
	 * Start.
	 *
	 * @throws Exception the exception
	 */
	public void start() throws Exception {
		if (!initialised) {
			LOGGER.info("Exiting init not called before start");
			System.exit(-1);
		}

		try {
			server.start();
		} catch (final Exception e) {
			LOGGER.error("Problem starting server", e);
		}
	}

	/**
	 * Start server.
	 */
	public void startServer() {
		try {
			init();
			start();
			while (!server.isStarted()) {

			}
			LOGGER.info("Server Started");
		} catch (final Exception e) {
			LOGGER.error("Application Exception", e);
		}
	}

	/**
	 * Stop.
	 *
	 * @throws Exception the exception
	 */
	public void stop() throws Exception {
		server.stop();
		while (!server.isStopped()) {

		}
	}
}
