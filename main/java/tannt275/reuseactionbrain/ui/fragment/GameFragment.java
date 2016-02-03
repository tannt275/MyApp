package tannt275.reuseactionbrain.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private static int _score = 0;

    private Thread threadCount;
    private Handler handler;
    private Runnable runnable;

    private int TIMEOUT_GAME = 2 * 1000;
    private GameModel gameModel;

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

        imbCorrect.setOnClickListener(answerCorrectListener);
        imbWrong.setOnClickListener(answerWrongListener);
        initAllView();
        startCount();
        return rootView;
    }

    private void initAllView() {

        gameModel = RandomGame.generateGame(0);
        updateUi(gameModel);
    }

    private void updateUi(GameModel g) {

        Log.e(TAG, "game was generate: " + g.convertToString());
        score.setText(String.valueOf(g.get_score()));
        firstNumber.setText(String.valueOf(g.get_firstNumber()));
        secondNumber.setText(String.valueOf(g.get_secondNumber()));
        operator.setText(g.get_operator());
        result.setText(String.valueOf(g.get_result()));

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "time out with current game...");
            }
        };
        handler.postDelayed(runnable, TIMEOUT_GAME);
    }

    private View.OnClickListener answerCorrectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(TAG, "button correct was clicked...");
            handler.removeCallbacks(runnable);
            if (gameModel.is_isCorrect()) {
                Log.e(TAG, "user and me with same answer...");
                nextGame();
            } else {
                Log.e(TAG, "user and me with not same answer...");
                endGame();
            }

        }
    };
    private View.OnClickListener answerWrongListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handler.removeCallbacks(runnable);
            Log.e(TAG, "button wrong was clicked...");
            if (gameModel.is_isCorrect()) {
                Log.e(TAG, "user and me with not same answer...");
                endGame();
            } else {
                Log.e(TAG, "user and me with same answer...");
                nextGame();
            }
        }
    };

    private void nextGame() {
        Log.e(TAG, "game is continous...");
        _score++;
        gameModel = RandomGame.generateGame(_score);
        updateUi(gameModel);
    }

    private void endGame() {
        Log.e(TAG, "run to end game...");
    }

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
