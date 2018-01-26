package com.example.tanvi.moviemania.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanvi.moviemania.Activites.AboutAMovieActivity;
import com.example.tanvi.moviemania.Activites.DisplayActivity;
import com.example.tanvi.moviemania.Activites.MoreAboutActivity;
import com.example.tanvi.moviemania.Adapters.GenreAdapter;
import com.example.tanvi.moviemania.Adapters.LessMovieDetailAdapter;
import com.example.tanvi.moviemania.Networking.ApiClient;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Genre;
import com.example.tanvi.moviemania.Templates.GenreCover;
import com.example.tanvi.moviemania.Templates.MovieDetail;
import com.example.tanvi.moviemania.Templates.MovieDetailCover;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.tanvi.moviemania.Activites.MainActivity.api_key;


public class MoviesTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG ="JSON DATA";
    private Context context;

    private ArrayList<Genre> genreArrayList=new ArrayList<>();
    private ArrayList<MovieDetail> popularMovieArrayList=new ArrayList<>();
    private ArrayList<MovieDetail> upcomingMovieList=new ArrayList<>();


    private GenreAdapter genreAdapter;
    private LessMovieDetailAdapter movieDetailAdapter,upcomingMovieAdapter;

    private RecyclerView genreRecyclerView,movieRecyclerView,upcomingMovieRecyclerView;
    private RecyclerView.LayoutManager genreLayoutManager,popMovieLayoutManager,upcomingMovieLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MoviesTab() {
        // Required empty public constructor
    }


//    // TODO: Rename and change types and number of parameters
//    public static MoviesTab newInstance(String param1, String param2) {
//        MoviesTab fragment = new MoviesTab();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movies_tab, container, false);

        genreRecyclerView = (RecyclerView) view.findViewById(R.id.genreRecyclerView);
        movieRecyclerView=(RecyclerView) view.findViewById(R.id.popularMovieRecyclerView);
        upcomingMovieRecyclerView=view.findViewById(R.id.upcomingMovieRecyclerView);
        movieRecyclerView.setHasFixedSize(true);
        genreRecyclerView.setHasFixedSize(true);
        upcomingMovieRecyclerView.setHasFixedSize(true);

        genreAdapter=new GenreAdapter(genreArrayList);
        movieDetailAdapter=new LessMovieDetailAdapter(popularMovieArrayList,1);
        upcomingMovieAdapter=new LessMovieDetailAdapter(upcomingMovieList,1);


        genreLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        popMovieLayoutManager =new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        upcomingMovieLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        genreRecyclerView.setLayoutManager(genreLayoutManager);
        genreRecyclerView.setItemAnimator(new DefaultItemAnimator());

        movieRecyclerView.setLayoutManager(popMovieLayoutManager);
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());

        upcomingMovieRecyclerView.setLayoutManager(upcomingMovieLayoutManager);
        upcomingMovieRecyclerView.setItemAnimator(new DefaultItemAnimator());

        genreRecyclerView.setAdapter(genreAdapter);
        movieRecyclerView.setAdapter(movieDetailAdapter);
        upcomingMovieRecyclerView.setAdapter(upcomingMovieAdapter);


        movieRecyclerView.addOnItemTouchListener(new LessMovieDetailAdapter.RecyclerTouchListener(getContext(),movieRecyclerView, new LessMovieDetailAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MovieDetail movie = popularMovieArrayList.get(position);
                Toast.makeText(getContext(),  movie.getTitle()+" is selected!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(), AboutAMovieActivity.class);
               // i.putExtra("movieId",popularMovieArrayList.get(position).getId());
                i.putExtra("MovieDetail",(Serializable)popularMovieArrayList.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        upcomingMovieRecyclerView.addOnItemTouchListener(new LessMovieDetailAdapter.RecyclerTouchListener(getContext(),upcomingMovieRecyclerView, new LessMovieDetailAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MovieDetail movie = upcomingMovieList.get(position);
                Toast.makeText(getContext(),  movie.getTitle()+" is selected!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(), AboutAMovieActivity.class);
                // i.putExtra("movieId",popularMovieArrayList.get(position).getId());
                i.putExtra("MovieDetail",(Serializable)upcomingMovieList.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        getDataForRecyclerViews();
        TextView morePopMovies=view.findViewById(R.id.morePopMovies);
        morePopMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MoreAboutActivity.class);
                Bundle b=new Bundle();

                // 1.popular movie  2.upcoming movies 3.top rated 4.now playing

                b.putSerializable("MovieList",(Serializable)popularMovieArrayList);
                b.putInt("movieType",1);
                i.putExtra("bundle",b);

                startActivity(i);
            }
        });

        TextView moreUpcomingMovies=view.findViewById(R.id.moreUpcomingMovies);
        moreUpcomingMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MoreAboutActivity.class);
                Bundle b=new Bundle();

                // 1.popular movie  2.upcoming movies 3.top rated 4.now playing

                b.putSerializable("MovieList",(Serializable)upcomingMovieList);
                b.putInt("movieType",2);
                i.putExtra("bundle",b);

                startActivity(i);
            }
        });

        return view;


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void getDataForRecyclerViews() {

        getGenre();
        getPopularMovie();
        getUpcomingMovies();








    }


    private void getGenre()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this.context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<GenreCover> call= ApiClient.getInterface().getGenre(api_key);
        call.enqueue(new Callback<GenreCover>() {

            @Override
            public void onResponse(Call<GenreCover> call, Response<GenreCover> response) {
                progressDialog.dismiss();
                GenreCover genreCover=response.body();
                genreArrayList=genreCover.getGenreList();
                genreAdapter=new GenreAdapter(genreArrayList);
                genreRecyclerView.setAdapter(genreAdapter);




                Log.i(TAG+" popularMovies", "onResponse: "+genreArrayList.get(0).getName());
               // Toast.makeText(getActivity(),response.body().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GenreCover> call, Throwable t) {
                progressDialog.dismiss();
                Log.i(TAG+" popularMovies", "onFailure: "+t.getMessage());
            }
        });

    }



    private void getPopularMovie()
    {


        Call<MovieDetailCover> call= ApiClient.getInterface().getPopularMovie(api_key,1);
        call.enqueue(new Callback<MovieDetailCover>() {

            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {

               MovieDetailCover movieDetailCover=response.body();
               popularMovieArrayList=movieDetailCover.getMovieDetails();
                movieDetailAdapter=new LessMovieDetailAdapter(popularMovieArrayList,1);
                movieRecyclerView.setAdapter(movieDetailAdapter);


                Log.i(TAG, "onResponse: "+response.body());

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
    private void getUpcomingMovies() {

        Call<MovieDetailCover> call= ApiClient.getInterface().getUpcomingMovie(api_key,1);
        call.enqueue(new Callback<MovieDetailCover>() {

            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {

                MovieDetailCover movieDetailCover=response.body();
                upcomingMovieList=movieDetailCover.getMovieDetails();

                upcomingMovieAdapter=new LessMovieDetailAdapter(upcomingMovieList,1);
                upcomingMovieRecyclerView.setAdapter(upcomingMovieAdapter);



                Log.i(TAG, "onResponse: "+response.body());

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

}
