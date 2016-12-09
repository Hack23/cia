/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.properties.encryption;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * The Class EncryptProperty.
 */
public class EncryptProperty {

	private static final String PBEWITHSHA256AND128BITAES_CBC_BC = "PBEWITHSHA256AND128BITAES-CBC-BC";
	private static final String BC_PROVIDER_NAME = "BC";
	private static final String ENC_CONTENT_SUFFIX = ")";
	private static final String ENC_CONTENT_PREFIX = "ENC(";
	private static final String ENCRYPTED_PROPERTY_VALUE = "Encrypted property value:";
	private static final String ENCRYPTED_VALUE = "Encrypted value:";

	
	
	/**
	 * Instantiates a new encrypt property.
	 */
	public EncryptProperty() {
		super();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String encryptValue = new EncryptProperty().encryptValue(args[1], args[2]);
		System.out.println(ENCRYPTED_VALUE +encryptValue);
		System.out.println(ENCRYPTED_PROPERTY_VALUE + ENC_CONTENT_PREFIX +encryptValue +ENC_CONTENT_SUFFIX);		
	}
	
	/**
	 * Encrypt value.
	 *
	 * @param symmetricKey
	 *            the symmetric key
	 * @param value
	 *            the value
	 * @return the string
	 */
	public String encryptValue(final String symmetricKey,final String value) {
		return getEncryptor(symmetricKey).encrypt(value);
		
	}

	/**
	 * Gets the encryptor.
	 *
	 * @param symmetricKey
	 *            the symmetric key
	 * @return the encryptor
	 */
	private StandardPBEStringEncryptor getEncryptor(final String symmetricKey) {
		Security.addProvider(new BouncyCastleProvider());
		final StandardPBEStringEncryptor mySecondEncryptor = new StandardPBEStringEncryptor();
		mySecondEncryptor.setProviderName(BC_PROVIDER_NAME);
		mySecondEncryptor.setAlgorithm(PBEWITHSHA256AND128BITAES_CBC_BC);
		mySecondEncryptor.setPassword(symmetricKey);
		return mySecondEncryptor;
	}


	/**
	 * Decrypt value.
	 *
	 * @param symmetricKey
	 *            the symmetric key
	 * @param value
	 *            the value
	 * @return the string
	 */
	public String decryptValue(final String symmetricKey,final String value) {
		return getEncryptor(symmetricKey).decrypt(value);
		
	}

}
