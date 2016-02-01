package tannt275.reuseactionbrain.model;

import java.util.Random;

/**
 * Created by tannt on 2/1/2016.
 */
public class TestModel {

    public static void main (String [] args){
        Random rd = new Random();
        int i = 0;
        while (i < 10){
            i ++;
            System.out.println ("random thu "  + i + " is : " + rd.nextInt(3));
        }
    }
}
