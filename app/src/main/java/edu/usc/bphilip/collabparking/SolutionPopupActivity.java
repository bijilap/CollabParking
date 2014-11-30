package edu.usc.bphilip.collabparking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SolutionPopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_popup);

        TextView tv=new TextView(this);
        tv = (TextView)findViewById(R.id.questionOfSolText);
        try{
            Bundle b = getIntent().getExtras();
            tv.setText(b.getString("question"));
            tv = (TextView)findViewById(R.id.yesCount);
            tv.setText("Yes:  "+b.getString("yes")+"%");
            tv = (TextView)findViewById(R.id.noCount);
            tv.setText("No:  "+b.getString("no")+"%");
        }
        catch(Exception e){
            tv.setText("My Question");
        }

        Button correctButton = (Button) findViewById(R.id.correctButton1);
        correctButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
        Button wrongButton = (Button) findViewById(R.id.wrongButton1);
        wrongButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solution_popup, menu);
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
