package net.sf.memoranda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LineCountTest {
	
	LineCounter line = null;
	
	@Before
	public void setUp() throws Exception {
		line = new LineCounter();
	}

	@Test
	public void test() {
		assertEquals(46, line.getLines("C:/Users/Chris/Google Drive/Documents/School/Junior Year/Semester 2/CST 316/spring16project-Haskell/src/net/sf/memoranda/LineCounter.java"));
		assertEquals(-1, line.getLines("C:/Users/Chris/Google Drive/Documents/School/Junior Year/Semester 2/CST 316/spring16project-Haskell/src/net/sf/memoranda/LineCounter"));
	}

}
