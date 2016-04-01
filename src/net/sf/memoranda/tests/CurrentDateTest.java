package net.sf.memoranda.tests;

import static org.junit.Assert.*;            
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
 
public class CurrentDateTest
{
    private CurrentDate cd = null;
	
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
    	cd = new CurrentDate();
    	cd.set((new CalendarDate()).today());
    }
    
    @After
    public void tearDown() throws Exception
    {
        //TODO - Create tearDown
    }

    @Test
	public void diffTest() {
    	String today = cd.get().toString();
    	cd.set((new CalendarDate()).tomorrow());
		assertTrue(!(today.equals(cd.get())));
	}
}
