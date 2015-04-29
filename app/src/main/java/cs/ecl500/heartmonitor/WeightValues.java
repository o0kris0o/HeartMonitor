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

public class WeightValues extends Activity implements OnClickListener{
Button saveWeight;
Date dt;
EditText e1;
String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weightinput);
		
		saveWeight = (Button)findViewById(R.id.saveweight);
		saveWeight.setOnClickListener(this);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
		e1 = (EditText)findViewById(R.id.weightvaluelb);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		DataBaseControl entry = new DataBaseControl(WeightValues.this);
		int w;
		switch(arg0.getId()){
		case R.id.saveweight:
			dt = new Date();
			w = Integer.parseInt(e1.getText().toString());
			entry.Open();
			//Toast.makeText(this, "Name:"+name, Toast.LENGTH_LONG).show();
			entry.saveWeightInfo(name,w,dt);
			entry.close();
			Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();
			break;
		}
	}

}
