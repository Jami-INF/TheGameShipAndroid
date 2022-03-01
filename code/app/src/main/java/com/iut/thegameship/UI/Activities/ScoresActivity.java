package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.iut.thegameship.R;
import com.iut.thegameship.data.Stub;
import com.iut.thegameship.model.score.Score;

import java.util.ArrayList;


public class ScoresActivity extends MainActivity {

    private ListView scoresList;
    private Stub modele = new Stub();
    private ArrayList<Score> scores = new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        Log.d("Create","onCreateScores()");

        //final TextView textNickNameTest = findViewById(R.id.nicknamebindtest);
        //textNickNameTest.setText(getIntent().getStringExtra("nickname"));//Mettre en couleur les scores correspondant a se pseudo
        scoresList = findViewById(R.id.scoresListView);

        scores = modele.loadscoresTmp();


        ArrayAdapter adapter = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1, scores);
        scoresList.setAdapter(adapter);

    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.putExtra("nickname", nickname);
        return intent;
    }
}
