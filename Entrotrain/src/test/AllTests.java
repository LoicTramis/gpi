package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IncidentTest.class, LineTest.class,
		TrainTest.class ,StationTest.class})
public class AllTests {

}
