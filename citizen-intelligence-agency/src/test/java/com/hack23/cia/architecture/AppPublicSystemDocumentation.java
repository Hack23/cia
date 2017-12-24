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
package com.hack23.cia.architecture;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.structurizr.Workspace;
import com.structurizr.analysis.AbstractSpringComponentFinderStrategy;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy;
import com.structurizr.analysis.SpringComponentComponentFinderStrategy;
import com.structurizr.analysis.SpringServiceComponentFinderStrategy;
import com.structurizr.analysis.SupportingTypesStrategy;
import com.structurizr.io.WorkspaceWriterException;
import com.structurizr.io.plantuml.PlantUMLWriter;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.model.ContainerInstance;
import com.structurizr.model.DeploymentNode;
import com.structurizr.model.Enterprise;
import com.structurizr.model.Location;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.Relationship;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.util.MapUtils;
import com.structurizr.view.DeploymentView;
import com.structurizr.view.EnterpriseContextView;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;

import net.sourceforge.plantuml.Run;


/**
 * The Class AppPublicSystemDocumentation.
 */
public class AppPublicSystemDocumentation {

	// Public site https://structurizr.com/share/37264#Enterprise
	// inspired from
	// https://github.com/structurizr/java/blob/master/structurizr-examples/src/com/structurizr/example/SpringPetClinic.java

	private static final long WORKSPACE_ID = 37264;
	private static final String API_KEY = "";
	private static final String API_SECRET = "";

	/**
	 * The Class SpringRepositoryComponentFinderStrategy.
	 */
	public static final class SpringRepositoryComponentFinderStrategy extends AbstractSpringComponentFinderStrategy {
		private static final String SPRING_REPOSITORY = "Spring Repository";

		/**
		 * Instantiates a new spring repository component finder strategy.
		 *
		 * @param strategies
		 *            the strategies
		 */
		public SpringRepositoryComponentFinderStrategy(final SupportingTypesStrategy... strategies) {
			super(strategies);
		}

		@Override
		protected Set<Component> doFindComponents() throws Exception {
			return findInterfacesForImplementationClassesWithAnnotation(org.springframework.stereotype.Repository.class,
					SPRING_REPOSITORY);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(final String[] args) throws Exception {
		final Workspace workspace = new Workspace("Citizen Intelligence Agency", "Public System Documentation");
		final Model model = workspace.getModel();
		final ViewSet viewSet = workspace.getViews();

		final Person userPerson = model.addPerson("User", "User of the system");
		final Person adminPerson = model.addPerson("Admin", "Manager of the system");
		
		final SoftwareSystem ciaSystem = model.addSoftwareSystem("Citizen Intelligence Agency System",
				"Tracking politicians like bugs!");

		final SoftwareSystem riksdagenApiSystem = model.addSoftwareSystem(Location.External, "data.riksdagen.se",
				"Public API Swedish Parliament data");
		final SoftwareSystem worldBankApiSystem = model.addSoftwareSystem(Location.External, "data.wordbank.org",
				"Public API Country indicators");
		final SoftwareSystem valApiSystem = model.addSoftwareSystem(Location.External, "www.val.se",
				"Public API Swedish Election data");
		final SoftwareSystem esvApiSystem = model.addSoftwareSystem(Location.External, "www.esv.se",
				"Public Data Swedish public sector spending data");

		final Container loadBalancerContainer = ciaSystem.addContainer("Loadbalancer", "Loadbalancer",
				"ALB/ELB/Apache/Nginx/HaProxy");

		final Container ciaWebContainer = ciaSystem.addContainer("Web Application", "Web Application", "Jetty/Java");
		final ComponentFinder componentFinderWeb = new ComponentFinder(ciaWebContainer, "com.hack23.cia",
				new SpringServiceComponentFinderStrategy(), new SpringComponentComponentFinderStrategy(),
				new SpringRepositoryComponentFinderStrategy());
		componentFinderWeb.exclude(".*ui.*");
		componentFinderWeb.exclude(".*service.external.*");
		componentFinderWeb.exclude(".*service.component.*");		
		componentFinderWeb.exclude(".*package.*");

		componentFinderWeb.findComponents();

		final Container relationalDatabase = ciaSystem.addContainer("Database", "Stores information", "Postgresql");
		relationalDatabase.addTags("Database");

		adminPerson.uses(ciaSystem, "Manages");
		userPerson.uses(ciaSystem, "Uses");
		ciaSystem.uses(riksdagenApiSystem, "Loads data");
		ciaSystem.uses(worldBankApiSystem, "Loads data");
		ciaSystem.uses(valApiSystem, "Loads data");
		ciaSystem.uses(esvApiSystem, "Loads data");
		loadBalancerContainer.uses(ciaWebContainer, "HTTPS/H2");

		ciaWebContainer.uses(relationalDatabase, "JDBC");

		final EnterpriseContextView enterpriseContextView = viewSet.createEnterpriseContextView("Enterprise", "Enterprise");		
		enterpriseContextView.addAllElements();
		Enterprise enterprise = new Enterprise("Hack23");
		enterpriseContextView.getModel().setEnterprise(enterprise);
		
		viewSet.createSystemContextView(ciaSystem, "System context", "System context").addAllElements();
		viewSet.createContainerView(ciaSystem, "Container view", "Application Overview").addAllContainers();
		viewSet.createComponentView(ciaWebContainer, "Web", "Web").addAllComponents();
				
		DeploymentNode applicationLoadbalancerNode = model.addDeploymentNode("Application Loadbalancer", "AWS", "ALB");
		applicationLoadbalancerNode.add(loadBalancerContainer);

		DeploymentNode webNode = model.addDeploymentNode("Application", "AWS", "EC2",2);
		webNode.add(ciaWebContainer);
		applicationLoadbalancerNode.uses(webNode, "Uses", "https");

		DeploymentNode databaseNode = model.addDeploymentNode("Database", "AWS", "RDS",2);
		databaseNode.add(relationalDatabase);
		webNode.uses(databaseNode, "Uses", "jdbc");
		
		DeploymentView developmentDeploymentView = viewSet.createDeploymentView(ciaSystem, "Deployment",
				"Deployment Aws.");
		developmentDeploymentView.add(applicationLoadbalancerNode);
		developmentDeploymentView.add(webNode);
		developmentDeploymentView.add(databaseNode);
		

		final Styles styles = viewSet.getConfiguration().getStyles();
		styles.addElementStyle(Tags.COMPONENT).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.CONTAINER).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);
		styles.addElementStyle("Database").shape(Shape.Cylinder);

