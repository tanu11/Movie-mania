package com.example.tanvi.moviemania.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.MovieDetail;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tanvi on 30-12-2017.
 */

public class LessMovieDetailAdapter extends RecyclerView.Adapter<LessMovieDetailAdapter.MovieViewHolder> {
    private ArrayList<MovieDetail> movieDetailArrayList;

    public LessMovieDetailAdapter(ArrayList<MovieDetail> movieDetailArrayList) {
        this.movieDetailArrayList = movieDetailArrayList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.less_movie_detail,parent,false);
        return new MovieViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
       MovieDetail movieDetail=movieDetailArrayList.get(position);
       holder.movieTitle.setText(movieDetail.getTitle());
       holder.movieVoteCount.setText(String.valueOf(movieDetail.getVote_count())+" Votes");
       holder.movieAvgVote.setText(String.valueOf((int)(movieDetail.getVote_average()*10))+"%");
       String dateInString=movieDetail.getRelease_date();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       try {

            Date date = formatter.parse(dateInString);
            formatter = new SimpleDateFormat("MMM d, yyyy");
            dateInString=formatter.format(date);


        } catch (ParseException e) {
            e.printStackTrace();
        }
       holder.movieReleaseDate.setText(dateInString);
       String baseURL ="https://image.tmdb.org/t/p/";
       String size="w185";
       Uri uri = Uri.parse(baseURL+size+movieDetail.getPoster_path());
       holder.movieImage.setImageURI(uri);
       //if(movieDetail.getIsAdult())
       //    holder.movieIsAdult.setText("A");


    }

    @Override
    public int getItemCount() {
        return movieDetailArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
    { public TextView movieTitle,movieAvgVote,movieVoteCount,movieReleaseDate,movieIsAdult;
        SimpleDraweeView  movieImage;


        public MovieViewHolder(View itemView) {
            super(itemView);
            movieTitle=itemView.findViewById(R.id.movieTitle);
            movieAvgVote=itemView.findViewById(R.id.vote_avg_tv);
            movieVoteCount=itemView.findViewById(R.id.vote_no_tv);
            movieReleaseDate=itemView.findViewById(R.id.movieReleaseDate);
            movieImage=itemView.findViewById(R.id.movieImage);
            //movieIsAdult=itemView.findViewById(R.id.isMovieAdult);


        }
    }



}
