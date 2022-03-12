package com.iut.thegameship.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iut.thegameship.R;
import com.iut.thegameship.model.score.Score;
import com.iut.thegameship.util.data.Stub;
import com.iut.thegameship.util.save.FileLoader;
import com.iut.thegameship.util.save.FileSaver;
import com.iut.thegameship.util.save.ILoad;
import com.iut.thegameship.util.save.ISave;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class EndGameActivity extends AppCompatActivity {

    public static final String PATHToScores = "scores";
    public static final String PATHToSeekBarDificulty = "SeekBarDificulty";

    private ISave save = new FileSaver();
    private ILoad loader;
    private Stub modele = new Stub();
    private ArrayList<Score> scores = null;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int dificulty = 0;
        setContentView(R.layout.end_game);
        Log.d("Create","onCreateEndGameActivity()");

        loader = new FileLoader();
        try {
            scores = (ArrayList<Score>) loader.load(openFileInput(PATHToScores));
            dificulty = (int) loader.load(openFileInput(PATHToSeekBarDificulty));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        if (scores == null) {
            scores = (ArrayList<Score>) modele.load(null);
        }
        String nickname = getIntent().getStringExtra("nickname");
        double scorePlayer = getIntent().getDoubleExtra("scorePlayer", 0);
        if (nickname.equals("")) {
            nickname = "guest";
        }
        scores.add(new Score(nickname, scorePlayer, dificulty));     //Score test

        final Button backButton = findViewById(R.id.backToMenu);
        backButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        final Button playAgainButton = findViewById(R.id.playAgain);
        String finalNickname = nickname;
        playAgainButton.setOnClickListener(e -> {
            Intent intent = GameActivity.newIntent(this, finalNickname);
            startActivity(intent);
        });

    }
    @Override
    protected void onStop() {

        super.onStop();
        Collections.sort(scores, Score.ComparatorDificulty);
        try {
            save.save(openFileOutput(PATHToScores, MODE_PRIVATE), scores);
            System.out.println("save ok");
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                scores.forEach((n) -> System.out.println(n.getPseudo()));
            }*/

        } catch (FileNotFoundException e) {

        }
    }

    public static Intent newIntent(Context context, String nickname, double scorePlayer) {
        Intent intent = new Intent(context, EndGameActivity.class);
        intent.putExtra("nickname",nickname);
        intent.putExtra("scorePlayer", scorePlayer);
        return intent;
    }
}
