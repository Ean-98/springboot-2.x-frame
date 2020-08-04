package cn.hlsd.websys.util;

import cn.hlsd.websys.entity.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eangaie
 * @date 2018/8/25 0025 下午 16:31
 */
public class ResultUtil {

    //当正确时返回的值
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }


    //当正确时返回的值
    public static Result success(Object data, int count){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("request ok!");
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    //当错误时返回的值
    public static Result error(int code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    //当错误时返回的值
    public static Result error(Object data, int code, String msg){
        Result result = new Result();
        result.setData(data);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
