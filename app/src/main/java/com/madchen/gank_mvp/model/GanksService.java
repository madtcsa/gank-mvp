package com.madchen.gank_mvp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chenwei on 2017/4/4.
 */

public interface GanksService {


    @GET("data/{type}/10/{pageIndex}")
    Call<Ganks> getGanks(@Path("type") String type, @Path("pageIndex") int pageIndex);

}
