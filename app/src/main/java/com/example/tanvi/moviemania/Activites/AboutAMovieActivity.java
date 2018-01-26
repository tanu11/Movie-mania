package com.example.tanvi.moviemania.Activites;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanvi.moviemania.Adapters.CastAdapter;
import com.example.tanvi.moviemania.Adapters.CrewAdapter;
import com.example.tanvi.moviemania.Adapters.LessMovieDetailAdapter;
import com.example.tanvi.moviemania.Networking.ApiClient;
import com.example.tanvi.moviemania.R;
import com.example.tanvi.moviemania.Templates.Cast;
import com.example.tanvi.moviemania.Templates.Credits;
import com.example.tanvi.moviemania.Templates.Crew;
import com.example.tanvi.moviemania.Templates.Genre;
import com.example.tanvi.moviemania.Templates.GenreCover;
import com.example.tanvi.moviemania.Templates.MovieCompleteDetail;
import com.example.tanvi.moviemania.Templates.MovieDetail;
import com.example.tanvi.moviemania.Templates.MovieDetailCover;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tanvi.moviemania.Activites.MainActivity.api_key;

public class AboutAMovieActivity extends AppCompatActivity {
  private int movieId;
  private MovieCompleteDetail movieCompleteDetail;
  private ArrayList<MovieDetail> recommendedMovies=new ArrayList<>();
  private ArrayList<MovieDetail> similarMovies=new ArrayList<>();
  private ArrayList<Cast> castArrayList=new ArrayList<>();
  private RecyclerView castRecyclerView;
  private RecyclerView.LayoutManager castlayoutManager;
  private CastAdapter castAdapter;
  private ArrayList<Crew> crewArrayList=new ArrayList<>();
  private RecyclerView crewRecyclerView;
  private RecyclerView.LayoutManager crewlayoutManager;
  private CrewAdapter crewAdapter;


