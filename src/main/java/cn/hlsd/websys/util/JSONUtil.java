package cn.hlsd.websys.util;

import com.google.gson.Gson;

public class JSONUtil {

    public static String objToJsonStr(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
