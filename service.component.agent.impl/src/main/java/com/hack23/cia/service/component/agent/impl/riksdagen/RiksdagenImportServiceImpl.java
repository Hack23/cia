/*
 * Copyright 2010 James Pether Sörling
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
 *	$Id: RiksdagenImportServiceImpl.java 6119 2015-07-31 17:44:12Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.component.agent.impl/src/main/java/com/hack23/cia/service/component/agent/impl/riksdagen/RiksdagenImportServiceImpl.java $
 */
package com.hack23.cia.service.component.agent.impl.riksdagen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData_;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.service.data.api.CommitteeProposalComponentDataDAO;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.data.api.DocumentElementDAO;
import com.hack23.cia.service.data.api.DocumentStatusContainerDAO;
import com.hack23.cia.service.data.api.PersonDataDAO;
import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class RiksdagenImportServiceImpl.
 */
@Component(value = "RiksdagenImportService")
@Transactional(propagation=Propagation.MANDATORY)
public final class RiksdagenImportServiceImpl implements RiksdagenImportService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenImportServiceImpl.class);

	/** The Constant RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT. */
	private static final String RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant RIKSDAGEN_JAVA_SIMPLE_DATE_TIME_FORMAT. */
	private static final String RIKSDAGEN_JAVA_SIMPLE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** The committee proposal component data dao. */
	@Autowired
	private CommitteeProposalComponentDataDAO committeeProposalComponentDataDAO;

	/** The document content data dao. */
	@Autowired
	private DocumentContentDataDAO documentContentDataDAO;

	/** The document element dao. */
	@Autowired
	private DocumentElementDAO documentElementDAO;

	/** The document status container dao. */
	@Autowired
	private DocumentStatusContainerDAO documentStatusContainerDAO;

	/** The person data dao. */
	@Autowired
	private PersonDataDAO personDataDAO;

	/** The vote data dao. */
	@Autowired
	private VoteDataDAO voteDataDAO;

	/**
	 * Creates the map from list.
	 *
	 * @param all
	 *            the all
	 * @return the map
	 */
	private static Map<String, String> createMapFromList(final List<String> all) {
		final Map<String, String> map = new ConcurrentHashMap<String, String>();

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
		final Map<String, String> map = new ConcurrentHashMap<String, String>();

		for (final VoteDataEmbeddedId documentElement : list) {
			map.put(documentElement.getBallotId(), documentElement.getBallotId());
		}
		return map;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getAvaibleCommitteeProposal()
	 */
	@Override
	public List<String> getAvaibleCommitteeProposal() {
		return documentStatusContainerDAO.getAvaibleCommitteeProposal();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getAvaibleDocumentContent()
	 */
	@Override
	public List<String> getAvaibleDocumentContent() {
		return documentElementDAO.getAvaibleDocumentContent();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getAvaibleDocumentStatus()
	 */
	@Override
	public List<String> getAvaibleDocumentStatus() {
		return documentElementDAO.getIdList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getCommitteeProposalComponentDataMap()
	 */
	@Override
	public Map<String, String> getCommitteeProposalComponentDataMap() {
		return createMapFromList(committeeProposalComponentDataDAO.getIdList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getDocumentContentMap()
	 */
	@Override
	public Map<String, String> getDocumentContentMap() {
		return createMapFromList(documentContentDataDAO.getIdList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getDocumentElementMap()
	 */
	@Override
	public Map<String, String> getDocumentElementMap() {
		final List<String> all = documentElementDAO.getIdList();
		return createMapFromList(all);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getDocumentElementMap(java.util.Date, java.util.List, boolean)
	 */
	@Override
	public Map<String, DocumentType> getDocumentElementMap(final Date after,
			final List<DocumentType> downloadType,
			final boolean onlyWithDocStatus) {
		final List<DocumentElement> all = documentElementDAO.getAll();

		final Map<String, DocumentType> map = new ConcurrentHashMap<String, DocumentType>();

		for (final DocumentElement documentElement : all) {
			try {



				if (getDate(documentElement.getMadePublicDate())
						.after(after)
						&& downloadType.contains(documentElement.getDocumentType())) {
					if (onlyWithDocStatus) {
						if (documentElement.getDocumentStatusUrlXml() != null) {
							map.put(documentElement.getId(),
									documentElement.getDocumentType());
						}

					} else {
						map.put(documentElement.getId(),
								documentElement.getDocumentType());
					}
				}
			} catch (final ParseException e) {
				LOGGER.warn("Add msg",e);
			}
		}
		return map;
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
		if(RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT.length() > date.length()) {
			return new SimpleDateFormat(
					RIKSDAGEN_JAVA_SIMPLE_DATE_TIME_FORMAT,Locale.ENGLISH).parse(date);
		} else {
			return new SimpleDateFormat(
					RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT,Locale.ENGLISH).parse(date);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getDocumentStatusContainerMap()
	 */
	@Override
	public Map<String, String> getDocumentStatusContainerMap() {
		return createMapFromList(documentStatusContainerDAO.getIdList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * getLoadedBallotIdMap()
	 */
	@Override
	public Map<String, String> getLoadedBallotIdMap() {
		return createMapFromListVote(voteDataDAO.getBallotIdList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.component.agent.impl.AgentWorkService#getPersonMap
	 * ()
	 */
	@Override
	public Map<String, String> getPersonMap() {
		return createMapFromList(personDataDAO.getIdList());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.component.agent.impl.AgentWorkService#update(com
	 * .hack23.cia.model.external.riksdagen.person.impl.PersonData)
	 */
	@Override
	public void update(final PersonData personData) {
		if (personDataDAO.load(personData.getId()) !=null) {
			personDataDAO.merge(personData);
		} else {
			personDataDAO.persist(personData);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateCommitteeProposalComponentDataList(java.util.List)
	 */
	@Override
	public void updateCommitteeProposalComponentData(
			final CommitteeProposalComponentData committeeProposal) {
		if (committeeProposalComponentDataDAO.findFirstByProperty(CommitteeProposalComponentData_.document , committeeProposal.getDocument()) !=null) {
			committeeProposalComponentDataDAO.merge(committeeProposal);
		} else {
			committeeProposalComponentDataDAO.persist(committeeProposal);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateDocumentContentDataList(java.util.List)
	 */
	@Override
	public void updateDocumentContentData(final DocumentContentData documentData) {
		if (documentContentDataDAO.findFirstByProperty(DocumentContentData_.id, documentData.getId()) !=null) {
			//documentContentDataDAO.merge(documentData);
		} else {
			documentContentDataDAO.persist(documentData);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateDocumentDataList(java.util.List)
	 */
	@Override
	public void updateDocumentData(final DocumentStatusContainer documentData) {
		documentStatusContainerDAO.persist(documentData);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateDocumentElementList(java.util.List)
	 */
	@Override
	public void updateDocumentElement(final DocumentElement documentData) {

		if (documentElementDAO.checkDocumentElement(documentData.getId())) {
			documentElementDAO.merge(documentData);
		} else {
			documentElementDAO.persist(documentData);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.component.agent.impl.AgentWorkService#
	 * updateVoteDataData(java.util.List)
	 */
	@Override
	public void updateVoteDataData(final List<VoteData> list) {
		voteDataDAO.persist(list);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.component.agent.impl.riksdagen.RiksdagenImportService#getStartYearForDocumentElement()
	 */
	@Override
	public int getStartYearForDocumentElement() {
		return documentElementDAO.getMissingDocumentStartFromYear();
	}

}
