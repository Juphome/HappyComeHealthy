package com.happycomehealthy.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * creator: ZZF
 * careate date: 2017/10/24  20:14.
 */

public class JsonConverterFactory extends Converter.Factory {

    public static JsonConverterFactory create() {
        return create(new Gson());
    }

    public static JsonConverterFactory create(Gson gson) {
        return new JsonConverterFactory(gson);
    }

    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        if (gson == null)
            throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    /**
     * 返回响应ResponseBody
     * @param type
     * @param annotations
     * @param retrofit
     * @return
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(gson,type);
    }
}