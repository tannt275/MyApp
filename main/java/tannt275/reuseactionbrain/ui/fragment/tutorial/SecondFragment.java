package tannt275.reuseactionbrain.ui.fragment.tutorial;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import tannt275.reuseactionbrain.R;

/**
 * Created by tannt on 2/24/2016.
 */
public class SecondFragment extends Fragment {

    public static String TAG = SecondFragment.class.getSimpleName();

    private LinearLayout operatorLayout;
    private ImageButton wrongImb;
    private ImageButton rightImb;

    private LayoutInflater layoutInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        operatorLayout = (LinearLayout) rootView.findViewById(R.id.operatorLayout);
        wrongImb = (ImageButton) rootView.findViewById(R.id.fragment_second_Wrong);
        rightImb = (ImageButton) rootView.findViewById(R.id.fragment_second_Right);
        layoutInflater = LayoutInflater.from(getActivity());
        rootView.post(new Runnable() {
            @Override
            public void run() {
                initAllView();
            }
        });
        return rootView;
    }

    private void initAllView() {
        showPopUpOperatorLayout();
    }

    private void showPopUpOperatorLayout(){
        View rootView = layoutInflater.inflate(R.layout.first_fragment_top_popup, null, false);
        PopupWindow popupWindow = new PopupWindow(rootView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rootView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int height = rootView.getMeasuredHeight();

        Rect rect = localeView(operatorLayout);

        popupWindow.setAnimationStyle(R.style.animationName);
        popupWindow.showAtLocation(operatorLayout, Gravity.NO_GRAVITY, rect.left, rect.top - height);
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
