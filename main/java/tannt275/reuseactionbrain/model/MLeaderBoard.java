package tannt275.reuseactionbrain.model;

/**
 * Created by tannt on 2/18/2016.
 */
public class MLeaderBoard extends BaseModel {

    public MLeaderBoard(String _name, int _score, long _time, int _type) {
        this._name = _name;
        this._score = _score;
        this._time = _time;
        this._type = _type;
    }
    public MLeaderBoard (){

    }

    private String _name;
    private int _score;
    private long _time;
    private int _type;


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public long get_time() {
        return _time;
    }

    public void set_time(long _time) {
        this._time = _time;
    }

    public int get_type() {
        return _type;
    }

    public void set_type(int _type) {
        this._type = _type;
    }
}
