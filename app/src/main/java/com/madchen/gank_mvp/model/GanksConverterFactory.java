package com.madchen.gank_mvp.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GanksConverterFactory extends Converter.Factory {

    private static final GanksConverterFactory INSTANCE = new GanksConverterFactory();

    private GanksConverterFactory() {
        super();
    }

    public static GanksConverterFactory create() {
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == Ganks.class) {
            return GanksConverter.INSTANCE;
        }
        return null;
    }
}
