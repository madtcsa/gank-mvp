package com.madchen.gank_mvp.model;

import java.util.List;

/**
 * Created by chenwei on 2017/4/4.
 */

public interface GankDataSource {

    interface LoadGanksCallBack {

        void loadGankSuccess(Ganks ganks);

        void dataNotAvailable();
    }

    void loadGanks(int type, LoadGanksCallBack loadGanksCallBack);

    interface  LoadGankContentCallBack{
        void loadContentSuccess(Ganks ganks);
        void dataNotAvailable();
    }
    void loadGankDetail(String gankId,LoadGankContentCallBack loadGankContentCallBack);
}
