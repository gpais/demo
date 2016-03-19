package com.LetsGetDigital.dao;

import java.util.List;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;

public class QuotesDao extends AbstractDAO<Quote>{

	public QuotesDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

	
	public void save(Quote quote){
		persist(quote);
	}
	
	public List<Quote> findAll(){
		return list(namedQuery("com.LetsGetDigital.model.Quote.findAll"));
	}
	
	
	public List<Quote> findAllBySearchCriteriaId(Long id){
		return list(namedQuery("com.LetsGetDigital.model.Quote.findBySearchCriteriaId").setLong("searchCriteriaId", id));
	}
	
	public Quote findQuoteByReference(String reference){
		return uniqueResult(namedQuery("com.LetsGetDigital.model.Quote.findQuoteByReference")
		.setString("reference", reference));

	}
	
	
	
	public List<Quote> findAllBySearchCriteria(SearchCriteria search){
		return list(namedQuery("com.LetsGetDigital.model.Quote.findBySearchCriteria")
				.setDate("fromDate", search.getFromDate())
				.setDate("toDate", search.getToDate())
				.setString("pickupLocation", search.getPickupLocation())
				.setString("droppOffLocation", search.getDroppOffLocation())
				.setString("pickUpTime", search.getPickUpTime())
				.setString("dropOffTime", search.getDropOffTime())
				.setString("countryOfResidence", search.getCountryOfResidence()));
	}

}
