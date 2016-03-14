package com.troncoguide.androidapp.troncoguide.Fragments.MostPopular.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.troncoguide.androidapp.troncoguide.R;

/**
 * Created by Alejandro on 12/11/2015.
 */
public class MostPopularViewHolder extends RecyclerView.ViewHolder{
    NetworkImageView poster;
    RatingBar mRating;
    TextView mTitle;
    TextView mVotesCount;



    public MostPopularViewHolder(View itemView) {
        super(itemView);
        poster = (NetworkImageView) itemView.findViewById(R.id.news_poster_img);
        mRating = (RatingBar) itemView.findViewById(R.id.rating_var);
        mTitle = (TextView) itemView.findViewById(R.id.movie_title);
        mVotesCount = (TextView) itemView.findViewById(R.id.votes_count);
        mRating.setMax(10);
        mRating.setStepSize(0.5f);
    }
}
