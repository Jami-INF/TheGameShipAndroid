package com.iut.thegameship.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.iut.thegameship.R;
import com.iut.thegameship.model.score.Score;


import java.util.ArrayList;
import java.util.List;

public class ArrayToView extends RecyclerView.Adapter {
    private ArrayList<Score> scores = new ArrayList<>();

    public ArrayToView(ArrayList<Score> scores) {
        this.scores = scores;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return scores.size();
    }
}
