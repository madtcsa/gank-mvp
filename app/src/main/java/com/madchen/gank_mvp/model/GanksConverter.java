package com.madchen.gank_mvp.model;

import com.google.gson.Gson;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GanksConverter implements Converter<ResponseBody, Ganks> {

    public static final GanksConverter INSTANCE = new GanksConverter();

    @Override
    public Ganks convert(ResponseBody value) throws IOException {
        String string = value.string();
        Ganks ganks = new Ganks();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(string);
            ganks.setError(jsonObject.optString("error"));
            List<Ganks.Results> resultsList = new ArrayList<>();
            Ganks.Results results;
            JSONArray jsonArray = jsonObject.optJSONArray("results");
            for (int index = 0; index < jsonArray.length(); index++) {
                results = new Ganks.Results();
                JSONObject obj = jsonArray.optJSONObject(index);
                results.set_id(obj.optString("_id"));
                results.setCreatedAt(obj.optString("createdAt"));
                results.setDesc(obj.optString("desc"));
                results.setPublishedAt(obj.optString("publishedAt"));
                results.setSource(obj.optString("source"));
                results.setType(obj.optString("type"));
                results.setUrl(obj.optString("url"));
                results.setUsed(obj.optString("true"));
                results.setWho(obj.optString("who"));
                JSONArray urlArray = obj.optJSONArray("images");
                if (urlArray != null) {
                    Log.d("GanksConverter", "---urlArray---- " + urlArray.length());
                    List<String> imgUrlList = new ArrayList<>();
                    for (int i = 0; i < urlArray.length(); i++) {
                        imgUrlList.add(urlArray.optString(i));
                    }
                    results.setImgUrls(imgUrlList);
                }
                resultsList.add(results);
            }
            ganks.setResultsList(resultsList);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return ganks;
    }
}
