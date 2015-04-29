package cs.ecl500.heartmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Search extends Activity implements OnClickListener{
TextView userWelcome;
ImageButton blood,weight, physical;
String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresult);
		
		blood = (ImageButton)findViewById(R.id.bp);
		weight = (ImageButton)findViewById(R.id.weight);
		physical = (ImageButton)findViewById(R.id.excercise);
		userWelcome = (TextView)findViewById(R.id.nameOfUser);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		userWelcome.setText("Welcome " + name);
		
		blood.setOnClickListener(this);
		weight.setOnClickListener(this);
		physical.setOnClickListener(this);
	}	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i;
		switch(arg0.getId()){
		case R.id.bp:
			i = new Intent(this,InputBP.class);
			i.putExtra("name", name);
			startActivity(i);
			break;
		case R.id.weight:
			i = new Intent(this,WeightValues.class);
			i.putExtra("name", name);
			startActivity(i);
			break;
		case R.id.excercise:
			i = new Intent(this,WeightInput.class);
			i.putExtra("name", name);
			startActivity(i);
			break;
		}
	}

}
