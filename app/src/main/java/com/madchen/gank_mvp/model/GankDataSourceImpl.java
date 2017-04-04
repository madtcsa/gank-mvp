package com.madchen.gank_mvp.model;

import android.util.Log;

import com.madchen.gank_mvp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GankDataSourceImpl implements GankDataSource,Constants.GankUrl {

    @Override
    public void loadGanks(String type, int pageIndex, final LoadGanksCallBack loadGankCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GanksConverterFactory.create())
                .build();
        GanksService service = retrofit.create(GanksService.class);
        Call<Ganks> call = service.getGanks(type, pageIndex);
        call.enqueue(new Callback<Ganks>() {
            @Override
            public void onResponse(Call<Ganks> call, Response<Ganks> response) {
                Ganks ganks = response.body();
                Log.d("GanksPresenterImpl", "---Ganks--- " + ganks.getResultsList().size());
                loadGankCallBack.loadGankSuccess(ganks);
            }

            @Override
            public void onFailure(Call<Ganks> call, Throwable t) {
                Log.d("GanksPresenterImpl", "---Failed--- ");
                loadGankCallBack.dataNotAvailable();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadGankDetail(String gankId, LoadGankContentCallBack loadGankContentCallBack) {

    }
}
