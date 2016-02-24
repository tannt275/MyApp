package tannt275.reuseactionbrain.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tannt275.reuseactionbrain.common.Log;
import tannt275.reuseactionbrain.model.GameModel;

/**
 * Created by tannt on 2/1/2016.
 */
public class RandomGame {

    public static String TAG = RandomGame.class.getSimpleName();

    /*public static void main (String [] args){
        GameModel gameModel = generateGame(3);
        System.out.println("game was generated: " + gameModel.convertToString());
    }*/

    public static GameModel generateGame(int _score) {

        List<String> listOperator = new ArrayList<>();
        GameModel gameModel = new GameModel(_score);

        Random random = new Random();
        boolean isCorrect = random.nextBoolean();

        int _firstNumber = random.nextInt(8) + 1;
        int _secondNumber = generateSecondNumberDifferReferrence(_firstNumber);
        gameModel.set_firstNumber(_firstNumber);
        gameModel.set_secondNumber(_secondNumber);

        gameModel.set_isCorrect(isCorrect);

        if (_score < 10) {
            gameModel.set_level(1);
            listOperator.add("+");
            listOperator.add("-");

            boolean isAdd = random.nextBoolean();

            if (isCorrect) {

                if (isAdd) {
                    gameModel.set_operator(listOperator.get(0));
                    gameModel.set_result(_firstNumber + _secondNumber);
                } else {
                    gameModel.set_operator(listOperator.get(1));
                    gameModel.set_result(_firstNumber - _secondNumber);
                }

            } else {
                int addToResult = random.nextInt(3) + 1;
                if (isAdd) {
                    gameModel.set_operator(listOperator.get(0));
                    gameModel.set_result(_firstNumber + _secondNumber + addToResult);
                } else  {
                    gameModel.set_operator(listOperator.get(1));
                    gameModel.set_result(_firstNumber - _secondNumber - addToResult);
                }

            }

        } else {
            gameModel.set_level(2);
            listOperator.add("+");
            listOperator.add("-");
            listOperator.add("*");
            listOperator.add("/");

            int typeOperator = random.nextInt(3);
            gameModel.set_operator(listOperator.get(typeOperator));
            if (isCorrect) {
                switch (typeOperator) {
                    case 0:
                        gameModel.set_result(_firstNumber + _secondNumber);
                        break;
                    case 1:
                        gameModel.set_result(_firstNumber - _secondNumber);
                        break;
                    case 2:
                        gameModel.set_result(_firstNumber * _secondNumber);
                        break;
                    case 3:
                        gameModel.set_result(_firstNumber / -_secondNumber);
                        break;
                    default:
                        Log.e(TAG, "Unknown operator expression...");
                        break;
                }
            } else {
                int addToResult = random.nextInt(5) + 2;
                switch (typeOperator) {

                    case 0:
                        gameModel.set_result(_firstNumber + _secondNumber + addToResult);
                        break;
                    case 1:
                        gameModel.set_result(_firstNumber - _secondNumber - addToResult);
                        break;
                    case 2:
                        gameModel.set_result(_firstNumber * _secondNumber + addToResult);
                        break;
                    case 3:
                        gameModel.set_result(_firstNumber / _secondNumber - addToResult);
                        break;
                    default:
                        Log.e(TAG, "Unknown operator expression...");
                        break;
                }

            }
        }


        return gameModel;
    }

    private static int generateSecondNumberDifferReferrence(int _firstNumber) {
        int _second;
        Random rd = new Random();
        while (true) {
            _second = rd.nextInt(9) + 1;
            if (_second != _firstNumber)
                break;
        }
        return _second;
    }

    private static int generateResultDifferReferrence(int result, int range) {
        int _result;
        Random rd = new Random();
        while (true) {
            _result = rd.nextInt(range) + 1;
            if (_result != result)
                break;
        }
        return 0;
    }
}
