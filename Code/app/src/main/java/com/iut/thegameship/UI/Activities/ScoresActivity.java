package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iut.thegameship.R;
import com.iut.thegameship.util.adapter.ArrayToView;
import com.iut.thegameship.util.save.FileLoader;
import com.iut.thegameship.util.save.FileSaver;
import com.iut.thegameship.util.save.ILoad;
import com.iut.thegameship.util.save.ISave;
import com.iut.thegameship.util.data.Stub;
import com.iut.thegameship.model.score.Score;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {

    public static final String PATHToScores = "scores";
    public static final String PATHToSeekBarDificulty = "SeekBarDificulty";

    private ISave save = new FileSaver();
    private ILoad loader;
    private RecyclerView scoresRecyclerView;
    private TextView textViewHUD;
    private Stub modele = new Stub();
    private ArrayList<Score> scores = null;
    ArrayList<Score> scoresOrder = new ArrayList<>();

    private int dificultyCurrent;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        Log.d("Create","onCreateScores()");

        loader = new FileLoader();
        try {
            scores = (ArrayList<Score>) loader.load(openFileInput(PATHToScores));
            dificultyCurrent = (int) loader.load(openFileInput(PATHToSeekBarDificulty));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        if (scores == null) {
            scores = (ArrayList<Score>) modele.load(null);
        }

        scoresRecyclerView = findViewById(R.id.scoresRecyclerView);
        scoresRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresRecyclerView.setAdapter(new ArrayToView(updateAffichageDifficulty(dificultyCurrent)));

        Button buttonEasy = findViewById(R.id.buttonEasy);
        buttonEasy.setOnClickListener(e -> {
            scoresOrder.clear();
            scoresRecyclerView.setAdapter(new ArrayToView(updateAffichageDifficulty(1)));
        });
        Button buttonMedium = findViewById(R.id.buttonMedium);
        buttonMedium.setOnClickListener(e -> {
            scoresOrder.clear();
            scoresRecyclerView.setAdapter(new ArrayToView(updateAffichageDifficulty(2)));
        });
        Button buttonHard = findViewById(R.id.buttonHard);
        buttonHard.setOnClickListener(e -> {
            scoresOrder.clear();
            scoresRecyclerView.setAdapter(new ArrayToView(updateAffichageDifficulty(3)));
        });
    }

    @Override
    protected void onStop() {
        try {
            save.save(openFileOutput(PATHToScores, MODE_PRIVATE), scores);
        } catch (FileNotFoundException e) {
            Log.e(getPackageName(), "Save failed");
        }
        super.onStop();
        Log.d("Stop","onStop()");
    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.putExtra("nickname", nickname);
        return intent;
    }
    public ArrayList<Score> updateAffichageDifficulty(int difficulty){
        ArrayList<Score> scoreSelected = new ArrayList<>();
        textViewHUD = findViewById(R.id.textViewHUD);
        switch(difficulty){
            case 1:
                textViewHUD.setText("Easy");
                break;

            case 2:
                textViewHUD.setText("Medium");
                break;

            case 3:
                textViewHUD.setText("Hard");
                break;
            default:
                textViewHUD.setText("");
                break;
        }
        for (Score score: scores) {
            if (score.getDifficulty() == difficulty)
                scoreSelected.add(score);
        }
        return scoreSelected;
    }
}
