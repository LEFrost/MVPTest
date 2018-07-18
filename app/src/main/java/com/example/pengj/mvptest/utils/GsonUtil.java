package com.example.pengj.mvptest.utils;

import com.google.gson.Gson;

public class GsonUtil {
    private static  class Holder{
        private static final Gson gson=new Gson();
    }
    private GsonUtil(){}
    public static Gson getInstance(){
        return Holder.gson;
    }

}
