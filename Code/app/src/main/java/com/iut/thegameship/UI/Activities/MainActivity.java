package com.iut.thegameship.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        setContentView(R.layout.menu);
        Log.d("Create","onCreate()");

        Button buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(e -> {
            TextInputLayout nicknameTextInput = findViewById(R.id.nicknameTextInput);
            String nickname = nicknameTextInput.getEditText().getText().toString();
            Intent intent = GameActivity.newIntent(this, nickname);
            startActivity(intent);
        });

        Button buttonLeaderBoard = findViewById(R.id.buttonLeaderBoard);
        buttonLeaderBoard.setOnClickListener(e -> {
            Intent intent = new Intent(this, ScoresActivity.class);
            startActivity(intent);
        });

        Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(e -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
        Button buttonTestEndGame = findViewById(R.id.buttonTestEndGame);
        buttonTestEndGame.setOnClickListener(e -> {
            Intent intent = EndGameActivity.newIntent(this, "nickname", 50.00);
            startActivity(intent);
        });


    }




}
