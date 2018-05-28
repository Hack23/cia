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

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
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
public final class VaultManagerImpl implements VaultManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant AES_GCM_NO_PADDING. */
	private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";

	/** The algorithm. */
	private static final String ALGORITHM = "AES";


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
		if (password != null && userId != null && value!=null) {
		
			try {
				final Key buildKey = buildKey(userId, password);			
				final SecureRandom secureRandom = new SecureRandom();
				final byte[] iv = new byte[12]; 
				secureRandom.nextBytes(iv);			
				final Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
				final GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv); 
				cipher.init(Cipher.ENCRYPT_MODE, buildKey, parameterSpec);
	
				final byte[] cipherText = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
				final ByteBuffer byteBuffer = ByteBuffer.allocate(4 + iv.length + cipherText.length);
				byteBuffer.putInt(iv.length);
				byteBuffer.put(iv);
				byteBuffer.put(cipherText);
				return Hex.toHexString(byteBuffer.array());
			} catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
				return null;
			}
		} else {
			return null;
		}
			
	}

	@Override
	public String decryptValue(final String password, final String userId, final String value) {
		if (password != null && userId != null && value!=null) {
			try {			
				final Key buildKey = buildKey(userId, password);
				final ByteBuffer byteBuffer = ByteBuffer.wrap(Hex.decode(value.getBytes(StandardCharsets.UTF_8)));
				final int ivLength = byteBuffer.getInt();
				final byte[] iv = new byte[ivLength];
				byteBuffer.get(iv);
				final byte[] cipherText = new byte[byteBuffer.remaining()];
				byteBuffer.get(cipherText);
				
				final Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
				cipher.init(Cipher.DECRYPT_MODE, buildKey, new GCMParameterSpec(128, iv));
				return new String(cipher.doFinal(cipherText),StandardCharsets.UTF_8);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
					| IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
				return null;
			}		
		} else {
			return null;
		}

	}

	private static Key buildKey(final String userid, final String password) {
		return new SecretKeySpec(Arrays.copyOf(
				new SHA3.Digest512().digest((userid + ".uuid" + password).getBytes(StandardCharsets.UTF_8)), 32),
				ALGORITHM);
	}
}
