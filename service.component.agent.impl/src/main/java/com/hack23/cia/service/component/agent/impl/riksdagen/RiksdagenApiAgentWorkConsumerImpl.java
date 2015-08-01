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
 *	$Id: RiksdagenApiAgentWorkConsumerImpl.java 6088 2015-06-03 22:30:14Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.component.agent.impl/src/main/java/com/hack23/cia/service/component/agent/impl/riksdagen/RiksdagenApiAgentWorkConsumerImpl.java $
 */
package com.hack23.cia.service.component.agent.impl.riksdagen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.personlista.impl.PersonElement;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.model.internal.application.data.impl.RiksdagenDataSources;
import com.hack23.cia.service.component.agent.impl.common.AbstractAgentWorkConsumerImpl;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenApi;

/**
 * The Class RiksdagenApiAgentWorkConsumerImpl.
 */
@Service(value = "RiksdagenApiAgentWorkConsumer")
public final class RiksdagenApiAgentWorkConsumerImpl extends AbstractAgentWorkConsumerImpl {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenApiAgentWorkConsumerImpl.class);

	/** The Constant RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT. */
	private static final String RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** The agent work service. */
	@Autowired
	private RiksdagenImportService importService;

	/** The committee proposal component data workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData")
	private Destination committeeProposalComponentDataWorkdestination;

	/** The document content workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData")
	private Destination documentContentWorkdestination;

	/** The document element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement")
	private Destination documentElementWorkdestination;

	/** The document status container workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer")
	private Destination documentStatusContainerWorkdestination;

	/** The person element workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.personlista.impl.PersonElement")
	private Destination personElementWorkdestination;

	/** The riksdagen api. */
	@Autowired
	private RiksdagenApi riksdagenApi;

	/** The vote data workdestination. */
	@Autowired
	@Qualifier("com.hack23.cia.model.external.riksdagen.votering.impl.VoteData")
	private Destination voteDataWorkdestination;

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		final ObjectMessage msg = (ObjectMessage) message;

		try {
			LOGGER.info("Consumed message:{}", msg.getObject());
			final RiksdagenDataSources dataSource = (RiksdagenDataSources) msg.getObject();

			switch (dataSource) {
			case DOCUMENT_LIST:
				startSwedishParliamentDocumentElementLoading();
				break;
			case PERSONS:
				startSwedishParliamentPersonLoading();
				break;
			case BALLOT_LIST:
				startBallotLoading();
				break;
			case COMMITTEE_PROPOSALS:
				startProposalLoading();
				break;
			case DOCUMENT_CONTENT:
				startSwedishParliamentContentLoading();
				break;
			case DOCUMENT_STATUS:
				startSwedishParliamentDocumentStatusLoading();
				break;
			case VOTES:
				break;
			default:
				LOGGER.warn("Missing import for :{}" ,dataSource);
				break;
			}


		} catch (final JMSException e1) {
			LOGGER.warn("jms", e1);
		}

	}

	/**
	 * Start ballot loading.
	 */
	private void startBallotLoading() {
		try {
			final List<BallotDocumentElement> ballotList = riksdagenApi.getBallotList();
			final Map<String, String> alreadySavedIdMap = importService
					.getLoadedBallotIdMap();

			for (final BallotDocumentElement ballotDocument : ballotList) {
				if (!alreadySavedIdMap.containsKey(ballotDocument.getBallotId())) {
					sendMessage(voteDataWorkdestination,
							ballotDocument.getBallotId());
					LOGGER.info("Load : http://data.riksdagen.se/votering/{}", ballotDocument.getBallotId());
				}
			}
		} catch (final Exception e) {
			LOGGER.warn("Loadin ballots", e);
		}
	}

	/**
	 * Start proposal loading.
	 */
	private void startProposalLoading() {
		try {
			final List<String> avaibleCommitteeProposal = importService
					.getAvaibleCommitteeProposal();
			final Map<String, String> committeeProposalComponentDataMap = importService
					.getCommitteeProposalComponentDataMap();

			LOGGER.info("getAvaibleCommitteeProposal()={}",avaibleCommitteeProposal.size());
			LOGGER.info("getCommitteeProposalComponentDataMap()={}",committeeProposalComponentDataMap.keySet().size());

			for (final String id : avaibleCommitteeProposal) {
				if (!committeeProposalComponentDataMap.containsKey(id)) {
					sendMessage(
							committeeProposalComponentDataWorkdestination,
							id);

					LOGGER.info("load http://data.riksdagen.se/utskottsforslag/{}", id);
				}
			}
		} catch (final Exception e1) {
			LOGGER.warn("Loading avaibleCommitteeProposal", e1);
		}
	}

	/**
	 * Start swedish parliament content loading.
	 */
	private void startSwedishParliamentContentLoading() {
		try {
			final Map<String, String> documentStatusContainerMap = importService
					.getDocumentContentMap();

			final List<String> avaibleDocumentStatus = importService
					.getAvaibleDocumentContent();

			for (final String id : avaibleDocumentStatus) {
				if (!documentStatusContainerMap.containsKey(id)) {
					sendMessage(documentContentWorkdestination,
							id);
				}
			}

		} catch (final Exception e) {
			LOGGER.warn("error loading document content", e);
		}
	}

	/**
	 * Start swedish parliament document element loading.
	 */
	private void startSwedishParliamentDocumentElementLoading() {
		try {

			final int startYearForDocumentElement = importService.getStartYearForDocumentElement();

			final org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTime fromDateTime = fmt.parseDateTime(startYearForDocumentElement+ "-01-01");

			DateTime loadedWeekDate = fmt.parseDateTime(startYearForDocumentElement+ "-01-01");

			final DateTime toDate = new DateTime();
			while (loadedWeekDate.isBefore(toDate)) {
				loadedWeekDate = loadedWeekDate.plusWeeks(1);

				riksdagenApi.processDocumentList(fmt.print(fromDateTime),fmt.print(loadedWeekDate),new DocumentElementWorkProducer());
				fromDateTime=fromDateTime.plusWeeks(1);

			}

		} catch (final Exception e) {
			LOGGER.warn("error loading documents", e);
		}
	}

	/**
	 * Start swedish parliament document status loading.
	 */
	private void startSwedishParliamentDocumentStatusLoading() {
		try {
			final Map<String, String> documentStatusContainerMap = importService
					.getDocumentStatusContainerMap();


			final List<DocumentType> selectedDocumentTypes = new ArrayList<DocumentType>();
			selectedDocumentTypes.add(DocumentType.BET);
			selectedDocumentTypes.add(DocumentType.PROP);
			selectedDocumentTypes.add(DocumentType.MOT);



			final Map<String, DocumentType> documentElementMap = importService
					.getDocumentElementMap(
							new SimpleDateFormat(
									RIKSDAGEN_JAVA_SIMPLE_DATE_FORMAT,Locale.ENGLISH).parse("2000-01-01 00:00:00"),
							selectedDocumentTypes, false);

			final Set<String> avaibleDocumentStatus = documentElementMap
					.keySet();

			for (final String id : avaibleDocumentStatus) {
				if (!documentStatusContainerMap.containsKey(id)) {
					sendMessage(documentStatusContainerWorkdestination,
							id);
				}
			}

		} catch (final Exception e) {
			LOGGER.warn("Loading document status ", e);
		}
	}

	/**
	 * Start swedish parliament person loading.
	 */
	private void startSwedishParliamentPersonLoading() {
		try {
			List<PersonElement> personList;
			personList = riksdagenApi.getPersonList().getPerson();
			final Map<String, String> currentSaved = importService.getPersonMap();

			for (final PersonElement personElement : personList) {
				if (!currentSaved.containsKey(personElement.getId())) {
					LOGGER.info("Send Load Order:{}" , personElement.getPersonUrlXml());
					sendMessage(personElementWorkdestination,
							personElement);
				}
			}
		} catch (final Exception e1) {
			LOGGER.warn("jms", e1);
		}
	}

	/**
	 * The Class DocumentElementWorkProducer.
	 */
	private class DocumentElementWorkProducer implements
	ProcessDataStrategy<DocumentElement> {

		/** The document element map. */
		private final Map<String, String> documentElementMap = importService
				.getDocumentElementMap();

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.hack23.cia.service.external.common.api.ProcessDataStrategy#process
		 * (java.lang.Object)
		 */
		@Override
		public void process(final DocumentElement t) {
			try {
				if (!documentElementMap.containsKey(t.getId())) {
					sendMessage(documentElementWorkdestination,
							t);
				}
			} catch (final Exception e) {
				LOGGER.warn("Error proccessing documentElement",e);
			}
		}
	}

}
