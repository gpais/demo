package com.LetsGetDigital.main;

import org.hibernate.SessionFactory;

import com.LetsGetDigital.engine.QuotationEngine;
import com.google.inject.AbstractModule;

public class ServerModule  extends AbstractModule {
 
	private SessionFactory sessionFaction;
	
    public ServerModule(SessionFactory sessionFaction) {
this.sessionFaction=sessionFaction;
}
	@Override
	protected void configure() {
      bind(SessionFactory.class).toInstance(sessionFaction);
      bind(QuotationEngine.class).toInstance(new QuotationEngine());

	}

	

	
}
