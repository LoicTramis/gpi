package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Cantontest.class, IncidentTest.class, LineTest.class,
		TrainTest.class })
public class AllTests {

}
