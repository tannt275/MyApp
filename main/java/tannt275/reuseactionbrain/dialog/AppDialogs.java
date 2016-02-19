package tannt275.reuseactionbrain.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.database.DataBaseHandle;
import tannt275.reuseactionbrain.model.DrawerView;
import tannt275.reuseactionbrain.model.GameModel;
import tannt275.reuseactionbrain.model.MLeaderBoard;

/**
 * Created by tannt on 2/3/2016.
 */
public class AppDialogs {

    public static String TAG = AppDialogs.class.getSimpleName();

    public static void showDialogTimeOut(Context context, final GameModel gameModel, final DialogCallBack callBack) {

        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_common);

        ImageView share = (ImageView) dialog.findViewById(R.id.dialog_share);
        final ImageView replay = (ImageView) dialog.findViewById(R.id.dialog_replay);
        ImageView home = (ImageView) dialog.findViewById(R.id.dialog_home);
        TextView newScore = (TextView) dialog.findViewById(R.id.dialog_newScore);
        TextView bestScore = (TextView) dialog.findViewById(R.id.dialog_bestScore);
        TextView mode = (TextView) dialog.findViewById(R.id.dialog_mode);

        newScore.setText(String.valueOf(gameModel.get_score()));
        String modeStr = (gameModel.get_mode() == AppConfig.MODE_TIME ? AppConfig.MODE_TIME_TITLE : AppConfig.MODE_NORMAL_TITLE);
        mode.setText(String.format(context.getString(R.string.dialog_mode), modeStr));
        mode.setVisibility(View.VISIBLE);

        DataBaseHandle dataBaseHandle = new DataBaseHandle(context);
        bestScore.setText(String.valueOf(dataBaseHandle.getBestScore(gameModel.get_mode())));

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onShare(gameModel);
                dialog.dismiss();
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onReplay();
                dialog.dismiss();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onHome();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static boolean beLeaderBoard(GameModel gameModel, DataBaseHandle dataBaseHandle){
        List<MLeaderBoard> leaderBoardList = dataBaseHandle.getAllLeaderBoardWithType(gameModel.get_mode());
        for (MLeaderBoard mLeaderBoard : leaderBoardList){
            if (gameModel.get_score() >= mLeaderBoard.get_score())
                return true;
        }
        return false;
    }

    public interface DialogCallBack {
        public void onHome();

        public void onShare(GameModel gameModel);

        public void onReplay();
    }
}
