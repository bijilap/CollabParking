package edu.usc.bphilip.collabparking;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.usc.bphilip.api.AddNewParking;


public class EditParkingLot extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parking_lot);

        Button submitButton = (Button) findViewById(R.id.submitFormButton);
        submitButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Bundle b = getIntent().getExtras();
                    String type = b.getString("code");
                    if (type.equals("add")) {
                        String location = b.getString("location");
                        EditText et = (EditText) findViewById(R.id.formNameValue);
                        String name = et.getText().toString();
                        et = (EditText) findViewById(R.id.formPricePerDayValue);
                        String pricePerDay = et.getText().toString();
                        et = (EditText) findViewById(R.id.formPricePerHourValue);
                        String pricePerHour = et.getText().toString();
                        et = (EditText) findViewById(R.id.formCapacityValue);
                        String capacity = et.getText().toString();
                        Log.d("Form", location+" "+name+" "+pricePerDay+" "+pricePerHour+" "+capacity);
                        AddNewParking anp = new AddNewParking();
                        anp.execute("http://54.69.152.156:55321/csp/data/parking/report", "add", name, pricePerDay, pricePerHour, capacity, location);
                    }
                    finish();
                } catch (Exception e) {
                    Log.d("Form", "Something went wrong");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_parking_lot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
