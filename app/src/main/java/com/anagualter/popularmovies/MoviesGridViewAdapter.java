package com.anagualter.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<Movies> movieList;

    public MoviesGridViewAdapter(LayoutInflater inflater, ArrayList<Movies> movieList) {
        this.inflater= inflater;
        this.movieList = movieList;
    }
    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder {
        ImageView posterView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movies movie = movieList.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.movie_item, null);
        }
        holder = new ViewHolder();
        holder.posterView = (ImageView) convertView.findViewById(R.id.posterView);


            Picasso.with(parent.getContext())
                    .load(movie.getPosterPath())
                    .placeholder(R.color.primary)
                    .noFade()
                    .into(holder.posterView);

        return convertView;
    }


}
