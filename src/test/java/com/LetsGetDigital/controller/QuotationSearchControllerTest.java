package com.LetsGetDigital.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.LetsGetDigital.dto.QuoteDto;
import com.LetsGetDigital.dto.QuotesResult;
import com.LetsGetDigital.dto.SearchCriteriaDto;
import com.LetsGetDigital.main.Apllication;
import com.LetsGetDigital.main.LetGetDigitalConfiguration;

public class QuotationSearchControllerTest {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("example.yml");

    @ClassRule
    public static final DropwizardAppRule<LetGetDigitalConfiguration> RULE = new DropwizardAppRule<>(
    		Apllication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    private Client client;

    @BeforeClass
    public static void migrateDb() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() throws Exception {
        RULE.getApplication().run("db", "drop-all","--confirm-delete-everything", CONFIG_PATH);
        client.close();
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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
    public void SearchForQuote() throws Exception {
    	SearchCriteriaDto searchCriteriaDto = getSearchCriteriaDto();
        final QuotesResult result = client.target("http://localhost:" + RULE.getLocalPort() + "/search/quotes")
               .request()
               .post(Entity.entity(searchCriteriaDto, MediaType.APPLICATION_JSON_TYPE))
               .readEntity(QuotesResult.class);
        
       assertThat(result.getSearchCriteria()).isEqualTo(searchCriteriaDto);
 	   assertEquals(10, result.getQuotes().size());
//       assertThat(ticket.getId()).isEqualTo(ticketDto.getId());

    }
    
    
    @Test
    public void SearchForQuoteAfterSave() throws Exception {
    	SearchCriteriaDto searchCriteriaDto = getSearchCriteriaDto();
        final QuotesResult result = client.target("http://localhost:" + RULE.getLocalPort() + "/search/quotes")
               .request()
               .post(Entity.entity(searchCriteriaDto, MediaType.APPLICATION_JSON_TYPE))
               .readEntity(QuotesResult.class);
        
       assertThat(result.getSearchCriteria()).isEqualTo(searchCriteriaDto);
 	   assertEquals(10, result.getQuotes().size());

    }
    
    
    @Test
    public void SearchForQuoteReferenceAfterSave() throws Exception {
    	SearchCriteriaDto searchCriteriaDto = getSearchCriteriaDto();
         QuotesResult result = client.target("http://localhost:" + RULE.getLocalPort() + "/search/quotes")
               .request()
               .post(Entity.entity(searchCriteriaDto, MediaType.APPLICATION_JSON_TYPE))
               .readEntity(QuotesResult.class);
        
       assertThat(result.getSearchCriteria()).isEqualTo(searchCriteriaDto);
 	   assertEquals(10, result.getQuotes().size());
 	   
 	      result = client.target("http://localhost:" + RULE.getLocalPort() + "/search/quotes")
 	    		   .path(result.getQuotes().get(0).getReference())
 	               .request()
 	               .get()
 	               .readEntity(QuotesResult.class);
 	     
 	     assertThat(result.getSearchCriteria()).isEqualTo(searchCriteriaDto);
 	 	 assertEquals(1, result.getQuotes().size());
 	   

    }
    
    @Test
    public void SearchForQuoteAfterSaveQuotes() throws Exception {
    	SearchCriteriaDto searchCriteriaDto = getSearchCriteriaDto();
         QuotesResult result = client.target("http://localhost:" + RULE.getLocalPort() + "/search/quotes")
               .request()
               .post(Entity.entity(searchCriteriaDto, MediaType.APPLICATION_JSON_TYPE))
               .readEntity(QuotesResult.class);
        
       assertThat(result.getSearchCriteria()).isEqualTo(searchCriteriaDto);
 	   assertEquals(10, result.getQuotes().size());
 	   
 	     result = client.target("http://localhost:" + RULE.getLocalPort() + "/search/quotes")
                .request()
                .post(Entity.entity(searchCriteriaDto, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(QuotesResult.class);
         
        assertThat(result.getSearchCriteria()).isEqualTo(searchCriteriaDto);
  	   assertEquals(10, result.getQuotes().size());

    }
    
}
