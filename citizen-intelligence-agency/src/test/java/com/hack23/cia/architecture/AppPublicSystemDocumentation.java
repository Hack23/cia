package com.hack23.cia.architecture;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.SpringServiceComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.Container;
import com.structurizr.model.Location;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;

public class AppPublicSystemDocumentation {

	// Public site https://structurizr.com/share/37264#Enterprise
	// inspired from https://github.com/structurizr/java/blob/master/structurizr-examples/src/com/structurizr/example/SpringPetClinic.java
	
	private static final long WORKSPACE_ID = 37264;
	private static final String API_KEY = "";
	private static final String API_SECRET = "";

	public static void main(String[] args) throws Exception {
		Workspace workspace = new Workspace("Citizen Intelligence Agency", "Public System Documentation");
		Model model = workspace.getModel();
		ViewSet viewSet = workspace.getViews();
		
		Person userPerson = model.addPerson("User", "User of the system");
		Person adminPerson = model.addPerson("Admin", "Manager of the system");

		SoftwareSystem ciaSystem = model.addSoftwareSystem("Citizen Intelligence Agency System",
				"Tracking politicians like bugs!");

		SoftwareSystem riksdagenApiSystem = model.addSoftwareSystem(Location.External, "data.riksdagen.se","Public API Swedish Parliament data");
		SoftwareSystem worldBankApiSystem = model.addSoftwareSystem(Location.External, "data.wordbank.org","Public API Country indicators");
		SoftwareSystem valApiSystem = model.addSoftwareSystem(Location.External, "www.val.se","Public API Swedish Election data");
		SoftwareSystem esvApiSystem = model.addSoftwareSystem(Location.External, "www.esv.se","Public Data Swedish public sector spending data");

	
		Container ciaWebContainer = ciaSystem.addContainer("Web", "Views", "Java");
		ComponentFinder componentFinderWeb = new ComponentFinder(ciaWebContainer,
				"com.hack23.cia.web.impl.ui.application", new SpringServiceComponentFinderStrategy());
		componentFinderWeb.findComponents();

		
		Container ciaServiceContainer = ciaSystem.addContainer("Service", "Service components", "Java");
		ComponentFinder componentFinderService = new ComponentFinder(ciaServiceContainer, "com.hack23.cia.service.impl",
				new SpringServiceComponentFinderStrategy());
		componentFinderService.findComponents();

		Container relationalDatabase = ciaSystem.addContainer("Database", "Stores information", "Postgresql");
		relationalDatabase.addTags("Database");

		
		adminPerson.uses(ciaSystem, "Manages");
		userPerson.uses(ciaSystem, "Uses");
		ciaSystem.uses(riksdagenApiSystem, "Loads data");
		ciaSystem.uses(worldBankApiSystem, "Loads data");
		ciaSystem.uses(valApiSystem, "Loads data");	
		ciaSystem.uses(esvApiSystem, "Loads data");			
		
		ciaWebContainer.uses(ciaServiceContainer, "Uses");
		ciaServiceContainer.uses(relationalDatabase, "JDBC");		
		
		viewSet.createEnterpriseContextView("Enterprise", "Enterprise").addAllElements();
		viewSet.createSystemContextView(ciaSystem, "System context", "System context").addAllElements();
		viewSet.createContainerView(ciaSystem, "Container view", "Application Overview").addAllContainers();
		viewSet.createComponentView(ciaWebContainer, "Web", "Web").addAllComponents();
		viewSet.createComponentView(ciaServiceContainer, "Service", "Service components").addAllComponents();

		Styles styles = viewSet.getConfiguration().getStyles();
		styles.addElementStyle(Tags.COMPONENT).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.CONTAINER).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);
		styles.addElementStyle("Database").shape(Shape.Cylinder);

		StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
		structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
	}
}