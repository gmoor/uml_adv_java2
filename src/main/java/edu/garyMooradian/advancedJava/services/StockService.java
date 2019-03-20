package edu.garyMooradian.advancedJava.services;

import java.util.ArrayList;
import java.util.List;

import edu.garyMooradian.advancedJava.exceptions.StockServiceException;
import edu.garyMooradian.advancedJava.model.StockQuote;

public interface StockService {
	
	public void addOrUpdateStockQuote(ArrayList<StockQuote> stockQuotes);

}
