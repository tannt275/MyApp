package tannt275.reuseactionbrain.model;

/**
 * Created by tannt on 2/1/2016.
 */
public class GameModel extends BaseModel {

    private int _score;

    private int _level;
    private int _firstNumber;
    private int _secondNumber;
    private int _result;
    private boolean _isCorrect;
    private String _operator;
    private int _mode;

    public int get_mode() {
        return _mode;
    }

    public void set_mode(int _mode) {
        this._mode = _mode;
    }

    public GameModel (int _score){
        this._score = _score;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }
    public String get_operator() {
        return _operator;
    }

    public void set_operator(String _operator) {
        this._operator = _operator;
    }

    public int get_firstNumber() {
        return _firstNumber;
    }

    public void set_firstNumber(int _firstNumber) {
        this._firstNumber = _firstNumber;
    }

    public int get_secondNumber() {
        return _secondNumber;
    }

    public void set_secondNumber(int _secondNumber) {
        this._secondNumber = _secondNumber;
    }

    public int get_result() {
        return _result;
    }

    public void set_result(int _result) {
        this._result = _result;
    }

    public boolean is_isCorrect() {
        return _isCorrect;
    }

    public void set_isCorrect(boolean _isCorrect) {
        this._isCorrect = _isCorrect;
    }
}

