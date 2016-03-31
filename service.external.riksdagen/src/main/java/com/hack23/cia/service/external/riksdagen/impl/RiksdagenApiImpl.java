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
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.external.riksdagen.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentContainerElement;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentType;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.personlista.impl.PersonContainerElement;
import com.hack23.cia.model.external.riksdagen.utskottsforslag.impl.CommitteeProposalComponentData;
import com.hack23.cia.model.external.riksdagen.votering.impl.BallotContainer;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataDto;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.VoteListContainerElement;
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenApi;

/**
 * The Class RiksdagenApiImpl.
 */
@Component
public final class RiksdagenApiImpl implements RiksdagenApi {

	/** The Constant BALLOT. */
	private static final String BALLOT = "http://data.riksdagen.se/votering/${ID_KEY}/xml";

	/** The Constant BALLOT_LIST. */
	private static final String BALLOT_LIST="http://data.riksdagen.se/voteringlista/?rm=&bet=&punkt=&iid=&parti=&valkrets=&rost=&sz=10000&utformat=xml&gruppering=votering_id";

	/** The Constant CHANGED_SINCE_KEY. */
	private static final String CHANGED_SINCE_KEY = "${CHANGED_SINCE}";

	/** The Constant CHANGED_TO_KEY. */
	private static final String CHANGED_TO_KEY = "${CHANGED_TO}";

	/** The Constant COMMITTE_PROPOSAL. */
	private static final String COMMITTE_PROPOSAL = "http://data.riksdagen.se/utskottsforslag/${ID_KEY}/xml";

	/** The Constant CONTAINS_ONE. */
	private static final int CONTAINS_ONE = 1;

	/** The Constant DOC_ID_KEY. */
	private static final String DOC_ID_KEY = "${DOC_ID}";

	/** The Constant DOCUMENT_CONTENT. */
	private static final String DOCUMENT_CONTENT = "http://data.riksdagen.se/dokument/${DOC_ID}/text";

	/** The Constant DOCUMENT_LIST_CHANGED_DATE. */
	private static final String DOCUMENT_LIST_CHANGED_DATE = "http://data.riksdagen.se/dokumentlista/?sok=&doktyp=&rm=&from=${CHANGED_SINCE}&tom=${CHANGED_TO}&ts=&bet=&tempbet=&nr=&org=&iid=&webbtv=&talare=&exakt=&planering=&sort=datum&sortorder=asc&rapport=&utformat=xml&a=";

	/** The Constant DOCUMENT_LIST_PERSON. */
	private static final String DOCUMENT_LIST_PERSON = "http://data.riksdagen.se/dokumentlista/?sok=&doktyp=&rm=&from=${FROM}&tom=${TO}&ts=&bet=&tempbet=&nr=&org=&iid=${PERSON_ID}&webbtv=&talare=&exakt=&planering=&sort=datum&sortorder=asc&rapport=&utformat=xml&a=";

	/** The Constant DOCUMENT_LIST_TYPE. */
	private static final String DOCUMENT_LIST_TYPE = "http://data.riksdagen.se/dokumentlista/?rm=&typ=${TYPE}&d=&ts=&parti=&iid=&bet=&org=&kat=&sz=200&sort=c&utformat=xml";

	/** The Constant DOCUMENT_LIST_TYPE_YEAR. */
	private static final String DOCUMENT_LIST_TYPE_YEAR = "http://data.riksdagen.se/dokumentlista/?rm=${YEAR}&doktyp=${TYPE}&d=&ts=&parti=&iid=&bet=&org=&kat=&sz=200&sort=c&utformat=xml";

	/** The Constant DOCUMENT_LIST_YEAR. */
	private static final String DOCUMENT_LIST_YEAR = "http://data.riksdagen.se/dokumentlista/?rm=${YEAR}&typ=&d=&ts=&parti=&iid=&bet=&org=&kat=&sz=200&sort=c&utformat=xml";

	/** The Constant DOCUMENT_STATUS. */
	private static final String DOCUMENT_STATUS = "http://data.riksdagen.se/dokumentstatus/${ID_KEY}/xml";

	/** The Constant ERROR_PROCESSING_DOCUMENT. */
	private static final String ERROR_PROCESSING_DOCUMENT = "Error processing document :{}";

