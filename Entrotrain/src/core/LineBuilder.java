package core;

import java.util.ArrayList;

import core.GenerateStation;
import core.Line;

/**
 * @author Karim
 *
 */
public class LineBuilder {
	private Line line;
	private int totalLength, cantonLength;
	
	/*public void buildLine(int totalLength, int cantonLength) {
		line = new Line(totalLength);
		int id = 1;
		while (!line.isFull()) {
			line.addCanton(id, cantonLength);
			id++;
		}
		line.getStations().addAll(GenerateStation.configfilestation(line));
	}*/
	public void buildLine(int totalLength, int cantonLength) {
		line = new Line(totalLength+100);
		int id = 1;
		for(int i=1; i<34; i++){
			line.addCanton(i, cantonLength);
		}
		//bifurcation au canton 9 a la ligne normal
		line.addCanton(100, cantonLength, 9);
		for(int i=101; i<128; i++){
			line.addCanton(i, cantonLength);
		}
		//bufurcation au caton 11 de la 1ligne
		line.addCanton(200, cantonLength, 111);
		for(int i=201; i<210; i++){
			line.addCanton(i, cantonLength);
		}
		this.totalLength=totalLength;
		this.cantonLength=cantonLength;
		line.getStations().addAll(GenerateStation.configfilestation(line));
	}
	
	public Line getBuiltLine() {
		return line;
	}
	
	public int getBifurcLength() {
		return totalLength-cantonLength;
	}
}
