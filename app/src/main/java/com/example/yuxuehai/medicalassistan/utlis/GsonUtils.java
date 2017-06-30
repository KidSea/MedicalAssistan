package com.example.yuxuehai.medicalassistan.utlis;

import com.google.gson.Gson;

/**
 * Created by yuxuehai on 2017/5/9.
 */

public class GsonUtils {

     //将Json数据解析成相应的映射对象

    public static <T> T parseJsonWithGson(String jsonData, Class<T> type){
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }
}
