package tannt275.reuseactionbrain.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import tannt275.reuseactionbrain.R;
import tannt275.reuseactionbrain.control.RandomGame;
import tannt275.reuseactionbrain.model.GameModel;

public class GameFragment extends Fragment {

    public static String TAG = GameFragment.class.getSimpleName();
    private TextView time, score;
    private ImageButton imbCorrect, imbWrong;
    private TextView firstNumber, secondNumber, operator, result;

    private Button btnNextGame;
    private static int _score = 0;

    private Thread threadCount;

    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        time = (TextView) rootView.findViewById(R.id.fgm_time);
        score = (TextView) rootView.findViewById(R.id.fgm_score);
        imbCorrect = (ImageButton) rootView.findViewById(R.id.imbCorrect);
        imbWrong = (ImageButton) rootView.findViewById(R.id.imbWrong);
        firstNumber = (TextView) rootView.findViewById(R.id.game_firstNumber);
        secondNumber = (TextView) rootView.findViewById(R.id.game_secondNumber);
        operator = (TextView) rootView.findViewById(R.id.game_operator);
        result = (TextView) rootView.findViewById(R.id.game_result);
        btnNextGame = (Button) rootView.findViewById(R.id.nextGame);
        btnNextGame.setOnClickListener(nextGameListener);
        initAllView();
        startCount();
        return rootView;
    }

    private void initAllView() {

        GameModel g = RandomGame.generateGame(0);
        updateUi(g);
    }

    private void updateUi(GameModel g) {

        Log.e(TAG, "game was generate: " + g.convertToString());
        score.setText(String.valueOf(g.get_score()));
        firstNumber.setText(String.valueOf(g.get_firstNumber()));
        secondNumber.setText(String.valueOf(g.get_secondNumber()));
        operator.setText(g.get_operator());
        result.setText(String.valueOf(g.get_result()));
    }

    private View.OnClickListener nextGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            _score++;
            GameModel game = RandomGame.generateGame(_score);
            updateUi(game);
        }
    };

    private void startCount() {

        threadCount = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        i++;
                        final int finalI = i;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time.setText(String.valueOf((finalI * 200)));
                            }
                        });
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadCount.start();
    }

}
