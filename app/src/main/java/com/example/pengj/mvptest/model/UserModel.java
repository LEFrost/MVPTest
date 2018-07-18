package com.example.pengj.mvptest.model;

import android.os.Handler;
import android.os.Looper;

import com.example.pengj.mvptest.entities.User;
import com.example.pengj.mvptest.utils.API;
import com.example.pengj.mvptest.utils.GsonUtil;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserModel {

    public UserModel() {
    }

    Handler mHandler;

    public void login(final User user, final CallBack callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(API.JSON, GsonUtil.getInstance().toJson(user));
                final Request request = new Request.Builder()
                        .url(API.LOGIN_API)
                        .post(requestBody)
                        .build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Looper.prepare();
                    callback.onResponse(user, response);
                    Looper.loop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
