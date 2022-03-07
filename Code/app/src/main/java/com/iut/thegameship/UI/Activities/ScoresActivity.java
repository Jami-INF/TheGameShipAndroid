package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iut.thegameship.R;
import com.iut.thegameship.adapter.ArrayToView;
import com.iut.thegameship.data.Stub;
import com.iut.thegameship.model.score.Score;

import java.util.ArrayList;


public class ScoresActivity extends AppCompatActivity {

    private RecyclerView scoresRecyclerView;
    private ScoresActivity mainActivity;
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

        //mainActivity = (ScoresActivity) scoresRecyclerView.getContext();

        scoresRecyclerView = findViewById(R.id.scoresRecyclerView);

        scores = modele.loadscoresTmp();
        scoresRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresRecyclerView.setAdapter(new ArrayToView(scores));

        //ArrayAdapter adapter = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1, scores);
        //scoresList.setAdapter(adapter);

    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.putExtra("nickname", nickname);
        return intent;
    }
}
