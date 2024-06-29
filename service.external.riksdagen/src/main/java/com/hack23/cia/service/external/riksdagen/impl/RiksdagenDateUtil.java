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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.hack23.cia.model.external.riksdagen.votering.impl.BallotContainer;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataDto;

/**
 * The Class RiksdagenDateUtil.
 */
final class RiksdagenDateUtil {

	/** The Constant YYYY_MM_DD. */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	/** The Constant CONTAINS_ONE. */
	private static final int CONTAINS_ONE = 1;


	/**
	 * Instantiates a new riksdagen date util.
	 */
	public RiksdagenDateUtil() {
		super();
	}

	/**
	 * Best guess vote date.
	 *
	 * @param ballotContainer
	 *            the ballot container
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date bestGuessVoteDate(final BallotContainer ballotContainer) throws ParseException {
		final com.hack23.cia.model.external.riksdagen.votering.impl.BallotDocumentElement ballotDocumentElement = ballotContainer.getBallotDocumentElement();
		Date result;

		final String createdDate=ballotContainer.getBallotDocumentElement().getCreatedDate();

		if(createdDate!= null && createdDate.length()>= YYYY_MM_DD.length()) {
			result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(createdDate);
		} else {
			final String systemDate = ballotDocumentElement.getSystemDate();

			if(systemDate!= null && systemDate.length()>= YYYY_MM_DD.length()) {
				result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(systemDate);
			} else {
				result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(ballotDocumentElement.getMadePublicDate());
			}
		}
		return result;
	}

	/**
	 * Check same date.
	 *
	 * @param voteList
	 *            the vote list
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private static Date checkSameDate(final List<VoteDataDto> voteList) throws ParseException {
		final Set<String> set = new HashSet<>();
		Date result=null;

		for (final VoteDataDto voteData : voteList) {
			final String voteDate = voteData.getVoteDate();
			if (voteDate !=null && voteDate.length() >= YYYY_MM_DD.length()) {
				set.add(voteData.getVoteDate());
			}
		}

		if (set.size() ==CONTAINS_ONE) {
			final String dateString = set.iterator().next();
			result=new SimpleDateFormat(YYYY_MM_DD,Locale.ENGLISH).parse(dateString);
		}
		return result;
	}

	/**
	 * Try to find valid vote date.
	 *
	 * @param ballotContainer
	 *            the ballot container
	 * @param voteDataList
	 *            the vote data list
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date tryToFindValidVoteDate(final BallotContainer ballotContainer, final List<VoteDataDto> voteDataList)
					throws ParseException {
		Date ballotDate;
		final Date sameDate = checkSameDate(voteDataList);

		if (sameDate != null) {
			ballotDate = sameDate;
		} else {
			ballotDate = bestGuessVoteDate(ballotContainer);
		}
		return ballotDate;
	}

}
