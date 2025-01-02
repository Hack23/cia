/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.service.external.riksdagen.api;

import java.util.List;

import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;

/**
 * The Interface RiksdagenBallotApi.
 */
public interface RiksdagenBallotApi {

	/**
	 * Gets the ballot.
	 *
	 * @param id
	 *            the id
	 * @return the ballot
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<VoteData> getBallot(String id) throws DataFailureException;

	/**
	 * Gets the ballot list.
	 *
	 * @return the ballot list
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	List<BallotDocumentElement> getBallotList() throws DataFailureException;

}
