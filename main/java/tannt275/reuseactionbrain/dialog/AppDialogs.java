package tannt275.reuseactionbrain.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.database.DataBaseHandle;
import tannt275.reuseactionbrain.model.GameModel;

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

    public static void showDialogAddName(Context context,  final DialogSingleCallBack callBack) {
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_name);

        TextView messageTv = (TextView) dialog.findViewById(R.id.dialog_add_name_message);
        Button btnAgree = (Button) dialog.findViewById(R.id.dialog_add_name_agreeBtn);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_add_name_cancelBtn);
        final EditText nameEdt = (EditText) dialog.findViewById(R.id.dialog_add_nameEdt);

        String mess = "Your Name";
        String message = String.format(context.getString(R.string.dialog_add_name_message), mess);
        Spannable spannable = new SpannableString(message);
        spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorRed)),
                message.indexOf(mess), mess.length() + message.indexOf(mess), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new RelativeSizeSpan(1.5f),message.indexOf(mess), mess.length() + message.indexOf(mess), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        messageTv.setText(spannable);

        nameEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null){
                    dialog.dismiss();
                    callBack.onCallBack(nameEdt.getText().toString());
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack !=null){

                    dialog.dismiss();
                    callBack.onCallBack(nameEdt.getText().toString());
                }
            }
        });
        dialog.show();
    }

    public interface DialogSingleCallBack {
        public void onCallBack(String string);
    }


    public interface DialogCallBack {
        public void onHome();

        public void onShare(GameModel gameModel);

        public void onReplay();
    }
}
