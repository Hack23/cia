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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentData_;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer_;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeDocumentData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeDocumentData_;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData_;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;
import com.hack23.cia.service.data.api.CommitteeProposalComponentDataDAO;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.data.api.DocumentStatusContainerDAO;
import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class RiksdagenImportServiceImpl.
 */
@Component("RiksdagenImportService")
@Transactional(propagation = Propagation.MANDATORY)
final class RiksdagenImportServiceImpl implements RiksdagenImportService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenImportServiceImpl.class);

	/** The Constant RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT. */
	private static final String RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant RIKSDAGEN_JAVA_SIMPLE_DATE_TIME_FORMAT. */
	private static final String RIKSDAGEN_JAVA_SIMPLE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;

	/** The committee proposal component data DAO. */
	@Autowired
	private CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO;

	/** The document content data DAO. */
	@Autowired
	private DocumentContentDataDAO documentContentDataDAO;

	/** The document element DAO. */
	@Autowired
	private DocumentElementDAO documentElementDAO;

	/** The document status container DAO. */
	@Autowired
	private DocumentStatusContainerDAO documentStatusContainerDAO;

	/** The vote data DAO. */
	@Autowired
	private VoteDataDAO voteDataDAO;

	/**
	 * Instantiates a new riksdagen import service impl.
	 */
	public RiksdagenImportServiceImpl() {
		super();
	}

	/**
	 * Creates the map from list.
	 *
	 * @param all
	 *            the all
	 * @return the map
	 */
	private static Map<String, String> createMapFromList(final List<String> all) {
		final Map<String, String> map = new ConcurrentHashMap<>();

		for (final String documentElement : all) {
			map.put(documentElement, documentElement);
		}
		return map;
	}

	/**
	 * Creates the map from list vote.
	 *
	 * @param list
	 *            the list
	 * @return the map
	 */
	private static Map<String, String> createMapFromListVote(final List<VoteDataEmbeddedId> list) {
		final Map<String, String> map = new ConcurrentHashMap<>();

		for (final VoteDataEmbeddedId documentElement : list) {
			map.put(documentElement.getBallotId(), documentElement.getBallotId());
		}
		return map;
	}

	@Override
	public List<String> getAvaibleCommitteeProposal() {
		return documentStatusContainerDAO.getAvaibleCommitteeProposal();
	}

	@Override
	public List<DocumentStatusContainer> getNoneCompletedDocumentStatusCommitteeReports() {
		return documentStatusContainerDAO.findListByEmbeddedProperty(DocumentStatusContainer_.document,DocumentData.class,DocumentData_.documentType,"bet");
	}


	@Override
	public List<String> getAvaibleDocumentContent() {
		return documentElementDAO.getAvaibleDocumentContent();
	}

	@Override
	public Map<String, String> getCommitteeProposalComponentDataMap() {
		return createMapFromList(committeeProposalComponentDataDAO.getIdList());
	}

	@Override
	public List<CommitteeProposalComponentData> getNoneCompletedCommitteeProposal() {
		return committeeProposalComponentDataDAO.findListByEmbeddedProperty(CommitteeProposalComponentData_.document,CommitteeDocumentData.class,CommitteeDocumentData_.status,"planerat");
	}


	@Override
	public Map<String, String> getDocumentContentMap() {
		return createMapFromList(documentContentDataDAO.getIdList());
	}

	@Override
	public Map<String, String> getDocumentElementMap(final Date after, final List<DocumentType> downloadType,
			final boolean onlyWithDocStatus) {
		final List<DocumentElement> all = documentElementDAO.getAll();

		final List<String> documentTypeValues = new ArrayList<>();

		for (final DocumentType docType : downloadType) {
			documentTypeValues.add(docType.value());
		}

		final Map<String, String> map = new ConcurrentHashMap<>();

		for (final DocumentElement documentElement : all) {
			if (checkIncludeDate(after, documentTypeValues, documentElement) && checkIncludeStatus(onlyWithDocStatus, documentElement)) {
				map.put(documentElement.getId(), documentElement.getDocumentType());
			}
		}
		return map;
	}

	/**
	 * Check include status.
	 *
	 * @param onlyWithDocStatus
	 *            the only with doc status
	 * @param documentElement
	 *            the document element
	 * @return true, if successful
	 */
	private static boolean checkIncludeStatus(final boolean onlyWithDocStatus, final DocumentElement documentElement) {
		return !onlyWithDocStatus || documentElement.getDocumentStatusUrlXml() != null;
	}

	/**
	 * Check include date.
	 *
	 * @param after
	 *            the after
	 * @param documentTypeValues
	 *            the document type values
	 * @param documentElement
	 *            the document element
	 * @return true, if successful
	 */
	private static boolean checkIncludeDate(final Date after, final List<String> documentTypeValues,
			final DocumentElement documentElement) {
		try {
			if (documentElement.getMadePublicDate() != null && documentElement.getMadePublicDate().length() > 0) {
				return getDate(documentElement.getMadePublicDate()).after(after)
						&& documentTypeValues.contains(documentElement.getDocumentType());
			} else {
				return getDate(documentElement.getCreatedDate()).after(after)
						&& documentTypeValues.contains(documentElement.getDocumentType());
			}
		} catch (final ParseException e) {
			LOGGER.warn("Problem getting date from{} : exception:{}", documentElement, e);
			return false;
		}
	}

	/**
	 * Gets the date.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date getDate(final String date) throws ParseException {
		if (RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT.length() > date.length()) {
			return new SimpleDateFormat(RIKSDAGEN_JAVA_SIMPLE_DATE_TIME_FORMAT, Locale.ENGLISH).parse(date);
		} else {
			return new SimpleDateFormat(RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT, Locale.ENGLISH).parse(date);
		}
	}

	@Override
	public Map<String, String> getDocumentStatusContainerMap() {
		return createMapFromList(documentStatusContainerDAO.getIdList());
	}

	@Override
	public Map<String, String> getLoadedBallotIdMap() {
		return createMapFromListVote(voteDataDAO.getBallotIdList());
	}

	@Override
	public int getStartYearForDocumentElement() {
		final ApplicationConfiguration registeredUsersGetAdminConfig = applicationConfigurationService
				.checkValueOrLoadDefault("Load Riksdagen documents from year", "Load Riksdagen documents from year",
						ConfigurationGroup.AGENT, RiksdagenImportService.class.getSimpleName(),
						"Riksdagen Import Service", "Responsible import Riksdagen data",
						"agent.riksdagen.documents.loadfromyear", "2000");

		return documentElementDAO
				.getMissingDocumentStartFromYear(Integer.parseInt(registeredUsersGetAdminConfig.getPropertyValue()));
	}

}
