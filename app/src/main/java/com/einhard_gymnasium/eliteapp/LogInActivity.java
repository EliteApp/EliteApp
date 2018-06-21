package com.einhard_gymnasium.eliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.einhard_gymnasium.eliteapp.Databases.UserDatabaseHelper;

public class LogInActivity extends AppCompatActivity {

    private UserDatabaseHelper userDatabaseHelper;

    private Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userDatabaseHelper = new UserDatabaseHelper(this);

        ImageView logo = findViewById(R.id.loginLogo);
        logo.setImageResource(R.drawable.logo_einhard);

        profile = new Profile();

        if(profile.getUserName(this).compareTo("") == 1) {
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    public void onClickBtn(View v){
        EditText userName = findViewById(R.id.loginUser);
        EditText pw = findViewById(R.id.loginPw);

        String[] resolution = userDatabaseHelper.check(userName.getText().toString(), pw.getText().toString());


        if(resolution != null){
            startActivity(new Intent(this, MainActivity.class));
            profile.setUserName(this, resolution[0]);
            profile.setPassword(this, resolution[1]);
            profile.setClass(this, Integer.parseInt(resolution[2]));
            profile.setName(this, resolution[3]);

        }
        else{
            Toast.makeText(this, "" + "wrong username or password", Toast.LENGTH_LONG).show();
        }

    }
}
