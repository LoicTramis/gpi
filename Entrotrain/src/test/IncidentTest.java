package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Incident;

public class IncidentTest {

	@Test
	public void test() {
		Incident test = new Incident(0);
		assertEquals(2,test.getIncidentTime(1)); 
		assertEquals(3,test.getIncidentTime(2));
		assertEquals(4,test.getIncidentTime(5));
		assertEquals(0,test.getIncidentTime(6));
		assertEquals(0,test.getIncidentTime(99));
		assertEquals(2,test.getIncidentSatisfaction(1)); 
		assertEquals(4,test.getIncidentSatisfaction(3));
		assertEquals(5,test.getIncidentSatisfaction(5));
		assertEquals(0,test.getIncidentSatisfaction(99));
	}

}
