package com.troncoguide.androidapp.troncoguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RatingBar;

import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Network.INetworkResponseHandler;
import com.troncoguide.androidapp.troncoguide.Network.MovieNetworkData;
import com.troncoguide.androidapp.troncoguide.Network.VolleyHttp;


public class FullScreenDetailActivity extends AppCompatActivity implements INetworkResponseHandler {

    private static final String TAG = "FullDetail";
    private String imagUrl;
    private NetworkImageView poster;
    private NetworkImageView back_poster;
    private SliderLayout mDemoSlider;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        mDemoSlider.setDuration(4000);
        mDemoSlider.setPresetTransformer(6);



     Intent i = getIntent();
String id = i.getStringExtra("ID");

        VolleyHttp volley = VolleyHttp.getInstance(this);
        MovieNetworkData mov = new MovieNetworkData(this,volley,getApplicationContext());
        mov.detail(id);

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                RatingBar r = (RatingBar) findViewById(R.id.rating_var);
//                r.setRating((float) (mov.vote_avg / 2));
//                TextSliderView t = new TextSliderView(getApplicationContext());
//                t.description(mov.title).image(mov.poster_img).setScaleType(BaseSliderView.ScaleType.Fit);
//                ;
//                mDemoSlider.addSlider(t);
//                TextSliderView t1 = new TextSliderView(getApplicationContext());
//                Log.d(TAG, "run: " + mov.poster_img);
//                Log.d(TAG, "run: " + mov.back_drop_img);
//                t1.description(mov.title).image(mov.back_drop_img).setScaleType(BaseSliderView.ScaleType.Fit);
//                ;
//                mDemoSlider.addSlider(t1);
//            }
//        });
    }


    @Override
    public <T> void onResponse(T response) {

    }

    @Override
    public <T> void onError(T error) {

    }
}
