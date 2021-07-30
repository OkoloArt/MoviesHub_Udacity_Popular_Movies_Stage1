package com.example.movieshub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

   @SerializedName("results")
   private List<MovieResults> results;

   public Movie(List<MovieResults> results) {
      this.results = results;
   }

   public List<MovieResults> getResults() {
      return results;
   }

   public void setResults(List<MovieResults> results) {
      this.results = results;
   }
}
