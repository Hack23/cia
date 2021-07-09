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
import com.structurizr.model.Model;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.DeploymentView;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;

import net.sourceforge.plantuml.Run;

/**
 * The Class AwsPublicSystemDocumentation.
 */
public class AwsPublicSystemDocumentation {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(final String[] args) throws Exception {
		final Workspace workspace = new Workspace("Citizen Intelligence Agency", "Public Aws System Documentation");
		final Model model = workspace.getModel();
		final ViewSet viewSet = workspace.getViews();

		final SoftwareSystem ciaSystem = model.addSoftwareSystem("Citizen Intelligence Agency",
				"Tracking politicians like bugs!");

		final DeploymentNode masterAccountNode = model.addDeploymentNode("Master Account", "AWS", "Aws Account");
		final Container awsAccountContainer = ciaSystem.addContainer("Master Account", "AWS", "Aws Account");

		final DeploymentNode iamAccountNode = model.addDeploymentNode("IAM Account", "AWS", "Aws Account");
		final Container iamAccountContainer = ciaSystem.addContainer("IAM Account", "AWS", "Aws Account");

		final DeploymentNode devAccountNode = model.addDeploymentNode("Development Account", "AWS", "Aws Account");
		final Container devAccountContainer = ciaSystem.addContainer("Development Account", "AWS", "Aws Account");

		final DeploymentNode opCenterAccountNode = model.addDeploymentNode("Operation Center Account", "AWS", "Aws Account");
		final Container opCenterAccountContainer = ciaSystem.addContainer("Operation Center Account", "AWS", "Aws Account");

		final DeploymentNode auditAccountNode = model.addDeploymentNode("Audit Account", "AWS", "Aws Account");
		final Container auditAccountContainer = ciaSystem.addContainer("Audit Account", "AWS", "Aws Account");

		final DeploymentNode appAccountNode = model.addDeploymentNode("Application Account", "AWS", "Aws Account");
		final Container appAccountContainer = ciaSystem.addContainer("Application Account", "AWS", "Aws Account");

		awsAccountContainer.uses(iamAccountContainer, "create/restrict");
		awsAccountContainer.uses(devAccountContainer, "create/restrict");
		awsAccountContainer.uses(opCenterAccountContainer, "create/restrict");
		awsAccountContainer.uses(auditAccountContainer, "create/restrict");
		awsAccountContainer.uses(appAccountContainer, "create/restrict");

		awsAccountContainer.uses(auditAccountContainer, "publish event/audit");
		iamAccountContainer.uses(auditAccountContainer, "publish event/audit");
		devAccountContainer.uses(auditAccountContainer, "publish event/audit");
		opCenterAccountContainer.uses(auditAccountContainer, "publish event/audit");
		appAccountContainer.uses(auditAccountContainer, "publish event/audit");

		opCenterAccountContainer.uses(auditAccountContainer, "Monitor event/audit");

		iamAccountContainer.uses(devAccountContainer, "manage access");
		iamAccountContainer.uses(appAccountContainer, "manage access");
		iamAccountContainer.uses(opCenterAccountContainer, "manage access");

		opCenterAccountNode.add(opCenterAccountContainer);
		devAccountNode.add(devAccountContainer);
		auditAccountNode.add(auditAccountContainer);
		appAccountNode.add(appAccountContainer);
		iamAccountNode.add(iamAccountContainer);
		masterAccountNode.add(awsAccountContainer);

		final DeploymentView developmentDeploymentView = viewSet.createDeploymentView(ciaSystem, "\"Production Aws Account structure\"",
				"\"Production Aws Account structure\"");

		developmentDeploymentView.add(masterAccountNode);
		developmentDeploymentView.add(iamAccountNode);
		developmentDeploymentView.add(devAccountNode);
		developmentDeploymentView.add(opCenterAccountNode);
		developmentDeploymentView.add(auditAccountNode);
		developmentDeploymentView.add(appAccountNode);


		final Styles styles = viewSet.getConfiguration().getStyles();
		styles.addElementStyle(Tags.COMPONENT).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.CONTAINER).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);
		styles.addElementStyle("Database").shape(Shape.Cylinder);

		printPlantUml(workspace);
		System.setProperty("PLANTUML_LIMIT_SIZE", "8192");
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
		plantUMLWriter.write(workspace, stringWriter);
		final String allPlantUmlsString = stringWriter.toString();

		final String awsSystem = allPlantUmlsString.substring(allPlantUmlsString.lastIndexOf("@startuml"),
				allPlantUmlsString.lastIndexOf("@enduml") + "@enduml".length());

		writePlantUml("Citizen-Intelligence-Agency-Prod-Aws-Account-Structure", awsSystem);

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
	private static void writePlantUml(final String filename, final String content) throws IOException, InterruptedException {
		final String fullFilePathPlantUmlFile = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator
				+ "target" + File.separator + "site" + File.separator + "architecture" + File.separator + filename
				+ ".pu";
		FileUtils.writeStringToFile(new File(fullFilePathPlantUmlFile), content, Charset.defaultCharset());
	}
}