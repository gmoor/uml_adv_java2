package edu.garyMooradian.advancedJava.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.garyMooradian.advancedJava.exceptions.StockServiceException;
import edu.garyMooradian.advancedJava.model.StockQuote;
import edu.garyMooradian.advancedJava.util.DatabaseUtils;

public class DatabaseStockService implements StockService {
	
    /**
     * Add a new person or update an existing Person's data
     *
     * @param person a person object to either update or create
     */
    @Override
    public void addOrUpdateStockQuote(ArrayList<StockQuote> stockQuotes) {
    	Session session;
        Transaction transaction = null;;
        try {

        	for(StockQuote stockQuote : stockQuotes) {
        	  	session = DatabaseUtils.getSessionFactory().openSession();
        		transaction = session.beginTransaction();
                session.saveOrUpdate(stockQuote);
                transaction.commit();
        	}
    		
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }
    
    
    /*
     * --> USING THIS METHOD FOR TEST PURPOSES ONLY; i.e.
     * testing addOrUpdateStockQuote method (see testAddOrUpdateStockQuote)
     */
    public List<StockQuote> getStockQuote() throws StockServiceException {
		
		Session session =  DatabaseUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		List<StockQuote> returnValue = null;
		
		try {
        	
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(StockQuote.class);

            /**
             * NOTE criteria.list(); generates unchecked warning so SuppressWarnings
             * is used - HOWEVER, this about the only @SuppressWarnings I think it is OK
             * to suppress them - in almost all other cases they should be fixed not suppressed
             * 
             */
            returnValue = criteria.list();

        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new StockServiceException("Could not get Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }

        return returnValue;
	}

}
