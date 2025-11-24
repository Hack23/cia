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
package com.hack23.cia.service.external.riksdagen.impl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
import com.hack23.cia.service.external.common.api.ProcessDataStrategy;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi;

/**
 * The Class RiksdagenDocumentApiImpl.
 */
@Component
final class RiksdagenDocumentApiImpl implements RiksdagenDocumentApi {

	/** The Constant CHANGED_SINCE_KEY. */
	private static final String CHANGED_SINCE_KEY = "${CHANGED_SINCE}";

	/** The Constant CHANGED_TO_KEY. */
	private static final String CHANGED_TO_KEY = "${CHANGED_TO}";

	/** The Constant DOC_ID_KEY. */
	private static final String DOC_ID_KEY = "${DOC_ID}";

	/** The Constant DOCUMENT_CONTENT. */
	private static final String DOCUMENT_CONTENT = "https://data.riksdagen.se/dokument/${DOC_ID}/xml";

	/** The Constant DOCUMENT_LIST_CHANGED_DATE. */
	private static final String DOCUMENT_LIST_CHANGED_DATE = "https://data.riksdagen.se/dokumentlista/?sok=&doktyp=&rm=&from=${CHANGED_SINCE}&tom=${CHANGED_TO}&ts=&bet=&tempbet=&nr=&org=&iid=&webbtv=&talare=&exakt=&planering=&sort=datum&sortorder=asc&rapport=&utformat=xml&a=";

	/** The Constant DOCUMENT_LIST_TYPE. */
	private static final String DOCUMENT_LIST_TYPE = "https://data.riksdagen.se/dokumentlista/?rm=&typ=${TYPE}&d=&ts=&parti=&iid=&bet=&org=&kat=&sz=200&sort=c&utformat=xml";

	/** The Constant DOCUMENT_LIST_YEAR. */
	private static final String DOCUMENT_LIST_YEAR = "https://data.riksdagen.se/dokumentlista/?rm=${YEAR}&typ=&d=&ts=&parti=&iid=&bet=&org=&kat=&sz=200&sort=c&utformat=xml";

	/** The Constant DOCUMENT_STATUS. */
	private static final String DOCUMENT_STATUS = "https://data.riksdagen.se/dokumentstatus/${ID_KEY}/xml";

	/** The Constant ERROR_PROCESSING_DOCUMENT. */
	private static final String ERROR_PROCESSING_DOCUMENT = "Error processing document :{}";

	/**
	 * The Constant HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://dokumentlista.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant
	 * HTTP_DOKUMENTSTATUS_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_DOKUMENTSTATUS_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://dokumentstatus.riksdagen.external.model.cia.hack23.com/impl";

	/** The Constant ID_KEY. */
	private static final String ID_KEY = "${ID_KEY}";

	/** The Constant LOADING_DOCUMENTS. */
	private static final String LOADING_DOCUMENTS = "Loading documents:{}/{}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RiksdagenDocumentApiImpl.class);

	/** The Constant PAGE_PROPERTY. */
	private static final String PAGE_PROPERTY = "&p=";

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
	 * The Constant PROBLEM_GETTING_DOCUMENT_LIST_FOR_YEAR_S_FROM_DATA_RIKSDAGEN_SE.
	 */
	private static final String PROBLEM_GETTING_DOCUMENT_LIST_FOR_YEAR_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document list for year: {} from data.riksdagen.se";

	/** The Constant PROBLEM_GETTING_DOCUMENT_STATUS_ID_S_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_DOCUMENT_STATUS_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting document status id:{}  from data.riksdagen.se";

	/**
	 * The Constant
	 * PROBLEM_PROCCESSING_DOCUMENT_BETWEEN_CHANGED_SINCE_DATE_S_AND_CHANGE_TO_DATE.
	 */
	private static final String PROBLEM_PROCCESSING_DOCUMENT_BETWEEN_CHANGED_SINCE_DATE_S_AND_CHANGE_TO_DATE = "Problem proccessing document between changedSinceDate: {} and changeToDate {}";

	/** The Constant TYPE_KEY. */
	private static final String TYPE_KEY = "${TYPE}";

	/** The Constant YEAR_KEY. */
	private static final String YEAR_KEY = "${YEAR}";

	/** The riksdagen document list marshaller. */
	@Autowired
	@Qualifier("riksdagenDocumentListMarshaller")
	private Unmarshaller riksdagenDocumentListMarshaller;

	/** The riksdagen document status marshaller. */
	@Autowired
	@Qualifier("riksdagenDocumentStatusMarshaller")
	private Unmarshaller riksdagenDocumentStatusMarshaller;

	/** The xml agent. */
	private final XmlAgent xmlAgent;

	/**
	 * Instantiates a new riksdagen document api impl.
	 *
	 * @param xmlAgent
	 *            the xml agent
	 */
	@Autowired
	public RiksdagenDocumentApiImpl(final XmlAgent xmlAgent) {
		super();
		this.xmlAgent = xmlAgent;
	}

