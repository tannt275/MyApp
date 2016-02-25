package tannt275.reuseactionbrain.common;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by tannt on 2/25/2016.
 */
public class AppSharePref {

    public static SharedPreferences pref;

    public static SharedPreferences getPref() {
        if (pref == null)
            pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return pref;
    }

    public static boolean getBoolean(String key, boolean defaultValue){
        return getPref().getBoolean(key,defaultValue);
    }
    public static void putBoolean(String key, boolean value){
        SharedPreferences.Editor editor = getPref().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

}
