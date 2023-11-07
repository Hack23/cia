/*
 * Copyright 2010-2021 James Pether Sörling
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

import org.apache.commons.io.FileUtils;

import com.structurizr.Workspace;
import com.structurizr.io.WorkspaceWriterException;
import com.structurizr.io.plantuml.BasicPlantUMLWriter;
import com.structurizr.io.plantuml.PlantUMLWriter;
import com.structurizr.model.Container;
import com.structurizr.model.DeploymentNode;
import com.structurizr.model.Enterprise;
import com.structurizr.model.Location;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.DeploymentView;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.SystemLandscapeView;
import com.structurizr.view.ViewSet;

import net.sourceforge.plantuml.Run;

/**
 * The Class AppPublicSystemDocumentation.
 */
public class AppPublicSystemDocumentation {

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
		final SoftwareSystem worldBankApiSystem = model.addSoftwareSystem(Location.External, "data.worldbank.org",
				"Public API Country indicators");
		final SoftwareSystem valApiSystem = model.addSoftwareSystem(Location.External, "www.val.se",
				"Public API Swedish Election data");
		final SoftwareSystem esvApiSystem = model.addSoftwareSystem(Location.External, "www.esv.se",
				"Public Data Swedish public sector spending data");

		final Container loadBalancerContainer = ciaSystem.addContainer("Loadbalancer", "Loadbalancer",
				"ALB/ELB/Apache/Nginx/HaProxy");

		final Container ciaWebContainer = ciaSystem.addContainer("Web Application", "Web Application", "Jetty/Java");

//		final SpringComponentFinderStrategy springComponentFinderStrategy = new SpringComponentFinderStrategy(new FirstImplementationOfInterfaceSupportingTypesStrategy()
//				,new ReferencedTypesSupportingTypesStrategy(),new ReferencedTypesInSamePackageSupportingTypesStrategy());
//		springComponentFinderStrategy.setIncludePublicTypesOnly(false);

//		final ComponentFinder componentFinderWeb = new ComponentFinder(ciaWebContainer, "com.hack23.cia",
//				springComponentFinderStrategy);
//		componentFinderWeb.exclude(".*ui.application.web.*");
//		componentFinderWeb.exclude(".*ui.application.views.common.pagelinks.*");
//		componentFinderWeb.exclude(".*ui.application.views.admin.*");
//		componentFinderWeb.exclude(".*ui.application.views.user.*");
//
//		componentFinderWeb.exclude(".*service.external.*");
//		componentFinderWeb.exclude(".*service.component.*");
//		componentFinderWeb.exclude(".*package.*");
//		componentFinderWeb.exclude(".*service.impl.action.*");
//		componentFinderWeb.exclude(".*service.impl.email.*");
//		componentFinderWeb.exclude(".*service.impl.rules.*");
//		componentFinderWeb.exclude(".*service.impl.task.*");
//		componentFinderWeb.exclude(".*service.data.impl.*");

//		componentFinderWeb.findComponents();

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

		final SystemLandscapeView enterpriseContextView = viewSet.createSystemLandscapeView("\"Enterprise\"",
				"\"Enterprise\"");
		enterpriseContextView.addAllElements();
		final Enterprise enterprise = new Enterprise("Hack23");
		enterpriseContextView.getModel().setEnterprise(enterprise);

		viewSet.createSystemContextView(ciaSystem, "\"System context\"", "\"System context\"").addAllElements();
		viewSet.createContainerView(ciaSystem, "\"Container view\"", "\"Application Overview\"").addAllContainers();
		viewSet.createComponentView(ciaWebContainer, "\"Web\"", "\"Web\"").addAllComponents();

		final DeploymentNode awsAccountNode = model.addDeploymentNode("AppOrg Account", "AWS", "Aws Account");

		final DeploymentNode awsVpcNode = awsAccountNode.addDeploymentNode("Project Network", "AWS", "VPC");

