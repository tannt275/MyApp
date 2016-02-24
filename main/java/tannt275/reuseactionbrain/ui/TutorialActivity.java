package tannt275.reuseactionbrain.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.ui.fragment.tutorial.FirstFragment;
import tannt275.reuseactionbrain.ui.fragment.tutorial.SecondFragment;

public class TutorialActivity extends AppCompatActivity {

    private Button bottomBtn;
    private TextView position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        position = (TextView) findViewById(R.id.tutorial_position);
        bottomBtn = (Button) findViewById(R.id.tutorial_button);

        /*FirstFragment firstFragment = new FirstFragment();
        displayFragment(firstFragment);*/

        SecondFragment secondFragment = new SecondFragment();
        displayFragment(secondFragment);
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tutorial_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