	/**
	 * The Constant
	 * HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://dokumentlista.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant
	 * HTTP_DOKUMENTSTATUS_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_DOKUMENTSTATUS_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://dokumentstatus.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant HTTP_PERSON_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_PERSON_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://person.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant
	 * HTTP_PERSONLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_PERSONLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://personlista.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant
	 * HTTP_UTSKOTTSFORSLAG_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_UTSKOTTSFORSLAG_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://utskottsforslag.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant HTTP_VOTERING_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_VOTERING_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://votering.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant
	 * HTTP_VOTERINGLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_VOTERINGLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://voteringlista.riksdagen.external.model.cia.hack23.com/impl";


	/** The Constant ID_KEY. */
	private static final String ID_KEY = "${ID_KEY}";

	/** The Constant LOADING_DOCUMENTS. */
	private static final String LOADING_DOCUMENTS = "Loading documents:{}/{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenApiImpl.class);

	/** The Constant PAGE_PROPERTY. */
	private static final String PAGE_PROPERTY = "&p=";

	/** The Constant PERSON. */
	private static final String PERSON = "http://data.riksdagen.se/person/${ID_KEY}";

	/** The Constant PERSON_ID_KEY. */
	private static final String PERSON_ID_KEY = "${PERSON_ID}";

	/** The Constant PERSON_LIST. */
	private static final String PERSON_LIST = "http://data.riksdagen.se/personlista/?iid=&fnamn=&enamn=&f_ar=&kn=&parti=&valkrets=&rdlstatus=samtliga&org=";

	/** The Constant PROBLEM_GETTING_BALLOT_ID_S_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_BALLOT_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting ballot id:{} from data.riksdagen.se";

	/** The Constant PROBLEM_GETTING_BALLOT_LIST_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_BALLOT_LIST_FROM_DATA_RIKSDAGEN_SE = "Problem getting ballot list from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_GETTING_COMMITTEE_PROPOSAL_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_COMMITTEE_PROPOSAL_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting committee proposal for id:{} from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_GETTING_DOCUMENT_CONTENT_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_DOCUMENT_CONTENT_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document content for id:{} from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_GETTING_DOCUMENT_LIST_CHANGED_SINCE_DATE_S_CHANGED_TO_DATE_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_DOCUMENT_LIST_CHANGED_SINCE_DATE_S_CHANGED_TO_DATE_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document list changedSinceDate:{} , changedToDate:{} from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_GETTING_DOCUMENT_LIST_FOR_DOCUMENT_TYPE_S_MAX_NUMBER_PAGES_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_DOCUMENT_LIST_FOR_DOCUMENT_TYPE_S_MAX_NUMBER_PAGES_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document list for documentType:{} , maxNumberPages: {} from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_GETTING_DOCUMENT_LIST_FOR_YEAR_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_DOCUMENT_LIST_FOR_YEAR_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document list for year: {} from data.riksdagen.se";

	/**
	 * The Constant PROBLEM_GETTING_DOCUMENT_STATUS_ID_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_DOCUMENT_STATUS_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document status id:{}  from data.riksdagen.se";

	/** The Constant PROBLEM_GETTING_PERSON_DATA_ID_S_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_PERSON_DATA_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting person data id:{}  from data.riksdagen.se";

	/** The Constant PROBLEM_GETTING_PERSON_LIST_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_PERSON_LIST_FROM_DATA_RIKSDAGEN_SE = "Problem getting person list from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_PROCCESSING_DOCUMENT_BETWEEN_CHANGED_SINCE_DATE_S_AND_CHANGE_TO_DATE.
	 */
	private static final String PROBLEM_PROCCESSING_DOCUMENT_BETWEEN_CHANGED_SINCE_DATE_S_AND_CHANGE_TO_DATE = "Problem proccessing document between changedSinceDate: {} and changeToDate {}";

	/** The Constant TYPE_KEY. */
	private static final String TYPE_KEY = "${TYPE}";

	/** The Constant YEAR_KEY. */
	private static final String YEAR_KEY = "${YEAR}";

	/** The Constant YYYY_MM_DD. */
	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	/** The person list unmarshaller. */
	@Autowired
	@Qualifier("riksdagenPersonListMarshaller")
	private Unmarshaller personListUnmarshaller;

	/** The person unmarshaller. */
	@Autowired
	@Qualifier("riksdagenPersonMarshaller")
	private Unmarshaller personUnmarshaller;

	/** The riksdagen ballot list marshaller. */
	@Autowired
	@Qualifier("riksdagenBallotListMarshaller")
	private Unmarshaller riksdagenBallotListMarshaller;


