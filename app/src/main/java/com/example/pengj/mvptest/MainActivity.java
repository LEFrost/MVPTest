package com.example.pengj.mvptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pengj.mvptest.view.LoginViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class    MainActivity extends AppCompatActivity {
@BindView(R.id.to_login)
 Button toLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
