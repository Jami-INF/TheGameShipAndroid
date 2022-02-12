package com.iut.thegameship.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iut.thegameship.R;
import com.iut.thegameship.data.Stub;

public class scores  extends MainActivity {
    private ListView scores;
    private Stub modele;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("Create","onCreateScores()");

        scores = findViewById(R.id.scoresView);
        scores.setAdapter(new ArrayAdapter(this, R.layout.cellule_score, modele.loadscoresTmp()));

        final Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });
    }
}
