package com.anagualter.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements MoviesAsyncTask.AsyncResponse {
    private static int index;
    private GridView movieGridView;
    private ArrayList<Movies> movies;
    private MoviesGridViewAdapter mAdapter;
    private MoviesAsyncTask moviesAsyncTask;
    private String key;

    private SharedPreferences sortPrefs;
    private SharedPreferences.Editor editor;
    private Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieGridView = (GridView) findViewById(R.id.movieGridView);
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
        sortPrefs = getSharedPreferences("sort_prefs", 0);
        editor = sortPrefs.edit();
        if(sortPrefs.contains("sort_by")){
            key = sortPrefs.getString("sort_by", null);
        } else{
            key = Constants.Api.KEY_SORT_POPULAR;
        }

        if(savedInstanceState == null) {
            Log.i("Cycle", "savedInstanceState null onCreate");
            initializeAsyncTask();
        }
        else {
            Log.i("Cycle", "savedInstanceState onCreate");
            movies =  savedInstanceState.getParcelableArrayList("movie_parcel");
            processFinish(movies);
        }
    }

    public void processFinish(ArrayList<Movies> moviesList){

        movies = moviesList;
        mAdapter = new MoviesGridViewAdapter(LayoutInflater.from(this),movies);
        movieGridView.setAdapter(mAdapter);
        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies movie = movies.get(position);

                Intent movieIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
                movieIntent.putExtra(Constants.Api.MOVIE_ID, movie.getId());
                movieIntent.putExtra(Constants.Api.MOVIE_TITLE, movie.getTitle());
                movieIntent.putExtra(Constants.Api.MOVIE_OVERVIEW, movie.getOverview());
                movieIntent.putExtra(Constants.Api.MOVIE_RELEASE_DATE, movie.getReleaseDate());
                movieIntent.putExtra(Constants.Api.MOVIE_BACKDROP_PATH, movie.getBackdropPath());
                movieIntent.putExtra(Constants.Api.MOVIE_POSTER_PATH, movie.getPosterPath());
                movieIntent.putExtra(Constants.Api.MOVIE_VOTE_AVERAGE, String.valueOf(movie.getVoteAverage()));
                index = movieGridView.getFirstVisiblePosition();
                startActivity(movieIntent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("Cycle", "onSaveInstanceState");
        outState.putParcelableArrayList("movie_parcel", movies);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("savedInstance Async", "have");
        if(savedInstanceState !=null) {
            movies =  savedInstanceState.getParcelableArrayList("movie_parcel");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_popular) {
            key = Constants.Api.KEY_SORT_POPULAR;
            editor.putString("sort_by",  Constants.Api.KEY_SORT_POPULAR);
            editor.commit();
            initializeAsyncTask();

            return true;

        } else if(id == R.id.sort_highest){
            key = Constants.Api.KEY_SORT_HIGHEST_RATED;
            editor.putString("sort_by", Constants.Api.KEY_SORT_HIGHEST_RATED);
            editor.commit();
            initializeAsyncTask();

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Cycle", "onResume");
        movieGridView.setSelection(index);

    }

    @Override
    protected void onPause() {
        super.onPause();
        movieGridView.setSelection(index);
        Log.i("Cycle", "onPause");
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

    }

    private void initializeAsyncTask() {
        moviesAsyncTask  = new MoviesAsyncTask(this);
        moviesAsyncTask.execute(key);
        moviesAsyncTask.delegate = this;
    }
}
