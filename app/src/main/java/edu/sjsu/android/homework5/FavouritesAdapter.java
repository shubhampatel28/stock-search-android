package edu.sjsu.android.homework5;

import android.content.Context;
import android.view.LayoutInflater;

public class FavouritesAdapter {
    Context context;
    LayoutInflater inflater;

    public FavouritesAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
