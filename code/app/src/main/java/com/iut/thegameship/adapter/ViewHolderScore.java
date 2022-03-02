package com.iut.thegameship.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iut.thegameship.R;
import com.iut.thegameship.model.score.Score;

public class ViewHolderScore extends RecyclerView.ViewHolder{
    private TextView textViewScore;
    public ViewHolderScore(@NonNull View itemView){
        super(itemView);
        textViewScore = itemView.findViewById(R.id.scoreText);
    }

    public TextView getTextViewScore() {
        return textViewScore;
    }


    public void setScoreCourant(Score score) {

    }
}
