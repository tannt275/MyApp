package tannt275.reuseactionbrain.ui;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.dialog.PopUpDialog;


public class MainActivity extends AppCompatActivity {

    private int timeSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.main_highScore).setOnClickListener(highScoreListener);
        findViewById(R.id.main_timedMode).setOnClickListener(timedModeListener);
        findViewById(R.id.main_normalMode).setOnClickListener(normalModeListener);
        findViewById(R.id.main_rateApp).setOnClickListener(rateAppListener);

    }

    private View.OnClickListener rateAppListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        }
    };

    private View.OnClickListener highScoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //show high score
           /* Intent toHighScore = new Intent(MainActivity.this, HighScoreActivity.class);
            toHighScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(toHighScore);*/
            PopUpDialog popUpDialog = new PopUpDialog();
            popUpDialog.show(getSupportFragmentManager(), "Share");
        }
    };
    private View.OnClickListener timedModeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // go to timed mode
            dialogChooseTime();
        }
    };

    private void toPlayWithMode(int mode, int time) {
        Intent toPlayActivity = new Intent(MainActivity.this, PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(AppConfig.MODE_IN_BUNDLE, mode);
        bundle.putInt(AppConfig.TIME_SET, time);
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

    private void dialogChooseTime() {

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int widthScreen = displayMetrics.widthPixels;

        View rootView = getLayoutInflater().inflate(R.layout.dialog_choose_time, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (0.8f * widthScreen), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(rootView, params);

        final TextInputLayout inputLayout = (TextInputLayout) dialog.findViewById(R.id.dialog_input_layout);
        final EditText enterTime = (EditText) dialog.findViewById(R.id.dialog_time_edt);
        Button acceptBtn = (Button) dialog.findViewById(R.id.dialog_acceptBtn);
        Button cancelBtn = (Button) dialog.findViewById(R.id.dialog_cancelBtn);

        enterTime.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                             }

                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {

                                             }

                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 String time = enterTime.getText().toString().trim();
                                                 if (TextUtils.isEmpty(time)) {
                                                     inputLayout.setErrorEnabled(true);
                                                     inputLayout.setError(getString(R.string.dialog_time_empty));
                                                     enterTime.requestFocus();
                                                 } else {
                                                     try {
                                                         timeSet = Integer.parseInt(time);
                                                         if (timeSet > AppConfig.TIME_SET_MAX) {
                                                             inputLayout.setErrorEnabled(true);
                                                             inputLayout.setError(getString(R.string.dialog_time_out_bound));
                                                             enterTime.setText("");
                                                             enterTime.requestFocus();
                                                         } else {
                                                             inputLayout.setErrorEnabled(false);
                                                         }
                                                     } catch (Exception e) {
                                                         e.printStackTrace();
                                                     }
                                                 }
                                             }
                                         }

        );
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPlayWithMode(AppConfig.MODE_TIME, timeSet);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
