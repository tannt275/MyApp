package tannt275.reuseactionbrain.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.awt.font.TextAttribute;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.Log;
import tannt275.reuseactionbrain.ui.fragment.tutorial.FinishFragment;
import tannt275.reuseactionbrain.ui.fragment.tutorial.FirstFragment;
import tannt275.reuseactionbrain.ui.fragment.tutorial.SecondFragment;

public class TutorialActivity extends AppCompatActivity {

    public static String TAG = TutorialActivity.class.getSimpleName();

    private Button bottomBtn;
    private static int current_position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        bottomBtn = (Button) findViewById(R.id.tutorial_button);

        FirstFragment firstFragment = new FirstFragment();
        displayFragment(firstFragment);
        bottomBtn.setOnClickListener(buttonClickListener);

    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            current_position ++;
            Log.e(TAG, "current position: "+ current_position);
            bottomBtn.setEnabled(false);
            showFragmentWithPosition(current_position);
        }
    };

    private void showFragmentWithPosition(int current_position) {
        if (current_position == 1){
            bottomBtn.setEnabled(true);
            SecondFragment secondFragment = new SecondFragment();
            displayFragment(secondFragment);
        } else if (current_position == 2){
            bottomBtn.setEnabled(true);
            FinishFragment finishFragment = new FinishFragment();
            displayFragment(finishFragment);
            bottomBtn.setText("Let's Start");
        } else {
            Log.e(TAG, "out size");
        }
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tutorial_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
