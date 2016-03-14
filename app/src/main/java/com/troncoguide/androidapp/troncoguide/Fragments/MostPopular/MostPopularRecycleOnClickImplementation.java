package com.troncoguide.androidapp.troncoguide.Fragments.MostPopular;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler.IRecycleViewEventItem;
import com.troncoguide.androidapp.troncoguide.FullScreenDetailActivity;


/**
 * Created by serret887 on 3/11/16.
 */
public class MostPopularRecycleOnClickImplementation implements IRecycleViewEventItem {
    private static final String TAG = "DISCOVERY";
    Context ctx;

    public MostPopularRecycleOnClickImplementation(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void OnClick(View v,int position) {
        Intent i = new Intent(ctx, FullScreenDetailActivity.class);
        Bundle b = new Bundle();



        Log.d(TAG, "OnClick: "+position);
        b.putString("NAME","http://image.tmdb.org/t/p/w342//qmDpIHrmpJINaRKAfWQfftjCdyi.jpg");

        b.putString("IMAGE_URL","http://image.tmdb.org/t/p/w342//qmDpIHrmpJINaRKAfWQfftjCdyi.jpg");
        b.putString("IMAGE_BACK","http://image.tmdb.org/t/p/w342//yTdTuJww8NnL9YLaxL2LxDG5uQ7.jpg");
        b.putInt("position",position);
        i.putExtras(b);
        ctx.startActivity(i);
    }
}