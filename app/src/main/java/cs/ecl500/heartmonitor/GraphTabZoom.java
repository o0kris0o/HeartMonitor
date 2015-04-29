package cs.ecl500.heartmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GraphTabZoom extends Activity{
String name;
Graph line;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
		//Intent linegraph = new Graph(name,this).getIntent(this);
		//startActivity(linegraph);
	}

}
