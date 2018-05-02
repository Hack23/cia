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
package com.hack23.cia.model.internal.application.secure.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;

import com.hack23.cia.model.common.api.ModelObject;

/**
 * The Class EncryptedValue.
 */
@Entity(name = "EncryptedValue")
public class EncryptedValue implements ModelObject {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
    @Id
    private Long id;
 
    /** The storage. */
    @ColumnTransformer(
        read =  "pgp_sym_decrypt(" +
                "    storage, " +
                "    current_setting('cia.encrypt.key')" +
                ")",
        write = "pgp_sym_encrypt( " +
                "    ?, " +
                "    current_setting('cia.encrypt.key')" +
                ") "
    )
    @Column(columnDefinition = "bytea")
    private String storage;
    
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the storage.
	 *
	 * @return the storage
	 */
	public String getStorage() {
		return storage;
	}

	/**
	 * Sets the storage.
	 *
	 * @param storage
	 *            the new storage
	 */
	public void setStorage(String storage) {
		this.storage = storage;
	}    
}