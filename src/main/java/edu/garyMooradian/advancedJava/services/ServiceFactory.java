package edu.garyMooradian.advancedJava.services;

/**
 * A factory that returns a <CODE>StockService</CODE> instance.
 */
public class ServiceFactory {

    /**
     * Prevent instantiations of ServiceFactory class; i.e. the default
     * constructor is public so we need to override it with a private constructor
     */
    private ServiceFactory() {}
       
 
	public static StockService getStockServiceInstance() {
		return new DatabaseStockService();//An instance of a StockService
	}

}

