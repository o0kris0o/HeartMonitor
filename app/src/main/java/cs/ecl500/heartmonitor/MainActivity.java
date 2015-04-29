package cs.ecl500.heartmonitor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private ImageButton s,r;
	EditText userName;
	DataBaseControl entry;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		userName = (EditText)findViewById(R.id.findMe);
		s = (ImageButton)findViewById(R.id.searchB);
		r = (ImageButton)findViewById(R.id.registerB);
		
		s.setOnClickListener(this);
		r.setOnClickListener(this);
		entry = new DataBaseControl(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View button) {
		// TODO Auto-generated method stub
		Intent i;
		DataBaseControl entry = new DataBaseControl(MainActivity.this);
		boolean flag = false;
		switch(button.getId()){
		case R.id.searchB:
			entry.Open();
			flag = entry.findUser(userName.getText().toString());
			entry.close();
			if(flag == false){
				Toast.makeText(this, "PLEASE REGISTER", Toast.LENGTH_LONG).show();
			}else{
				i = new Intent(this,Search.class);
				i.putExtra("name", userName.getText().toString());
				startActivity(i);
			}
			break;
		case R.id.registerB:
			i = new Intent(this,Register.class);
			startActivity(i);
			break;
		}
	}

}
