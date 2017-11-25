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
package com.hack23.cia.service.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class DataSummary.
 */
public final class DataSummary implements DataModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The committee proposal size. */
	public final long committeeProposalSize;

	/** The document content size. */
	public final long documentContentSize;

	/** The document element size. */
	public final long documentElementSize;

	/** The document status size. */
	public final long documentStatusSize;

	/** The person size. */
	public final long personSize;

	/** The total ballot votes. */
	public final long totalBallotVotes;

	/** The vote size. */
	public final long voteSize;

	/**
	 * Instantiates a new data summary.
	 *
	 * @param personSize
	 *            the person size
	 * @param voteSize
	 *            the vote size
	 * @param totalBallotVotes
	 *            the total ballot votes
	 * @param documentElementSize
	 *            the document element size
	 * @param documentContentSize
	 *            the document content size
	 * @param documentStatusSize
	 *            the document status size
	 * @param committeeProposalSize
	 *            the committee proposal size
	 */
	public DataSummary(final long personSize,final long voteSize, final long totalBallotVotes,final long documentElementSize,final long documentContentSize,final long documentStatusSize,final long committeeProposalSize) {
		this.personSize = personSize;
		this.voteSize = voteSize;
		this.totalBallotVotes = totalBallotVotes;
		this.documentElementSize = documentElementSize;
		this.documentContentSize = documentContentSize;
		this.documentStatusSize = documentStatusSize;
		this.committeeProposalSize = committeeProposalSize;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the committee proposal size.
	 *
	 * @return the committee proposal size
	 */
	public long getCommitteeProposalSize() {
		return committeeProposalSize;
	}

	/**
	 * Gets the document content size.
	 *
	 * @return the document content size
	 */
	public long getDocumentContentSize() {
		return documentContentSize;
	}

	/**
	 * Gets the document element size.
	 *
	 * @return the document element size
	 */
	public long getDocumentElementSize() {
		return documentElementSize;
	}

	/**
	 * Gets the document status size.
	 *
	 * @return the document status size
	 */
	public long getDocumentStatusSize() {
		return documentStatusSize;
	}

	/**
	 * Gets the person size.
	 *
	 * @return the person size
	 */
	public long getPersonSize() {
		return personSize;
	}

	/**
	 * Gets the total ballot votes.
	 *
	 * @return the total ballot votes
	 */
	public long getTotalBallotVotes() {
		return totalBallotVotes;
	}

	/**
	 * Gets the vote size.
	 *
	 * @return the vote size
	 */
	public long getVoteSize() {
		return voteSize;
	}

	@Override
	public final boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public final String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_FIELD_NAMES_STYLE);
	}

	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
}