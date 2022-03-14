package com.iut.thegameship.UI.Activities;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("Create","onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

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
    }
}
