package tannt275.reuseactionbrain.common;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

/**
 * Created by tannt on 2/19/2016.
 */
public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
