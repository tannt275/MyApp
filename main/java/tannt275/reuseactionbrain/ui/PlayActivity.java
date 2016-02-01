package tannt275.reuseactionbrain.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class PlayActivity extends AppCompatActivity {


    private AdView adViewTop;
    private AdRequest adRequestTop;
    private AdView adViewBottom;
    private AdRequest adRequestBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        adRequestTop = new AdRequest.Builder().build();
        adViewTop = (AdView) findViewById(R.id.play_adview_top);
        adViewTop.loadAd(adRequestTop);

        adRequestBottom = new AdRequest.Builder().build();
        adViewBottom = (AdView) findViewById(R.id.play_adview_bottom);
        adViewBottom.loadAd(adRequestBottom);

    }
}
