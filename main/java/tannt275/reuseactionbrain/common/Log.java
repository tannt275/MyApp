package tannt275.reuseactionbrain.common;

/**
 * Created by tannt on 2/24/2016.
 */
public class Log {

    public static boolean DEBUG = AppConfig.IS_DEV_MODE;

    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.e(tag, msg + "");
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            android.util.Log.d(tag, msg + "");
        }
    }
}
