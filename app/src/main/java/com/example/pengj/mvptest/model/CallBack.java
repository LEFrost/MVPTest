package com.example.pengj.mvptest.model;

import com.example.pengj.mvptest.entities.User;

import java.io.IOException;

import okhttp3.Response;

public interface CallBack {

    void onResponse(User user, Response response) throws IOException;
}
