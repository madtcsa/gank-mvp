package com.madchen.gank_mvp.presenter;

import android.util.Log;

import com.madchen.gank_mvp.fragments.IView;
import com.madchen.gank_mvp.model.GankDataSource;
import com.madchen.gank_mvp.model.GankDataSourceImpl;
import com.madchen.gank_mvp.model.Ganks;
import com.madchen.gank_mvp.model.GanksConverterFactory;
import com.madchen.gank_mvp.model.GanksService;
import com.madchen.gank_mvp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GanksPresenterImpl implements GanksPresenter, Constants.GankUrl, Constants.GankCategory {

    private IView gankFragment;
    private GankDataSource mGankDataSourceImpl;

    public GanksPresenterImpl(IView gankFragment) {
        this.gankFragment = gankFragment;
        mGankDataSourceImpl = new GankDataSourceImpl();
    }

    @Override
    public void getGankList(String type, int pageIndex) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GanksConverterFactory.create())
                .build();
        GanksService service =  retrofit.create(GanksService.class);
        Call<Ganks> call = service.getGanks(type,pageIndex);
        call.enqueue(new Callback<Ganks>() {
            @Override
            public void onResponse(Call<Ganks> call, Response<Ganks> response) {
                Ganks ganks = response.body();
                Log.d("GanksPresenterImpl", "---Ganks--- " + ganks.getResultsList().size());
            }

            @Override
            public void onFailure(Call<Ganks> call, Throwable t) {
                Log.d("GanksPresenterImpl", "---Failed--- ");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getGankContent(int gankId) {

    }
}