		final DeploymentNode wafNode = awsAccountNode.addDeploymentNode("Web Application Firewall", "AWS", "WAF");
		final Container ciaWafContainer = ciaSystem.addContainer("WebACL Rules", "AWS", "WAF");
		wafNode.add(ciaWafContainer);
		ciaWafContainer.uses(loadBalancerContainer, "Protects/Filter");

		final DeploymentNode awsAuditAccountNode = model.addDeploymentNode("Audit Account", "AWS", "Aws Account");

		final DeploymentNode awsConfigNode = awsAuditAccountNode.addDeploymentNode("Config", "AWS", "Config");
		final Container awsConfigContainer = ciaSystem.addContainer("Rules", "AWS", "Config Rules");
		awsConfigNode.add(awsConfigContainer);

		final DeploymentNode awsInspectorNode = awsAccountNode.addDeploymentNode("System Compliance checks", "AWS",
				"Inspector");
		final Container awsInspectorContainer = ciaSystem.addContainer("ScanningRules", "AWS", "Scanning Rules");
		awsInspectorNode.add(awsInspectorContainer);
		awsInspectorContainer.uses(ciaWebContainer, "Inspects");

		final DeploymentNode awsSSMNode = awsAccountNode.addDeploymentNode("Patch Compliance", "AWS",
				"System Mananger");
		final Container awsSSMContainer = ciaSystem.addContainer("InventoryList", "AWS", "InventoryList");
		awsSSMNode.add(awsSSMContainer);
		awsSSMContainer.uses(ciaWebContainer, "Run Commands");

		final DeploymentNode awsQuickSightNode = awsAccountNode.addDeploymentNode("Business analytics", "AWS",
				"QuickSight");
		final Container awsQuickSightContainer = ciaSystem.addContainer("Dashboards", "AWS", "Dashboards");
		awsQuickSightNode.add(awsQuickSightContainer);
		awsQuickSightContainer.uses(relationalDatabase, "Loads Data");

		final DeploymentNode awsGuardDutyNode = awsAuditAccountNode.addDeploymentNode("Guard Duty", "AWS", "GuardDuty");
		final Container awsGuardDutyContainer = ciaSystem.addContainer(
				"Intelligent threat detection and continuous monitoring", "AWS",
				"Intelligent threat detection and continuous monitoring");
		awsGuardDutyNode.add(awsGuardDutyContainer);

		final DeploymentNode awsMacieNode = awsAuditAccountNode.addDeploymentNode("A machine learning-powered security",
				"AWS", "macie");
		final Container awsMacieContainer = ciaSystem.addContainer("discover, classify, and protect sensitive data",
				"AWS", "discover, classify, and protect sensitive data");
		awsMacieNode.add(awsMacieContainer);

		final DeploymentNode awsLogGroupNode = awsAuditAccountNode.addDeploymentNode("LogGroup", "AWS", "Cloudwatch");
		final Container awsLogstreamContainer = ciaSystem.addContainer("Logstreams", "AWS", "LogStream");
		awsLogGroupNode.add(awsLogstreamContainer);

		ciaWebContainer.uses(awsLogstreamContainer, "Write logs");
		relationalDatabase.uses(awsLogstreamContainer, "Write logs");

		final DeploymentNode awsCloudtrailNode = awsAuditAccountNode.addDeploymentNode("Audit", "AWS", "Cloudtrail");
		final Container awsAuditLogBucketContainer = ciaSystem.addContainer("LogBucket", "AWS", "S3");
		awsCloudtrailNode.add(awsAuditLogBucketContainer);

		final DeploymentNode awsAcessLogsNode = awsAuditAccountNode.addDeploymentNode("Access Logs", "AWS", "S3");
		final Container awsAccessLogBucketContainer = ciaSystem.addContainer("AccessLogBucket", "AWS", "S3");
		awsAcessLogsNode.add(awsAccessLogBucketContainer);

		loadBalancerContainer.uses(awsAccessLogBucketContainer, "Write logs");

		final DeploymentNode applicationLoadbalancerNode = awsAccountNode.addDeploymentNode("Application Loadbalancer",
				"AWS", "ALB");
		applicationLoadbalancerNode.add(loadBalancerContainer);
		wafNode.uses(applicationLoadbalancerNode, "Protects", "filter rules");

