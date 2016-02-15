package tannt275.reuseactionbrain.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.model.GameModel;

/**
 * Created by tannt on 2/3/2016.
 */
public class AppDialogs {

    public static String TAG = AppDialogs.class.getSimpleName();

    public static void showDialogTimeOut(Context context, final GameModel gameModel, final DialogCallBack callBack) {

        Dialog dialog = new Dialog(context);
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
        String modeStr = (gameModel.get_mode() == AppConfig.MODE_TIME ? AppConfig.MODE_TIME_TITLE : AppConfig.MODE_NORMAL_TIME);
        mode.setText(String.format(context.getString(R.string.dialog_mode), modeStr));
        mode.setVisibility(View.VISIBLE);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onShare(gameModel);
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onReplay();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onHome();
            }
        });

        dialog.show();
    }

    public interface DialogCallBack {
        public void onHome();

        public void onShare(GameModel gameModel);

        public void onReplay();
    }
}
