package com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Network.VolleyHttp;
import com.troncoguide.androidapp.troncoguide.R;
import com.troncoguide.androidapp.troncoguide.Utils.Network.URLBuilderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 11/19/2015.
 */
public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverViewHolder> {
    private static final String TAG = "NEWS_ADAPT";
private List<MovieDiscovery> movies;
    private Context context;
VolleyHttp volleyHttp ;

    public DiscoverAdapter(Context ctx) {
        this.context = ctx;
        volleyHttp = VolleyHttp.getInstance(ctx);
        this.movies = new ArrayList<>();
    }

    public void setData(List<MovieDiscovery> data) {
        if(data!=null){
        int amount_data_in_adapter =getItemCount();
            int count_data = data.size();

            if(movies.containsAll(data)){
                movies = data;
                notifyDataSetChanged();
            }
            else{
                movies.addAll(data);
                amount_data_in_adapter =getItemCount();
            if (count_data > amount_data_in_adapter )
                notifyItemRangeChanged(getItemCount(), count_data);
            if (count_data <= amount_data_in_adapter )
                notifyDataSetChanged();
            }
        Log.d(TAG, "setData: " + movies.size() + " Data is already loaded");
        }}

    public List<MovieDiscovery> getData() {
        return movies;
    }

    //using network image
    private void imageHttpRequest(int position, final DiscoverViewHolder holder) {
//
        MovieDiscovery mov = movies.get(position);
        ImageLoader imageLoader = volleyHttp.getImageLoader();
        holder.poster.setImageUrl(mov.poster_img, imageLoader);
        holder.mTitle.setText(mov.title.toString().trim());
//        holder.hidden.setText(mov.id);

        holder.mRating.setRating((float)mov.vote_avg/2);
        Log.d(TAG, "imageHttpRequest: " + mov.vote_avg);

        holder.mVotesCount.setText("Votes: "+mov.vote_count);

////        Bitmap image = imageLoader.get(imageURL[position], ImageLoader.getImageListener(holder.poster, R.drawable.ads, R.drawable.ads)).getBitmap();
////        holder.poster.setImageBitmap(image);
////        holder.poster.setImageUrl(imageURL[position], imageLoader);
        Log.i(TAG, "image http request is already processed");
    }


    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_grid_item, parent, false);
        return new DiscoverViewHolder(v);
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        // TODO: 11/19/2015 first i need to check in the database

        imageHttpRequest(position, holder);
        }


    @Override
    public int getItemCount() {
        if (movies != null)
            return movies.size();
        return 0;
    }
}
