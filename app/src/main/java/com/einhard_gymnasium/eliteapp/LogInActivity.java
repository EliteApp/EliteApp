package com.einhard_gymnasium.eliteapp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userDatabaseHelper = new UserDatabaseHelper(this);

        ImageView logo = findViewById(R.id.loginLogo);
        logo.setImageResource(R.drawable.logo_einhard);

    }

    public void onClickBtn(View v){
        EditText userName = findViewById(R.id.loginUser);
        EditText pw = findViewById(R.id.loginPw);

        String[] resolution = userDatabaseHelper.check(userName.getText().toString(), pw.getText().toString());

        if(resolution != null){
            Toast.makeText(this, "Hat funktioniert", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "" + "Hat nicht funktioniert", Toast.LENGTH_LONG).show();
        }

    }
}
