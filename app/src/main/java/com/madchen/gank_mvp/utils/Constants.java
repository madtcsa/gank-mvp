package com.madchen.gank_mvp.utils;

/**
 * Created by chenwei on 2017/4/4.
 */

public interface Constants {

    interface GankCategory {
        String all = "all";
        String _android = "Android";
        String ios = "iOS";
        String restVideo = "休息视频";
        String welfare = "福利";
        String expandRes = "拓展资源";
        String frontEnd = "前端";
        String recommend = "瞎推荐";
        String app = "App";
    }

    interface GankUrl {

        String baseUrl = "http://gank.io/api/";
        String allDataUrl = "data";
        String searchUrl = "search/query";
        String targerDayContentUrl = "history/content/day";

        String perPageCount = "/10/";
    }
}
