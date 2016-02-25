package tannt275.reuseactionbrain.ui.fragment.tutorial;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;

public class FinishFragment extends Fragment {

    private TextView textView;
    private Animation animation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finish, container, false);
        textView = (TextView) rootView.findViewById(R.id.text_finish);
        initView();
        return rootView;
    }

    private void initView() {
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        textView.startAnimation(animation);
    }
}
