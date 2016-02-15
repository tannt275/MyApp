package tannt275.reuseactionbrain.model;

import android.os.AsyncTask;

import java.util.Random;

/**
 * Created by tannt on 2/1/2016.
 */
public class TestModel {

    public static void main (String [] args){

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
