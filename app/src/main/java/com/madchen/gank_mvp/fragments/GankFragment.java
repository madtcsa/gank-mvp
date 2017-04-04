package com.madchen.gank_mvp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private String fragmentContentCategory;
    private GanksPresenter mGanksPresenterImpl;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private boolean isFirstVisible = false;
    private int pageIndex = 1;

    public GankFragment() {
        mGanksPresenterImpl = new GanksPresenterImpl(this);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if (args != null) {
            fragmentContentCategory = args.getString(KEY_FRAGMENT_CONTENT_CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank_layout, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        isFirstVisible = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirstVisible) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(new GanksListAdapter(getContext(),new ArrayList<Ganks.Results>()));
            mProgressBar.setVisibility(View.VISIBLE);
            mGanksPresenterImpl.getGankList(fragmentContentCategory, pageIndex);
            isFirstVisible = false;
        }
    }

    @Override
    public void showGanks(Ganks ganks) {
        ((GanksListAdapter)mRecyclerView.getAdapter()).setResultsList(ganks.getResultsList());
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void gankDetail() {

    }

}
