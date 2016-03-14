package com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler.IRecycleViewEventItem;
import com.troncoguide.androidapp.troncoguide.FullScreenDetailActivity;
import com.troncoguide.androidapp.troncoguide.R;


/**
 * Created by serret887 on 3/11/16.
 */
public class DiscoveryRecycleOnClickImplementation implements IRecycleViewEventItem {
    private static final String TAG = "DISCOVERY";
    Context ctx;

    public DiscoveryRecycleOnClickImplementation(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void OnClick(View v,int position) {
        Intent i = new Intent(ctx, FullScreenDetailActivity.class);
        Bundle b = new Bundle();

        TextView idTextView = ((TextView) v.findViewById(R.id.exp_hidden_id));
        String id = idTextView.getText().toString();
        Log.d(TAG, "OnClick: "+position);
        b.putString("ID",id);
        i.putExtras(b);
        ctx.startActivity(i);
    }
}