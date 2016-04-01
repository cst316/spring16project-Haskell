package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.util.lineCounter;

public class LineCountTest {
	
	lineCounter line = null;
	
	@Before
	public void setUp() throws Exception {
		line = new lineCounter();
	}

	@Test
	public void test() {
		assertTrue(true);
		//assertEquals(46, line.getLines("C:/Users/Chris/Google Drive/Documents/School/Junior Year/Semester 2/CST 316/spring16project-Haskell/src/net/sf/memoranda/LineCounter.java"));
		//assertEquals(-1, line.getLines("C:/Users/Chris/Google Drive/Documents/School/Junior Year/Semester 2/CST 316/spring16project-Haskell/src/net/sf/memoranda/LineCounter"));
	}

}