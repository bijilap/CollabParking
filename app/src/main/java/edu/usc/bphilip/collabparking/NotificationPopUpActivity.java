package edu.usc.bphilip.collabparking;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import edu.usc.bphilip.api.AnswerQuestion;


public class NotificationPopUpActivity extends Activity {

    private boolean answer = false;
    private String questionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_pop_up);

        final String csp_server = ((MainApplication) getApplication()).CSP_SERVER2;
        final String userId = ((MainApplication) getApplication()).me.id;

        TextView tv=new TextView(this);
        tv = (TextView)findViewById(R.id.questionText);
        try{
            Bundle b = getIntent().getExtras();
            tv.setText(b.getString("question"));
            questionId = b.getString("question_id");
        }
        catch(Exception e){
            tv.setText("My Question");
        }

        Button submitButton = (Button) findViewById(R.id.submitButton1);
        submitButton.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = csp_server+"/"+userId+"/answer/"+questionId;
                AnswerQuestion ansq = new AnswerQuestion();
                String finalAns = "No";
                if(answer)
                    finalAns = "Yes";
                ansq.execute(url, finalAns);
                Log.d("Notification-debug", "Submit button");
                finish();

            }
        });
        Button ignoreButton = (Button) findViewById(R.id.ignoreButton1);
        ignoreButton.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("Notification-debug", "Ignore button");
                finish();
            }
        });
        Switch answerSwitch = (Switch) findViewById(R.id.answerSwitch1);
        answerSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                answer = isChecked;
                Log.d("Notification-debug", answer+"");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_pop_up, menu);
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
