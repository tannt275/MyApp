package tannt275.reuseactionbrain.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.model.MLeaderBoard;

/**
 * Created by tannt on 2/18/2016.
 */
public class HighScoreAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<MLeaderBoard> leaderBoardList;

    public HighScoreAdapter(Context context, List<MLeaderBoard> leaderBoardList) {
        this.context = context;
        this.leaderBoardList = leaderBoardList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return leaderBoardList.size();
    }

    @Override
    public Object getItem(int position) {
        return leaderBoardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HighScoreHolder highScoreHolder;
        if (convertView == null) {
            highScoreHolder = new HighScoreHolder();
            convertView = layoutInflater.inflate(R.layout.item_high_score, parent, false);
            highScoreHolder.rank = (TextView) convertView.findViewById(R.id.item_high_score_rank);
            highScoreHolder.name = (TextView) convertView.findViewById(R.id.item_high_score_name);
            highScoreHolder.score = (TextView) convertView.findViewById(R.id.item_high_score_score);
            highScoreHolder.time = (TextView) convertView.findViewById(R.id.item_high_score_time);
            convertView.setTag(highScoreHolder);
        } else highScoreHolder = (HighScoreHolder) convertView.getTag();

        MLeaderBoard mLeaderBoard = leaderBoardList.get(position);
        highScoreHolder.rank.setText(String.valueOf(position + 1));
        highScoreHolder.name.setText((TextUtils.isEmpty(mLeaderBoard.get_name())) ? "You" : mLeaderBoard.get_name());
        highScoreHolder.score.setText(String.valueOf(mLeaderBoard.get_score()));
        highScoreHolder.time.setText(AppConfig.parseTimeToString(mLeaderBoard.get_time()));
        return convertView;
    }

    class HighScoreHolder {
        TextView rank;
        TextView name;
        TextView score;
        TextView time;
    }
}
