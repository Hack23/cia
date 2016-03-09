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
package com.hack23.cia.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * A Java class to test tokenization and indexing of text with WEKA.
 * WEKA is available at: http://www.cs.waikato.ac.nz/ml/weka/
 * Copyright (C) 2013 Jose Maria Gomez Hidalgo - http://www.esp.uem.es/jmgomez
 *
 * This program is free software: you can redistribute it and/or modify
 * it for any purpose.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * The Class IndexTest.
 */
public final class IndexTest {

	/** The input instances. */
	Instances inputInstances;

	/** The output instances. */
	Instances outputInstances;

	/**
	 * Load arff.
	 *
	 * @param fileName
	 *            the file name
	 */
	public void loadARFF(final String fileName) {

		try {
			final BufferedReader reader = new BufferedReader(new FileReader(fileName));
			final ArffReader arff = new ArffReader(reader);
			inputInstances = arff.getData();
			System.out.println("===== Loaded dataset: " + fileName + " =====");
			reader.close();
		}
		catch (final IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}

	/**
	 * Index.
	 */
	public void index() {
		// outputInstances = inputInstances;
		try {

			// Set the tokenizer
			final NGramTokenizer tokenizer = new NGramTokenizer();
			tokenizer.setNGramMinSize(1);
			tokenizer.setNGramMaxSize(1);
			tokenizer.setDelimiters("\\W");

			// Set the filter
			final StringToWordVector filter = new StringToWordVector();
			filter.setTokenizer(tokenizer);
			filter.setInputFormat(inputInstances);
			filter.setWordsToKeep(1000000);
			filter.setDoNotOperateOnPerClassBasis(true);
			filter.setLowerCaseTokens(true);

			// Filter the input instances into the output ones
			outputInstances = Filter.useFilter(inputInstances,filter);

			System.out.println("===== Filtering dataset done =====");
		}
		catch (final Exception e) {
			System.out.println("Problem found when training");
		}
	}

	/**
	 * Save arff.
	 *
	 * @param fileName
	 *            the file name
	 */
	public void saveARFF(final String fileName) {

		try {
			final PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			writer.print(outputInstances);
			System.out.println("===== Saved dataset: " + fileName + " =====");
			writer.close();
		}
		catch (final IOException e) {
			System.out.println("Problem found when writing: " + fileName);
		}
	}


	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {

		long time1, time2;
		final IndexTest indexer = new IndexTest();
		if (args.length < 2) {
			System.out.println("Usage: java IndexTest <fileInput> <fileOutput>");
		} else {
			indexer.loadARFF(args[0]);
			time1 = System.currentTimeMillis();
			System.out.println("Started indexing at: " + time1);
			indexer.index();
			time2 = System.currentTimeMillis();
			System.out.println("Finished indexing at: " + time2);
			System.out.println("Total indexing time: " + (time2-time1));
			indexer.saveARFF(args[1]);
		}
	}
}