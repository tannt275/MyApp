package tannt275.reuseactionbrain.ui.fragment.tutorial;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.Log;

public class FirstFragment extends Fragment {

    public static String TAG = FirstFragment.class.getSimpleName();

    private Button normalMode;
    private Button timedMode;

    LayoutInflater layoutInflater;

    private PopupWindow popupTop;
    private PopupWindow popupBottom;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        normalMode = (Button) rootView.findViewById(R.id.first_fragment_normalMode);
        timedMode = (Button) rootView.findViewById(R.id.first_fragment_timedMode);
        layoutInflater = LayoutInflater.from(getActivity());
        rootView.post(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        });
        return rootView;
    }

    private void initView() {
        showPopUpTop();
        int i =0;
        while (true){
            try {
                Thread.sleep(1000);
                i++;
                Log.e(TAG, "i: " + i);
                if (i ==3){
                    showPopUpBottom();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showPopUpTop() {
        Log.e(TAG, "run on: showPopUpTop");
        View rootViewTop = layoutInflater.inflate(R.layout.first_fragment_top_popup, null, false);
        popupTop = new PopupWindow(rootViewTop,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupTop.setOutsideTouchable(true);
        popupTop.setFocusable(true);
        rootViewTop.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int heightRootView = rootViewTop.getMeasuredHeight();

        Rect rectNormalBtn = localeView(normalMode);

        popupTop.setAnimationStyle(R.style.animationName);
        popupTop.showAtLocation(normalMode, Gravity.NO_GRAVITY, rectNormalBtn.left, rectNormalBtn.top - heightRootView);

    }

    private void showPopUpBottom() {
        Log.e(TAG, "run on: showPopUpBottom");
        View rootViewBottom = layoutInflater.inflate(R.layout.first_fragment_bottom_popup, null, false);
        popupBottom = new PopupWindow(rootViewBottom,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rootViewBottom.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        Rect rectTimedBtn = localeView(timedMode);

        popupBottom.setAnimationStyle(R.style.animationName);
        popupBottom.showAtLocation(timedMode, Gravity.NO_GRAVITY, rectTimedBtn.left, rectTimedBtn.bottom );

    }

    public static Rect localeView(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationInWindow(loc_int);
        } catch (NullPointerException npe) {
            return new Rect();
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }
}
