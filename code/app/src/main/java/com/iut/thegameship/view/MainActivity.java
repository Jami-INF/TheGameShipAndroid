package com.iut.thegameship.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.iut.thegameship.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        Log.d("Start","onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("Stop","onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Destroy","onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("Pause","onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("Resume","onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("Restart","onRestart()");
        super.onRestart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Create","onCreate()");

        final Button buttonbuttonPlay = findViewById(R.id.buttonPlay);
        buttonbuttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextInputLayout nicknameTextInput = findViewById(R.id.nicknameTextInput);
                String nickname = nicknameTextInput.getEditText().getText().toString();
                Intent intentbuttonPlay = GameActivity.newIntent(getBaseContext(), nickname);
                startActivity(intentbuttonPlay);
            }
        });
        final Button buttonLeaderBoard = findViewById(R.id.buttonLeaderBoard);
        buttonLeaderBoard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextInputLayout nicknameTextInput = findViewById(R.id.nicknameTextInput);
                String nickname = nicknameTextInput.getEditText().getText().toString();
                Intent intentLeaderBoard = ScoresActivity.newIntent(getBaseContext(), nickname);
                startActivity(intentLeaderBoard);
            }
        });
        final Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSettings = SettingsActivity.newIntent(getBaseContext());
                startActivity(intentSettings);
            }
        });
    }
}
