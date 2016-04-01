package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import net.sf.memoranda.util.AgendaGenerator;
import net.sf.memoranda.date.CalendarDate;


public class AgendaGeneratorTest
{
    private AgendaGenerator ag = null;
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        //TODO - Create setUp
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        //TODO - Create tearDown
    }
    
    @Before
    public void setUp() throws Exception
    {
        //TODO - Create setUp
    	ag = new AgendaGenerator();
    }
    
    @After
    public void tearDown() throws Exception
    {
        //TODO - Create tearDown
    }

    @Test
    public void Equals(){
    	List<String> collection = new ArrayList();
    	collection.add("One");
    	collection.add("Two");
    	collection.add("Three");
    	collection.add("Four");
    	String getAgenda = ag.getAgenda(new CalendarDate(), collection);
    	assertTrue(ag.getAgenda(new CalendarDate(), collection).equals(getAgenda));
    }
    
    @Test
    public void Inequals(){
    	List<String> collection = new ArrayList<String>();
    	collection.add("One");
    	collection.add("Two");
    	collection.add("Three");
    	collection.add("Four");
    	String getAgenda = AgendaGenerator.getAgenda(CalendarDate.yesterday(), collection);
    	CalendarDate cd = CalendarDate.tomorrow();
    	assertTrue(AgendaGenerator.getAgenda(cd, collection).equals(getAgenda));
    }
}
