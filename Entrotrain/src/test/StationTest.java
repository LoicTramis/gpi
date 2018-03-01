package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import core.GenerateStation;
import core.LineBuilder;
import core.Station;

public class StationTest {
	static LineBuilder buildtest;
	
	@BeforeClass
	public  static void BuildLinetest(){
		buildtest = new LineBuilder();
		buildtest.buildLine(900,300);
	}
	@Test
	public void test() {
		GenerateStation test = new GenerateStation();
		ArrayList<Station> testconf = new ArrayList<Station>();
		testconf = GenerateStation.configfilestation(buildtest.getBuiltLine());
		if(testconf.isEmpty())
			fail("GenerateStationfail");
	}

}
