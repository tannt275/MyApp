package tannt275.reuseactionbrain.ui.fragment.tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tannt275.reuseactionbrain.R;

/**
 * Created by tannt on 2/24/2016.
 */
public class SecondFragment extends Fragment {

    public static String TAG = SecondFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        return rootView;
    }

}
