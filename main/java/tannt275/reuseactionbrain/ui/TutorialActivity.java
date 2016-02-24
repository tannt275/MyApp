package tannt275.reuseactionbrain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.Log;
import tannt275.reuseactionbrain.ui.fragment.tutorial.FinishFragment;
import tannt275.reuseactionbrain.ui.fragment.tutorial.FirstFragment;
import tannt275.reuseactionbrain.ui.fragment.tutorial.SecondFragment;
import tannt275.reuseactionbrain.ui.fragment.tutorial.StartFragment;

public class TutorialActivity extends AppCompatActivity {

    public static String TAG = TutorialActivity.class.getSimpleName();

    private Button bottomBtn;
    private static int current_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        bottomBtn = (Button) findViewById(R.id.tutorial_button);

        StartFragment startFragment = new StartFragment();
        displayFragment(startFragment);

        bottomBtn.setText("Start Tutorial");
        bottomBtn.setOnClickListener(buttonClickListener);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            current_position++;
            Log.e(TAG, "current position: " + current_position);
            bottomBtn.setEnabled(false);
            showFragmentWithPosition(current_position);
        }
    };

    private void showFragmentWithPosition(int current_position) {
        if (current_position == 1) {
            bottomBtn.setEnabled(true);
            bottomBtn.setText("Next");
            FirstFragment firstFragment = new FirstFragment();
            displayFragment(firstFragment);

        } else if (current_position == 2) {
            bottomBtn.setEnabled(true);
            bottomBtn.setText("Next");
            SecondFragment secondFragment = new SecondFragment();
            displayFragment(secondFragment);

        } else if (current_position == 3) {
            FinishFragment finishFragment = new FinishFragment();
            displayFragment(finishFragment);
            bottomBtn.setText("Let's Start");
            bottomBtn.setEnabled(true);
        } else if (current_position == 4) {
            navigateToMain();
        } else {
            Log.e(TAG, "out size...");
        }
    }

    private void navigateToMain() {
        Intent toMain = new Intent(TutorialActivity.this, MainActivity.class);
        startActivity(toMain);
        finish();
    }

    private void displayFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tutorial_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
