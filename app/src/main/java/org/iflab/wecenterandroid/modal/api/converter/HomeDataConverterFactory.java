package org.iflab.wecenterandroid.modal.api.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by Lyn on 15/12/16.
 */
public class HomeDataConverterFactory  extends Converter.Factory {
    private final Gson gson;

    public static HomeDataConverterFactory create() {
        return create(new Gson());
    }

    public static HomeDataConverterFactory create(Gson gson) {
        return new HomeDataConverterFactory(gson);
    }

    private HomeDataConverterFactory(Gson gson) {
        if(gson == null) {
            throw new NullPointerException("gson == null");
        } else {
            this.gson = gson;
        }
    }

    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new HomeResponseBodyConverter(this.gson, type);
    }

    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new RequestBodyConverter(this.gson, type);
    }
}