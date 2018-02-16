package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import core.LineBuilder;
import exception.TerminusException;

public class LineTest {
	static LineBuilder buildtest;
	
	@BeforeClass
	public  static void BuildLinetest(){
		buildtest = new LineBuilder();
		buildtest.buildLine(900,300);
	}
	@Test
	public void test() throws TerminusException {
		if(!buildtest.getBuiltLine().isFull())
			fail("Line doesn't build correctly");
		buildtest.getBuiltLine().addCanton(1,300);
		assertEquals(4, buildtest.getBuiltLine().getCantons().size());		
		assertEquals(buildtest.getBuiltLine().getCantons().get(0),buildtest.getBuiltLine().getCantonByPosition(100));
		assertEquals(buildtest.getBuiltLine().getCantons().get(2),buildtest.getBuiltLine().getCantonByPosition(700));
		try {
			buildtest.getBuiltLine().getCantonByPosition(20000);
			} catch (Exception e) {
			}	
		}

}