		final DeploymentNode webNode = awsVpcNode.addDeploymentNode("Application", "AWS", "EC2", 2);
		webNode.addDeploymentNode("Jetty", "Jetty", "JVM").add(ciaWebContainer);
		applicationLoadbalancerNode.uses(webNode, "Uses", "https");

		final DeploymentNode databaseNode = awsVpcNode.addDeploymentNode("Database", "AWS", "RDS", 2);
		databaseNode.add(relationalDatabase);
		webNode.uses(databaseNode, "Uses", "jdbc");

		final DeploymentNode githubAccountNode = model.addDeploymentNode("Github Org", "Github", "Github Org");
		final Container sourceCodeContainer = ciaSystem.addContainer("SCM", "Github", "Scm");
		githubAccountNode.add(sourceCodeContainer);
		final Container documentationContainer = ciaSystem.addContainer("Documentation", "Github", "Documentation");
		githubAccountNode.add(documentationContainer);

		final DeploymentNode devNetworkNode = model.addDeploymentNode("Dev Network", "AWS", "Dev Network");

		final Container nexusContainer = ciaSystem.addContainer("Nexus", "Dev", "Nexus");
		devNetworkNode.add(nexusContainer);

		final Container sonarContainer = ciaSystem.addContainer("Sonarqube", "Dev", "Sonarqube");
		devNetworkNode.add(sonarContainer);

		final Container jenkinsContainer = ciaSystem.addContainer("Jenkins", "Dev", "Jenkins");
		jenkinsContainer.uses(sourceCodeContainer, "builds");
		jenkinsContainer.uses(sonarContainer, "Publish QA metrics", "https");
		jenkinsContainer.uses(nexusContainer, "publish artifacts", "https");
		jenkinsContainer.uses(documentationContainer, "publish Documentation", "https");
		devNetworkNode.add(jenkinsContainer);

		final DeploymentNode sumologicSecurityAccountNode = model.addDeploymentNode("Security Account", "Sumologic",
				"Sumologic Account");

		final DeploymentNode sumologicNetworkSecurityDashboardNode = sumologicSecurityAccountNode
				.addDeploymentNode("Nework Security Dashboard", "AWS", "Nework Security Dashboard");

		final DeploymentNode sumologicServerSecurityDashboardNode = sumologicSecurityAccountNode
				.addDeploymentNode("Server Security Dashboard", "AWS", "Server Security Dashboard");

		final DeploymentNode sumologicAwsAccountSecurityDashboardNode = sumologicSecurityAccountNode
				.addDeploymentNode("AWS Account Security Dashboard", "AWS", "AWS Account Security Dashboard");

		final DeploymentNode sumologicApplicationSecurityDashboardNode = sumologicSecurityAccountNode
				.addDeploymentNode("Application Security Dashboard", "AWS", "Application Security Dashboard");

		final Container sumologicVpcFlowLogsContainer = ciaSystem.addContainer("VpcFlowLogs", "Sumologic",
				"VpcFlowLogs");
		sumologicVpcFlowLogsContainer.uses(awsLogstreamContainer, "Recieve logs");
		sumologicNetworkSecurityDashboardNode.add(sumologicVpcFlowLogsContainer);

		final Container sumologicCloudtrailConfigContainer = ciaSystem.addContainer("CloudTrailLogs", "Sumologic",
				"CloudTrailLogs");
		sumologicCloudtrailConfigContainer.uses(awsAuditLogBucketContainer, "Recieve logs");
		sumologicAwsAccountSecurityDashboardNode.add(sumologicCloudtrailConfigContainer);

		final Container sumologicAwsConfigContainer = ciaSystem.addContainer("AwsConfigLogs", "Sumologic",
				"AwsConfigLogs");
		sumologicAwsConfigContainer.uses(awsConfigContainer, "Recieve logs");
		sumologicAwsAccountSecurityDashboardNode.add(sumologicAwsConfigContainer);

