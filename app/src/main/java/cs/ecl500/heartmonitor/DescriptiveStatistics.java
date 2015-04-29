package cs.ecl500.heartmonitor;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptiveStatistics extends Activity {
String name;
DataBaseControl entry;


	Integer[] SP;// = new Integer[] { 100, 200, 195, 180, 130, 50, 50 };
	Integer[] DP; //   = new Integer[] { 44, 50, 38, 96, 42, 47, 40, 39, 46, 50 };
	Integer[] HR; //  = new Integer[] { 120, 100, 110, 100, 150, 130, 130, 130, 130,130 };
	Integer[] Weight; //  = new Integer[] { 90, 100, 110, 120, 130, 140, 150, 160,170, 180 };

	Button button1;
	Button button2;
	Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_descriptive_statistics);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
		entry = new DataBaseControl(DescriptiveStatistics.this);
		
		entry.Open();
		SP = entry.getSP(name);
		DP = entry.getDP(name);
		HR = entry.getHR(name);
		Weight = entry.getWeight(name);
		entry.close();
		 
		final TextView txtSPMEAN = (TextView) findViewById(R.id.textView1);
		final TextView txtDPMEAN = (TextView) findViewById(R.id.textView9);
		final TextView txtHRMEAN = (TextView) findViewById(R.id.textView17);
		final TextView txtWMEAN = (TextView) findViewById(R.id.TextView99);

		final TextView txtSPMIN = (TextView) findViewById(R.id.textView2);
		final TextView txtDPMIN = (TextView) findViewById(R.id.textView10);
		final TextView txtHRMIN = (TextView) findViewById(R.id.textView18);
		final TextView txtWMIN = (TextView) findViewById(R.id.TextView97);

		final TextView txtSPMAX = (TextView) findViewById(R.id.textView3);
		final TextView txtDPMAX = (TextView) findViewById(R.id.textView11);
		final TextView txtHRMAX = (TextView) findViewById(R.id.textView19);
		final TextView txtWMAX = (TextView) findViewById(R.id.TextView98);

		final TextView txtSPMID = (TextView) findViewById(R.id.textView4);
		final TextView txtDPMID = (TextView) findViewById(R.id.textView12);
		final TextView txtHRMID = (TextView) findViewById(R.id.textView20);
		final TextView txtWMID = (TextView) findViewById(R.id.TextView100);

		final TextView txtSPMODE = (TextView) findViewById(R.id.textView5);
		final TextView txtDPMODE = (TextView) findViewById(R.id.textView13);
		final TextView txtHRMODE = (TextView) findViewById(R.id.textView21);
		final TextView txtWMODE = (TextView) findViewById(R.id.TextView101);

		final TextView txtSPRANGE = (TextView) findViewById(R.id.textView6);
		final TextView txtDPRANGE = (TextView) findViewById(R.id.textView14);
		final TextView txtHRRANGE = (TextView) findViewById(R.id.textView22);
		final TextView txtWRANGE = (TextView) findViewById(R.id.TextView102);

		final TextView txtSPVARIANCE = (TextView) findViewById(R.id.textView7);
		final TextView txtDPVARIANCE = (TextView) findViewById(R.id.textView15);
		final TextView txtHRVARIANCE = (TextView) findViewById(R.id.textView23);
		final TextView txtWVARIANCE = (TextView) findViewById(R.id.TextView103);

		final TextView txtSPSD = (TextView) findViewById(R.id.textView8);
		final TextView txtDPSD = (TextView) findViewById(R.id.textView16);
		final TextView txtHRSD = (TextView) findViewById(R.id.textView24);
		final TextView txtWSD = (TextView) findViewById(R.id.TextView104);

		if (txtSPVARIANCE != null)
			txtSPVARIANCE.setText(Double.toString(findVariance(SP)));
		if (txtDPVARIANCE != null)
			txtDPVARIANCE.setText(Double.toString(findVariance(DP)));
		if (txtHRVARIANCE != null)
			txtHRVARIANCE.setText(Double.toString(findVariance(HR)));
		if (txtWVARIANCE != null)
			txtWVARIANCE.setText(Double.toString(findVariance(Weight)));

		if (txtSPSD != null)
			txtSPSD.setText(Integer.toString(findStandardDeviation(SP)));
		if (txtDPSD != null)
			txtDPSD.setText(Integer.toString(findStandardDeviation(DP)));
		if (txtHRSD != null)
			txtHRSD.setText(Integer.toString(findStandardDeviation(HR)));
		if (txtWSD != null)
			txtWSD.setText(Double.toString(findStandardDeviation(Weight)));

		if (txtSPMEAN != null)
			txtSPMEAN.setText(Double.toString(getMean(SP)));
		if (txtDPMEAN != null)
			txtDPMEAN.setText(Double.toString(getMean(DP)));
		if (txtHRMEAN != null)
			txtHRMEAN.setText(Double.toString(getMean(HR)));
		if (txtWMEAN != null)
			txtWMEAN.setText(Double.toString(getMean(Weight)));

		if (txtSPMIN != null)
			txtSPMIN.setText(Integer.toString(FindMin(SP)));
		if (txtDPMIN != null)
			txtDPMIN.setText(Integer.toString(FindMin(DP)));
		if (txtHRMIN != null)
			txtHRMIN.setText(Integer.toString(FindMin(HR)));
		if (txtWMIN != null)
			txtWMIN.setText(Integer.toString(FindMin(Weight)));

		if (txtSPMAX != null)
			txtSPMAX.setText(Integer.toString(FindMax(SP)));
		if (txtDPMAX != null)
			txtDPMAX.setText(Integer.toString(FindMax(DP)));
		if (txtHRMAX != null)
			txtHRMAX.setText(Integer.toString(FindMax(HR)));
		if (txtWMAX != null)
			txtWMAX.setText(Integer.toString(FindMax(Weight)));

		if (txtSPMID != null)
			txtSPMID.setText(Double.toString(FindMid(SP)));
		if (txtDPMID != null)
			txtDPMID.setText(Double.toString(FindMid(DP)));
		if (txtHRMID != null)
			txtHRMID.setText(Double.toString(FindMid(HR)));
		if (txtWMID != null)
			txtWMID.setText(Double.toString(FindMid(Weight)));

		if (txtSPMODE != null)
			txtSPMODE.setText(Integer.toString(findMode(SP)));
		if (txtDPMODE != null)
			txtDPMODE.setText(Integer.toString(findMode(DP)));
		if (txtHRMODE != null)
			txtHRMODE.setText(Integer.toString(findMode(HR)));
		if (txtWMODE != null)
			txtWMODE.setText(Integer.toString(findMode(Weight)));

		if (txtSPRANGE != null)
			txtSPRANGE.setText(Integer.toString(findRange(SP)));
		if (txtDPRANGE != null)
			txtDPRANGE.setText(Integer.toString(findRange(DP)));
		if (txtHRRANGE != null)
			txtHRRANGE.setText(Integer.toString(findRange(HR)));
		if (txtWRANGE != null)
			txtWRANGE.setText(Integer.toString(findRange(Weight)));

	}

	public Double getMean(Integer[] A) {

		double total = 0;
		for (int i = 0; i < A.length; i++) {
			total = total + A[i];
		}

		DecimalFormat df = new DecimalFormat("#.##");
		double mean = total / A.length;

		Double dd = Double.parseDouble(df.format(mean));

		return dd;

	}

	public Integer FindMin(Integer[] A) {

		int smallest = A[0];

		for (int i = 0; i < A.length; i++) {

			if (A[i] < smallest) {
				smallest = A[i];
			}
		}
		return smallest;
	}

	public Integer FindMax(Integer[] A) {

		int largest = A[0];

		for (int i = 0; i < A.length; i++) {

			if (A[i] > largest) {
				largest = A[i];
			}
		}

		return largest;
	}

	public double FindMid(Integer[] A) {

		Integer[] B = new Integer[A.length];

		Double mid;
		for (int i = 0; i < A.length; i++) {
			B[i] = A[i];
		}

		Arrays.sort(B, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return x - y;
			}
		});

		if (B.length % 2 == 0) {
			int val = B.length / 2;
			Integer[] even = new Integer[] { B[val], B[val + 1] };
			mid = getMean(even);
		} else {
			int midvalue = (B.length + 1) / 2;
			mid = (double) B[midvalue - 1];

		}

		return mid;
	}

	public Integer findMode(Integer[] A) {
		Integer[] Count = new Integer[A.length];

		int value;
		int counter = 0;
		for (int i = 0; i < A.length; i++) {

			value = A[i];

			for (int x = 0; x < A.length; x++) {

				if (value == A[x])
					counter++;

			}

			Count[i] = counter;
			counter = 0;
		}

		int pos = 0;
		for (int i = 0; i < Count.length; i++) {
			if (Count[i] > pos) {
				pos = i;
			}
		}

		return A[pos];
	}

	public Integer findRange(Integer[] A) {

		int a = FindMin(A);
		int b = FindMax(A);

		int diff = b - a;

		return diff;

	}

	public double findVariance(Integer[] A) {

		double Mean = getMean(A);
		double diff = 0.0;
		double variance = 0.0;
		double Calc[] = new double[A.length];

		for (int i = 0; i < A.length; i++) {
			diff = A[i] - Mean;
			Calc[i] = diff * diff;
		}

		for (int i = 0; i < Calc.length; i++) {
			variance += Calc[i];
		}

		DecimalFormat df = new DecimalFormat("#.##");

		return (Double.parseDouble(df.format(variance / (A.length - 1)))

		);

	}

	public int findStandardDeviation(Integer[] A) {

		double val = findVariance(A);

		return (int) Math.sqrt(val);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_descriptive_statistics, menu);
		return true;
	}

	public void onActivityCreated(Bundle savedInstanceState) {

	}
}
