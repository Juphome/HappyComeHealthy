package com.happycomehealthy.net;



import com.happycomehealthy.bean.Comment;
import com.happycomehealthy.bean.DayGuest;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.net.pojo.MonthStatisticBean;

import java.util.List;

import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * 请求网络的API接口类
 * Created by zzf on 17/10/08.
 */
public interface ApiService {


    /**
     * 添加客户
     *
     * @return
     */
    @POST("/api/u/addUser")
    Call<BaseResponse> addUsers(@Body RequestBody reqBody);

    /**
     *
     * @param personalId
     * @return
     */
    @GET("/api/u/getUser")
    Call<BaseResponse<Guest>> getGuest(@Query("personId") String personalId);
    /**
     * 获取某个月的客人签到数据
     * @param yearMonth yyyy-mm
     * @return
     */
    @GET("/api/u/getSignDatesInMonth")
    Call<BaseResponse<String[]>> getSignDatesInMonth(@Query("guest_id") String guest_id,@Query("yearMonth") String yearMonth);

    /**
     * 根据客户的个人编号，获取客户信息
     * @return
     */
    @GET("/api/u/getPersonId")
    Call<BaseResponse> getGuest();

    /**
     * 客户签到
     *
     * @return
     */
    @POST("/api/u/sign")
    Call<BaseResponse> sign(@Body RequestBody reqBody);

    /**
     * 获取某月的每一天的统计
     * @param yearMonth yyyy-MM
     * @return
     */
    @GET("/api/u/getStatisticInMonth")
    Call<BaseResponse<List<MonthStatisticBean>>> getMonthStatisticsData(@Query("yearMonth") String yearMonth);

    /**
     * 获取某一天的客户
     * @param yearMonthDay yyyy-MM-DD
     * @return
     */
    @GET("/api/u/getStatisticInDay")
    Call<BaseResponse<List<DayGuest>>> getDayStatisticsData(@Query("yearMonthDay") String yearMonthDay);


    /**
     * 获取客户的反馈评价信息
     * @param guestName 客户名字
     * @return
     */
    @GET("/api/u/getPersonalComments")
    Call<BaseResponse<List<Comment>>> getCommentData(@Query("guestName") String guestName);

    /**
     * 添加客户的反馈评价信息
     * @return
     */
    @POST("/api/u/addGuestComment")
    Call<BaseResponse> addCommentData(@Body RequestBody reqBody);

//    @GET
//    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);

    /*
        Retrofit get annotation with our URL
        And our method that will return us the List of Contacts
        */
//    @Multipart
//    @POST("/rest/services/file")
//    Observable<String> uploadImage(@Body RequestBody reqBody,
//                             @Part MultipartBody.Part file);

//    @Multipart
//    @POST("/rest/services/file")
//    Observable<ResponseBody> uploadImage2(@Part("description") RequestBody description, @Part MultipartBody.Part imgs);
//
//    //    @Multipart
////    @POST("/rest/services/file")  //这里是自己post文件的地址
////    Observable<ResponseBody> postGoodsReturnPostEntitys( @Part MultipartBody.Part part);]
//    @Multipart
//    @POST("/rest/services/file")
//    Call<String> uploadFileWithRequestBody(@Part("json") RequestBody jsonBody,
//                                           @Part MultipartBody.Part file);
//
//    @Multipart
//    @POST("file")
//    Observable<Response<String>> upLoadPicToNet(@Part("json") RequestBody jsonBody,
//                                                @Part MultipartBody.Part picFile);
}
