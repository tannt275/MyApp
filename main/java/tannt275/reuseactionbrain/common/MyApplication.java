package tannt275.reuseactionbrain.common;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by tannt on 2/19/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
