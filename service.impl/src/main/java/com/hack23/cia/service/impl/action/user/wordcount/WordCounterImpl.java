/*
 * Copyright 2010-2019 James Pether Sörling
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
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.external.riksdagen.documentcontent.impl.DocumentContentData;

import smile.feature.Bag;
import smile.nlp.normalizer.SimpleNormalizer;
import smile.nlp.tokenizer.SimpleTokenizer;

/**
 * The Class WordCounterImpl.
 */
@Service
final class WordCounterImpl implements WordCounter {

	/** The Constant TOKEN_DELIMITERS. */
	private static final String TOKEN_DELIMITERS = " \r\n\t.,;:'\"()?!'";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WordCounterImpl.class);


	/**
	 * Instantiates a new word counter impl.
	 */
	public WordCounterImpl() {
		super();
	}


	public Map<String, Integer> calculateWordCount(final DocumentContentData documentContentData, final int maxResult) {

		final String html = documentContentData.getContent();

		String normalized = SimpleNormalizer.getInstance().normalize(html);
				
	    SimpleTokenizer instance = new SimpleTokenizer(true);
        String[] tokens = instance.split(normalized);
        
        Bag<String> bag = new Bag<>(tokens);
		final Map<String,Integer> result = new HashMap<>();

		try {
			
			double[][] x = new double[tokens.length][];
			
			 for (int i = 0; i < tokens.length; i++) {
		            double[] feature = bag.feature(tokens);
		            result.put(tokens[i],(int) feature[i]);
		        }
			
		} catch (final Exception e) {
			LOGGER.warn("Problem calculating wordcount for : {} , exception:{}",documentContentData.getId() ,e);
		}


		return result;
	}

}
