package tannt275.reuseactionbrain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.dialog.AppDialogs;
import tannt275.reuseactionbrain.model.GameModel;
import tannt275.reuseactionbrain.ui.fragment.GameFragment;

public class PlayActivity extends AppCompatActivity implements GameFragment.GameCallBack {
    public static String TAG = PlayActivity.class.getSimpleName();

    private AdView adViewTop;
    private AdRequest adRequestTop;
    private AdView adViewBottom;
    private AdRequest adRequestBottom;

    private int _modeSet;
    private int _timeSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            _modeSet = bundle.getInt(AppConfig.MODE_IN_BUNDLE);
            _timeSet = bundle.getInt(AppConfig.TIME_SET);
            Log.e(TAG, "mode game: " + _modeSet + " time set = " + _timeSet);
        }

        adRequestTop = new AdRequest.Builder().build();
        adViewTop = (AdView) findViewById(R.id.play_adview_top);
        adViewTop.loadAd(adRequestTop);

        adRequestBottom = new AdRequest.Builder().build();
        adViewBottom = (AdView) findViewById(R.id.play_adview_bottom);
        adViewBottom.loadAd(adRequestBottom);

        settingGameToPlay();
    }

    private void settingGameToPlay() {
        GameFragment gameFragment = GameFragment.newInstance(_modeSet, _timeSet);
        gameFragment.setGameCallBack(this);
        displayFragment(gameFragment);
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.play_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onHome() {
        Log.e(TAG, "onHome action");
        Intent toHomeActivity = new Intent(PlayActivity.this, MainActivity.class);
        startActivity(toHomeActivity);
    }

    @Override
    public void onShare(GameModel game) {
        Log.e(TAG, "onShare action");
//        AppDialogs.showShareImage(PlayActivity.this,game);
    }

    @Override
    public void onReplay() {
        Log.e(TAG, "onReplay action");
        settingGameToPlay();
    }
}
