package com.iut.thegameship.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iut.thegameship.R;

public class EndGameActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.end_game);
        Log.d("Create","onCreateEndGameActivity()");

        final Button backButton = findViewById(R.id.backToMenu);
        backButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        final Button playAgainButton = findViewById(R.id.playAgain);
        playAgainButton.setOnClickListener(e -> {
            String nickname = "Jean michel tmp";// a mettre le pseudo précedent
            Intent intent = GameActivity.newIntent(this, nickname);
            startActivity(intent);
        });

        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onStop() {

        super.onStop();
    }

    public static Intent newIntent(Context context, String nickname, double scorePlayer) {
        Intent intent = new Intent(context, EndGameActivity.class);
        intent.putExtra("nickname",nickname);
        intent.putExtra("scorePlayer", scorePlayer);
        return intent;
    }
}
