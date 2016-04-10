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

import com.hack23.cia.service.api.action.common.AbstractResponse;


/**
 * The Class SearchDocumentResponse.
 */
public final class SetGoogleAuthenticatorCredentialResponse extends AbstractResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


    private String googleAuthKey;
    private Integer googleAuthVerificationCode;
    private List<Integer> googleAuthScratchCodes;

	/**
	 * Instantiates a new search document response.
	 *
	 * @param result
	 *            the result
	 */
	public SetGoogleAuthenticatorCredentialResponse(final ServiceResult result) {
		super(result);
	}

	public String getGoogleAuthKey() {
		return googleAuthKey;
	}

	public void setGoogleAuthKey(String googleAuthKey) {
		this.googleAuthKey = googleAuthKey;
	}

	public Integer getGoogleAuthVerificationCode() {
		return googleAuthVerificationCode;
	}

	public void setGoogleAuthVerificationCode(Integer googleAuthVerificationCode) {
		this.googleAuthVerificationCode = googleAuthVerificationCode;
	}

	public List<Integer> getGoogleAuthScratchCodes() {
		return googleAuthScratchCodes;
	}

	public void setGoogleAuthScratchCodes(List<Integer> googleAuthScratchCodes) {
		this.googleAuthScratchCodes = googleAuthScratchCodes;
	}

}
