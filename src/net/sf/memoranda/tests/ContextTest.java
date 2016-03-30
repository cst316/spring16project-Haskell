package net.sf.memoranda.tests;

import static org.junit.Assert.*;            
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import net.sf.memoranda.util.Context;
 
public class ContextTest
{
    private Context c = null;
	
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
    	c = new Context();
    }
    
    @After
    public void tearDown() throws Exception
    {
        //TODO - Create tearDown
    }

    @Test
	public void keyValTest() {
    	String key = "key";
    	String value = "val";
    	c.put(key, value);
		assertTrue(c.get(key).equals(value));
	}
}
