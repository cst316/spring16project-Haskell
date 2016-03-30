package net.sf.memoranda.tests;

import static org.junit.Assert.*;            
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import net.sf.memoranda.util.AppList;

import nu.xom.Element;
import nu.xom.Elements;
public class AppListTest
{
    private AppList al = null;
    private Element el;
	
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
    	al = new AppList(el);
    }
    
    @After
    public void tearDown() throws Exception
    {
        //TODO - Create tearDown
    }

    @Test
	public void rootTest() {
    	assertTrue(al._root==(el));
	}
}
