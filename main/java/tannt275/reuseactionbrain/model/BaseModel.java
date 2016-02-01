package tannt275.reuseactionbrain.model;

import com.google.gson.Gson;

/**
 * Created by tannt on 2/1/2016.
 */
public class BaseModel {

    public String convertToString (){
        return new Gson().toJson(this);
    }
}
