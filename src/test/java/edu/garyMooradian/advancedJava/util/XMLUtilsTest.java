package edu.garyMooradian.advancedJava.util;

import static org.junit.Assert.*;

import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import edu.garyMooradian.advancedJava.exceptions.InvalidXMLException;
import edu.garyMooradian.advancedJava.jabx.Stocks;
import edu.garyMooradian.advancedJava.xml.XMLDomainObject;
import test.Stocks_test;

public class XMLUtilsTest {

	/*
	 * Testing -> public static  <T extends XMLDomainObject> T unmarshall(
	 * 												String xmlInstance, Class T)
	 *
	 * The unmarshall method we are testing receives an xml file and
	 * a JAXB style class noted by T. The return value is a T type object
	 * wrapped around underlying objects (from inner class).
	 * We have access to those underlying objects via outter class T type's
	 * getStock accessor method.
	 * 
	 * We test that those inner class related objects contain the right data
	 * as expressed in the xml file
	 * 
	 * We test that when this method is passed a valid xml file
	 * and associated jaxb class, the inner class related objects
	 * contain the right data as expressed in the xml file
	 * 
	 */
	@Test
	public void testUnmarshall() throws InvalidXMLException {
		
		Stocks stocks = XMLUtils.unmarshall("./src/main/resources/test/stock_info.xml", Stocks.class);

		assertEquals("Symbol value in Stock object does not match with symbol attribute value in xml file",
				"VNET", stocks.getStock().get(0).getSymbol());
		
		assertEquals("Time value in Stock object does not match with time attribute value in xml file",
				"2015-02-10 00:00:01", stocks.getStock().get(0).getTime());
		
		assertEquals("Price value in Stock object does not match with price attribute value in xml file",
				"110.10", stocks.getStock().get(0).getPrice());
	}
	
	
	/*
	 * We created a copy of Stocks.java (called it Stocks_test.java) and placed
	 * it in src/main/resources/test. Stocks_test was then modified so that the
	 * annotation @XmlRootElement name attribute was changed from "stocks" to "stockss"
	 * This means that the "name" attribute will not match with the xml root tag
	 * in the stock_info.xml file. This should result in an InvalidXMLException.
	 */
	@Test(expected = InvalidXMLException.class)
	public void testUnmarshallNegative() throws InvalidXMLException {
		
		Stocks_test stocks_test = XMLUtils.unmarshall("./src/main/resources/test/stock_info.xml", Stocks_test.class);

	}
	
	
	

}
