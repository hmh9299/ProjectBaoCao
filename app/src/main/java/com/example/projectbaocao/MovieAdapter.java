package com.example.projectbaocao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter {

    private List<Movie> movieList;
    private Activity activity;

    public MovieAdapter(@NonNull Context context, int resource,List<Movie> list ) {
        super(context,resource);
        this.movieList = list;
    }

    class LayoutHandler{
        private TextView tvName,tvType,tvDate,tvDescription;
        private RatingBar review;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutHandler layoutHandler;
        if(v==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.movie_card,null);
            layoutHandler = new LayoutHandler();
            layoutHandler.tvName = v.findViewById(R.id.cardmovie_name);
            layoutHandler.tvType = v.findViewById(R.id.cardmovie_type);
            layoutHandler.tvDate = v.findViewById(R.id.cardmovie_date);
            layoutHandler.review = v.findViewById(R.id.cardmovie_review);
            layoutHandler.tvDescription = v.findViewById(R.id.cardmovie_description);
            v.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) v.getTag();
        }
        Movie movie = (Movie) movieList.get(position);
        layoutHandler.tvName.setText(movie.getName()+ " - ID: "+movie.getId());
        layoutHandler.tvType.setText(movie.getType());
        layoutHandler.tvDate.setText(movie.getDate());
        layoutHandler.review.setRating(movie.getReview());
        layoutHandler.tvDescription.setText(movie.getDescription());
        return v;
    }
    public void swapItems(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }
}
