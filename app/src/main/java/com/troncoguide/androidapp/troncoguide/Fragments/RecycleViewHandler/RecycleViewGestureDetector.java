package com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by serret887 on 3/11/16.
 */
//thsi is teh implementtation of the gesture detector for my recycleView
public class RecycleViewGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private static final String TAG = "RV_Gest_Detect";
    private IRecycleViewEventItem mTouch;
    private RecyclerView recyclerView;

    public RecycleViewGestureDetector(IRecycleViewEventItem mTouch, RecyclerView recyclerView) {
        this.mTouch = mTouch;
        this.recyclerView = recyclerView;

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp() called with: " + "e = [" + e + "]");
        View v = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (v != null && mTouch != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            mTouch.OnClick(v,position);
            Log.d(TAG, "onSingleTapUp() HANDLED");
            return true;
        }
        return false;
    }
}
