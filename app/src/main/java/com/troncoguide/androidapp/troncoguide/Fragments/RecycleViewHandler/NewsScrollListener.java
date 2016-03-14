package com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by serret887 on 3/12/16.
 */
public class NewsScrollListener extends RecyclerView.OnScrollListener  {
    private final  IScrollAction action;
    int page, total_pages = 1;
    int current_page = 0;

    public NewsScrollListener(IScrollAction action){
        this.action = action;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        GridLayoutManager mLayout = (GridLayoutManager) recyclerView.getLayoutManager();

        int totalItemCount = mLayout.getItemCount();
        int firstVisibleItemIndex = mLayout.findLastCompletelyVisibleItemPosition();
        page = totalItemCount / 20;
        if(page>total_pages)
            total_pages = page;
        if (page == total_pages) {
            current_page = Math.round((float) firstVisibleItemIndex / 20);

            if (current_page == page ) {
                page++;
                action.performAction(this.page);
                total_pages = page;
            }
        }

        super.onScrolled(recyclerView, dx, dy);
    }
}
