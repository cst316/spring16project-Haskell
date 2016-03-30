package net.sf.memoranda.tests;

import static org.junit.Assert.*;            
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test; 
import net.sf.memoranda.CurrentNote;
import net.sf.memoranda.Note;
import net.sf.memoranda.NoteImpl;
import net.sf.memoranda.Project;
import nu.xom.Element;
 
public class CurrentNoteTest
{
    private CurrentNote cn = null;
	private Element el;
	private Project prj;
	private Note nt;
	
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
    	cn = new CurrentNote();
    	el = null;
    	prj = null;
    	nt = new NoteImpl(el,prj);
    }
    
    @After
    public void tearDown() throws Exception
    {
        //TODO - Create tearDown
    }

    @Test
	public void sameTest() {
    	cn.set(nt, false);
		assertTrue(cn.get().equals(nt));
	}
}
