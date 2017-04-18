package com.example.xgj.livemi.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2017/4/1.
 */

public class NetHelpInfo {
    public static Map getMain(String bizType) {
        Map<String, String> map = new HashMap<>();
        map.put("bizType",bizType );
        return map;
    }
}
