package cs.ecl500.heartmonitor;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class Graph {
	DataBaseControl entry;
	public String name;
	String[] datesimport;
	public Graph(String name, InputBP inputBP) {
		// TODO Auto-generated constructor stub
		this.name = name;
		entry = new DataBaseControl(inputBP);
	}

	public Intent getIntent(Context context){
		Integer[] x; 
		Integer[] y; 
		Integer[] z; 
		Integer[] k;
		Integer[] l; 
		entry.Open();
		if(entry.getSP(name) != null){
		datesimport = entry.getDates(name);
		x = new Integer[datesimport.length];
		for(int i = 0;i<x.length;i++){
			x[i] = i+1;
		}
		y = entry.getSP(name);// {12,32,63,74,35,46,57,68,29};
		z = entry.getDP(name);//{15,12,33,44,15,66,37,38,59};
		k = entry.getHR(name);//{5,12,3,34,25,66,37,38,19};
		l = entry.getWeight(name);//{5,5,5,30,35,45,55,40,25};
		}else{
			x = new Integer[]{1,2,3,4,5,6,7,8};
			y = new Integer[] {1};
			z = new Integer[] {1};
			k = new Integer[] {1};
			l = new Integer[] {1};
		}
		entry.close();
		TimeSeries series = new TimeSeries("Systolic");
		for(int i =0;i < y.length;i++){
			series.add(x[i],y[i]);
		}
		TimeSeries series2 = new TimeSeries("Diastolic");
		for(int i =0;i < z.length;i++){
			series2.add(x[i],z[i]);
		}
		TimeSeries series3 = new TimeSeries("HeartRate");
		for(int i =0;i < k.length;i++){
			series3.add(x[i],k[i]);
		}
		TimeSeries series4 = new TimeSeries("Weight");
		for(int i =0;i < l.length;i++){
			series4.add(x[i],l[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		dataset.addSeries(series4);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		//line one
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.WHITE);
		renderer.setPointStyle(PointStyle.SQUARE);
		//line two
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.YELLOW);
		renderer2.setPointStyle(PointStyle.SQUARE);
		//line three
		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		renderer3.setColor(Color.GREEN);
		renderer3.setPointStyle(PointStyle.SQUARE);
		//line four
		XYSeriesRenderer renderer4 = new XYSeriesRenderer();
		renderer4.setColor(Color.RED);
		renderer4.setPointStyle(PointStyle.SQUARE);
		
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		mRenderer.addSeriesRenderer(renderer3);
		mRenderer.addSeriesRenderer(renderer4);
		mRenderer.setChartTitle("BP/Weight");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setShowGrid(true);
		mRenderer.setGridColor(Color.GRAY);
		mRenderer.setXTitle("Time(Days)");
		//mRenderer.setYTitle("BP/Weight(lb)");
		mRenderer.setZoomButtonsVisible(true);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer,"Blood Pressure and Weight");
		
		return intent;
	}
}
