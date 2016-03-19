package com.LetsGetDigital.main;

import org.hibernate.SessionFactory;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.LetsGetDigital.Facade.Facade;
import com.LetsGetDigital.controller.QuotationSearchController;
import com.LetsGetDigital.dao.QuotesDao;
import com.LetsGetDigital.dao.SearchCriteriaDao;
import com.LetsGetDigital.engine.QuotationEngine;
import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;


public class Apllication extends Application<LetGetDigitalConfiguration> {

	
  
            
            
        	
            private final HibernateBundle<LetGetDigitalConfiguration> hibernateBundle =
                    new HibernateBundle<LetGetDigitalConfiguration>(SearchCriteria.class,Quote.class) {
                        @Override
                        public DataSourceFactory getDataSourceFactory(LetGetDigitalConfiguration configuration) {
                            return configuration.getDatabase();
                        }
                    };
                    
    public static void main(final String[] args) throws Exception {
        new Apllication().run(args);
    }

    @Override
    public String getName() {
        return "LetsGetDIgitalApllication";
    }

    @Override
    public void initialize(final Bootstrap<LetGetDigitalConfiguration> bootstrap) {
    	
     	bootstrap.addBundle(new MigrationsBundle<LetGetDigitalConfiguration>() {
    		@Override
    		public DataSourceFactory getDataSourceFactory(LetGetDigitalConfiguration configuration) {
    			return configuration.getDatabase();
    		}
    	});
     	
    	
        bootstrap.addBundle(hibernateBundle);
        
    }

    @Override
    public void run(final LetGetDigitalConfiguration configuration, final Environment environment) {
    	SessionFactory factory = hibernateBundle.getSessionFactory();
    	Facade facade = new Facade( new QuotationEngine(),
        		new SearchCriteriaDao(factory),
        		new QuotesDao(factory));
        		
        environment.jersey().register(
                new QuotationSearchController(facade)
        		
        		);

    }

}
