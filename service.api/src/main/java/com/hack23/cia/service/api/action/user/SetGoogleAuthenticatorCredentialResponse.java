/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.util.ArrayList;
import java.util.List;

import com.hack23.cia.service.api.action.common.AbstractResponse;

/**
 * The Class SetGoogleAuthenticatorCredentialResponse.
 */
public final class SetGoogleAuthenticatorCredentialResponse extends AbstractResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The google auth key. */
	private String googleAuthKey;

	/** The google auth verification code. */
	private Integer googleAuthVerificationCode;

	/** The google auth scratch codes. */
	private List<Integer> googleAuthScratchCodes = new ArrayList<>();

	/** The otp auth totp url. */
	private String otpAuthTotpURL;

	/**
	 * Instantiates a new sets the google authenticator credential response.
	 *
	 * @param result
	 *            the result
	 */
	public SetGoogleAuthenticatorCredentialResponse(final ServiceResult result) {
		super(result);
	}

	/**
	 * Gets the google auth key.
	 *
	 * @return the google auth key
	 */
	public String getGoogleAuthKey() {
		return googleAuthKey;
	}

	/**
	 * Sets the google auth key.
	 *
	 * @param googleAuthKey
	 *            the new google auth key
	 */
	public void setGoogleAuthKey(final String googleAuthKey) {
		this.googleAuthKey = googleAuthKey;
	}

	/**
	 * Gets the google auth verification code.
	 *
	 * @return the google auth verification code
	 */
	public Integer getGoogleAuthVerificationCode() {
		return googleAuthVerificationCode;
	}

	/**
	 * Sets the google auth verification code.
	 *
	 * @param googleAuthVerificationCode
	 *            the new google auth verification code
	 */
	public void setGoogleAuthVerificationCode(final Integer googleAuthVerificationCode) {
		this.googleAuthVerificationCode = googleAuthVerificationCode;
	}

	/**
	 * Gets the google auth scratch codes.
	 *
	 * @return the google auth scratch codes
	 */
	public List<Integer> getGoogleAuthScratchCodes() {
		return googleAuthScratchCodes.stream().toList();
	}

	/**
	 * Sets the google auth scratch codes.
	 *
	 * @param googleAuthScratchCodes
	 *            the new google auth scratch codes
	 */
	public void setGoogleAuthScratchCodes(final List<Integer> googleAuthScratchCodes) {
		if (googleAuthScratchCodes != null) {
			this.googleAuthScratchCodes = googleAuthScratchCodes.stream().toList();
		}
	}

	/**
	 * Gets the otp auth totp url.
	 *
	 * @return the otp auth totp url
	 */
	public String getOtpAuthTotpURL() {
		return otpAuthTotpURL;
	}

	/**
	 * Sets the otp auth totp url.
	 *
	 * @param otpAuthTotpURL
	 *            the new otp auth totp url
	 */
	public void setOtpAuthTotpURL(final String otpAuthTotpURL) {
		this.otpAuthTotpURL = otpAuthTotpURL;
	}

}
