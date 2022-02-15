package com.iut.thegameship.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.iut.thegameship.R;
import com.iut.thegameship.data.Stub;

public class SettingsActivity extends MainActivity{
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Log.d("Create","onCreateScores()");

        final Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }
}
