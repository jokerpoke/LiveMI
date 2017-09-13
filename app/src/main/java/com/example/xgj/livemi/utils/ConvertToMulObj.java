package com.example.xgj.livemi.utils;

/**
 * Created by chen on 2017/6/12.
 */

public class ConvertToMulObj {
    //数据类型转换，避免当要转换对象为空时包异常。
    public static final int convertToInt(Object objValue, int defaultValue) {
        if (objValue == null || "".equals(objValue.toString().trim())) {
            return defaultValue;
        }
        try {

            return Integer.valueOf(objValue.toString());
        } catch (Exception E) {
            try {
                return Double.valueOf(objValue.toString()).intValue();
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }
}
