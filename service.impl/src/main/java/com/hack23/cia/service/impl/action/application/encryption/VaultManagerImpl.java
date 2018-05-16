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
package com.hack23.cia.service.impl.action.application.encryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue;
import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue_;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.data.api.EncryptedValueDAO;

/**
 * The Class VaultManagerImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VaultManagerImpl implements VaultManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String ALGORITHM = "DESede";

	private static String TRIPLE_DES_TRANSFORMATION = "DESede/ECB/PKCS7Padding";

	private static String BOUNCY_CASTLE_PROVIDER = "BC";

	/** The encrypted value DAO. */
	@Autowired
	private EncryptedValueDAO encryptedValueDAO;

	/**
	 * Instantiates a new vault manager impl.
	 */
	public VaultManagerImpl() {
		super();
	}

	static {
		Security.setProperty("crypto.policy", "unlimited");
		Security.addProvider(new BouncyCastleProvider());
	}

	@Override
	public String getEncryptedValue(final String password, final UserAccount userExist) {
		if (userExist != null) {
			final EncryptedValue encryptedValue = encryptedValueDAO.findFirstByProperty(EncryptedValue_.userId,
					userExist.getUserId());

			if (encryptedValue != null) {
				return decryptValue(password, userExist.getUserId(), encryptedValue.getStorage());
			}
		}
		return null;
	}

	@Override
	public String encryptValue(final String password, final String userId, final String value) {
		try {
			final Key buildKey = buildKey(userId, password);
			final Cipher encrypter = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION, BOUNCY_CASTLE_PROVIDER);
			encrypter.init(Cipher.ENCRYPT_MODE, buildKey);
			return Hex.toHexString(encrypter.doFinal(value.getBytes(StandardCharsets.UTF_8)));
		} catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException
				| NoSuchPaddingException | InvalidKeyException e) {
			return null;
		}
	}

	@Override
	public String decryptValue(final String password, final String userId, final String value) {
		final Key buildKey = buildKey(userId, password);
		Cipher decrypter;
		try {
			decrypter = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION, BOUNCY_CASTLE_PROVIDER);
			decrypter.init(Cipher.DECRYPT_MODE, buildKey);
			return new String((decrypter.doFinal(Hex.decode(value.getBytes(StandardCharsets.UTF_8)))),StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			return null;
		}
	}

	private static Key buildKey(String userid, String password) {
		return new SecretKeySpec(Arrays.copyOf(
				new SHA3.Digest512().digest((userid + ".uuid" + password).getBytes(StandardCharsets.UTF_8)), 24),
				ALGORITHM);
	}
}
