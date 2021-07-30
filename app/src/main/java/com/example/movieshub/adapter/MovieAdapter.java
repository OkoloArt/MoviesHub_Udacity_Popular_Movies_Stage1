package com.example.movieshub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieshub.DetailActivity;
import com.example.movieshub.R;
import com.example.movieshub.model.MovieResults;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final List<MovieResults> movieList;

    public MovieAdapter(Context context, List<MovieResults> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {
        MovieResults movie = movieList.get(position);
        holder.movieTitle.setText(movie.getTitle());

        String plotSynopsis = movie.getOverview();
        String rating = movie.getVoteAverage();
        String releaseDate = movie.getReleaseDate();

        String image = "https://image.tmdb.org/t/p/w342" + movie.getPosterPath();
        Picasso.with(context).load(image).into(holder.movieImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("title", movie.getTitle());
                detailIntent.putExtra("plot", plotSynopsis);
                detailIntent.putExtra("rating", rating);
                detailIntent.putExtra("date", releaseDate);
                detailIntent.putExtra("image", image);
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView movieImage;
        private final TextView movieTitle;
        private final CardView cardView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieTitle = itemView.findViewById(R.id.movie_title);
            cardView = itemView.findViewById(R.id.cardView_display);
        }
    }
}
