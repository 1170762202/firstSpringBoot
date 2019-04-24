package com.zlx.firstSpringBoot.constant;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReturnUtil {

    public ReturnUtil() {
    }

    public static <T> Map<String, Object> returnValue(ResponseCode responseCode, T data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", responseCode.getCode());
        map.put("message", responseCode.getMessage());
        map.put("data", data);
        return map;
    }

    public static <T> Map<String, Object> returnFailured(T data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ResponseCode.failed.getCode());
        map.put("message", ResponseCode.failed.getMessage());
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> returnValue(ResponseCode responseCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", responseCode.getCode());
        map.put("message", responseCode.getMessage());
        map.put("data", null);
        return map;
    }
}