	/**
	 * Fix broken url.
	 *
	 * @param nextPage
	 *            the next page
	 * @return the string
	 */
	private static String fixBrokenUrl(final String nextPage) {
		if (nextPage.startsWith("//")) {
			return "http:" + nextPage;
		} else {
			return nextPage;
		}
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
			} catch (final RuntimeException e) {
				LOGGER.warn(ERROR_PROCESSING_DOCUMENT, documentElement.getId(), e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi#getDocumentContent(java.lang.String)
	 */
	@Override
	public DocumentContentData getDocumentContent(final String id) throws DataFailureException {
		try {
			final String encodedId = UrlHelper.urlEncode(id, StandardCharsets.UTF_8.toString());
			return new DocumentContentData().withId(encodedId)
					.withContent(xmlAgent.retriveContent(DOCUMENT_CONTENT.replace(DOC_ID_KEY, encodedId)));
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_CONTENT_FOR_ID_S_FROM_DATA_RIKSDAGEN_SE, id);
			throw new DataFailureException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi#getDocumentList(com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentType, int)
	 */
	@Override
	public List<DocumentElement> getDocumentList(final DocumentType documentType, final int maxNumberPages)
			throws DataFailureException {
		try {
			return loadDocumentList(DOCUMENT_LIST_TYPE.replace(TYPE_KEY, documentType.value()), maxNumberPages);
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_LIST_FOR_DOCUMENT_TYPE_S_MAX_NUMBER_PAGES_S_FROM_DATA_RIKSDAGEN_SE,
					documentType, Integer.toString(maxNumberPages));
			throw new DataFailureException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi#getDocumentList(java.lang.Integer, int)
	 */
	@Override
	public List<DocumentElement> getDocumentList(final Integer year, final int maxNumberPages)
			throws DataFailureException {
		try {
			return loadDocumentList(DOCUMENT_LIST_YEAR.replace(YEAR_KEY, year.toString()), maxNumberPages);
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_LIST_FOR_YEAR_S_FROM_DATA_RIKSDAGEN_SE, year);
			throw new DataFailureException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi#getDocumentList(java.lang.String, java.lang.String, int)
	 */
	@Override
	public List<DocumentElement> getDocumentList(final String changedSinceDate, final String changedToDate,
			final int maxNumberPages) throws DataFailureException {
		try {
			return loadDocumentList(DOCUMENT_LIST_CHANGED_DATE.replace(CHANGED_SINCE_KEY, changedSinceDate)
					.replace(CHANGED_TO_KEY, changedToDate), maxNumberPages);
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_LIST_CHANGED_SINCE_DATE_S_CHANGED_TO_DATE_S_FROM_DATA_RIKSDAGEN_SE,
					changedSinceDate, changedToDate);
			throw new DataFailureException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi#getDocumentStatus(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DocumentStatusContainer getDocumentStatus(final String id) throws DataFailureException {
		try {
			final String url = DOCUMENT_STATUS.replace(ID_KEY, UrlHelper.urlEncode(id, StandardCharsets.UTF_8.toString()));
			return ((JAXBElement<DocumentStatusContainer>) xmlAgent.unmarshallXml(riksdagenDocumentStatusMarshaller,
					url, HTTP_DOKUMENTSTATUS_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL, null, null)).getValue();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_DOCUMENT_STATUS_ID_S_FROM_DATA_RIKSDAGEN_SE, id);
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
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	@SuppressWarnings("unchecked")
	private void loadAndProcessDocumentList(final String url,
			final ProcessDataStrategy<DocumentElement> processStrategy) throws XmlAgentException {
		final DocumentContainerElement dokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent.unmarshallXml(
				riksdagenDocumentListMarshaller, url, HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,
				null, null)).getValue();

		int resultSize = dokumentLista.getDokument().size();
		processAll(dokumentLista.getDokument(), processStrategy);
		final BigInteger pages = dokumentLista.getTotalPages();
		for (int i = 1; i < pages.intValue(); i++) {
			final DocumentContainerElement otherPagesdokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent
					.unmarshallXml(riksdagenDocumentListMarshaller, url + PAGE_PROPERTY + i,
							HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL, null, null)).getValue();
			resultSize = resultSize + otherPagesdokumentLista.getDokument().size();
			processAll(otherPagesdokumentLista.getDokument(), processStrategy);
			LOGGER.info(LOADING_DOCUMENTS, resultSize, dokumentLista.getHits());
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
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	@SuppressWarnings("unchecked")
	private List<DocumentElement> loadDocumentList(final String url, final int maxNumberPages) throws XmlAgentException {
		final List<DocumentElement> result = new ArrayList<>();

		DocumentContainerElement dokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent.unmarshallXml(
				riksdagenDocumentListMarshaller, url, HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,
				null, null)).getValue();
		result.addAll(dokumentLista.getDokument());
		final BigInteger pages = dokumentLista.getTotalPages();
		for (int i = 1; i < pages.intValue() && i < maxNumberPages; i++) {
			dokumentLista = ((JAXBElement<DocumentContainerElement>) xmlAgent.unmarshallXml(
					riksdagenDocumentListMarshaller, fixBrokenUrl(dokumentLista.getNextPage()),
					HTTP_DOKUMENTLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL, null, null)).getValue();
			result.addAll(dokumentLista.getDokument());
			LOGGER.info(LOADING_DOCUMENTS, result.size(), dokumentLista.getHits());
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenDocumentApi#processDocumentList(java.lang.String, java.lang.String, com.hack23.cia.service.external.common.api.ProcessDataStrategy)
	 */
	@Override
	public void processDocumentList(final String changedSinceDate, final String changedToDate,
			final ProcessDataStrategy<DocumentElement> processStrategy) throws DataFailureException {
		try {
			loadAndProcessDocumentList(DOCUMENT_LIST_CHANGED_DATE.replace(CHANGED_SINCE_KEY, changedSinceDate)
					.replace(CHANGED_TO_KEY, changedToDate), processStrategy);
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_PROCCESSING_DOCUMENT_BETWEEN_CHANGED_SINCE_DATE_S_AND_CHANGE_TO_DATE, changedSinceDate,
					changedToDate);
			throw new DataFailureException(e);
		}
	}

}
