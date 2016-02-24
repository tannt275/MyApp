package tannt275.reuseactionbrain.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.Log;

public class ActivityTest extends AppCompatActivity {

    private EditText edt;
    TextView time;
    Button start,stop;
    CountAsynTask countAsynTask;
    int timeSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        time = (TextView) findViewById(R.id.testTime);
        stop = (Button) findViewById(R.id.stopTime);
        start = (Button) findViewById(R.id.startTime);
        edt = (EditText) findViewById(R.id.enterTime);
        stop.setEnabled(false);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("TestActivity", "afterTextChanged");
                start.setEnabled(!TextUtils.isEmpty(edt.getText().toString()));
                timeSet = Integer.parseInt(edt.getText().toString());
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setEnabled(false);
                countAsynTask = new CountAsynTask(timeSet);
                countAsynTask.execute();
                stop.setEnabled(true);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(false);
                countAsynTask.cancel(true);
                countAsynTask = null;
                start.setEnabled(true);
            }
        });

    }


    private class CountAsynTask extends AsyncTask<String, String, String> {

        private String resp;
        private int startTime;

        public CountAsynTask(int startTime) {
            this.startTime = startTime;
        }

        @Override
        protected String doInBackground(String... params) {
            if (startTime == 0){
                for (int i = 0; i < 5; i ++){
                    try {
                        Thread.sleep(1000);
                        resp = "Slept for " + String.valueOf(i+1) + " milliseconds";
                        publishProgress(resp);

                        if(isCancelled())
                            break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        resp = e.getMessage();
                    }
                }
            } else {
                for (int i = startTime; i >= 0; i --){
                    try {
                        Thread.sleep(1000);
                        resp = "Slept for " + String.valueOf(i+1) + " milliseconds";
                        publishProgress(resp);

                        if(isCancelled())
                            break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        resp = e.getMessage();
                    }
                }
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("TestActivity", "onPostExecute");
//            time.setText(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.e("TestActivity", "onProgressUpdate");
            time.setText(values[0]);
        }

    }
}
