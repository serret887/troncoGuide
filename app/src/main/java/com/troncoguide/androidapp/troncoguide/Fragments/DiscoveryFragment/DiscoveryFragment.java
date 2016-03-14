package com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment.Adapter.DiscoverAdapter;
import com.troncoguide.androidapp.troncoguide.Fragments.IFragAction;
import com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler.IScrollAction;
import com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler.NewsScrollListener;
import com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler.RecycleTouchListener;
import com.troncoguide.androidapp.troncoguide.Fragments.RecycleViewHandler.RecycleViewGestureDetector;
import com.troncoguide.androidapp.troncoguide.Network.INetworkResponseHandler;
import com.troncoguide.androidapp.troncoguide.Network.MovieNetworkData;
import com.troncoguide.androidapp.troncoguide.Network.VolleyHttp;
import com.troncoguide.androidapp.troncoguide.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
*Discovery Fragment is in charge of present the data of the
 * discovery link
 * this fragment is in charge of create the recycleView for display the data and
 * also is in charrge of load the discovery data from the content provider
 */
public class DiscoveryFragment extends Fragment implements INetworkResponseHandler, IScrollAction, IFragAction {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int LOADER_LIST_ID = 1;
    private static final String TAG = "DISCOVERY_FRAG";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private IFragAction mListener;
    private DiscoverAdapter mAdapter;
    private boolean performed;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoveryFragment.
     */
    // TODO: if for any casuality i need to pass params to this ffragment
    public static DiscoveryFragment newInstance(String param1, String param2) {

        DiscoveryFragment fragment = new DiscoveryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static DiscoveryFragment newInstance() {
        DiscoveryFragment fragment = new DiscoveryFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //here i should trigger the first call


        if(!performed){
      performAction(1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.news_list);
        int columns = 3;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 5;
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),columns));

        mAdapter = new DiscoverAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        RecycleTouchListener mRecycleClickListener = new RecycleTouchListener(getContext(), new RecycleViewGestureDetector(new DiscoveryRecycleOnClickImplementation(getContext()), mRecyclerView));
        mRecyclerView.addOnItemTouchListener(mRecycleClickListener);

        mRecyclerView.addOnScrollListener(new NewsScrollListener(this));





        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public <T> void onResponse(T datas) {

        mAdapter.setData((List<MovieDiscovery>) datas);
    }
    @Override
    public <T> void onError(T error) {

    }

    @Override
    public void sort(final String sort) {

            List<MovieDiscovery> movies = mAdapter.getData();
            Collections.sort(movies, new Comparator<MovieDiscovery>() {
                @Override
                public int compare(MovieDiscovery lhs, MovieDiscovery rhs) {
                    if (sort.equals("byName")) {
                        return lhs.title.compareTo(rhs.title);
                    }
                    if(sort.equals("byRating")){
                        return (lhs.vote_avg <rhs.vote_avg)?1:-1;
                    }
                return 0;
                }});

        mAdapter.setData(movies);

    }

    @Override
    public void performAction(int page) {
        VolleyHttp volley = VolleyHttp.getInstance(getContext());
        MovieNetworkData mov = new MovieNetworkData(this,volley,getContext());
        mov.discoveryPage(page);
        performed = true;
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }
}
