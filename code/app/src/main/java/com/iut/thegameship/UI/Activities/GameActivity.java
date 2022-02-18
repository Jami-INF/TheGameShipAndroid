package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.iut.thegameship.R;

public class GameActivity extends MainActivity {
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Log.d("Create","onCreateScores()");

        final TextView textNickNameTest = findViewById(R.id.nicknamebindtest);
        textNickNameTest.setText("Vous etes : "+getIntent().getStringExtra("nickname"));
    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("nickname", nickname);
        return intent;
    }
}
