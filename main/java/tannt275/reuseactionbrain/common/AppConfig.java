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
    public static String MODE_NORMAL_TITLE = "Normal Mode";

    public static int CONDITION_BE_LEADERBOARD = 10;
    public static int SIZE_LEADERBOARD = 10;

    public static String parseTimeToString(long d) {
        String str;
        if (d < 0)
            return "";
        else {
            long s = d / 1000;
            long mls = d - s * 1000;
            String first = "";
            String second = "";
            if (s < 10)
                first = "00" + String.valueOf(s);
            else if (s < 100)
                first = "0" + String.valueOf(s);
            if (mls == 0)
                second = "000";
            else second = String.valueOf(mls);
                str = String.format("%1$s : %2$s", first, second);
            return str;
        }
    }

}
