package com.hack23.cia.service.impl;

/**
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
import java.io.*;

public class IndexTest {

	/**
	 * Instances to be indexed.
	 */
	Instances inputInstances;
	
	/**
	 * Instances after indexing.
	 */
	Instances outputInstances;
		
	/**
	 * Loads an ARFF file into an instances object.
	 * @param fileName The name of the file to be loaded.
	 */
	public void loadARFF(String fileName) {
	
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			ArffReader arff = new ArffReader(reader);
			inputInstances = arff.getData();
			System.out.println("===== Loaded dataset: " + fileName + " =====");
			reader.close();
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}
	
	/**
	 * Index the inputInstances string features using the StringToWordVector filter.
	 */
	public void index() {
		// outputInstances = inputInstances;
		try {
			
			// Set the tokenizer
			NGramTokenizer tokenizer = new NGramTokenizer();
			tokenizer.setNGramMinSize(1);
			tokenizer.setNGramMaxSize(1);
			tokenizer.setDelimiters("\\W");
			
			// Set the filter
			StringToWordVector filter = new StringToWordVector();
			filter.setTokenizer(tokenizer);
			filter.setInputFormat(inputInstances);
			filter.setWordsToKeep(1000000);
			filter.setDoNotOperateOnPerClassBasis(true);
			filter.setLowerCaseTokens(true);
			
			// Filter the input instances into the output ones
			outputInstances = Filter.useFilter(inputInstances,filter);
			
			System.out.println("===== Filtering dataset done =====");
		}
		catch (Exception e) {
			System.out.println("Problem found when training");
		}
	}
	
	/**
	 * Save an instances object into an ARFF file.
	 * @param fileName The name of the file to be saved.
	 */	
	public void saveARFF(String fileName) {
	
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			writer.print(outputInstances);
			System.out.println("===== Saved dataset: " + fileName + " =====");
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Problem found when writing: " + fileName);
		}
	}
	

	/**
	 * Main method. It is an example of the usage of this class.
	 * @param args Command-line arguments: fileData and fileModel.
	 */
	public static void main(String[] args) {
	
		long time1, time2;
		IndexTest indexer = new IndexTest();
		if (args.length < 2)
			System.out.println("Usage: java IndexTest <fileInput> <fileOutput>");
		else {
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