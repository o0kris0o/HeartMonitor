package cs.ecl500.heartmonitor;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class BloodPressure extends TabActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bpin);
		
		TabHost tb = getTabHost();
		
		TabSpec spec1 = tb.newTabSpec("New");
		Intent i = new Intent(this,InputBP.class);
		spec1.setContent(i);
	}

}
