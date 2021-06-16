package com.markz.horizon.utils;

import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal {
    private static final ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<Map<String, Object>>(){
        @Override
        protected Map<String,Object> initialValue(){
            Map<String,Object> data = new HashMap<String, Object>();

            return data;
        }


    };

    public static Map<String,Object> getData(){
        return threadLocal.get();
    }

    public static void setData(Map<String,Object> data){
        threadLocal.set(data);
    }
}
