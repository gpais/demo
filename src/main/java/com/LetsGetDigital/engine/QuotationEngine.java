package com.LetsGetDigital.engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;

public class QuotationEngine {
	


	public List<Quote> getQuotes(final SearchCriteria search){
		BiFunction<SearchCriteria, Integer, List<Quote>> searchQuotes=(a,b)->{
			List<Quote> quotes= new ArrayList<Quote>();
			for(int i=0;i<b;i++){
				Quote quote= new Quote();
				quote.setSearchCriteria(search);
				quote.setBaseCurrency("EUR");
				quote.setPaymentCurrency("EUR");
				quote.setReference(search.getPickupLocation()+i);
				quote.setTotal(new BigDecimal(130.50));
				quote.setBrowseTotal(new BigDecimal(130.50));
				quote.setExchangeRate(new BigDecimal(1.00));
				quotes.add(quote);
			}
			return quotes;
		};
		
		return searchQuotes.apply(search,10);
		
	}
}
