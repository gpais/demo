package com.LetsGetDigital.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.LetsGetDigital.model.SearchCriteria;

public class SearchCriteriaDao  extends AbstractDAO<SearchCriteria>{

	public SearchCriteriaDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public void save(SearchCriteria search){
		persist(search);
	}

	
	  public List<SearchCriteria> findAll() {
	        return list(namedQuery("com.LetsGetDigital.model.SearchCriteria.findAll"));
	    }
}
