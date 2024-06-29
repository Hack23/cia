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
package com.hack23.cia.service.external.riksdagen.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.external.riksdagen.votering.impl.BallotContainer;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataDto;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.BallotDocumentElement;
import com.hack23.cia.model.external.riksdagen.voteringlista.impl.VoteListContainerElement;
import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.service.external.riksdagen.api.DataFailureException;
import com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi;

/**
 * The Class RiksdagenBallotApiImpl.
 */
@Component
final class RiksdagenBallotApiImpl implements RiksdagenBallotApi {

	/** The Constant BALLOT. */
	private static final String BALLOT = "https://data.riksdagen.se/votering/${ID_KEY}/xml";

	/** The Constant BALLOT_LIST. */
	private static final String BALLOT_LIST="https://data.riksdagen.se/voteringlista/?rm=&bet=&punkt=&iid=&parti=&valkrets=&rost=&sz=10000&utformat=xml&gruppering=votering_id";

	/** The Constant HTTP_VOTERING_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL. */
	private static final String HTTP_VOTERING_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://votering.riksdagen.external.model.cia.hack23.com/impl";

	/**
	 * The Constant HTTP_VOTERINGLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL.
	 */
	private static final String HTTP_VOTERINGLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL = "http://voteringlista.riksdagen.external.model.cia.hack23.com/impl";


	/** The Constant ID_KEY. */
	private static final String ID_KEY = "${ID_KEY}";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RiksdagenBallotApiImpl.class);

	/** The Constant PROBLEM_GETTING_BALLOT_ID_S_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_BALLOT_ID_S_FROM_DATA_RIKSDAGEN_SE = "Problem getting ballot id:{} from data.riksdagen.se";

	/** The Constant PROBLEM_GETTING_BALLOT_LIST_FROM_DATA_RIKSDAGEN_SE. */
	private static final String PROBLEM_GETTING_BALLOT_LIST_FROM_DATA_RIKSDAGEN_SE = "Problem getting ballot list from data.riksdagen.se";

	/** The riksdagen ballot list marshaller. */
	@Autowired
	@Qualifier("riksdagenBallotListMarshaller")
	private Unmarshaller riksdagenBallotListMarshaller;

	/** The riksdagen ballot marshaller. */
	@Autowired
	@Qualifier("riksdagenBallotMarshaller")
	private Unmarshaller riksdagenBallotMarshaller;

	/** The xml agent. */
	private final XmlAgent xmlAgent;


	/**
	 * Instantiates a new riksdagen ballot api impl.
	 *
	 * @param xmlAgent
	 *            the xml agent
	 */
	@Autowired
	public RiksdagenBallotApiImpl(final XmlAgent xmlAgent) {
		super();
		this.xmlAgent = xmlAgent;
	}




	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi#getBallot(java.lang.String)
	 */
	@Override
	public List<VoteData> getBallot(final String id) throws DataFailureException {
		final String url = BALLOT.replace(ID_KEY, id);

		try {
			final BallotContainer ballotContainer = ((JAXBElement<BallotContainer>) xmlAgent.unmarshallXml(
					riksdagenBallotMarshaller, url,
					HTTP_VOTERING_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
					.getValue();

			final List<VoteData> result = new ArrayList<>();
			if (ballotContainer != null && ballotContainer.getBallotDocumentData() != null) {

				final List<VoteDataDto> voteDataList = ballotContainer.getBallotDocumentData().getVoteDataList();
				final Date ballotDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);

				for (final VoteDataDto voteDataDto: voteDataList) {
					final VoteData voteData= new VoteData().withEmbeddedId(new VoteDataEmbeddedId().withBallotId(voteDataDto.getBallotId()).withIntressentId(voteDataDto.getIntressentId()).withIssue(voteDataDto.getIssue()).withConcern(voteDataDto.getConcern()));

					voteData.setBankNumber(voteDataDto.getBankNumber());
					voteData.setLabel(voteDataDto.getLabel());
					voteData.setLastName(voteDataDto.getLastName());
					voteData.setBornYear(voteDataDto.getBornYear());
					voteData.setFirstName(voteDataDto.getFirstName());
					voteData.setPlace(voteDataDto.getPlace());
					voteData.setGender(voteDataDto.getGender());
					voteData.setFullName(voteDataDto.getFullName());
					voteData.setParty(voteDataDto.getParty().toUpperCase(Locale.ENGLISH));
					voteData.setRm(voteDataDto.getRm());
					voteData.setVote(voteDataDto.getVote());
					voteData.setBallotType(voteDataDto.getBallotType());
					voteData.setElectoralRegionNumber(voteDataDto.getElectoralRegionNumber());
					voteData.setElectoralRegion(voteDataDto.getElectoralRegion());
					voteData.setVoteDate(ballotDate);

					result.add(voteData);
				}
			}

			return result;

		} catch (final XmlAgentException | ParseException e) {
			LOGGER.warn(PROBLEM_GETTING_BALLOT_ID_S_FROM_DATA_RIKSDAGEN_SE,id);
			throw new DataFailureException(e);
		}



	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.external.riksdagen.api.RiksdagenBallotApi#getBallotList()
	 */
	@Override
	public List<BallotDocumentElement> getBallotList() throws DataFailureException {

		try {
			final String url = BALLOT_LIST;
			return ((JAXBElement<VoteListContainerElement>) xmlAgent.unmarshallXml(riksdagenBallotListMarshaller, url,
							HTTP_VOTERINGLISTA_RIKSDAGEN_EXTERNAL_MODEL_CIA_HACK23_COM_IMPL,null,null))
							.getValue().getVotering();
		} catch (final XmlAgentException e) {
			LOGGER.warn(PROBLEM_GETTING_BALLOT_LIST_FROM_DATA_RIKSDAGEN_SE);
			throw new DataFailureException(e);
		}
	}
}
