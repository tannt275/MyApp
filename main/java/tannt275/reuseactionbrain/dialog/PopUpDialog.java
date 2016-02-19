package tannt275.reuseactionbrain.dialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.widget.ShareInformation;

/**
 * Created by tannt on 2/18/2016.
 */
public class PopUpDialog extends DialogFragment {

    ShareInformation shareInformation;
    Bitmap appIcon;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.test_popup, container, false);
        shareInformation = (ShareInformation) rootView.findViewById(R.id.shareInfomation);
//        shareInformation.prepareDrawing();
        fillData();
        return rootView;
    }

    private void fillData() {
        appIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.app_launcher);
        shareInformation.drawAvatar(appIcon);
        shareInformation.drawAppName();
    }
}
