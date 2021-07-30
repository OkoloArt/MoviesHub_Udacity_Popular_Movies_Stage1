package com.example.movieshub;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieshub.adapter.MovieAdapter;
import com.example.movieshub.model.Movie;
import com.example.movieshub.model.MovieResults;
import com.example.movieshub.network.MovieApiInterface;
import com.example.movieshub.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "7af2394d40b06bda9cd7d96cb7a29d3f";
    private static final String LANGUAGE = "en-US";
    private static final int PAGES = 1;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<MovieResults> movieResultsList = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar_display);
        recyclerView = findViewById(R.id.rv_display);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(false);

        retreivePopularMovieJson(API_KEY, LANGUAGE, PAGES);
    }

    public void retreivePopularMovieJson(String apiKey, String language, int pages) {

        Call<Movie> movieCall = RetrofitInstance.getRetrofitInstance().create(MovieApiInterface.class).getPopularMovies(apiKey, language, pages);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    movieResultsList.clear();
                    movieResultsList = response.body().getResults();
                    movieAdapter = new MovieAdapter(MainActivity.this, movieResultsList);
                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.top_rated) {
            retreiveTopRatedMovieJSON(API_KEY, LANGUAGE, PAGES);
        }
        return super.onOptionsItemSelected(item);
    }

    public void retreiveTopRatedMovieJSON(String api, String language, int pages) {

        Call<Movie> call = RetrofitInstance.getRetrofitInstance().create(MovieApiInterface.class).getTopRatedMovies(api, language, pages);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    movieResultsList.clear();
                    movieResultsList = response.body().getResults();
                    movieAdapter = new MovieAdapter(MainActivity.this, movieResultsList);
                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}