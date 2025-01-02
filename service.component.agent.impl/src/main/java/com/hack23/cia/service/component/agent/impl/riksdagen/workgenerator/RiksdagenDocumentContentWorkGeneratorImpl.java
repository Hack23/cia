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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;

/**
 * The Class RiksdagenDocumentContentWorkGeneratorImpl.
 */
@Service("RiksdagenDocumentContentWorkGeneratorImpl")
final class RiksdagenDocumentContentWorkGeneratorImpl extends AbstractRiksdagenDataSourcesWorkGenerator {

	/** The document content workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData")
	private Destination documentContentWorkdestination;

	/**
	 * Instantiates a new riksdagen document content work generator impl.
	 */
	public RiksdagenDocumentContentWorkGeneratorImpl() {
		super(RiksdagenDataSources.DOCUMENT_CONTENT);
	}

	@Override
	public void generateWorkOrders() {
		final Map<String, String> documentStatusContainerMap = getImportService().getDocumentContentMap();

		final List<String> avaibleDocumentStatus = getImportService().getAvaibleDocumentContent();

		for (final String id : avaibleDocumentStatus) {
			if (!documentStatusContainerMap.containsKey(id)) {
				getJmsSender().send(documentContentWorkdestination, id);
			}
		}
	}

}
