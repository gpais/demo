package com.LetsGetDigital.Facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.LetsGetDigital.dao.QuotesDao;
import com.LetsGetDigital.dao.SearchCriteriaDao;
import com.LetsGetDigital.dto.QuoteDto;
import com.LetsGetDigital.dto.QuotesResult;
import com.LetsGetDigital.dto.SearchCriteriaDto;
import com.LetsGetDigital.engine.QuotationEngine;
import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;
import com.LetsGetDigital.util.Converter;

public class Facade {

	private SearchCriteriaDao searchCriteriaDao;
	private QuotesDao quotesDao;
	private QuotationEngine quotationEngine;

	public Facade(QuotationEngine quotationEngine,
			SearchCriteriaDao searchCriteriaDao, QuotesDao quotesDao) {
		this.searchCriteriaDao = searchCriteriaDao;
		this.quotesDao = quotesDao;
		this.quotationEngine = quotationEngine;
	}

	public SearchCriteriaDto getSearchCriteriaSample() {

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setCountryOfResidence("France");
		searchCriteria.setPickUpTime("10:00");
		searchCriteria.setDropOffTime("10:00");
		Calendar calendar = Calendar.getInstance();
		searchCriteria.setFromDate(calendar.getTime());

		calendar.add(Calendar.DAY_OF_MONTH, 5);
		searchCriteria.setToDate(calendar.getTime());

		searchCriteria.setPickupLocation("PARIS");
		searchCriteria.setDroppOffLocation("PARIS");

		return Converter.convertTo(searchCriteria);
	}

	public QuotesResult getQuotes(SearchCriteriaDto search) {
    	QuotesResult result = new QuotesResult();
    	result.setSearchCriteria(search);
    	
    	SearchCriteria searchModel = Converter.convertFrom(search);
    	this.searchCriteriaDao.save(searchModel);
    	
    	Function<List<Quote>, List<QuoteDto>> convertToDtos = a->{
    		List<QuoteDto> quoteDtos= new ArrayList<>();
    		a.stream()
    		.forEach(b-> {
    			this.quotesDao.save(b);
    			quoteDtos.add(Converter.convertTo(b));
    			
    		});
    		return quoteDtos;
    	};
    	
    	result.setQuotes(convertToDtos.apply(this.quotationEngine.getQuotes(searchModel)));
    	return result;
    }

}
