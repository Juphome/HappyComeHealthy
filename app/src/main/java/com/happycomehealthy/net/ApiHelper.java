package com.happycomehealthy.net;





import android.text.TextUtils;

import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.utils.PreferenceUtils;


import okhttp3.MediaType;


/**
 * 网络接口辅助操作类
 * 学生，教室，课程，班级等接口
 * Created by zzf on 17/10/08.
 */
public class ApiHelper extends RetrofitUtil {


    private ApiService apiService;
//    private static ApiHelper mInstance;
    public static MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
    /**
     * 服务器地址
     */
//    private static  String API_BASE  = "http://192.168.8.175:6788";

//    private static  String API_BASE  = "http://192.168.43.246:6788";
    private static  String API_BASE  = "http://192.168.43.204:6788";

    public ApiService getApiService() {

        if (apiService == null) {
            setApiService();
        }
        return apiService;
    }
    public boolean setApiService() {
        String ip_address = PreferenceUtils.getString(Constants.IP_ADDRESS,"");
        String ip_port = PreferenceUtils.getString(Constants.IP_POST,"");
        if(TextUtils.isEmpty(ip_address) || TextUtils.isEmpty(ip_port)){

            return false;
        }else {
//            API_BASE = "http://"+ip_address + ":" +ip_port + "/";
//            API_BASE = "http://192.168.1.1:6788";
            apiService = createRetrofitService(ApiService.class,API_BASE);
            return true;
        }


    }


    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     *
     * @author xuzhaohu
     *
     */
    private static class SingletonHolder {
        private static ApiHelper mPersonalInfo = new ApiHelper();
    }

    /**
     * 私有的构造函数
     */
    private ApiHelper() {
//        API_BASE = "http://baidu.com/";
        apiService = createRetrofitService(ApiService.class,API_BASE);
    }

    public static ApiHelper getInstance() {
        return SingletonHolder.mPersonalInfo;
    }
//    public static ApiHelper getInstance(){
//        if (mInstance == null){
//            synchronized (ApiHelper.class){
//                mInstance = new ApiHelper();




//    /**
//     * 获取服务器时钟
//     *
//     * @return
//     */
//    public Observable<BaseResponse> addUsers(RequestBody reqBody) {
//
//        return getApiService().addUsers(reqBody)
//                .subscribeOn(Schedulers.io())
//
//                .observeOn(AndroidSchedulers.mainThread());
//
//    }
//
}
