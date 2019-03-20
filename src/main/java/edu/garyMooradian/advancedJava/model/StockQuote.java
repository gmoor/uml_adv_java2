package edu.garyMooradian.advancedJava.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;


@Entity
//@Table(name="quotes2")//original line
@Table(name="quotes3")//new line
public class StockQuote {
	
	private int id;
	private String symbol;
	
	//private DateTime time;//original line
	private String time;//new line
	
	private BigDecimal price;
	
	
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Basic
	@Column(name = "symbol", nullable = false, insertable = true, updatable = true, length = 4)
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	
	@Basic
	@Column(name = "time", nullable = false, insertable = true, updatable = true)
	//public DateTime getTime() {//original line
	public String getTime() {//new line
		return time;
	}
	
	//public void setTime(DateTime time) {//original line
	public void setTime(String time) {//new line
		this.time = time;
	}
	
	
	
	@Basic
	@Column(name = "price", nullable = false, insertable = true, updatable = true)
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	@Override
    public boolean equals(Object o) {

        if (this == o) return true; //They are the same object so of course they are equal

        if (o == null || getClass() != o.getClass()) return false; //they are different types of objects so they're not equal

        StockQuote stockQuote = (StockQuote) o; //cast the incoming Object type to a StockQuote type

        //different instantiations of the same type, but is it the same record?
        if (id != stockQuote.id) return false;
        
        if (symbol != null ? !symbol.equals(stockQuote.symbol) : stockQuote.symbol != null) return false;       
        if (time != null ? !time.equals(stockQuote.time) : stockQuote.time != null) return false;
        if (price != null ? !price.equals(stockQuote.price) : stockQuote.price != null) return false;
      
        return true;
    }
	
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
        
        //More needs to be done here; i.e. for the other variables
    }

    @Override
    public String toString() {
        
        return "StockQuote{id=" + id + ", symbol=" + "'" + symbol + "', time=" + "'" + time + "', price=" + "'" + price + "'";
        
    }

}
