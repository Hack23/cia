package com.hack23.cia.service.impl;

import org.junit.Test;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class WordCountTest {

	@Test
	public void wordCountTest() throws Exception {

		// http://weka.wikispaces.com/Use+WEKA+in+your+Java+code#Classification-Building

		String html = "Det måste bli lättare att bygga. Plan- och byggreglerna behöver förenklas och processen förkortas, för att därigenom möjliggöra en ökad takt på byggandet i ett Sverige där bostadsbristen är ett allvarligt problem. Samplaneringen mellan infrastruktur och bostäder behöver förtydligas. Folkpartiet har under Alliansregeringen medverkat till en rad lagändringar för att förenkla plan- och byggprocessen, och även den nu aktuella propositionen är resultatet av ett lagstiftningsarbete som påbörjades under Alliansregeringen. Vi välkomnar att detta arbete nu leder till konkret lagstiftning. Däremot är vi kritiska till att den nuvarande regeringen inte tillräckligt grundligt har vägt in de olika aspekter som behöver uppmärksammas i lagstiftningsarbetet.";

		Attribute input = new Attribute("html", (FastVector<String>) null);

		FastVector inputVec = new FastVector();
		inputVec.addElement(input);

		Instances htmlInst = new Instances("html", inputVec, 1);
		
		htmlInst.add(new DenseInstance(1));
		htmlInst.instance(0).setValue(0, html);
			

		StringToWordVector filter = new StringToWordVector();
		StopwordsHandler StopwordsHandler = new StopwordsHandler() {

			@Override
			public boolean isStopword(String word) {

				return word.length() <3;
			}
		};

		NGramTokenizer tokenizer = new NGramTokenizer();
		tokenizer.setNGramMinSize(1);
		tokenizer.setNGramMaxSize(1);
		tokenizer.setDelimiters(" \r\n\t.,;:'\"()?!'");

		filter.setTokenizer(tokenizer);

		filter.setStopwordsHandler(StopwordsHandler);
		filter.setLowerCaseTokens(true);
		// filter.setUseStoplist(true);
		filter.setOutputWordCounts(true);
		filter.setWordsToKeep(10);

		filter.setInputFormat(htmlInst);
		Instances dataFiltered = Filter.useFilter(htmlInst, filter);

		Instance last = dataFiltered.lastInstance();

		int numAttributes = last.numAttributes();

		for (int i = 0; i < numAttributes; i++) {
			if (Integer.parseInt(last.toString(i)) > 1) 
			System.out.println(last.attribute(i).name() + ":"
					+ last.toString(i));
		}
	}

}
