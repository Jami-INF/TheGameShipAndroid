package com.thegameship.view;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.thegameship.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        Log.d("Start","onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("Stop","onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Destroy","onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("Pause","onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("Resume","onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("Restart","onRestart()");
        super.onRestart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Create","onCreate()");
    }
}
