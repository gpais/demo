package com.LetsGetDigital.dao;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import com.LetsGetDigital.main.Apllication;
import com.LetsGetDigital.main.LetGetDigitalConfiguration;
import com.LetsGetDigital.model.Quote;
import com.LetsGetDigital.model.SearchCriteria;

@SuppressWarnings("deprecation")
public class DAOTests {
    SessionFactory sessionFactory;
    
	private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("example.yml");


    @ClassRule
    public static final DropwizardAppRule<LetGetDigitalConfiguration> RULE = new DropwizardAppRule<LetGetDigitalConfiguration>(
    		Apllication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    private Client client;

    @BeforeClass
    public static void migrateDb() throws Exception {
        RULE.getApplication().run("db", "drop-all","--confirm-delete-everything", CONFIG_PATH);
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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

    
    public DAOTests() {
        AnnotationConfiguration config=new AnnotationConfiguration();
        config.setProperty("hibernate.connection.url","jdbc:h2:./target/example");
        config.setProperty("hibernate.connection.username","sa");
        config.setProperty("hibernate.connection.password","sa");
        config.setProperty("hibernate.connection.driver_class","org.h2.Driver");
        config.setProperty("hibernate.current_session_context_class", "thread");
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(SearchCriteria.class);
        config.addAnnotatedClass(Quote.class);

        sessionFactory=config.buildSessionFactory();
    }

    public Session getSession()
    {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (SessionException se) {
            session = sessionFactory.openSession();
        }

        return session;
    }


}
