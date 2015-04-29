package cs.ecl500.heartmonitor;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeightInput extends Activity implements OnClickListener{
Button e1,vgraph;
String name;
Date dt;
EditText e2,e3,e4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.physicalinput);
		
		//vgraph = (Button)findViewById(R.id.vegraph);
		e1 = (Button)findViewById(R.id.saveweightdata);
		e1.setOnClickListener(this);
		//vgraph.setOnClickListener(this);
		
		e2 = (EditText)findViewById(R.id.eType);
		e3 = (EditText)findViewById(R.id.eDuration);
		e4 = (EditText)findViewById(R.id.eIntensity);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		DataBaseControl entry = new DataBaseControl(WeightInput.this);
		int v1;
		switch(arg0.getId()){
		case R.id.saveweightdata:
			v1 = Integer.parseInt(e3.getText().toString());
			dt = new Date();
			entry.Open();
			entry.saveExerciseDate(name,dt,e2.getText().toString(),v1,e4.getText().toString());
			entry.close();
			Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();
			break;
		}
	}
	
}
