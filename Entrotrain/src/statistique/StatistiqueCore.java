package statistique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset; 


public class StatistiqueCore {
	
	private String title;
	
	public StatistiqueCore(String title) {
		this.title = title;		
	}
	
	//diagrame circulaire 
	/**
	 *this function allows to display a pie chart, it returns a panel and needs a hashmap that contains 
	 *a String (the key) the title and the value an Integer
	 * @param stat
	 * @return
	 */
	public JPanel generatePieChart3D(HashMap<String,Integer> stat){
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Entry<String, Integer> e : stat.entrySet()){
		   dataset.setValue(e.getKey(), e.getValue());
		}
		
		JFreeChart chart = ChartFactory.createPieChart(this.title, dataset, true, true, false);
		
		final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
	    plot.setStartAngle( 270 );             
	    plot.setForegroundAlpha( 0.60f );             
	    plot.setInteriorGap( 0.02 );             
	    
	    ChartPanel panel = new ChartPanel(chart); 
	    return panel;		
	}
	//diagrame en baton
	/**
	 *this function allows to display a pie chart, it returns a panel and needs a ArrayList that contains 
	 *a String , one ArrayList that contains the values and a string for X name's and Y name's
	 * @param stat
	 * @return
	 */
	public JPanel barChart3D( HashMap<String,Integer> stat , String columsName , String xName , String yName ) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for (Entry<String, Integer> e : stat.entrySet()){
			   dataset.addValue(e.getValue(), e.getKey() ,e.getKey());
		}
		
		JFreeChart barChart = ChartFactory.createBarChart3D(this.title,xName,yName,dataset,PlotOrientation.VERTICAL,false,true,false);
		ChartPanel panel = new ChartPanel(barChart);
		
		return panel;
	}
	
	

	
}
