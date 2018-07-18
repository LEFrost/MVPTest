package com.example.pengj.mvptest.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pengj.mvptest.R;
import com.example.pengj.mvptest.entities.User;
import com.example.pengj.mvptest.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginViewActivity extends AppCompatActivity implements ILoginView {
    LoginPresenter presenter;
    @BindView(R.id.edit_account)
    EditText EditAccount;
    @BindView(R.id.edit_password)
    EditText EditPassword;
    @BindView(R.id.button_login)
    Button LoginButton;
    @BindView(R.id.progressbar_login)
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_login);
        ButterKnife.bind(this);
        handler = new Handler();
        presenter = new LoginPresenter(this,new Handler());
    }

    @OnClick(R.id.button_login)
    void Login(View v) {
        presenter.login();
    }


    @Override
    public void showLoginType() {
        LoginButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginType() {
        LoginButton.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public String getAccount() {
        return EditAccount.getText().toString();
    }

    @Override
    public String getPassword() {
        return EditPassword.getText().toString();
    }

    @Override
    public void loginSuccess(User user) {
        System.out.println(user.getUserName());
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(this, "登录失败," + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHint(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
