package com.happycomehealthy.net;

import android.util.Log;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;

/**
 *
 * 整个网络访问流程：创建Bean类 --> 创建接口形式的http 请求方法
 * --> 通过Retrofit.builder() 创建接口对象并调用http 方法请求网络数据
 * --> 在RxJava 的Observable 中异步处理请求结果！
 * Created by shixinshan on 2017/10/7.
 */

public class RetrofitUtil {



    private static RetrofitUtil mInstance;
    private Retrofit retrofit;

    public RetrofitUtil(){

    }

    public static RetrofitUtil getInstance(){
        if (mInstance == null){
            synchronized (RetrofitUtil.class){
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

//    final Observable.Transformer transformer = new Observable.Transformer() {
//        @Override
//        public Object call(Object observable) {
//            return ((Observable) observable).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .flatMap(new Func1() {
//                        @Override
//                        public Object call(Object response) {
//                            return flatResponse((Response<Object>)response);
//                        }
//                    })
//                    ;
//        }
//    };
//    /**
//     * 对网络接口返回的Response进行分割操作
//     *
//     * @param response
//     * @param <T>
//     * @return
//     */
//    public <T> Observable<T> flatResponse(final Response<T> response) {
//        return Observable.create(new Observable.OnSubscribe<T>() {
//
//            @Override
//            public void call(Subscriber<? super T> subscriber) {
//
//                if (response.isSuccess()) {
//                    if (!subscriber.isUnsubscribed()) {
//
//                        subscriber.onNext(response.object);
//                    }
//                } else {
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onError(new APIException(response.code, response.message));
//                    }
//                    return;
//                }
//
//                if (!subscriber.isUnsubscribed()) {
//                    subscriber.onCompleted();
//                }
//
//            }
//        });
//    }
    /**
     * 自定义异常，当接口返回的{@link }不为{@link # OK}时，需要跑出此异常
     * eg：登陆时验证码错误；参数为传递等
     */
    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }



    public  <T>T createRetrofitService(final Class<T> service,String baseUrl) {


            retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())//指定网络执行器
                    //添加Gson转换器
                    .addConverterFactory(GsonConverterFactory.create())
                  //  .addConverterFactory(JsonConverterFactory.create())//指定 Gson 作为解析Json数据的Converter
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//指定使用RxJava 作为CallAdapter
                    .baseUrl(baseUrl)
                    .build();

        return retrofit.create(service);
    }

    /**
     * 日志记录
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor(){
        //日志显示级别 分为4类：NONE、BASIC、HEADERS、BODY。
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("sxs","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    public  OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)  ;
        //定制OkHttp
                     //设置连接超时
//                .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
//                .writeTimeout(10000L, TimeUnit.MILLISECONDS);     //设置写入超时;
        //OkHttp进行添加拦截器loggingInterceptor
//        httpClientBuilder.addInterceptor(new HeaderInterceptor());
        httpClientBuilder.addInterceptor(getHttpLoggingInterceptor());


        return httpClientBuilder.build();
    }


}
