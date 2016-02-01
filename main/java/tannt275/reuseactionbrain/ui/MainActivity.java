package tannt275.reuseactionbrain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tannt275.reuseactionbrain.R;

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
            Intent toHighScore =  new Intent(MainActivity.this, HighScoreActivity.class);
            toHighScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(toHighScore);
        }
    };
    private View.OnClickListener timedModeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // go to timed mode
        }
    };

    private View.OnClickListener normalModeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // go to normal mode
            Intent toPlayNormalMode = new Intent(MainActivity.this, PlayActivity.class);
            startActivity(toPlayNormalMode);
        }
    };

}
