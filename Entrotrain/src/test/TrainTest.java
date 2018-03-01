package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import core.Canton;
import core.LineBuilder;
import core.Passenger;
import core.Station;
import core.Train;
import exception.TerminusException;

public class TrainTest {
	static LineBuilder buildtest;
	
	@Test
	public void test() {
		
		ArrayList<Passenger> arraypassenger = new ArrayList<Passenger>();
		Passenger passtest = new Passenger(0, null, 0, 0, 0);
		for(int index = 0; index < 20 ; index++)
			arraypassenger.add(passtest);
		ArrayList<Passenger> arraypassenger2 = new ArrayList<Passenger>();
		for(int index = 0; index < 10 ; index++)
			arraypassenger2.add(passtest);
		Station station1 = new Station(20,0,null,0,arraypassenger);
		Station station2 = new Station(10,0,null,0,arraypassenger2);
		Train traintest1;
		Canton canton1 = new Canton(0, 0, 0);
		Canton canton2 = new Canton(0, 0, 0);
		traintest1 = new Train(null,canton1,station1,0,0,10);
		traintest1.trainBoarding(station1);
		assertEquals(traintest1.getMaxPassenger(),traintest1.getTrainPassengers().size()); 
		Train traintest2 = new Train(null,canton2,station2,0,0,30);
		traintest2.trainBoarding(station1);
		assertEquals(10,traintest2.getTrainPassengers().size());
		traintest2.trainBoarding(station2);
		assertEquals(20,traintest2.getTrainPassengers().size());
	}
}
