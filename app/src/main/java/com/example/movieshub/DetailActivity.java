package com.example.movieshub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String image, title, date, rating, plot = "";
    private ImageView backImage, movieImage;
    private TextView movieName, movieDate, movieRating, moviePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        backImage = findViewById(R.id.backImage);
        movieImage = findViewById(R.id.movie_mainImage);
        movieName = findViewById(R.id.detail_movieTitle);
        movieDate = findViewById(R.id.detail_movieDate);
        movieRating = findViewById(R.id.detail_movieRating);
        moviePlot = findViewById(R.id.overView);

        Intent intent = getIntent();

        if (intent != null) {

            image = intent.getStringExtra("image");
            title = intent.getStringExtra("title");
            date = intent.getStringExtra("date");
            rating = intent.getStringExtra("rating");
            plot = intent.getStringExtra("plot");

            movieName.setText(title);
            movieDate.setText(date);
            movieRating.setText(rating);
            moviePlot.setText(plot);

            Picasso.with(this).load(image).into(backImage);
            Picasso.with(this).load(image).into(movieImage);

            getSupportActionBar().setTitle(title);
        }
    }
}