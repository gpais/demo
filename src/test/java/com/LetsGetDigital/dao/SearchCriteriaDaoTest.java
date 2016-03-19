package com.LetsGetDigital.dao;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

import com.LetsGetDigital.model.SearchCriteria;

public class SearchCriteriaDaoTest extends DAOTests {
	    
	
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
	


	    @Test
	    public void testSaveSearchCriteria(){
	    	   Transaction tx = getSession().beginTransaction();
	    	   
	    	   
	    	   SearchCriteriaDao dao= new SearchCriteriaDao(sessionFactory);
	    	   
	    	   
	    	   dao.save(getSearchCriteria());
	    	   
	    	  List<SearchCriteria> searchCriterias = dao.findAll();
	    	  
	    	   assertEquals(searchCriterias.size(), 1);

	    	   tx.rollback();;

	    	
	    }


}
