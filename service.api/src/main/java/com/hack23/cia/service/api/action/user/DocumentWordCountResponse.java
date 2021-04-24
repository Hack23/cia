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
package com.hack23.cia.service.api.action.user;

import java.util.Map;

import com.hack23.cia.service.api.action.common.AbstractResponse;

/**
 * The Class DocumentWordCountResponse.
 */
public final class DocumentWordCountResponse extends AbstractResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The word count map. */
	private Map<String, Integer> wordCountMap;

	/**
	 * Instantiates a new document word count response.
	 *
	 * @param result
	 *            the result
	 */
	public DocumentWordCountResponse(final ServiceResult result) {
		super(result);
	}

	/**
	 * Gets the word count map.
	 *
	 * @return the word count map
	 */
	public Map<String, Integer> getWordCountMap() {
		return wordCountMap;
	}

	/**
	 * Sets the word count map.
	 *
	 * @param wordCountMap
	 *            the new word count map
	 */
	public void setWordCountMap(final Map<String, Integer> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}

}
