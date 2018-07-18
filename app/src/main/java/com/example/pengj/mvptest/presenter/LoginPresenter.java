package com.example.pengj.mvptest.presenter;

import android.icu.text.RelativeDateTimeFormatter;

import com.example.pengj.mvptest.entities.LoginResult;
import com.example.pengj.mvptest.entities.User;
import com.example.pengj.mvptest.model.CallBack;
import com.example.pengj.mvptest.model.UserModel;
import com.example.pengj.mvptest.utils.GsonUtil;
import com.example.pengj.mvptest.view.ILoginView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class LoginPresenter implements CallBack {
    private ILoginView loginView;
    private UserModel userModel;
    android.os.Handler mhandler;

    public LoginPresenter(ILoginView loginView, android.os.Handler handler) {
        this.loginView = loginView;
        mhandler = handler;
        userModel = new UserModel();
    }

    public void login() {
        loginView.showLoginType();
        User user = new User();
        if (loginView.getAccount().isEmpty()) {
            loginView.showHint("账号不能为空");
            loginView.hideLoginType();

            return;
        }
        if (loginView.getPassword().isEmpty()) {
            loginView.showHint("密码不能为空");
            loginView.hideLoginType();

            return;
        }
        user.setPassword(loginView.getPassword());
        user.setUserName(loginView.getAccount());
        userModel.login(user, this);

    }


    @Override
    public void onResponse(final User user, final Response response) throws IOException {
        if (response.code() == 200 || response.code() == 400) {
            String json = response.body().string();
            final LoginResult loginResult = GsonUtil.getInstance().fromJson(json, LoginResult.class);
            if (loginResult.getCode() == 200) {
                loginView.loginSuccess(user);
            } else {
                loginView.loginFail(loginResult.getMessage());

            }
        } else
            mhandler.post(new Runnable() {
                @Override
                public void run() {
                    loginView.showHint(String.valueOf(response.code()));
                }
            });

        mhandler.post(new Runnable() {
            @Override
            public void run() {
                loginView.hideLoginType();
            }
        });
    }
}