package com.LetsGetDigital.dao;

import java.util.List;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.LetsGetDigital.model.Quote;

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
	
	

}
