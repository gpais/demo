package com.LetsGetDigital.controller;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.LetsGetDigital.Facade.Facade;
import com.LetsGetDigital.dao.QuotesDao;
import com.LetsGetDigital.dao.SearchCriteriaDao;
import com.LetsGetDigital.dto.QuotesResult;
import com.LetsGetDigital.dto.SearchCriteriaDto;
import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;
import com.google.inject.Inject;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuotationSearchController {
	
	private Facade facade;

	
	
	
	@Inject
	public QuotationSearchController(Facade facade){
		this.facade=facade;
}


	
	@javax.ws.rs.GET
    @Path("/quotes/{reference}")
	@UnitOfWork
    public QuotesResult getQuotes(@PathParam("reference") String reference) {
        return this.facade.getQuoteByReference(reference);
    }
	
	
	@javax.ws.rs.POST
    @Path("/quotes")
	@UnitOfWork
    public QuotesResult getQuotes(SearchCriteriaDto search) {
        return this.facade.getQuotes(search);
    }

	
	@javax.ws.rs.GET
    @Path("/")
	@UnitOfWork
    public SearchCriteriaDto getSearchCriteriaSample() {
        return facade.getSearchCriteriaSample();
    }

}
