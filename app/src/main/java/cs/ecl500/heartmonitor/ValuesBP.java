package cs.ecl500.heartmonitor;
import java.util.Date;


import cs.ecl500.heartmonitor.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ValuesBP extends Activity implements OnClickListener{
Button b1;
Date dt;
EditText e1,e2,e3;
String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputbpvalues);
		
		b1 = (Button)findViewById(R.id.saveDatabp);
		e1 = (EditText)findViewById(R.id.svalue);
		e2 = (EditText)findViewById(R.id.dvalue);
		e3 = (EditText)findViewById(R.id.hrvalue);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
		b1.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		int v1,v2,v3;
		// TODO Auto-generated method stub
		DataBaseControl entry = new DataBaseControl(ValuesBP.this);
		switch(arg0.getId()){
		case R.id.saveDatabp:
			v1 = Integer.parseInt(e1.getText().toString());
			v2 = Integer.parseInt(e2.getText().toString());
			v3 = Integer.parseInt(e3.getText().toString());
			entry.Open();
			dt = new Date();
			entry.saveBPData(name,v1,v2,v3,dt);
			entry.close();
			Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();
			break;
		}
	}

}
