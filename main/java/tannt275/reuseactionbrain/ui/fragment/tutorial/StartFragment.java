package tannt275.reuseactionbrain.ui.fragment.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.Log;

public class StartFragment extends Fragment implements Animation.AnimationListener {

    private TextView textView;
    private Animation animation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        textView = (TextView) rootView.findViewById(R.id.textStart);
        initView();
        return rootView;
    }

    private void initView() {
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        animation.setAnimationListener(this);
        textView.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.e("StartFragment","onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.e("StartFragment","onAnimationEnd");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.e("StartFragment","onAnimationRepeat");
    }
}
