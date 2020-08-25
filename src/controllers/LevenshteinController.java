package controllers;

import algorithms.Levenshtein;
import viewModels.LevenshteinViewModel;

public class LevenshteinController {
	private Levenshtein levenshteinModel;
	
	public LevenshteinController() {
		this.levenshteinModel = new Levenshtein();
	}
	
	public LevenshteinViewModel getLevenshteinMatrix(String word1, String word2) {
		if(!word1.equals("") && !word2.equals("")) {
			levenshteinModel.levenshtein(word1, word2);
			return new LevenshteinViewModel(this.levenshteinModel.getMatrix(), this.levenshteinModel.getDirection());
		}
		
		return null;
	}
}
