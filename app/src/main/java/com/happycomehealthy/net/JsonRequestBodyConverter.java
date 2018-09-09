package com.happycomehealthy.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * creator: ZZF
 * careate date: 2017/10/24  20:15.
 */

public class JsonRequestBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Gson mGson;
    private TypeAdapter<T> mAdapter;

    public JsonRequestBodyConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        T t = null;
        try {
            String jsonStr = value.string();

            JSONObject mainObj = new JSONObject(jsonStr);
            String resCode = mainObj.getString("retCode");
//            if (resCode.equals("0000")) {

                return mGson.fromJson(jsonStr,type);
//            } else {


                //ErrResponse 将msg解析为异常消息文本
//                ErrorResponse errorResponse = mGson.fromJson(jsonStr,ErrorResponse.class);
//                throw new ResultException(errorResponse.getRetCode(), errorResponse.getResultMessage());

//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {

        }

        return t;
    }
}