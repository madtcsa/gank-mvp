package com.madchen.gank_mvp.presenter;

/**
 * Created by chenwei on 2017/4/4.
 */

public interface GanksPresenter {

    void getGankList(String type,int pageIndex);

    void getGankContent(int gankId);
}
