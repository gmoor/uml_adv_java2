package edu.garyMooradian.advancedJava.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.garyMooradian.advancedJava.exceptions.InvalidXMLException;

//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;

//import org.joda.time.DateTime;
//import org.joda.time.LocalDateTime;
//import org.joda.time.format.DateTimeFormat;

import edu.garyMooradian.advancedJava.jabx.Stocks;
import edu.garyMooradian.advancedJava.jabx.Stocks.Stock;
import edu.garyMooradian.advancedJava.model.StockQuote;
import edu.garyMooradian.advancedJava.services.DatabaseStockService;
import edu.garyMooradian.advancedJava.util.XMLUtils;

public class JAXBApp 
{

    public static void main(String[] args) {

        // here's how to go from XML to Java
        
		try {
			
			Stocks stocks = XMLUtils.unmarshall("./src/main/resources/xml/stock_info.xml",
												Stocks.class);
			
			/*
			 * We want to get the 3 values in the Stocks object, and use them
			 * to set the corresponding 3 values in the StockQuote (ORM) object.
			 * 
			 * There are several Stocks objects, so we'll need to create the same number
			 * of StockQuote objects from those Stock objects and add them to an ArrayList
			 * of StockQuotes. Create the ArrayList
			*/
			ArrayList<StockQuote> stockQuotes = new ArrayList<>();

			/*
			 * This loop will get a Stock object's values and assign them to
			 * the corresponding StockQuote object's variables 
			 */
			for(Stock stock : stocks.getStock()) {

				//Create a StockQuote (ORM) object
				StockQuote stockQuote = new StockQuote();
				
				/*
				 * We now have a Stock object that contains the String values for
				 * one line from the xml file.
				 * We also have a StockQuote object that maps to the quotes3
				 * table; i.e. ORM/Hibernate
				 * 
				 * We will get the String values from the stock object,
				 * and convert them to the proper types if necessary, and then
				 * set the corresponding variables in the stockQuote object.
				 * Then we will add the stockQuote object to the stockQuotes ArrayList
				 */
				
				stockQuote.setSymbol(stock.getSymbol());
				
				//Convert String price to BigDecimal price
				stockQuote.setPrice(new BigDecimal(stock.getPrice()));
				
				/*****IMPORTANT
				 * 
				 * The quotes3 table's column for date and Time is a String type
				 * We do that because of issues trying to create the right DateTime in our
				 * that will allow us to add it to the sql table. To get around this issue
				 * for Assignment 7, I made the column type for the time column, a String
				 */
				stockQuote.setTime(stock.getTime());
				
				/*
				 * With all 3 values assigned to the StockQuote object
				 * we add the object to the ArrayList
				 */
				stockQuotes.add(stockQuote);
				
			}
			
			//Now we call the persistence method
			DatabaseStockService databaseStockService = new DatabaseStockService();
			
			//passing the ArrayList of StockQuote objects
			databaseStockService.addOrUpdateStockQuote(stockQuotes);
					
		} catch (InvalidXMLException e) {
			e.getMessage();
			e.printStackTrace();
		}
    }
}
