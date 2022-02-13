package com.iut.thegameship.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iut.thegameship.R;


import java.util.List;

public class ArrayToView extends ArrayAdapter<String> {

    public ArrayToView(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ArrayToView(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ArrayToView(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

    public ArrayToView(@NonNull Context context, int resource, int textViewResourceId, @NonNull String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ArrayToView(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    public ArrayToView(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View scoresView = ((AppCompatActivity)getContext()).getLayoutInflater().inflate(R.layout.scores, parent, false);
        ((TextView)scoresView.findViewById(R.id.scoresView)).setText(getItem(position));
        return scoresView;
    }

}
