package cs.ecl500.heartmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener{
	private Button register_user;
	EditText n,a,p,en,ea,ep;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerform);
		
		register_user = (Button)findViewById(R.id.registerB);
		n = (EditText)findViewById(R.id.username);
		a = (EditText)findViewById(R.id.useraddress);
		p = (EditText)findViewById(R.id.userphone);
		en = (EditText)findViewById(R.id.econtact);
		ea = (EditText)findViewById(R.id.eaddress);
		ep = (EditText)findViewById(R.id.ephone);
		
		register_user.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		DataBaseControl entry = new DataBaseControl(Register.this);
		Intent i;
		switch(arg0.getId()){
		case R.id.registerB:
			String name,add,number,ename,eadd,enumber;
			name = n.getText().toString();
			add = a.getText().toString();
			number = p.getText().toString();
			ename = en.getText().toString();
			eadd = ea.getText().toString();
			enumber = ep.getText().toString();
			//Toast.makeText(this, "name:"+name+"*", Toast.LENGTH_LONG).show();
			if(n.length()!=0 && a.length()!=0 && p.length()!=0 &&
			   en.length()!=0 && ea.length()!=0 && ep.length()!=0 ){
				entry.Open();
				entry.registerNewUser(name,add,number,ename,eadd,enumber);
				entry.close();
				Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();
				i = new Intent(this,Search.class);
				i.putExtra("name", name);
				startActivity(i);
			}else{
				Toast.makeText(this, "Fill in all fields", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
	
}
