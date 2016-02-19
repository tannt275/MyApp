package tannt275.reuseactionbrain.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.model.MLeaderBoard;

/**
 * Created by tannt on 2/18/2016.
 */
public class DataBaseHandle extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "BRAIN_DB";

    private String TABLE_NORMAL = "TABLE_NORMAL";
    private String TABLE_TIMED = "TABLE_TIMED";

    private String KEY_ID = "_id";
    private String KEY_NAME = "_name";
    private String KEY_SCORE = "_score";
    private String KEY_TIME = "_time";
    private String KEY_TYPE = "_type";

    private String CREATE_TABLE_NORMAL = "CREATE TABLE " + TABLE_NORMAL + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT, "
            + KEY_SCORE + " INTEGER, "
            + KEY_TIME + " INTEGER, "
            + KEY_TYPE + " INTEGER NOT NULL DEFAULT 999 )";

    private String CREATE_TABLE_TIMED = "CREATE TABLE " + TABLE_TIMED + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT, "
            + KEY_SCORE + " INTEGER, "
            + KEY_TIME + " INTEGER, "
            + KEY_TYPE + " INTEGER NOT NULL DEFAULT 998 )";

    public DataBaseHandle(Context context) {
        super(context, DataBaseHandle.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NORMAL);
        db.execSQL(CREATE_TABLE_TIMED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_TABLE_NORMAL);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_TABLE_TIMED);
        onCreate(db);
    }

    public void insertData(MLeaderBoard mLeaderBoard, DataBaseCallback callback) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int type = mLeaderBoard.get_type();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, mLeaderBoard.get_name());
        contentValues.put(KEY_SCORE, mLeaderBoard.get_score());
        contentValues.put(KEY_TIME, mLeaderBoard.get_time());
        contentValues.put(KEY_TYPE, mLeaderBoard.get_type());
        long d = sqLiteDatabase.insert((type == AppConfig.MODE_NORMAL ? TABLE_NORMAL : TABLE_TIMED), null, contentValues);
        if (d > 0) {
            if (callback != null)
                callback.onSuccess();
        } else if (callback != null)
            callback.onFail();
        sqLiteDatabase.close();
    }

    public List<MLeaderBoard> getAllLeaderBoardWithType(int type) {

        List<MLeaderBoard> list = new ArrayList<>();
        String query = "SELECT * FROM ";
        query += (type == AppConfig.MODE_NORMAL) ? TABLE_NORMAL : TABLE_TIMED;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                MLeaderBoard mLeaderBoard = new MLeaderBoard();
                int limScore = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SCORE));
                if (limScore > AppConfig.CONDITION_BE_LEADERBOARD){

                    mLeaderBoard.set_name(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                    mLeaderBoard.set_score(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SCORE)));
                    mLeaderBoard.set_time(cursor.getLong(cursor.getColumnIndexOrThrow(KEY_TIME)));
                    mLeaderBoard.set_type(type);
                    list.add(mLeaderBoard);
                }

            } while (cursor.moveToNext());
        }

        Collections.sort(list, new Comparator<MLeaderBoard>() {
            @Override
            public int compare(MLeaderBoard lhs, MLeaderBoard rhs) {
                if (lhs.get_score() > rhs.get_score())
                    return -1;
                else if (lhs.get_score() == rhs.get_score()) {

                    if (lhs.get_time() > rhs.get_time())
                        return 1;
                    else
                        return 0;
                } else
                    return 1;
            }
        });
        List<MLeaderBoard> newList = new ArrayList<>();
        if (list.size() < 10 )
            newList.addAll(list);
        else {

            for (int i = 0; i < 10; i ++){
                MLeaderBoard mLeaderBoard = list.get(i);
                newList.add(mLeaderBoard);
            }
        }
        return newList;
    }

    public int getBestScore(int type) {
        int bestScore = Integer.MIN_VALUE;
        String query = "SELECT * FROM ";
        query += (type == AppConfig.MODE_NORMAL) ? TABLE_NORMAL : TABLE_TIMED;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SCORE));
                if (score > bestScore)
                    bestScore = score;
            } while (cursor.moveToNext());
        }
        return bestScore;
    }

    public interface DataBaseCallback {
        public void onSuccess();

        public void onFail();
    }
}
