package com.hack23.cia.architecture;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.structurizr.Workspace;
import com.structurizr.analysis.AbstractSpringComponentFinderStrategy;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.SpringComponentComponentFinderStrategy;
import com.structurizr.analysis.SpringServiceComponentFinderStrategy;
import com.structurizr.analysis.SupportingTypesStrategy;
import com.structurizr.io.WorkspaceWriterException;
import com.structurizr.io.dot.DotWriter;
import com.structurizr.io.plantuml.PlantUMLWriter;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.model.Enterprise;
import com.structurizr.model.Location;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.EnterpriseContextView;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;

public class AppPublicSystemDocumentation {

	// Public site https://structurizr.com/share/37264#Enterprise
	// inspired from
	// https://github.com/structurizr/java/blob/master/structurizr-examples/src/com/structurizr/example/SpringPetClinic.java

	private static final long WORKSPACE_ID = 37264;
	private static final String API_KEY = "";
	private static final String API_SECRET = "";

	public static final class SpringRepositoryComponentFinderStrategy extends AbstractSpringComponentFinderStrategy {
		private static final String SPRING_REPOSITORY = "Spring Repository";

		public SpringRepositoryComponentFinderStrategy(final SupportingTypesStrategy... strategies) {
			super(strategies);
		}

		@Override
		protected Set<Component> doFindComponents() throws Exception {
			return findInterfacesForImplementationClassesWithAnnotation(org.springframework.stereotype.Repository.class,
					SPRING_REPOSITORY);
		}
	}

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
		componentFinderWeb.exclude(".*pagemode.*");
		componentFinderWeb.exclude(".*common.*");
		componentFinderWeb.exclude(".*action.*");
		componentFinderWeb.exclude(".*listener.*");
		componentFinderWeb.exclude(".*ui.*");
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

		final Styles styles = viewSet.getConfiguration().getStyles();
		styles.addElementStyle(Tags.COMPONENT).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.CONTAINER).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);
		styles.addElementStyle("Database").shape(Shape.Cylinder);

		// StructurizrClient structurizrClient = new StructurizrClient(API_KEY,
		// API_SECRET);
		// structurizrClient.putWorkspace(WORKSPACE_ID, workspace);

		createDotAndPngFiles(workspace);
		printPlantUml(workspace);
	}

	
	private static void printPlantUml(final Workspace workspace) throws WorkspaceWriterException, IOException {
		StringWriter stringWriter = new StringWriter();
		PlantUMLWriter plantUMLWriter = new PlantUMLWriter();
		plantUMLWriter.write(workspace, stringWriter);
		String allPlantUmlsString = stringWriter.toString();
		
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


	private static void writePlantUml(String filename,String content) throws IOException {
		final String fullFilePathPlantUmlFile = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator
				+ "target" + File.separator + "site" + File.separator + "architecture" + File.separator
				+ filename + ".plantuml";
		System.out.println("Writing file:" + fullFilePathPlantUmlFile);
		FileUtils.writeStringToFile(new File(fullFilePathPlantUmlFile), content, Charset.defaultCharset());
	}
	
	private static void createDotAndPngFiles(final Workspace workspace) throws IOException {
		final StringWriter stringWriter = new StringWriter();
		final DotWriter dotWriter = new DotWriter();
		dotWriter.write(workspace, stringWriter);

		final String[] split = stringWriter.getBuffer().toString().split("#");

		for (final String string : split) {
			if (!string.isEmpty()) {
				final String[] split2 = string.split(System.lineSeparator(), 2);
				final String fullFilePathDotFile = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator
						+ "target" + File.separator + "site" + File.separator + "architecture" + File.separator
						+ split2[0].trim().replace(" ", "_").replaceAll("_-_", "_") + ".dot";
				FileUtils.writeStringToFile(new File(fullFilePathDotFile), split2[1], Charset.defaultCharset());

				System.out.println("Writing file:" + fullFilePathDotFile);

				final List<String> commands = new ArrayList<String>();
				commands.add("/bin/bash");
				commands.add("-c");
				commands.add("dot dot -Tpng -O " + fullFilePathDotFile);

				final Runtime r = Runtime.getRuntime();
				try {
					System.out.println("generate png :" + fullFilePathDotFile.replace(".dot", ".png"));
					final Process p = r.exec(commands.toArray(new String[commands.size()]));

					p.waitFor();
					final BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line = "";

					while ((line = b.readLine()) != null) {
						System.out.println(line);
					}

					b.close();
				} catch (final Exception e) {
					System.err.println("Failed to generate png (dot executable missing) for :" + fullFilePathDotFile);
					e.printStackTrace();
				}

			}
		}
	}
}