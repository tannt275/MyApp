package tannt275.reuseactionbrain.model;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by tannt on 2/1/2016.
 */
public class TestModel {

    public static void main (String [] args){

        List<MLeaderBoard> list = new ArrayList<>();

        MLeaderBoard mLeaderBoard1 = new MLeaderBoard("A",10,10,998);
        MLeaderBoard m2 = new MLeaderBoard("B", 10, 11, 998);
        MLeaderBoard m3 = new MLeaderBoard("C", 13, 100, 998);
        MLeaderBoard m4 = new MLeaderBoard("D", 9, 10, 998);
        list.add(mLeaderBoard1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

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

        for (MLeaderBoard mLeaderBoard : list){
            System.out.println("" + mLeaderBoard.convertToString());
        }
    }
    private class CountAsynTask extends AsyncTask<Integer, Void, Integer> {

        private int startTime;

        public CountAsynTask(int startTime) {
            this.startTime = startTime;
        }

        @Override
        protected Integer doInBackground(Integer... params) {

            int start = params[0];
            if (start == 0){
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    start ++;
                }
            }
            return start;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }
}
