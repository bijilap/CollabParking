package edu.usc.bphilip.collabparking;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.usc.bphilip.api.AskQuestion;


public class MapMarkerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_marker);

        try{
            Bundle b = getIntent().getExtras();
            TextView tv = new TextView(this);
            tv = (TextView)findViewById(R.id.capacity_value);
            tv.setText(b.getString("capacity"));
            tv = (TextView)findViewById(R.id.price_per_day_value);
            tv.setText(b.getString("pricePerDay"));
            tv = (TextView)findViewById(R.id.price_per_hour_value);
            tv.setText(b.getString("pricePerHour"));
            Log.d("Marker", b.toString());
            this.setTitle(b.getString("name"));

            final String parkingLotId = b.getString("id");
            final String csp_server = ((MainApplication) getApplication()).CSP_SERVER2;
            final String userId = ((MainApplication) getApplication()).me.id;
            final String lotName = b.getString("name");
            Button enquireButton = (Button) findViewById(R.id.enquireButton);
            enquireButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = csp_server+"/"+userId+"/ask/1/"+parkingLotId;
                    Log.d("BroadCast-Q", url);
                    AskQuestion asq = new AskQuestion();
                    asq.execute(url, lotName);
                    finish();
                }
            });
        }
        catch(Exception e){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_marker, menu);
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
