package com.einhard_gymnasium.eliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ImageView logo = findViewById(R.id.loginLogo);
        logo.setImageResource(R.drawable.logo_einhard);
    }
}
