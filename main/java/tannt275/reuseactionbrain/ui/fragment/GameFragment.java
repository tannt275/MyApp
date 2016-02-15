package tannt275.reuseactionbrain.ui.fragment;

import android.os.AsyncTask;
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
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.control.RandomGame;
import tannt275.reuseactionbrain.dialog.AppDialogs;
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

    private int TIMEOUT_GAME = 3 * 1000;
    private GameModel gameModel;

    private int _modeGame;
    private int _timeSet;

    private CountAsynTask countAsynTask;
    private CountDownTask countDownTask;

    public static GameFragment newInstance(int _mode, int _timeSet) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putInt(AppConfig.MODE_IN_BUNDLE, _mode);
        args.putInt(AppConfig.TIME_SET, _timeSet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _modeGame = getArguments().getInt(AppConfig.MODE_IN_BUNDLE);
            _timeSet = getArguments().getInt(AppConfig.TIME_SET);
            Log.e(TAG, "mode game: " + _modeGame + " time set: " + _timeSet);

            if (_modeGame == AppConfig.MODE_NORMAL) {
//                startCount();
                countAsynTask = new CountAsynTask();
                countAsynTask.execute();
            } else if (_modeGame == AppConfig.MODE_TIME) {
//                countDown();
                countDownTask = new CountDownTask(_timeSet);
                countDownTask.execute();
            }
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

        return rootView;
    }

    private void initAllView() {

        gameModel = RandomGame.generateGame(0);
        updateUi(gameModel);
    }

    private void updateUi(final GameModel g) {

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

//                AppDialogs.showDialogTimeOut(getActivity(), g);

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

    /*private void startCount() {

        threadCount = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {

                        final int finalI = i;
                        Thread.sleep(100);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time.setText(AppConfig.parseIntToString(finalI));
                            }
                        });
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadCount.start();
    }

    private void countDown() {

        threadCount = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = _timeSet;
                while (i > 0) {
                    try {
                        final int finalI = i;
                        Thread.sleep(100);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time.setText(AppConfig.parseIntToString(finalI * 1000));
                            }
                        });
                        i--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadCount.start();
    }*/

    private class CountAsynTask extends AsyncTask<String, String, String> {

//        private int startTime;
        private String reStr;

        /*public CountAsynTask(int startTime) {
            this.startTime = startTime;
        }*/

        @Override
        protected String doInBackground(String... params) {
            int i = 0;
            while (true){
                try {
                    Thread.sleep(100);
                    i ++;
                    reStr = String.valueOf(i * 100) + "";
                    publishProgress(reStr);

                    if (isCancelled())
                        break;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    reStr = e.getMessage();
                }
            }
            return reStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            time.setText(values[0]);
        }
    }

    private class CountDownTask extends AsyncTask<String, String, String>{
        private int timeCountDown;
        private String reStr;

        public CountDownTask(int timeCountDown) {
            this.timeCountDown = timeCountDown;
        }

        @Override
        protected String doInBackground(String... params) {
            if (timeCountDown <= 0)
                reStr = "";
            int i = timeCountDown * 10;
            do {
                try {
                    Thread.sleep(100);
                    i --;
                    reStr = String.valueOf(i * 100) + "";
                    publishProgress(reStr);

                    if (isCancelled())
                        break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    reStr = e.getMessage();
                }
            } while ( i > 0);
            return reStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            time.setText(values[0]);
        }
    }
}
