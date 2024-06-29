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
package com.hack23.cia.service.data.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty;
import com.hack23.cia.model.external.val.partier.impl.SwedenPoliticalParty_;
import com.hack23.cia.service.data.api.SwedenPoliticalPartyDAO;

/**
 * The Class SwedenPoliticalPartyDAOImpl.
 */
@Repository("SwedenPoliticalPartyDAO")
final class SwedenPoliticalPartyDAOImpl extends
AbstractGenericDAOImpl<SwedenPoliticalParty, String> implements
SwedenPoliticalPartyDAO {

	/**
	 * Instantiates a new sweden political party dao impl.
	 */
	public SwedenPoliticalPartyDAOImpl() {
		super(SwedenPoliticalParty.class);
	}

	@Override
	public List<String> getIdList() {
		return getStringIdList(SwedenPoliticalParty_.partyId);
	}

}
