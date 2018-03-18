package com.example.tanvi.moviemania.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import java.lang.reflect.Array;
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
    private GenreAdapter genreAdapter;
    private RecyclerView genreRecyclerView;
    private RecyclerView.LayoutManager genreLayoutManager;



    private int noOfMovieType=4;

    private ArrayList<ArrayList<MovieDetail>> allTypesOfMovies=new ArrayList<>(noOfMovieType);
    private ArrayList<LessMovieDetailAdapter> allMovieAdapter=new ArrayList<>(noOfMovieType);
    private ArrayList<RecyclerView.LayoutManager> allMovieLayoutManager=new ArrayList<>();
    private ArrayList<RecyclerView> allMovieRecyclerView=new ArrayList<>(noOfMovieType);
    private ArrayList<TextView> allMoreTextViews=new ArrayList<>();




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MoviesTab() {
        // Required empty public constructor

           }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        for(int i=0;i<noOfMovieType;i++) {
            allTypesOfMovies.add(new ArrayList<MovieDetail>());
            allMovieRecyclerView.add(null);
            allMovieLayoutManager.add(null);
            allMovieAdapter.add(null);
        }





        getDataForRecyclerViews();
        Log.i("check", "onCreateView: insidr on create");




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movies_tab, container, false);


        genreRecyclerView = (RecyclerView) view.findViewById(R.id.genreRecyclerView);

        allMovieRecyclerView.set(0,(RecyclerView) view.findViewById(R.id.popularMovieRecyclerView));
        allMovieRecyclerView.set(1,(RecyclerView) view.findViewById(R.id.upcomingMovieRecyclerView));
        allMovieRecyclerView.set(2,(RecyclerView) view.findViewById(R.id.MovieType1));
        allMovieRecyclerView.set(3,(RecyclerView) view.findViewById(R.id.nowPlayingRecyclerView));


        genreRecyclerView.setHasFixedSize(true);
        genreAdapter=new GenreAdapter(genreArrayList);
        genreLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        genreRecyclerView.setLayoutManager(genreLayoutManager);
        genreRecyclerView.setItemAnimator(new DefaultItemAnimator());
        genreRecyclerView.setAdapter(genreAdapter);




        for(int iteratingMovie=0;iteratingMovie<noOfMovieType;iteratingMovie++) {
            allMovieRecyclerView.get(iteratingMovie).setHasFixedSize(true);
            allMovieAdapter.set(iteratingMovie,new LessMovieDetailAdapter(allTypesOfMovies.get(iteratingMovie),1));
            allMovieLayoutManager.set(iteratingMovie,new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            allMovieRecyclerView.get(iteratingMovie).setLayoutManager(allMovieLayoutManager.get(iteratingMovie));
            allMovieRecyclerView.get(iteratingMovie).setItemAnimator(new DefaultItemAnimator());
            allMovieRecyclerView.get(iteratingMovie).setAdapter(allMovieAdapter.get(iteratingMovie));
            final ArrayList<MovieDetail> list=allTypesOfMovies.get(iteratingMovie);
            if(list==null)
            Log.i(TAG, "onCreateView: "+"NULL");

            allMovieRecyclerView.get(iteratingMovie).addOnItemTouchListener(new LessMovieDetailAdapter.RecyclerTouchListener(getContext(), allMovieRecyclerView.get(iteratingMovie), new LessMovieDetailAdapter.ClickListener() {



                @Override
                public void onClick(View view, int position) {

                    MovieDetail movie = list.get(position);
//                Toast.makeText(getContext(),  list.size()+" is selected!", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getContext(), AboutAMovieActivity.class);
                    i.putExtra("movieId",movie.getId());
                    i.putExtra("MovieDetail",(Serializable)movie);
                    startActivity(i);

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }

        allMoreTextViews.add((TextView) view.findViewById(R.id.morePopMovies));
        allMoreTextViews.add((TextView) view.findViewById(R.id.moreUpcomingMovies));
        allMoreTextViews.add((TextView) view.findViewById(R.id.moreOfType1));
        allMoreTextViews.add((TextView) view.findViewById(R.id.moreOfNowPlaying));

        for(int i=0;i<noOfMovieType;i++){
            final ArrayList<MovieDetail> temp=allTypesOfMovies.get(i);
            final int index=i;
            allMoreTextViews.get(i).setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), MoreAboutActivity.class);
                    Bundle b=new Bundle();

                    // 1.popular movie  2.upcoming movies 3.top rated 4.now playing

                    b.putSerializable("MovieList",(Serializable)temp);
                    b.putInt("movieType",index+1);
                    intent.putExtra("bundle",b);

                    startActivity(intent);

                }
            });
        }








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
        getTopRatedMovies();
        getNowPlayingMovies();
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
                int identifier=0;
                MovieDetailCover movieDetailCover=response.body();

                allTypesOfMovies.get(identifier).clear();

                allTypesOfMovies.get(identifier).addAll( movieDetailCover.getMovieDetails());
                Log.i(TAG, "onResponse: pop movie "+allTypesOfMovies.get(identifier).get(0).getTitle());

                allMovieAdapter.set(identifier, new LessMovieDetailAdapter(allTypesOfMovies.get(identifier), 1));
                allMovieRecyclerView.get(identifier).setAdapter(allMovieAdapter.get(identifier));


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
                 int identifier=1;
                MovieDetailCover movieDetailCover=response.body();
                allTypesOfMovies.get(identifier).clear();
                allTypesOfMovies.get(identifier).addAll( movieDetailCover.getMovieDetails());

                allMovieAdapter.set(identifier, new LessMovieDetailAdapter(allTypesOfMovies.get(identifier), 1));
                allMovieRecyclerView.get(identifier).setAdapter(allMovieAdapter.get(identifier));



                Log.i(TAG, "onResponse: "+response.body());

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
    private void getTopRatedMovies()
    {


        Call<MovieDetailCover> call= ApiClient.getInterface().getTopRatedMovie(api_key,1);
        call.enqueue(new Callback<MovieDetailCover>() {

            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {

                if(response.isSuccessful()) {
                    int identifier=2;
                    MovieDetailCover movieDetailCover = response.body();
                    allTypesOfMovies.get(identifier).clear();
                    allTypesOfMovies.get(identifier).addAll( movieDetailCover.getMovieDetails());
                    Toast.makeText(getContext(),allTypesOfMovies.get(identifier).size()+" size",Toast.LENGTH_SHORT).show();

                    allMovieAdapter.set(identifier, new LessMovieDetailAdapter(allTypesOfMovies.get(identifier), 1));
                    allMovieRecyclerView.get(identifier).setAdapter(allMovieAdapter.get(identifier));


                    Log.i(TAG, "onResponse: Top Rated" + response.body().getMovieDetails().toString());

                }
                else
                    Toast.makeText(getContext(),"no top rated",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });


    }
    private void getNowPlayingMovies()
    {


        Call<MovieDetailCover> call= ApiClient.getInterface().getNowPlayingMovie(api_key,1);
        call.enqueue(new Callback<MovieDetailCover>() {

            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {

                if(response.isSuccessful()) {
                    int identifier=3;
                    MovieDetailCover movieDetailCover = response.body();
                    allTypesOfMovies.get(identifier).clear();
                    allTypesOfMovies.get(identifier).addAll( movieDetailCover.getMovieDetails());
                    Toast.makeText(getContext(),allTypesOfMovies.get(identifier).size()+" size",Toast.LENGTH_SHORT).show();

                    allMovieAdapter.set(identifier, new LessMovieDetailAdapter(allTypesOfMovies.get(identifier), 1));
                    allMovieRecyclerView.get(identifier).setAdapter(allMovieAdapter.get(identifier));


                    Log.i(TAG, "onResponse: now playing" + response.body().getMovieDetails().toString());

                }
                else
                    Toast.makeText(getContext(),"no top rated",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });


    }

}
