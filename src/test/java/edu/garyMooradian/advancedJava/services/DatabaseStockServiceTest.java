package edu.garyMooradian.advancedJava.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.garyMooradian.advancedJava.app.JAXBApp;
import edu.garyMooradian.advancedJava.exceptions.StockServiceException;
import edu.garyMooradian.advancedJava.model.StockQuote;

public class DatabaseStockServiceTest {

	/*
	 * The verification process for this test involves the
	 * id, symbol, date, and price
	 * Because the id is also involved in the test process,
	 * this test needs to be run on a table that has no records
	 * from another source, UNLESS the other source was this test.
	 * 
	 * For example (starting with an empty table):
	 * 
	 * - Run of the application (not this JUnit test) produces at least
	 * one record in the table.
	 * A subsequent run of this test case therefore, will fail
	 * 
	 * - Run of this test case (which will produce 2 records) will pass
	 * Subsequent runs of this test case will pass
	 * 
	 * Provide (the method under test) a List of StockQuote objects
	 * We expect them to persist to the database. We verify that they
	 * do by getting back those records, which then maps to a separate List
	 * of StockQuote objects. The StockQuote objects of one List should
	 * be equal to the corresponding StockQuote objects of the other List 
	 * 
	 */
	@Test
	public void testAddOrUpdateStockQuote() throws StockServiceException {
		StockQuote stockQuote;
		DatabaseStockService databaseStockService = new DatabaseStockService();
		ArrayList<StockQuote> stockQuoteList = new ArrayList<>();

		//create a couple of StockQuote objects and add to ArrayList

		stockQuote = new StockQuote();
		stockQuote.setSymbol("GOOG");
		stockQuote.setTime("2018-10-13 03:15:43");
		stockQuote.setPrice(new BigDecimal(1104));
		stockQuoteList.add(stockQuote);
		
		stockQuote = new StockQuote();
		stockQuote.setSymbol("AAPL");
		stockQuote.setTime("2018-06-22 11:25:32");
		stockQuote.setPrice(new BigDecimal(174));
		stockQuoteList.add(stockQuote);
		
		//Persist to the database
		databaseStockService.addOrUpdateStockQuote(stockQuoteList);
		
		/*
		 * NOTE: id is automatically handled in sql when we persist, thus, we don't
		 * provide an id when setting the StockQuote variables.
		 * However, when comparing records by comparing the entire objects,
		 * if the ids are not the same, the objects wont be equal.
		 * Therefore, AFTER we persist, we now add the ids to match with what they are
		 * in the table; i.e. ids set to 1 and 2 since we'll always be getting just the
		 * first 2 records no matter how many times we run this test
		 */
		
		stockQuoteList.get(0).setId(1);
		stockQuoteList.get(1).setId(2);

		
		//Now read back from the database and compare the objects
		
		List<StockQuote> stockQuotes = databaseStockService.getStockQuote();
		
		assertTrue("The first record/object received is not equal to the first object persisted, but should be",
				stockQuoteList.get(0).equals(stockQuotes.get(0)));
		assertTrue("The second record/object received is not equal to the second object persisted, but should be",
				stockQuoteList.get(1).equals(stockQuotes.get(1)));
	}

}
