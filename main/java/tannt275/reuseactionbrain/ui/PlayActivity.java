package tannt275.reuseactionbrain.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.ui.fragment.GameFragment;

public class PlayActivity extends AppCompatActivity {


    private AdView adViewTop;
    private AdRequest adRequestTop;
    private AdView adViewBottom;
    private AdRequest adRequestBottom;

    private int _modeGet;
    private int _timeSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            _modeGet = bundle.getInt(AppConfig.MODE_IN_BUNDLE);
            _timeSet = bundle.getInt(AppConfig.TIME_SET);
        }

        adRequestTop = new AdRequest.Builder().build();
        adViewTop = (AdView) findViewById(R.id.play_adview_top);
        adViewTop.loadAd(adRequestTop);

        adRequestBottom = new AdRequest.Builder().build();
        adViewBottom = (AdView) findViewById(R.id.play_adview_bottom);
        adViewBottom.loadAd(adRequestBottom);

        GameFragment g = new GameFragment();
        displayFragment(g);
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.play_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
