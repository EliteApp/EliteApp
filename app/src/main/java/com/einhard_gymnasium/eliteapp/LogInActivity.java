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

//by Nick

public class LogInActivity extends AppCompatActivity {

    private UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userDatabaseHelper = new UserDatabaseHelper(this);

        ImageView logo = findViewById(R.id.loginLogo);
        logo.setImageResource(R.drawable.logo_einhard);

        //Überprüft, ob noch Nutzderdaten gespeichert sind
        //wenn ja, gelangt der Nutzer direkt auf seine Startseite, ohne sich neu anmelden zu müssen

        if(SaveSharedPreferences.getClass(this) != 0) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (SaveSharedPreferences.getClass(this) == 0) {
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(SaveSharedPreferences.getClass(this) != 0){
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

        }
    }

// by Nathalie
    public void onClickBtn(View v){
        Toast.makeText(this, SaveSharedPreferences.getUserName(this), Toast.LENGTH_LONG);
        EditText userName = findViewById(R.id.loginUser);
        EditText pw = findViewById(R.id.loginPw);

        String[] resolution = userDatabaseHelper.check(userName.getText().toString(), pw.getText().toString());
        // Der Nutzer wird eingeloggt, gelangt auf seine Startseite
        // Die Nutzerdaten werden gespeichert
        if(resolution != null){
            SaveSharedPreferences.setUserName(this, resolution[0]);
            SaveSharedPreferences.setPassword(this, resolution[1]);
            SaveSharedPreferences.setClass(this, Integer.parseInt(resolution[2]));
            SaveSharedPreferences.setName(this, resolution[3]);
            startActivity(new Intent(this, MainActivity.class));
            LogInActivity.this.finish();
        }
        else{
            Toast.makeText(this, "" + "wrong username or password", Toast.LENGTH_LONG).show();
        }



    }

}
