package tannt275.reuseactionbrain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.main_highScore).setOnClickListener(highScoreListener);
        findViewById(R.id.main_timedMode).setOnClickListener(timedModeListener);
        findViewById(R.id.main_normalMode).setOnClickListener(normalModeListener);

    }

    private View.OnClickListener highScoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //show high score
            Intent toHighScore = new Intent(MainActivity.this, HighScoreActivity.class);
            toHighScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(toHighScore);
        }
    };
    private View.OnClickListener timedModeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // go to timed mode
//            toPlayWithMode(AppConfig.MODE_TIME, timeSet);
        }
    };

    private void toPlayWithMode(int mode, int timeSet) {
        Intent toPlayActivity = new Intent(MainActivity.this, PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(AppConfig.MODE_IN_BUNDLE, mode);
        bundle.putInt(AppConfig.TIME_SET, timeSet);
        toPlayActivity.putExtras(bundle);
        startActivity(toPlayActivity);
    }

    private View.OnClickListener normalModeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // go to normal mode
            toPlayWithMode(AppConfig.MODE_NORMAL, 0);
        }
    };

    private void dialogChooseTime(){

    }

}
