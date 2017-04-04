package com.madchen.gank_mvp.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.madchen.gank_mvp.R;
import com.madchen.gank_mvp.model.Ganks;
import com.madchen.gank_mvp.presenter.GanksPresenter;
import com.madchen.gank_mvp.presenter.GanksPresenterImpl;

import java.util.ArrayList;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GankFragment extends Fragment implements IView {

    public static final String KEY_FRAGMENT_CONTENT_CATEGORY = "categoryKey";
    public static final String KEY_CURRENT_FRAGMENT_INDEX = "fragmentIndex";
    private String fragmentContentCategory;
    private GanksPresenter mGanksPresenterImpl;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private int pageIndex = 1;
    private boolean isDefaultFragment = false;

    public GankFragment() {
        mGanksPresenterImpl = new GanksPresenterImpl(this);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if (args != null) {
            fragmentContentCategory = args.getString(KEY_FRAGMENT_CONTENT_CATEGORY);
            isDefaultFragment = args.getBoolean(KEY_CURRENT_FRAGMENT_INDEX);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank_layout, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        if (isDefaultFragment) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
            mRecyclerView.setAdapter(new GanksListAdapter(getContext(), new ArrayList<Ganks.Results>()));
            mProgressBar.setVisibility(View.VISIBLE);
            mGanksPresenterImpl.getGankList(fragmentContentCategory, pageIndex);
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("GankFragment", "-----setUserVisibleHint----- ");
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isDefaultFragment) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
            mRecyclerView.setAdapter(new GanksListAdapter(getContext(), new ArrayList<Ganks.Results>()));
            mProgressBar.setVisibility(View.VISIBLE);
            mGanksPresenterImpl.getGankList(fragmentContentCategory, pageIndex);
            isDefaultFragment = true;
        }
    }

    @Override
    public void showGanks(Ganks ganks) {
        ((GanksListAdapter) mRecyclerView.getAdapter()).setResultsList(ganks.getResultsList());
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void gankDetail() {

    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        private SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
