/*
 * Copyright 2010 -2025 James Pether Sörling
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

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.en.PorterStemFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.sv.SwedishLightStemFilterFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

/**
 * The Class DataSearchAnalysisConfigurer.
 */
public class DataSearchAnalysisConfigurer implements LuceneAnalysisConfigurer {

	@Override
	public void configure(final LuceneAnalysisConfigurationContext context) {

		context.analyzer("ngram").custom().tokenizer(StandardTokenizerFactory.class)
		.tokenFilter(LowerCaseFilterFactory.class).tokenFilter(NGramFilterFactory.class)
		.param("minGramSize", "3").param("maxGramSize", "3");

		context.analyzer("se").custom()
		.tokenizer(StandardTokenizerFactory.class).tokenFilter(LowerCaseFilterFactory.class)
		.tokenFilter(SwedishLightStemFilterFactory.class);

		context.analyzer("en").custom()
		.tokenizer(StandardTokenizerFactory.class).tokenFilter(LowerCaseFilterFactory.class)
		.tokenFilter(PorterStemFilterFactory.class);
	}


}