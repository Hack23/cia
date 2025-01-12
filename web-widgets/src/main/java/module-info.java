/**
 * Web Widgets Module.
 *
 * <p>This module provides web widgets for the application.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Custom Vaadin components</li>
 *   <li>Reusable UI elements</li>
 *   <li>Integration with the main web module</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Vaadin for UI components</li>
 *   <li>Apache Commons Lang</li>
 * </ul>
 *
 * @see com.hack23.cia.web
 */
open module com.hack23.cia.web.widgets {
	exports com.hack23.cia.web.widgets.charts;
	
	requires vaadin.compatibility.shared;
	requires vaadin.compatibility.server;
	requires vaadin.shared;
	requires vaadin.server;
	requires org.apache.commons.lang3;

}
