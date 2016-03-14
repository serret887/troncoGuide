package com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by serret887 on 3/11/16.
 */
//simple OnItemTouchListener
public class RecycleTouchListener extends RecyclerView.SimpleOnItemTouchListener
{
    private static final String TAG = "RV_Touch_Listener";
    private final Context context;
    private GestureDetectorCompat mdetector;

    public RecycleTouchListener(Context ctx, RecycleViewGestureDetector gestureListener) {
        super();
        this.context = ctx;

        this.mdetector = new GestureDetectorCompat(ctx,gestureListener);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d(TAG, "onTouchEvent() called with: " + "rv = [" + rv + "], e = [" + e + "]");

        super.onTouchEvent(rv, e);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent() called with: " + "rv = [" + rv + "], e = [" + e + "]");
        boolean b = mdetector.onTouchEvent(e);
        Log.d(TAG, "onInterceptTouchEvent() returned: " + b);
        return false;
    }
}