		final Container sumologicEc2SystemLogsContainer = ciaSystem.addContainer("Ec2SystemLogs", "Sumologic",
				"Ec2SystemLogs");
		sumologicEc2SystemLogsContainer.uses(awsLogstreamContainer, "Recieve logs");
		sumologicServerSecurityDashboardNode.add(sumologicEc2SystemLogsContainer);

		final Container sumologicEc2ApplicationLogsContainer = ciaSystem.addContainer("Ec2ApplicationLogs", "Sumologic",
				"Ec2ApplicationLogs");
		sumologicEc2ApplicationLogsContainer.uses(awsLogstreamContainer, "Recieve logs");
		sumologicApplicationSecurityDashboardNode.add(sumologicEc2ApplicationLogsContainer);

		final Container sumologicAwsInspectorResultsContainer = ciaSystem.addContainer("AwsInspectorResults",
				"Sumologic", "AwsInspectorResults");
		sumologicAwsInspectorResultsContainer.uses(awsInspectorContainer, "Recieve reports");
		sumologicServerSecurityDashboardNode.add(sumologicAwsInspectorResultsContainer);

		final Container sumologicAwsAlbLogsContainer = ciaSystem.addContainer("AwsLoadbalancerAccessLogs", "Sumologic",
				"AwsLoadbalancerAccessLogs");
		sumologicAwsAlbLogsContainer.uses(awsAccessLogBucketContainer, "Recieve logs");
		sumologicNetworkSecurityDashboardNode.add(sumologicAwsAlbLogsContainer);

		final DeploymentView developmentDeploymentView = viewSet.createDeploymentView(ciaSystem, "\"Deployment\"",
				"\"Deployment Aws.\"");

		developmentDeploymentView.add(awsAuditAccountNode);
		developmentDeploymentView.add(wafNode);
		developmentDeploymentView.add(awsQuickSightNode);
		developmentDeploymentView.add(awsInspectorNode);
		developmentDeploymentView.add(awsSSMNode);
		developmentDeploymentView.add(sumologicSecurityAccountNode);
		developmentDeploymentView.add(githubAccountNode);
		developmentDeploymentView.add(devNetworkNode);

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
		System.setProperty("PLANTUML_LIMIT_SIZE", "16384");
		Run.main(new String[] { Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + "target"
				+ File.separator + "site" + File.separator + "architecture" + File.separator });
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
	private static void printPlantUml(final Workspace workspace)
			throws WorkspaceWriterException, IOException, InterruptedException {
		final StringWriter stringWriter = new StringWriter();
		final PlantUMLWriter plantUMLWriter = new BasicPlantUMLWriter();
		//plantUMLWriter.setSizeLimit(16384);
		plantUMLWriter.write(workspace, stringWriter);
		String allPlantUmlsString = stringWriter.toString();

		final String systemUml2 = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"),
				allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(systemUml2, "");
		writePlantUml("Citizen-Intelligence-Agency-System-System-Deployment", systemUml2);

		final String componentUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"),
				allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(componentUml, "");
		writePlantUml("Citizen-Intelligence-Agency-System-Web-Application-Components", componentUml);

		final String containersUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"),
				allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(containersUml, "");
		writePlantUml("Citizen-Intelligence-Agency-System-Containers", containersUml);

		final String systemUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"),
				allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(systemUml, "");
		writePlantUml("Citizen-Intelligence-Agency-System-System-Context", systemUml);

		final String enterpriseUml = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"),
				allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());
		allPlantUmlsString = allPlantUmlsString.replace(enterpriseUml, "");
		writePlantUml("Enterprise-Context-for-Hack23", enterpriseUml);
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
	private static void writePlantUml(final String filename, final String content)
			throws IOException, InterruptedException {
		final String fullFilePathPlantUmlFile = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator
				+ "target" + File.separator + "site" + File.separator + "architecture" + File.separator + filename
				+ ".pu";
		FileUtils.writeStringToFile(new File(fullFilePathPlantUmlFile), content, Charset.defaultCharset());
	}
}