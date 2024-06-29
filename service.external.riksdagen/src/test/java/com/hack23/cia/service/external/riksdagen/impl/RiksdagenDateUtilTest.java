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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.hack23.cia.model.external.riksdagen.votering.impl.BallotContainer;
import com.hack23.cia.model.external.riksdagen.votering.impl.BallotDocumentData;
import com.hack23.cia.model.external.riksdagen.votering.impl.BallotDocumentElement;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataDto;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class RiksdagenDateUtilTest.
 */
public final class RiksdagenDateUtilTest extends AbstractUnitTest {


	/**
	 * Try to find valid vote date list exist same date success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateListExistSameDateSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String madePublicBallotDate = "2018-01-01";
		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withCreatedDate(madePublicBallotDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();

		final String voteDate = "2002-01-01";
		voteDataList.add(new VoteDataDto().withVoteDate(voteDate));

		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(voteDate,dateFormat.format(tryToFindValidVoteDate));
	}

	/**
	 * Try to find valid vote date list exist different date success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateListExistDifferentDateSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String madePublicBallotDate = "2018-01-01";
		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withCreatedDate(madePublicBallotDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();

		final String voteDate = "2002-01-01";
		voteDataList.add(new VoteDataDto().withVoteDate(voteDate));
		voteDataList.add(new VoteDataDto().withVoteDate("2001-01-04"));

		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(madePublicBallotDate,dateFormat.format(tryToFindValidVoteDate));
	}


	/**
	 * Try to find valid vote date ballot created day exist success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateBallotCreatedDayExistSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String ballotDate = "2018-01-01";
		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withCreatedDate(ballotDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();
		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(ballotDate,dateFormat.format(tryToFindValidVoteDate));
	}

	/**
	 * Try to find valid vote date ballot created day invalid fallback to system
	 * date exist success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateBallotCreatedDayInvalidFallbackToSystemDateExistSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String ballotDate = "2018-01";
		final String systemDate = "2018-01-01";

		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withCreatedDate(ballotDate).withSystemDate(systemDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();
		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(systemDate,dateFormat.format(tryToFindValidVoteDate));
	}


	/**
	 * Try to find valid vote date ballot public date exist success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateBallotPublicDateExistSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String ballotDate = "2017-01-01";
		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withMadePublicDate(ballotDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();
		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(ballotDate,dateFormat.format(tryToFindValidVoteDate));
	}

	/**
	 * Try to find valid vote date system date exist success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateSystemDateExistSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String ballotDate = "2016-01-01";
		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withSystemDate(ballotDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();
		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(ballotDate,dateFormat.format(tryToFindValidVoteDate));
	}

	/**
	 * Try to find valid vote date system date invalid fallback to public date exist
	 * success.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void tryToFindValidVoteDateSystemDateInvalidFallbackToPublicDateExistSuccess() throws Exception {
		final RiksdagenDateUtil riksdagenDateUtil = new RiksdagenDateUtil();

		final String systemDate = "2016-01";
		final String madePublicDate = "2016-01-01";

		final BallotContainer ballotContainer = new BallotContainer().withBallotDocumentData(new BallotDocumentData()).withBallotDocumentElement(new BallotDocumentElement().withSystemDate(systemDate).withMadePublicDate(madePublicDate));
		final List<VoteDataDto> voteDataList = new ArrayList<>();
		final Date tryToFindValidVoteDate = RiksdagenDateUtil.tryToFindValidVoteDate(ballotContainer, voteDataList);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(RiksdagenDateUtil.YYYY_MM_DD,Locale.ENGLISH);
		assertEquals(madePublicDate,dateFormat.format(tryToFindValidVoteDate));
	}


}
