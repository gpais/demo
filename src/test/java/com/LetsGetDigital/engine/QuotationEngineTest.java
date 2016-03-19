package com.LetsGetDigital.engine;

import java.util.Calendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;
import static org.junit.Assert.assertEquals;

public class QuotationEngineTest  {
	private static QuotationEngine quotationEngineTest;
	
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
	
	@BeforeClass
	public static void setUp(){
		quotationEngineTest= new QuotationEngine();
	}
	
	@Test
	public void testGetQuotes(){
		List<Quote> quotes = quotationEngineTest.getQuotes(getSearchCriteria());
		
		assertEquals(quotes.size(), 10);
		
	}

}
