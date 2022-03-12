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
    private ISave save = new FileSaver();
    private ILoad loader;
    private RecyclerView scoresRecyclerView;
    private ScoresActivity mainActivity;
    private Stub modele = new Stub();

    private ArrayList<Score> scores = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        Log.d("Create","onCreateScores()");

        //final TextView textNickNameTest = findViewById(R.id.nicknamebindtest);
        //textNickNameTest.setText(getIntent().getStringExtra("nickname"));//Mettre en couleur les scores correspondant a se pseudo

        loader = new FileLoader();
        try {
            scores = (ArrayList<Score>) loader.load(openFileInput(PATHToScores));
        } catch (FileNotFoundException e) {
        }

        if (scores == null) {
            scores = (ArrayList<Score>) modele.load(null);
        }
        scoresRecyclerView = findViewById(R.id.scoresRecyclerView);

        //scores = modele.loadscoresTmp();
        System.out.println("scores :");
        System.out.println(scores);
        scoresRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresRecyclerView.setAdapter(new ArrayToView(scores));

    }
    @Override
    protected void onStop() {
        try {
            save.save(openFileOutput(PATHToScores, MODE_PRIVATE), scores);
        } catch (FileNotFoundException e) {
            Log.e(getPackageName(), "save failed");
        }

        super.onStop();
        Log.d("Stop","onStop()");
    }

    public static Intent newIntent(Context context, String nickname){
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.putExtra("nickname", nickname);
        return intent;
    }
}
