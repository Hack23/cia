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
package com.hack23.cia.service.impl.action.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationOperationType;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.CreateApplicationEventRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationEventResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.api.action.user.DocumentWordCountRequest;
import com.hack23.cia.service.api.action.user.DocumentWordCountResponse;
import com.hack23.cia.service.data.api.DocumentContentDataDAO;
import com.hack23.cia.service.impl.action.common.AbstractBusinessServiceImpl;
import com.hack23.cia.service.impl.action.common.BusinessService;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * The Class DocumentWordCountService.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout=600)
public final class DocumentWordCountService extends
		AbstractBusinessServiceImpl<DocumentWordCountRequest, DocumentWordCountResponse>
		implements BusinessService<DocumentWordCountRequest, DocumentWordCountResponse> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DocumentWordCountService.class);

	/** The create application event service. */
	@Autowired
	private BusinessService<CreateApplicationEventRequest, CreateApplicationEventResponse> createApplicationEventService;

	@Autowired
	private DocumentContentDataDAO documentContentDataDAO;

	/**
	 * Instantiates a new search document service.
	 */
	public DocumentWordCountService() {
		super(DocumentWordCountRequest.class);
	}


	@Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_ANONYMOUS" })
	@Override
	public DocumentWordCountResponse processService(
			final DocumentWordCountRequest serviceRequest) {

		LOGGER.info("{}:{}",serviceRequest.getClass().getSimpleName(),serviceRequest.getDocumentId());

		final CreateApplicationEventRequest eventRequest = new CreateApplicationEventRequest();
		eventRequest.setEventGroup(ApplicationEventGroup.USER);
		eventRequest.setApplicationOperation(ApplicationOperationType.READ);
		eventRequest.setActionName(DocumentWordCountRequest.class.getSimpleName());
		eventRequest.setSessionId(serviceRequest.getSessionId());

		final UserAccount userAccount = getUserAccountFromSecurityContext();


		if (userAccount != null) {

			eventRequest.setUserId(userAccount.getUserId());
		}

		final DocumentWordCountResponse response = new DocumentWordCountResponse(ServiceResult.SUCCESS);

		DocumentContentData documentContentData = documentContentDataDAO.findFirstByProperty(DocumentContentData_.id, serviceRequest.getDocumentId());

		if (documentContentData == null) {
			response.setWordCountMap(new HashMap<>());
		} else {
				response.setWordCountMap(calculateWordCount(documentContentData,serviceRequest.getMaxResults()));
		}

		eventRequest.setApplicationMessage(response.getResult().toString());
		createApplicationEventService.processService(eventRequest);

		return response;
	}


	/**
	 * Calculate word count.
	 *
	 * @param documentContentData
	 *            the document content data
	 * @param j
	 * @return the map
	 */
	private static Map<String, Integer> calculateWordCount(DocumentContentData documentContentData, int maxResult) {

		String html = documentContentData.getContent();

		final Attribute input = new Attribute("html", (FastVector<String>) null);

		final FastVector inputVec = new FastVector();
		inputVec.addElement(input);

		final Instances htmlInst = new Instances("html", inputVec, 1);

		htmlInst.add(new DenseInstance(1));
		htmlInst.instance(0).setValue(0, html);


		final StringToWordVector filter = new StringToWordVector();
		final StopwordsHandler StopwordsHandler = new StopwordsHandler() {

			@Override
			public boolean isStopword(final String word) {

				return word.length() <5;
			}
		};

		final NGramTokenizer tokenizer = new NGramTokenizer();
		tokenizer.setNGramMinSize(1);
		tokenizer.setNGramMaxSize(1);
		tokenizer.setDelimiters(" \r\n\t.,;:'\"()?!'");

		filter.setTokenizer(tokenizer);

		filter.setStopwordsHandler(StopwordsHandler);
		filter.setLowerCaseTokens(true);
		filter.setOutputWordCounts(true);
		filter.setWordsToKeep(maxResult);

		Map<String,Integer> result = new HashMap<>();

		try {
			filter.setInputFormat(htmlInst);
			final Instances dataFiltered = Filter.useFilter(htmlInst, filter);

			final Instance last = dataFiltered.lastInstance();

			final int numAttributes = last.numAttributes();

			for (int i = 0; i < numAttributes; i++) {
				result.put(last.attribute(i).name(), Integer.valueOf(last.toString(i)));
			}
		} catch (Exception e) {
			LOGGER.warn("Problem calculating wordcount for : {} , exception:{}",documentContentData.getId() ,e);
		}


		return result;
	}



}
