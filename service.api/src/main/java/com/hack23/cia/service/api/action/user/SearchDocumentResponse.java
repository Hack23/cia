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
package com.hack23.cia.service.api.action.user;

import java.util.List;
import java.util.Map;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;
import com.hack23.cia.service.api.action.common.AbstractResponse;


/**
 * The Class SearchDocumentResponse.
 */
public final class SearchDocumentResponse extends AbstractResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The result element. */
	private List<DocumentElement> resultElement;

	/** The status map. */
	private Map<String,DocumentStatusContainer> statusMap;

	/** The data map. */
	private Map<String,DocumentContentData> dataMap;


	/**
	 * Instantiates a new search document response.
	 *
	 * @param result
	 *            the result
	 */
	public SearchDocumentResponse(final ServiceResult result) {
		super(result);
	}


	public List<DocumentElement> getResultElement() {
		return resultElement;
	}


	public void setResultElement(final List<DocumentElement> resultElement) {
		this.resultElement = resultElement;
	}


	public Map<String, DocumentStatusContainer> getStatusMap() {
		return statusMap;
	}


	public void setStatusMap(final Map<String, DocumentStatusContainer> statusMap) {
		this.statusMap = statusMap;
	}


	public Map<String, DocumentContentData> getDataMap() {
		return dataMap;
	}


	public void setDataMap(final Map<String, DocumentContentData> dataMap) {
		this.dataMap = dataMap;
	}

}
