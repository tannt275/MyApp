package tannt275.reuseactionbrain.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.adapter.HighScoreAdapter;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.database.DataBaseHandle;
import tannt275.reuseactionbrain.model.MLeaderBoard;

public class HighScoreActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_high_score, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView emptyHighScore;
        private ListView listHighScore;
        private int type;
        private List<MLeaderBoard> leaderBoardList;
        private DataBaseHandle dataBaseHandle;
        private HighScoreAdapter highScoreAdapter;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            dataBaseHandle = new DataBaseHandle(getActivity());
            if (getArguments() != null)
                type = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_high_score, container, false);
            listHighScore = (ListView) rootView.findViewById(R.id.listHighScore);
            emptyHighScore = (TextView) rootView.findViewById(R.id.empty_high_score);
            fillData();
            return rootView;
        }

        private void fillData() {
            if (type == 0)
                leaderBoardList = dataBaseHandle.getAllLeaderBoardWithType(AppConfig.MODE_NORMAL);
            else if (type == 1)
                leaderBoardList = dataBaseHandle.getAllLeaderBoardWithType(AppConfig.MODE_TIME);
            emptyHighScore.setVisibility(leaderBoardList.size() == 0 ? View.VISIBLE : View.GONE);
            highScoreAdapter = new HighScoreAdapter(getActivity(), leaderBoardList);
            listHighScore.setAdapter(highScoreAdapter);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return AppConfig.MODE_NORMAL_TITLE;
                case 1:
                    return AppConfig.MODE_TIME_TITLE;
            }
            return null;
        }
    }
}
