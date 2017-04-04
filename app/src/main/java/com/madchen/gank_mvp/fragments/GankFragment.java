package com.madchen.gank_mvp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madchen.gank_mvp.R;
import com.madchen.gank_mvp.presenter.GanksPresenter;
import com.madchen.gank_mvp.presenter.GanksPresenterImpl;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GankFragment extends Fragment implements IView {

    public static final String KEY_FRAGMENT_CONTENT_CATEGORY = "categoryKey";
    private String fragmentContentCategory;
    private GanksPresenter mGanksPresenterImpl;

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
        View view = inflater.inflate(R.layout.frgment_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(fragmentContentCategory);
        mGanksPresenterImpl.getGankList(fragmentContentCategory,1);
        return view;
    }

    @Override
    public void showGankList() {

    }

    @Override
    public void gankDetail() {

    }
}
