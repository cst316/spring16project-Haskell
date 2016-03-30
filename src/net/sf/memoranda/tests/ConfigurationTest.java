package net.sf.memoranda.tests;

import static org.junit.Assert.*;            
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import net.sf.memoranda.util.Configuration;
 
public class ConfigurationTest
{
    private Configuration c = null;
	
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
    	c = new Configuration();
    }
    
    @After
    public void tearDown() throws Exception
    {
        //TODO - Create tearDown
    }
    
    @Test
    public void putGetTest(){
    	String obj = "Hai";
    	c.put("obj", obj);
    	assertTrue(c.get("obj").equals(obj));
    }
}
