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

/**
 * The Enum DocumentTypeNames.
 */
public enum DocumentTypeNames {

	/** The account and report. */
	ACCOUNT_AND_REPORT("frsrdg","Framställning och redogörelse"),

	/** The agenda. */
	AGENDA("f-lista","Föredragningslista"),

	/** The answer to question. */
	ANSWER_TO_QUESTION("frs","Svar på skriftlig fråga"),

	/** The attachement. */
	ATTACHEMENT("bilaga","Bilaga"),

	/** The bill. */
	BILL("mot","Motion"),

	/** The committee document. */
	COMMITTEE_DOCUMENT("utskottsdokument","Utskottsdokument"),

	/** The committee report. */
	COMMITTEE_REPORT("bet","Betänkande"),

	/** The committee story. */
	COMMITTEE_STORY("komm","Kommittéberättelser"),

	/** The communication. */
	COMMUNICATION("skr","Skrivelse"),

	/** The department serie. */
	DEPARTMENT_SERIE("ds","Departementsserien (Ds)"),

	/** The directive. */
	DIRECTIVE("dir","Direktiv"),

	/** The eu committee. */
	EU_COMMITTEE("eundok","EU-nämndsdokument"),

	/** The eu committee attachement. */
	EU_COMMITTEE_ATTACHEMENT("eunbil","Bilaga till EU-nämndsdokument"),

	/** The eu committee notes. */
	EU_COMMITTEE_NOTES("eunprot","EU-nämndens stenografiska uppteckningar"),

	/** The eu committee summons and agenda. */
	EU_COMMITTEE_SUMMONS_AND_AGENDA("kf-lista","EU-nämndens kallelser och föredragningslistor"),

	/** The fact memorandum. */
	FACT_MEMORANDUM("fpm","Faktapromemoria"),

	/** The inspection report. */
	INSPECTION_REPORT("rir","Granskningsrapport"),

	/** The interpellation. */
	INTERPELLATION("ip","Interpellation"),

	/** The minister memorandum. */
	MINISTER_MEMORANDUM("minråd","Ministerrådspromemoria"),

	/** The parliament communication. */
	PARLIAMENT_COMMUNICATION("rskr","Riksdagsskrivelse"),

	/** The parliament report. */
	PARLIAMENT_REPORT("rfr","Rapport från riksdagen"),

	/** The proposition. */
	PROPOSITION("prop","Propositioner"),

	/** The protocol. */
	PROTOCOL("prot","Protokoll"),

	/** The public investigation. */
	PUBLIC_INVESTIGATION("sou","Statens offentliga utredningar (SOU)"),

	/** The question. */
	QUESTION("fr","Skriftlig fråga"),

	/** The speaker list. */
	SPEAKER_LIST("t-lista","Talarlista"),

	/** The swedish code of statutes. */
	SWEDISH_CODE_OF_STATUTES("sfs","Svensk författningssamling"),

	/** The statement. */
	STATEMENT("yttr","Yttrande");

	/** The description. */
	private final String description;

	/** The short code. */
	private final String shortCode;

	/**
	 * Instantiates a new document type names.
	 *
	 * @param shortCode
	 *            the short code
	 * @param description
	 *            the description
	 */
	DocumentTypeNames(final String shortCode,final String description) {
		this.shortCode = shortCode;
		this.description = description;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the short code.
	 *
	 * @return the short code
	 */
	public String getShortCode() {
		return shortCode;
	}

}
