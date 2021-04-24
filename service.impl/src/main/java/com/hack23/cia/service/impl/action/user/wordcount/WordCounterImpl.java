/*
 * Copyright 2010-2021 James Pether Sörling
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;

import smile.nlp.SimpleCorpus;
import smile.nlp.Text;
import smile.nlp.dictionary.EnglishPunctuations;
import smile.nlp.tokenizer.SimpleSentenceSplitter;
import smile.nlp.tokenizer.SimpleTokenizer;

/**
 * The Class WordCounterImpl.
 */
@Service
final class WordCounterImpl implements WordCounter {

	/**
	 * Instantiates a new word counter impl.
	 */
	public WordCounterImpl() {
		super();
	}

	public Map<String, Integer> calculateWordCount(final DocumentContentData documentContentData, final int maxResult) {

		final String html = documentContentData.getContent();
		
		final SimpleCorpus simpleCorpus = new SimpleCorpus(SimpleSentenceSplitter.getInstance(), new SimpleTokenizer(),
				new SwedishStopWords(), EnglishPunctuations.getInstance());

		simpleCorpus.add(new Text( Jsoup.clean(html, Whitelist.basic())));

		final Iterator<String> terms = simpleCorpus.getTerms();

		final Map<String, Integer> result = new HashMap<>();
		while (terms.hasNext()) {
			final String term = terms.next();
			result.put(term, simpleCorpus.getTermFrequency(term));
		}
		return result;
	}

}
