package com.example.pengj.mvptest.view;

import com.example.pengj.mvptest.entities.User;

public interface ILoginView {
    void showLoginType();
    void hideLoginType();
    String getAccount();
    String getPassword();
    void loginSuccess(User user);
    void loginFail(String message);
    void showHint(String message);
}