	/** The riksdagen ballot marshaller. */
	@Autowired
	@Qualifier("riksdagenBallotMarshaller")
	private Unmarshaller riksdagenBallotMarshaller;

	/** The riksdagen committee proposal marshaller. */
	@Autowired
	@Qualifier("riksdagenCommitteeProposalMarshaller")
	private Unmarshaller riksdagenCommitteeProposalMarshaller;

	/** The riksdagen document list marshaller. */
	@Autowired
	@Qualifier("riksdagenDocumentListMarshaller")
	private Unmarshaller riksdagenDocumentListMarshaller;

	/** The riksdagen document status marshaller. */
	@Autowired
	@Qualifier("riksdagenDocumentStatusMarshaller")
	private Unmarshaller riksdagenDocumentStatusMarshaller;

	/** The xml agent. */
	@Autowired
	private XmlAgent xmlAgent;


	/**
	 * Instantiates a new riksdagen api impl.
	 */
	public RiksdagenApiImpl() {
		super();
	}


	/**
	 * Best guess vote date.
	 *
	 * @param ballotContainer
	 *            the ballot container
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date bestGuessVoteDate(final BallotContainer ballotContainer) throws ParseException {
		final com.hack23.cia.model.external.riksdagen.votering.impl.BallotDocumentElement ballotDocumentElement = ballotContainer.getBallotDocumentElement();
		Date result;

		final String createdDate=ballotContainer.getBallotDocumentElement().getCreatedDate();

		if(createdDate!= null && createdDate.length()>= YYYY_MM_DD.length()) {
			result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(createdDate);
		} else {
			final String systemDate = ballotDocumentElement.getSystemDate();

			if(systemDate!= null && systemDate.length()>= YYYY_MM_DD.length()) {
				result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(systemDate);
			} else {
				result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(ballotDocumentElement.getMadePublicDate());
			}
		}
		return  result;
	}

	/**
	 * Check same date.
	 *
	 * @param voteList
	 *            the vote list
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date checkSameDate(final List<VoteDataDto> voteList) throws ParseException {
		final Set<String> set = new HashSet<>();
		Date result=null;

		for (final VoteDataDto voteData : voteList) {
			final String voteDate = voteData.getVoteDate();
			if (voteDate !=null && voteDate.length() >= YYYY_MM_DD.length()) {
				set.add(voteData.getVoteDate());
			}
		}

		if (set.size() ==CONTAINS_ONE) {
			final String dateString = set.iterator().next();
			result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(dateString);
		}
		return result;
	}

	private static String fixBrokenUrl(final String nextPage) {
		if (nextPage.startsWith("//")) {
			return "http:" + nextPage;
		} else return nextPage;
	}

	/**
	 * Process all.
	 *
	 * @param dokument
	 *            the dokument
	 * @param processStrategy
	 *            the process strategy
	 */
	private static void processAll(final List<DocumentElement> dokument,
			final ProcessDataStrategy<DocumentElement> processStrategy) {
		for (final DocumentElement documentElement : dokument) {

			try {
				processStrategy.process(documentElement);
			} catch (final Exception e) {
				LOGGER.warn(ERROR_PROCESSING_DOCUMENT, documentElement.getId(),e);
			}
		}
	}

