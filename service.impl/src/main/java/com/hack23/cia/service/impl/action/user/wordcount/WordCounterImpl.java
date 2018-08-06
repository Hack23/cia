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
package com.hack23.cia.service.impl.action.user.wordcount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * The Class WordCounterImpl.
 */
@Service
final class WordCounterImpl implements WordCounter {

	/** The Constant TOKEN_DELIMITERS. */
	private static final String TOKEN_DELIMITERS = " \r\n\t.,;:'\"()?!'";

	/** The Constant HTML. */
	private static final String HTML = "html";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WordCounterImpl.class);


	/**
	 * Instantiates a new word counter impl.
	 */
	public WordCounterImpl() {
		super();
	}

	@Override
	public Map<String, Integer> calculateWordCount(final DocumentContentData documentContentData, final int maxResult) {

		final String html = documentContentData.getContent();

		final Attribute input = new Attribute(HTML, (ArrayList<String>) null);

		final ArrayList<Attribute> inputVec = new ArrayList<>();
		inputVec.add(input);

		final Instances htmlInst = new Instances(HTML, inputVec, 1);

		htmlInst.add(new DenseInstance(1));
		htmlInst.instance(0).setValue(0, html);


		final StopwordsHandler stopwordsHandler = word -> word.length() <5;

		final NGramTokenizer tokenizer = new NGramTokenizer();
		tokenizer.setNGramMinSize(1);
		tokenizer.setNGramMaxSize(1);
		tokenizer.setDelimiters(TOKEN_DELIMITERS);

		final StringToWordVector filter = new StringToWordVector();
		filter.setTokenizer(tokenizer);
		filter.setStopwordsHandler(stopwordsHandler);
		filter.setLowerCaseTokens(true);
		filter.setOutputWordCounts(true);
		filter.setWordsToKeep(maxResult);

		final Map<String,Integer> result = new HashMap<>();

		try {
			filter.setInputFormat(htmlInst);
			final Instances dataFiltered = Filter.useFilter(htmlInst, filter);

			final Instance last = dataFiltered.lastInstance();

			final int numAttributes = last.numAttributes();

			for (int i = 0; i < numAttributes; i++) {
				result.put(last.attribute(i).name(), Integer.valueOf(last.toString(i)));
			}
		} catch (final Exception e) {
			LOGGER.warn("Problem calculating wordcount for : {} , exception:{}",documentContentData.getId() ,e);
		}


		return result;
	}


}
