package com.iut.thegameship.UI.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.iut.thegameship.R;
import com.iut.thegameship.util.save.FileLoader;
import com.iut.thegameship.util.save.FileSaver;
import com.iut.thegameship.util.save.ILoad;
import com.iut.thegameship.util.save.ISave;

import java.io.FileNotFoundException;

public class SettingsActivity extends MainActivity {

    int positionSeekBarDificulty;
    public static final String PATHToSeekBarDificulty = "SeekBarDificulty";
    private ISave save = new FileSaver();
    private ILoad loader;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Log.d("Create","onCreateScores()");

        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loader = new FileLoader();
        try {
            positionSeekBarDificulty = (int) loader.load(openFileInput(PATHToSeekBarDificulty));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarDificulty);
        seekBar.setProgress(positionSeekBarDificulty);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println("Seeekbar position is : " +i);
                positionSeekBarDificulty = i;
                System.out.println(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seekBar.setProgress(1);
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        try {
            save.save(openFileOutput(PATHToSeekBarDificulty, MODE_PRIVATE), positionSeekBarDificulty);
            System.out.println("save ok");
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                scores.forEach((n) -> System.out.println(n.getPseudo()));
            }*/

        } catch (FileNotFoundException e) {

        }
    }

}