    private CollapsingToolbarLayout collapsingToolbarLayout;
  MovieDetail movieDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_amovie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        collapsingToolbarLayout=findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        castRecyclerView=findViewById(R.id.eCastRecyclerView);
        crewRecyclerView=findViewById(R.id.eCrewRecyclerView);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (appBarLayout.getTotalScrollRange() + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(movieDetail.getTitle());
                    isShow = true;

                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;

                }
            }
        });




        Bundle Data=getIntent().getExtras();
       // movieId=Data.getInt("movieId");
        movieDetail= (MovieDetail) Data.getSerializable("MovieDetail");
        movieId=movieDetail.getId();
        castRecyclerView.setHasFixedSize(true);
        crewRecyclerView.setHasFixedSize(true);



        Toast.makeText(this, "movieId"+movieId,Toast.LENGTH_SHORT).show();

        castlayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        castRecyclerView.setLayoutManager(castlayoutManager);
        castRecyclerView.setItemAnimator(new DefaultItemAnimator());
        castAdapter =new CastAdapter(castArrayList);
        castRecyclerView.setAdapter(castAdapter);

        crewlayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        crewRecyclerView.setLayoutManager(crewlayoutManager);
        crewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        crewAdapter =new CrewAdapter(crewArrayList);
        crewRecyclerView.setAdapter(crewAdapter);


        getMovieCompleteDetail();
        getMovieCredits();
        getRecommendations();
        getSimilarMovies();







    }

    private void getSimilarMovies() {
        Call<MovieDetailCover> call =ApiClient.getInterface().getSimilarMovies(movieId,api_key,1);
        call.enqueue(new Callback<MovieDetailCover>() {
            @Override
            public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {
                if(response.isSuccessful())
                {
                     similarMovies=response.body().getMovieDetails();

                }
                else
                    Toast.makeText(AboutAMovieActivity.this,"no result",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MovieDetailCover> call, Throwable t) {

            }
        });

    }

    private void getRecommendations() {
       Call<MovieDetailCover> call=ApiClient.getInterface().getRecommendedMovies(movieId,api_key,1);
       call.enqueue(new Callback<MovieDetailCover>() {
           @Override
           public void onResponse(Call<MovieDetailCover> call, Response<MovieDetailCover> response) {
               if (response.isSuccessful())
               recommendedMovies=response.body().getMovieDetails();

           }

           @Override
           public void onFailure(Call<MovieDetailCover> call, Throwable t) {

           }
       });
    }

    private void getMovieCompleteDetail() {
        Call<MovieCompleteDetail> call= ApiClient.getInterface().getMovieById(movieId,api_key);
        call.enqueue(new Callback<MovieCompleteDetail>() {
            @Override
            public void onResponse(Call<MovieCompleteDetail> call, Response<MovieCompleteDetail> response) {

                if(response.isSuccessful())
                 {  movieCompleteDetail= response.body();
                    //Toast.makeText(AboutAMovieActivity.this,movieCompleteDetail.getTagline(),Toast.LENGTH_SHORT).show();
                     //inside
                     String imagePath=movieCompleteDetail.getBackdrop_path();
                     SimpleDraweeView simpleDraweeView=findViewById(R.id.movieBackdropPoster);

                     String baseURL ="https://image.tmdb.org/t/p/";
                     String size="w780";
                     Uri uri = Uri.parse(baseURL+size+movieCompleteDetail.getBackdrop_path());
                     simpleDraweeView.setImageURI(uri);
                     fillDetails();
                    Log.i("TAG", "onResponse: " + response.body().toString());
                }
                else
                    Toast.makeText(AboutAMovieActivity.this,"no result",Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<MovieCompleteDetail> call, Throwable t) {
                Toast.makeText(AboutAMovieActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();


            }
        });

     }

    private void getMovieCredits()
    {
        Call<Credits> call=ApiClient.getInterface().getMovieCredits(movieId,api_key);
        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                if(response.isSuccessful()){
                    Credits credits=response.body();
                    int len=credits.getCast().size();
                    castArrayList=new ArrayList<>(credits.getCast().subList(0,Math.min(len,10)));
                    //String name=credits.getCast().get(0).getName();
                    //Toast.makeText(AboutAMovieActivity.this,name,Toast.LENGTH_SHORT).show();
                    castAdapter=new CastAdapter(castArrayList);
                    castRecyclerView.setAdapter(castAdapter);


                    len=credits.getCrew().size();

                    crewArrayList=new ArrayList<>(credits.getCrew().subList(0,Math.min(len,10)));

                    crewAdapter=new CrewAdapter(crewArrayList);
                    crewRecyclerView.setAdapter(crewAdapter);
                }
                else{
                    Toast.makeText(AboutAMovieActivity.this,"no results ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Credits> call, Throwable t) {

            }
        });

    }



    private void fillDetails() {


        TextView movieTitle=findViewById(R.id.eMovieTitle);
        TextView movieTagline=findViewById(R.id.eTagline);
        TextView movieGenre=findViewById(R.id.eGenre);
        TextView movieRuntime=findViewById(R.id.eRuntime);
        TextView movieRating=findViewById(R.id.eRating);
        TextView movieIsAdult=findViewById(R.id.eIsAdult);
        TextView movieVotes=findViewById(R.id.eVotes);
        TextView movieStatus=findViewById(R.id.eStatus);
        TextView movieOverview=findViewById(R.id.eOverView);



        movieTitle.setText(movieDetail.getTitle());
        movieTagline.setText(movieCompleteDetail.getTagline());
        StringBuilder stringBuilder=new StringBuilder();
        ArrayList<Genre> genre=movieCompleteDetail.getGenres();
        for(int i=0;i<genre.size();i++)
        {  stringBuilder.append(genre.get(i).getName());
           stringBuilder.append(" | ");

        }
        String str=stringBuilder.toString().substring(0,Math.max(0,stringBuilder.length()-2));
        if(str==null)
            str=" ";

        movieGenre.setText(str);


        if(movieCompleteDetail.isAdult())
            movieIsAdult.setText("A");
        else
            movieIsAdult.setText("UA");

        movieStatus.setText(movieCompleteDetail.getStatus());
        int hr=movieCompleteDetail.getRuntime();
        StringBuilder s=new StringBuilder();
        s.append(hr/60);
        s.append(" hr ");
        if(hr%60!=0) {
            s.append(hr % 60);
            s.append(" min");
        }

        movieRuntime.setText(s.toString());
        movieVotes.setText(movieDetail.getVote_count()+" Votes");
        movieRating.setText(String.valueOf((int)(movieDetail.getVote_average()*10))+"%");
        movieOverview.setText(movieCompleteDetail.getOverview());

    }
}



