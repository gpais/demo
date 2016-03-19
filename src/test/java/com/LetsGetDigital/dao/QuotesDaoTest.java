package com.LetsGetDigital.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

import com.LetsGetDigital.model.PaymentModel;
import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;

public class QuotesDaoTest extends DAOTests {

	public SearchCriteria getSearchCriteria(){
		
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setCountryOfResidence("France");
		searchCriteria.setPickUpTime("10:00");
		searchCriteria.setDropOffTime("10:00");
		Calendar calendar=Calendar.getInstance();
		searchCriteria.setFromDate(calendar.getTime());
		
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		searchCriteria.setToDate(calendar.getTime());
		
		searchCriteria.setPickupLocation("PARIS");
//		
		searchCriteria.setDroppOffLocation("PARIS");

		return searchCriteria;
		
	}
	

	private Quote getQuote(){
		
		Quote quote= new Quote();
		 quote.setBaseCurrency("EUR");
		 quote.setPaymentCurrency("EUR");
		 quote.setExchangeRate(new BigDecimal(1.000));
		 quote.setBrowseTotal(new BigDecimal(100.75));
		 quote.setPaymentModel(PaymentModel.PrePaid);
		 quote.setTotal(new BigDecimal(100.75));
		 quote.setReference("MYRef");
		return quote;
		
	}

	    @Test
	    public void testSaveQuote(){
	    	   Transaction tx = getSession().beginTransaction();
	    	   
	    	   
	    	   SearchCriteriaDao dao= new SearchCriteriaDao(sessionFactory);
	    	   
	    	   
	    	   dao.save(getSearchCriteria());
	    	   
	    	  List<SearchCriteria> searchCriterias = dao.findAll();
	    	  
	    	   assertEquals(searchCriterias.size(), 1);
	    	   
	    	   QuotesDao quotesDao= new QuotesDao(sessionFactory);
	    	   
	    	   Quote quote = getQuote();
	    	   quote.setSearchCriteria(searchCriterias.get(0));

	    	   quotesDao.save(quote);
	    	   
	    	  List<Quote> quotes = quotesDao.findAll();
	    	  quote = quotes.get(0);
	    	  
	    	  assertEquals(PaymentModel.PrePaid, quote.getPaymentModel());
	    	  assertEquals(new BigDecimal(100.75), quote.getBrowseTotal());
	    	  assertEquals(new BigDecimal(100.75), quote.getTotal());
	    	  assertEquals("EUR", quote.getBaseCurrency());
	    	  assertEquals("EUR", quote.getPaymentCurrency());
	    	  assertEquals(new BigDecimal(1.000), quote.getExchangeRate());
	    	  assertEquals("MYRef", quote.getReference());


//	    	   quotesDao.f
	    	   tx.rollback();;

	    	
	    }
}