		// StructurizrClient structurizrClient = new StructurizrClient(API_KEY,
		// API_SECRET);
		// structurizrClient.putWorkspace(WORKSPACE_ID, workspace);

		printPlantUml(workspace);
		System.setProperty("PLANTUML_LIMIT_SIZE", "8192");
		Run.main(new String[] { Paths.get(".").toAbsolutePath().normalize().toString() + File.separator
				+ "target" + File.separator + "site" + File.separator + "architecture" + File.separator});
	}

	
	/**
	 * Prints the plant uml.
	 *
	 * @param workspace
	 *            the workspace
	 * @throws WorkspaceWriterException
	 *             the workspace writer exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private static void printPlantUml(final Workspace workspace) throws WorkspaceWriterException, IOException, InterruptedException {
		StringWriter stringWriter = new StringWriter();
		PlantUMLWriter plantUMLWriter = new PlantUMLWriter();
		plantUMLWriter.write(workspace, stringWriter);
		String allPlantUmlsString = stringWriter.toString();
		
		String systemUml2 = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"), allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(systemUml2,"");
		writePlantUml("Citizen-Intelligence-Agency-System-System-Deployment",systemUml2);
		
		String componentUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"), allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(componentUml,"");
		writePlantUml("Citizen-Intelligence-Agency-System-Web-Application-Components",componentUml);
		
		String containersUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"), allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(containersUml,"");
		writePlantUml("Citizen-Intelligence-Agency-System-Containers",containersUml);
		
		String systemUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"), allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(systemUml,"");
		writePlantUml("Citizen-Intelligence-Agency-System-System-Context",systemUml);

		
		String enterpriseUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"), allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(enterpriseUml,"");
		writePlantUml("Enterprise-Context-for-Hack23",enterpriseUml);
	}


	/**
	 * Write plant uml.
	 *
	 * @param filename
	 *            the filename
	 * @param content
	 *            the content
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private static void writePlantUml(String filename,String content) throws IOException, InterruptedException {
		final String fullFilePathPlantUmlFile = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator
				+ "target" + File.separator + "site" + File.separator + "architecture" + File.separator
				+ filename + ".pu";
		System.out.println("Writing file:" + fullFilePathPlantUmlFile);
		FileUtils.writeStringToFile(new File(fullFilePathPlantUmlFile), content, Charset.defaultCharset());		
	}
}