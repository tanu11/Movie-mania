package com.example.tanvi.moviemania.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Genre;

import java.util.ArrayList;

/**
 * Created by tanvi on 29-12-2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {
    ArrayList<Genre> genres;

    public GenreAdapter(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Genre genre=genres.get(position);
        holder.genreName.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView genreName;

        public MyViewHolder(View view) {
            super(view);
          genreName=view.findViewById(R.id.genreName);

        }
    }

}
