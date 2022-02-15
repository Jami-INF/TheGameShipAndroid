package com.iut.thegameship.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.iut.thegameship.R;
import com.iut.thegameship.data.Stub;


public class ScoresActivity extends MainActivity {

    private ListView scores;
    private Stub modele;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        Log.d("Create","onCreateScores()");

        final TextView textNickNameTest = findViewById(R.id.nicknamebindtest);
        textNickNameTest.setText(getIntent().getStringExtra("nickname"));//Mettre en couleur les scores correspondant a se pseudo
        //scores = findViewById(R.id.scoresView);
        //scores.setAdapter(new ArrayAdapter(this, R.layout.score_field, modele.loadscoresTmp()));

    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.putExtra("nickname", nickname);
        return intent;
    }
}