	/**
	 * Try to find valid vote date.
	 *
	 * @param ballotContainer
	 *            the ballot container
	 * @param voteDataList
	 *            the vote data list
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date tryToFindValidVoteDate(final BallotContainer ballotContainer, final List<VoteDataDto> voteDataList)
					throws ParseException {
		Date ballotDate;
		final Date sameDate = checkSameDate(voteDataList);

		if (sameDate != null) {
			ballotDate = sameDate;
		} else {
			ballotDate = bestGuessVoteDate(ballotContainer);
		}
		return ballotDate;
	}


	@Override
	public List<VoteData> getBallot(final String id) throws DataFailureException {
		final String url = BALLOT.replace(ID_KEY, id);

		BallotContainer ballotContainer;
		try {
			ballotContainer = ((JAXBElement<BallotContainer>) xmlAgent.unmarshallXml(
					riksdagenBallotMarshaller, url,
					HTTP_VOTERING_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
					.getValue();

			final List<VoteDataDto> voteDataList = ballotContainer.getBallotDocumentData().getVoteDataList();

			final Date ballotDate=tryToFindValidVoteDate(ballotContainer, voteDataList);

			final List<VoteData> result = new ArrayList<>();
			for (final VoteDataDto voteDataDto: voteDataList) {
				final VoteData voteData= new VoteData().withEmbeddedId(new VoteDataEmbeddedId().withBallotId(voteDataDto.getBallotId()).withIntressentId(voteDataDto.getIntressentId()).withIssue(voteDataDto.getIssue()).withConcern(voteDataDto.getConcern()));

				voteData.setBankNumber(voteDataDto.getBankNumber());
				voteData.setLabel(voteDataDto.getLabel());
				voteData.setLastName(voteDataDto.getLastName());
				voteData.setBornYear(voteDataDto.getBornYear());
				voteData.setFirstName(voteDataDto.getFirstName());
				voteData.setPlace(voteDataDto.getPlace());
				voteData.setGender(voteDataDto.getGender());
				voteData.setFullName(voteDataDto.getFullName());
				voteData.setParty(voteDataDto.getParty().toUpperCase());
				voteData.setRm(voteDataDto.getRm());
				voteData.setVote(voteDataDto.getVote());
				voteData.setBallotType(voteDataDto.getBallotType());
				voteData.setElectoralRegionNumber(voteDataDto.getElectoralRegionNumber());
				voteData.setElectoralRegion(voteDataDto.getElectoralRegion());

				voteData.setVoteDate(ballotDate);

				result.add(voteData);
			}


			return result;

		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_BALLOT_ID_S_FROM_DATA_RIKSDAGEN_SE,id);
			throw new DataFailureException(e);
		}



	}

	@Override
	public List<BallotDocumentElement> getBallotList() throws DataFailureException {

		try {
			final String url = BALLOT_LIST;
			return ((JAXBElement<VoteListContainerElement>) xmlAgent.unmarshallXml(riksdagenBallotListMarshaller, url,
							HTTP_VOTERINGLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue().getVotering();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_BALLOT_LIST_FROM_DATA_RIKSDAGEN_SE);
			throw new DataFailureException(e);
		}
	}

	@Override
	public CommitteeProposalComponentData getCommitteeProposal(final String id)
			throws DataFailureException {
		try {
			final String url = COMMITTE_PROPOSAL.replace(ID_KEY, id);
			return ((JAXBElement<CommitteeProposalComponentData>) xmlAgent.unmarshallXml(riksdagenCommitteeProposalMarshaller, url,
							HTTP_UTSKOTTSFORSLAG_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_COMMITTEE_PROPOSAL_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE,id);
			throw new DataFailureException(e);
		}
	}


	@Override
	public DocumentContentData getDocumentContent(final String id) throws DataFailureException {
		try {
			final String url = DOCUMENT_CONTENT.replace(DOC_ID_KEY, id);
			final DocumentContentData documentContentData = new DocumentContentData();
			documentContentData.setId(id);

			String content;
			content = xmlAgent.retriveContent(url);
			documentContentData.setContent(content);
			return documentContentData;
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_CONTENT_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE,id);
			throw new DataFailureException(e);
		}
	}


	@Override
	public List<DocumentElement> getDocumentList(final DocumentType documentType,
			final int maxNumberPages) throws DataFailureException {
		try {
			return loadDocumentList(
					DOCUMENT_LIST_TYPE.replace(TYPE_KEY, documentType.value()),
					maxNumberPages);
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_LIST_FOR_DOCUMENT_TYPE_S_MAX_NUMBER_PAGES_S_FROM_DATA_RIKSDAGEN_SE,documentType.toString(), Integer.toString(maxNumberPages));
			throw new DataFailureException(e);
		}
	}


	@Override
	public List<DocumentElement> getDocumentList(final Integer year,
			final int maxNumberPages) throws DataFailureException {
		try {
			return loadDocumentList(
					DOCUMENT_LIST_YEAR.replace(YEAR_KEY, year.toString()),
					maxNumberPages);
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_LIST_FOR_YEAR_S_FROM_DATA_RIKSDAGEN_SE,year.toString());
			throw new DataFailureException(e);
		}
	}

	@Override
	public List<DocumentElement> getDocumentList(final String changedSinceDate,final String changedToDate,
			final int maxNumberPages) throws DataFailureException {
			try {
				return loadDocumentList(DOCUMENT_LIST_CHANGED_DATE.replace(
						CHANGED_SINCE_KEY, changedSinceDate).replace(CHANGED_TO_KEY, changedToDate), maxNumberPages);
			} catch (final Exception e) {
				LOGGER.warn(PROBLEM_GETTING_DOCUMENT_LIST_CHANGED_SINCE_DATE_S_CHANGED_TO_DATE_S_FROM_DATA_RIKSDAGEN_SE,changedSinceDate, changedToDate);
				throw new DataFailureException(e);
			}
	}

	@Override
	public DocumentStatusContainer getDocumentStatus(final String id)
			throws DataFailureException {
		try {
			final String url = DOCUMENT_STATUS.replace(ID_KEY, id);
			return ((JAXBElement<DocumentStatusContainer>) xmlAgent.unmarshallXml(riksdagenDocumentStatusMarshaller, url,
							HTTP_DOKUMENTSTATUS_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_STATUS_ID_S_FROM_DATA_RIKSDAGEN_SE,id);
			throw new DataFailureException(e);
		}
	}

	@Override
	public PersonData getPerson(final String id) throws DataFailureException {
		try {
			final String url = PERSON.replace(ID_KEY, id);
			return ((JAXBElement<com.hack23.cia.model.external.riksdagen.person.impl.PersonContainerData>) xmlAgent.unmarshallXml(personUnmarshaller, url,
							HTTP_PERSON_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue().getPerson();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_PERSON_DATA_ID_S_FROM_DATA_RIKSDAGEN_SE,id);
			throw new DataFailureException(e);
		}
	}

	@Override
	public PersonContainerElement getPersonList() throws DataFailureException {
		try {
			return ((JAXBElement<PersonContainerElement>) xmlAgent
					.unmarshallXml(personListUnmarshaller, PERSON_LIST,
							HTTP_PERSONLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue();
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_GETTING_PERSON_LIST_FROM_DATA_RIKSDAGEN_SE);
			throw new DataFailureException(e);
		}
	}

	/**
	 * Load and process document list.
	 *
	 * @param url
	 *            the url
	 * @param processStrategy
	 *            the process strategy
	 * @throws Exception
	 *             the exception
	 */
	private void loadAndProcessDocumentList(final String url,
			final ProcessDataStrategy<DocumentElement> processStrategy)
					throws Exception {
		final DocumentContainerElement dokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent
				.unmarshallXml(riksdagenDocumentListMarshaller, url,
						HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
						.getValue();

		int resultSize = dokumentLista.getDokument().size();
		processAll(dokumentLista.getDokument(), processStrategy);
		final BigInteger pages = dokumentLista.getTotalPages();
		for (int i = 1; i < pages.intValue(); i++) {
			final DocumentContainerElement otherPagesdokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent
					.unmarshallXml(riksdagenDocumentListMarshaller,
							url + PAGE_PROPERTY+ i,
							HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue();
			resultSize = resultSize + otherPagesdokumentLista.getDokument().size();
			processAll(otherPagesdokumentLista.getDokument(), processStrategy);
			LOGGER.info(LOADING_DOCUMENTS , resultSize,dokumentLista.getHits());
		}
	}


	/**
	 * Load document list.
	 *
	 * @param url
	 *            the url
	 * @param maxNumberPages
	 *            the max number pages
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	private List<DocumentElement> loadDocumentList(final String url,
			final int maxNumberPages) throws Exception {
		final List<DocumentElement> result = new ArrayList<>();

		DocumentContainerElement dokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent
				.unmarshallXml(riksdagenDocumentListMarshaller, url,
						HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
						.getValue();
		result.addAll(dokumentLista.getDokument());
		final BigInteger pages = dokumentLista.getTotalPages();
		for (int i = 1; i < pages.intValue() && i < maxNumberPages; i++) {
			dokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent
					.unmarshallXml(riksdagenDocumentListMarshaller,
							fixBrokenUrl(dokumentLista.getNextPage()),
							HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue();
			result.addAll(dokumentLista.getDokument());
			LOGGER.info(LOADING_DOCUMENTS,result.size(),dokumentLista.getHits());
		}

		return result;
	}


	@Override
	public void processDocumentList(final String changedSinceDate,final String changedToDate,
			final ProcessDataStrategy<DocumentElement> processStrategy) throws DataFailureException {
		try {
			loadAndProcessDocumentList(DOCUMENT_LIST_CHANGED_DATE.replace(
					CHANGED_SINCE_KEY, changedSinceDate).replace(CHANGED_TO_KEY, changedToDate), processStrategy);
		} catch (final Exception e) {
			LOGGER.warn(PROBLEM_PROCCESSING_DOCUMENT_BETWEEN_CHANGED_SINCE_DATE_S_AND_CHANGE_TO_DATE,changedSinceDate,changedToDate);
			throw new DataFailureException(e);
		}
	}

}
