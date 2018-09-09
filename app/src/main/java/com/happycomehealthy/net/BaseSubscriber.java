package com.happycomehealthy.net;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;


import org.json.JSONException;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.io.IOException;
import java.net.SocketException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * creator: ZZF
 * careate date: 2017/10/25  09:13.
 */

public abstract class BaseSubscriber<t> implements Subscriber<t> {

    protected Context mContext;
    private String TAG = this.getClass().getSimpleName();







    @Override
    public void onError(final Throwable e) {
        Log.w("Subscriber onError", e);
        if (e instanceof HttpException) {
            // We had non-2XX http error
            Log.e(TAG,"HttpException");
           // Toast.makeText(mContext, mContext.getString(R.string.server_internal_error), Toast.LENGTH_SHORT).show();
        } else if (e instanceof IOException) {
            Log.e(TAG,"IOException");
           // // A network or conversion error happened
         //   Toast.makeText(mContext, mContext.getString(R.string.cannot_connected_server), Toast.LENGTH_SHORT).show();
        }else if(e instanceof SocketException){
            Log.e(TAG,"SocketException");
        }
        else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            Log.e(TAG,"ApiException");
            Log.e(TAG,"ApiException:"+exception.getCode());
            Log.e(TAG,"ApiException:data="+exception.getData());
            if (exception.isTokenExpried()) {
                //处理token失效对应的逻辑
            } else {
               // Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }






}