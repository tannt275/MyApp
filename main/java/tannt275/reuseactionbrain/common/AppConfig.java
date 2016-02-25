package tannt275.reuseactionbrain.common;

import android.content.Context;

import java.util.List;

import tannt275.reuseactionbrain.database.DataBaseHandle;
import tannt275.reuseactionbrain.model.GameModel;
import tannt275.reuseactionbrain.model.MLeaderBoard;

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

    public static boolean IS_DEV_MODE = true;
    public static String FIRST_USE = "FIRST_USE";

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

    public static String parseToSecond(long d){
        String str = "";
        if (d < 0)
            str = "";
        else {
            long s = d /1000;
            long mls = d - s*1000;
            str = String.valueOf(s) + "," + String.valueOf(mls);
        }
        return str;
    }

    public static boolean isLeaderBoard(GameModel gameModel, Context context) {
        DataBaseHandle dataBaseHandle = new DataBaseHandle(context);
        List<MLeaderBoard> mLeaderBoardList = dataBaseHandle.getAllLeaderBoardWithType(gameModel.get_mode());
        if (mLeaderBoardList.size() < 9 && gameModel.get_score() > AppConfig.CONDITION_BE_LEADERBOARD)
            return true;
        else {
            for (MLeaderBoard mLeaderBoard : mLeaderBoardList) {
                Log.d(TAG, "leader board: "+ mLeaderBoard.convertToString());
                if (mLeaderBoard.get_score() < gameModel.get_score()) {
                    return true;
                }
            }
        }
        return false;
    }

}
