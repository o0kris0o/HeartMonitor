package cs.ecl500.heartmonitor;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class InputBP extends TabActivity{
String name;
TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bpin);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
		tabHost=getTabHost();
		
		
		//First Tab
        TabSpec spec1=tabHost.newTabSpec("Tab 1").setIndicator("New");
        Intent in1=new Intent(this, ValuesBP.class);
        in1.putExtra("name", name);
        spec1.setContent(in1);
        
        TabSpec spec2=tabHost.newTabSpec("Tab 2").setIndicator("Analysis");
        Intent in2=new Intent(this,DescriptiveStatistics.class);
        in2.putExtra("name", name);
        spec2.setContent(in2);
        
        TabSpec spec3=tabHost.newTabSpec("Tab 3").setIndicator("Graph");
        Log.v("ERROR","after Graph1 new");
        Graph g1 = new Graph(name,this);
        Intent in3=g1.getIntent(this);
        //Intent in3=new Intent(this,GraphTabZoom.class);
        //in3.putExtra("name", name);
        spec3.setContent(in3);
        
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        
        //tabHost.setOnClickListener(this);
        
        
	}
	

}
