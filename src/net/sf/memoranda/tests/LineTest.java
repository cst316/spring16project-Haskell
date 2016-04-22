package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import net.sf.memoranda.util.lineCounter;


public class LineTest 
{
	lineCounter l = null;
	static String fileName = System.getProperty("user.dir")
							+ "testCount.java";
	static int trueLines;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    	
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        //TODO - Create tearDown
    }
    
    @Before
    public void setUp() throws Exception
    {
    	PrintWriter writer = new PrintWriter(fileName, "UTF-8");
    	for(trueLines = 0; trueLines<10;trueLines++){
    		writer.println("Line-"+(trueLines+1));
    	}
    	writer.close();
    }
    
    @After
    public void tearDown() throws Exception
    {
        try{
        	Files.delete(Paths.get(fileName));
        }
        catch(Exception e){
        	assertTrue(false);
        }
    }
    
    @Test
    public void counter(){
    	l = new lineCounter();
    	System.out.println(l.getLines(fileName));
    	System.out.println(fileName);
    	assertTrue(l.getLines(fileName)==trueLines);    	
    }
}
