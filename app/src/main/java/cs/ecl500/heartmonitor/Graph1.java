package cs.ecl500.heartmonitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.net.ParseException;
import android.util.Log;

public class Graph1 extends AbstractDemoChart{
	DataBaseControl entry;
	public String name;
	public Graph1(String name, InputBP inputBP) {
		// TODO Auto-generated constructor stub
		this.name = name;
		 entry = new DataBaseControl(inputBP);
	}
	

	public String getName() {
	    return "Temperature and sunshine";
	  }

	
	

	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	@Override
	 public Intent execute(Context context) {
	    String[] titles = new String[] { "Systolic Pressure", "Diastolic Pressure", "Heart Rate", "Weight"};
	      List<Date[]> dates = new ArrayList<Date[]>();
	      List<double[]> values = new ArrayList<double[]>();
	        
	      
	      
	      
	       SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
	      entry.Open();
	        String[] datesimport = entry.getDates(name);
	        Integer[] SPVALUES = entry.getSP(name);
	        Integer[]  DPVALUES = entry.getDP(name);
	        Integer[] HRVALUES = entry.getHR(name);
	        Integer[] WEIGHTVALUES = entry.getWeight(name);
	      entry.close();
	        Date[] DDATES = new Date[datesimport.length];
	        double[] SPData = new double[SPVALUES.length];
	        double[] DPData = new double[DPVALUES.length];
	        double[] HRData = new double[HRVALUES.length];
	        double[] WEIGHTData = new double[WEIGHTVALUES.length];
	                
	              
	        //Convert TO DOUBLE
	        for (int i = 0; i < SPVALUES.length; i++){
	         SPData[i] = (double)SPVALUES[i];       
	        }      
	        for (int i = 0; i < DPVALUES.length; i++){
	         DPData[i] = (double)DPVALUES[i];       
	        }
	        for (int i = 0; i < HRVALUES.length; i++){
	         HRData[i] = (double)HRVALUES[i];       
	        }
	        for (int i = 0; i < WEIGHTVALUES.length; i++){
	         WEIGHTData[i] = (double)WEIGHTVALUES[i];       
	        }
	      
	       
	       for (int i =0; i< datesimport.length; i++){        
	        try {
				//DDATES[i] = df.parse(datesimport[i]);
				DDATES[i] = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(datesimport[i]);
				Log.v("ERROR","WORKED!!!!!!"+DDATES[i].toString());
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				Log.v("ERROR","CATCHHHHHHH:" +i);
				e.printStackTrace();
			}        
	       }
	       
	      int length = titles.length;
	             
	    
	      for (int i = 0; i < length ; i++) {   
	       dates.add(new Date[DDATES.length]);  
	       Log.v("ERROR","CATCHHHHHHH 2nd for:" +i+"**"+DDATES.length);
	         for (int x = 0; x < DDATES.length; x++){          
	           dates.get(i)[x] = DDATES[x];     
	           Log.v("ERROR","CATCHHHHHHH 3nd for:" +x);
	         }
	      }      

	      
	      values.add(SPData);
	      values.add(DPData);
	      values.add(HRData);
	      values.add(WEIGHTData);
	      length = values.get(0).length;
	      int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};
	      PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,PointStyle.POINT, PointStyle.POINT,};
	      XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	      setChartSettings(renderer, "Project work status", "Date", "Tickets", dates.get(0)[0].getTime(),
	          dates.get(0)[11].getTime(), 50, 190, Color.GRAY, Color.LTGRAY);
	      renderer.setXLabels(5);

	      renderer.setYLabels(10);
	      length = renderer.getSeriesRendererCount();
	      for (int i = 0; i < length; i++) {
	        SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
	        seriesRenderer.setDisplayChartValues(true);
	      }
	      return ChartFactory.getTimeChartIntent(context, buildDateDataset(titles, dates, values),
	          renderer, "MM/dd/yyyy");

	 }


	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
