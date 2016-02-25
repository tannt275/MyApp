package tannt275.reuseactionbrain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.facebook.appevents.AppEventsLogger;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.common.AppSharePref;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateActivity();
            }
        }, 2000);
    }

    public void navigateActivity() {

        if (AppSharePref.getBoolean(AppConfig.FIRST_USE, true)) {
            toTutorial();
        } else {
            toMainActivity();
        }
    }

    private void toMainActivity() {
        Intent toMain = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(toMain);
        finish();
    }

    private void toTutorial() {
        Intent toTutorial = new Intent(SplashActivity.this, TutorialActivity.class);
        startActivity(toTutorial);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

}
