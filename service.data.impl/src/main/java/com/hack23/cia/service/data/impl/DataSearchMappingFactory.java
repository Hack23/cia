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
package com.hack23.cia.service.data.impl;

import java.lang.annotation.ElementType;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.en.PorterStemFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.sv.SwedishLightStemFilterFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.cfg.SearchMapping;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;
import com.hack23.cia.model.external.riksdagen.dokumentlista.impl.DocumentElement;
import com.hack23.cia.model.external.riksdagen.dokumentstatus.impl.DocumentStatusContainer;

/**
 * A factory for creating DataSearchMapping objects.
 */
public final class DataSearchMappingFactory {

	/**
	 * Gets the search mapping.
	 *
	 * @return the search mapping
	 */
	@Factory
	public SearchMapping getSearchMapping() {
		final SearchMapping mapping = new SearchMapping();
		mapping.analyzerDef("ngram", StandardTokenizerFactory.class).filter(LowerCaseFilterFactory.class)
				.filter(NGramFilterFactory.class).param("minGramSize", "3").param("maxGramSize", "3")
				.analyzerDef("se", StandardTokenizerFactory.class).filter(LowerCaseFilterFactory.class)
				.filter(SwedishLightStemFilterFactory.class).analyzerDef("en", StandardTokenizerFactory.class)
				.filter(LowerCaseFilterFactory.class).filter(PorterStemFilterFactory.class)
				.entity(DocumentContentData.class).indexed().property("hjid", ElementType.FIELD).documentId().property("content", ElementType.METHOD).field().analyzer("se").store(Store.NO).analyze(Analyze.YES).property("id", ElementType.METHOD).field()
				.entity(DocumentElement.class).indexed().property("id", ElementType.FIELD).documentId().property("title", ElementType.METHOD).field().analyzer("se").store(Store.NO).analyze(Analyze.YES).property("subTitle", ElementType.METHOD).field().analyzer("se").store(Store.NO).analyze(Analyze.YES)
				.entity(DocumentStatusContainer.class).indexed().property("hjid", ElementType.FIELD).documentId().property("documentCategory", ElementType.METHOD).field().analyzer("se").store(Store.NO).analyze(Analyze.YES);

		return mapping;
	}
}
