package com.anagualter.popularmovies;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class MovieDetailFragment extends Fragment {


    private View movieDetailView;
    private int movieId;
    private String backdropPath;
    private String posterPath;
    private String movieTitle;
    private String movieReleaseDate;
    private String movieOverview;
    private String movieRating;


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieId = getArguments().getInt(Constants.Api.MOVIE_ID);
            backdropPath = getArguments().getString(Constants.Api.MOVIE_BACKDROP_PATH);
            posterPath = getArguments().getString(Constants.Api.MOVIE_POSTER_PATH);
            movieTitle = getArguments().getString(Constants.Api.MOVIE_TITLE);
            movieReleaseDate = getArguments().getString(Constants.Api.MOVIE_RELEASE_DATE);
            movieOverview = getArguments().getString(Constants.Api.MOVIE_OVERVIEW);
            movieRating = getArguments().getString(Constants.Api.MOVIE_VOTE_AVERAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movieDetailView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView backdrop = (ImageView) movieDetailView.findViewById(R.id.backdrop);
        ImageView poster = (ImageView) movieDetailView.findViewById(R.id.moviePoster);

        TextView title = (TextView) movieDetailView.findViewById(R.id.title);
        TextView releaseDate = (TextView) movieDetailView.findViewById(R.id.release_date);
        TextView overview = (TextView) movieDetailView.findViewById(R.id.overview);
        TextView ratings = (TextView) movieDetailView.findViewById(R.id.rating);

        title.setText(movieTitle);
        if(movieReleaseDate.isEmpty() || movieReleaseDate.equals("null")){
            releaseDate.setText(getResources().getString(R.string.release_date_label) +" " + "Not Found");
        } else{
            releaseDate.setText(getResources().getString(R.string.release_date_label) +" " + movieReleaseDate);
        }

        if(movieOverview.isEmpty() || movieOverview.equals("null")) {
            overview.setText(getResources().getString(R.string.no_overview));
        } else{
            overview.setText(movieOverview);
        }

        ratings.setText(movieRating);

        Picasso.with(getActivity())
                .load(backdropPath)
                .noFade()
                .into(backdrop);

        Picasso.with(getActivity())
                .load(posterPath)
                .placeholder(R.color.primary)
                .noFade()
                .into(poster);


        return movieDetailView;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
