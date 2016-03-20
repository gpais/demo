package com.LetsGetDigital.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import com.LetsGetDigital.dto.QuoteDto;
import com.LetsGetDigital.dto.SearchCriteriaDto;
import com.LetsGetDigital.model.CarType;
import com.LetsGetDigital.model.PaymentModel;
import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;

public class ConverterTest {
    SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy");
   
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
	
	public Quote getQuote(){
		Quote quote= new  Quote();
		quote.setBaseCurrency("EUR");
		quote.setPaymentCurrency("EUR");
		quote.setPaymentModel(PaymentModel.PrePaid);
		quote.setExchangeRate(new BigDecimal(1.00));
		quote.setBrowseTotal(new BigDecimal(130.50));
		quote.setTotal(new BigDecimal(130.50));
        quote.setReference("TESTREF");
        quote.setCartype(new CarType());
        quote.getCartype().setSipp("EBMR");
        quote.getCartype().setSupplier("AVIS");

		return quote;
	}
	
	public SearchCriteriaDto getSearchCriteriaDto(){
		
		SearchCriteriaDto searchCriteria = new SearchCriteriaDto();
		searchCriteria.setCountryOfResidence("France");
		searchCriteria.setPickUpTime("10:00");
		searchCriteria.setDropOffTime("10:00");
		searchCriteria.setFromDateTime("10/02/2016");
		searchCriteria.setToDateTime("12/02/2016");
		
		searchCriteria.setPickupLocation("PARIS");
//		
		searchCriteria.setDroppOffLocation("PARIS");

		return searchCriteria;
		
	}
	
	
	@Test
	public void testConvertFromQuote(){
		
		QuoteDto quoteDto = Converter.convertTo(getQuote());
		
		
	 	   assertEquals("EUR", quoteDto.getBaseCurrency());
	 	   assertEquals("EUR",quoteDto.getPaymentCurrency());
	 	   assertEquals(PaymentModel.PrePaid, quoteDto.getPaymentModel());
	 	   assertEquals(new BigDecimal(1.00), quoteDto.getExchangeRate());
	 	   assertEquals("TESTREF", quoteDto.getReference());
	 	   assertEquals(new BigDecimal(130.50), quoteDto.getBrowseTotal());
	 	   assertEquals(new BigDecimal(130.50), quoteDto.getTotal());
	 	   assertEquals("EBMR", quoteDto.getCarType().getSipp());
	 	   assertEquals("AVIS", quoteDto.getCarType().getSupplier());

	}
	
	@Test
	public void testConvertFrom(){
		SearchCriteria searchCriteria= getSearchCriteria();
		SearchCriteriaDto dto = Converter.convertTo(getSearchCriteria());
		
 	   assertEquals("France", dto.getCountryOfResidence());
 	   assertEquals("10:00", dto.getDropOffTime());
 	   assertEquals("10:00", dto.getPickUpTime());
 	   assertEquals("PARIS", dto.getDroppOffLocation());
 	   assertEquals("PARIS", dto.getPickupLocation());
 	   assertEquals( format.format(searchCriteria.getFromDate()), dto.getFromDateTime());
 	   assertEquals( format.format(searchCriteria.getToDate()), dto.getToDateTime());
		
	}
	
	
	@Test
	public void testConvertTO() throws ParseException{
		SearchCriteriaDto dto = getSearchCriteriaDto();
		SearchCriteria searchCriteria=  Converter.convertFrom(dto);
		
 	   assertEquals("France", searchCriteria.getCountryOfResidence());
 	   assertEquals("10:00", searchCriteria.getDropOffTime());
 	   assertEquals("10:00", searchCriteria.getPickUpTime());
 	   assertEquals("PARIS", searchCriteria.getDroppOffLocation());
 	   assertEquals("PARIS", searchCriteria.getPickupLocation());
 	   assertEquals( format.parse(dto.getFromDateTime()), searchCriteria.getFromDate());
 	   assertEquals( format.parse(dto.getToDateTime()), searchCriteria.getToDate());
		
	}
	

}
