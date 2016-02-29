package tannt275.reuseactionbrain.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.common.Log;
import tannt275.reuseactionbrain.model.GameModel;
import tannt275.reuseactionbrain.ui.fragment.GameFragment;
import tannt275.reuseactionbrain.widget.ShareInformation;

public class PlayActivity extends AppCompatActivity implements GameFragment.GameCallBack {
    public static String TAG = PlayActivity.class.getSimpleName();

    private AdView adViewTop;
    private AdRequest adRequestTop;
    private AdView adViewBottom;
    private AdRequest adRequestBottom;

    private int _modeSet;
    private int _timeSet;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
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

    private void displayFragment(Fragment fragment) {
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
//        shareOnFacebook(game);
        Bitmap bitmapAvatar = BitmapFactory.decodeResource(getResources(), R.mipmap.app_launcher);
        ShareInformation shareInformation = new ShareInformation(this);
        shareInformation.drawAvatar(bitmapAvatar);
        shareInformation.drawAppName();
        shareInformation.drawerContent(game);

        Bitmap inFoBitmap = shareInformation.getBitmap();

        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(inFoBitmap)
                .build();
        SharePhotoContent sharePhotoContent =  new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();
        if (shareDialog.canShow(SharePhotoContent.class)){
            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                @Override
                public void onSuccess(Sharer.Result result) {
                    Log.e(TAG, "onSuccess");
                }

                @Override
                public void onCancel() {
                    Log.e(TAG, "onCancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.e(TAG, "onError");
                }
            });
            shareDialog.show(sharePhotoContent);
        }

    }

    @Override
    public void onReplay() {
        Log.e(TAG, "onReplay action");
//        settingGameToPlay();
        GameFragment gameFragment = GameFragment.newInstance(_modeSet,_timeSet);
        gameFragment.setGameCallBack(this);
        displayFragment(gameFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
