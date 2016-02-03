package tannt275.reuseactionbrain.common;

/**
 * Created by tannt on 2/3/2016.
 */
public class AppConfig {

    public static String TAG = AppConfig.class.getSimpleName();

    public static int TIME_SET_MAX = 100;
    public static String MODE_IN_BUNDLE = "MODE_IN_BUNDLE";
    public static String TIME_SET = "TIME_SET";
    public static int MODE_TIME = 999;
    public static int MODE_NORMAL = 998;

    public static String MODE_TIME_TITLE = "Time Mode";
    public static String MODE_NORMAL_TIME = "Normal Mode";

    public static String parseIntToString (int d){
        String str ;
        if ( d < 0 )
            return "";
        else {
            int s = d / 1000;
            int mls = d - s * 1000;
            str = String.format( "%1$s : %2$s", s, mls);
            return str;
        }
    }
}
