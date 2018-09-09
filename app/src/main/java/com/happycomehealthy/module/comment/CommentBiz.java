package com.happycomehealthy.module.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.bean.Comment;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.utils.DateUtils;
import com.happycomehealthy.utils.PreferenceUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.happycomehealthy.R.string.user_id;

/**
 * Created by shixinshan on 2018/9/5.
 */

public class CommentBiz implements CommentContract.ICommentBiz {

    @Override
    public void getCommentData(final OperationListener operationListener) {
        String guestName = CacheData.getInstance().getGuestName();
        Call<BaseResponse<List<Comment>>> user =
                ApiHelper.getInstance().getApiService().getCommentData(guestName);
        user.enqueue(new Callback<BaseResponse<List<Comment>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Comment>>> call, Response<BaseResponse<List<Comment>>> response) {
                BaseResponse body = response.body();
                if(body == null){
                    operationListener.onError("后台返回数据空");
                    return;
                }
                String code = body.getCode();
                if(code.equals("ok")){
                    List<Comment> data = (List<Comment>) body.getData();
                    ListSort(data);
//                    Collections.reverse(data); // 倒序排列


                    operationListener.onDone(data);

                }else {
                    operationListener.onError(body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Comment>>> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof SocketTimeoutException){

                    operationListener.onError("连接超时");
                }else if(t instanceof ConnectException){
                    operationListener.onError("连接失败");
                }else {

                    operationListener.onError("未知错误");

                }
            }
        });
    }
    private  void ListSort(List<Comment> list) {
        Collections.sort(list, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date dt1 = format.parse(o1.getUpdateTime());
                    Date dt2 = format.parse(o2.getUpdateTime());

                    return dt2.compareTo(dt1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
    @Override
    public void saveCommentData(String comment, final OperationListener operationListener) {
        String guestName = CacheData.getInstance().getGuestName();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = sdf.format(new Date());
        String operatorName = PreferenceUtils.getString(Constants.OPERATOR_NAME, "zzx");
        JsonObject jObj = new JsonObject();
        jObj.addProperty("guestName", guestName);
        jObj.addProperty("operatorName", operatorName);
        jObj.addProperty("content", comment);
        jObj.addProperty("updateTime", updateTime);


        Gson gson = new Gson();
        RequestBody reqBody = RequestBody.create(ApiHelper.mediaType, gson.toJson(jObj));



        Call<BaseResponse> user =
                ApiHelper.getInstance().getApiService().addCommentData(reqBody);
        user.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                String code = baseResponse.getCode();
                if(code.equals("ok")){
                    operationListener.onDone(baseResponse.getData());
                }else {
                    operationListener.onError(baseResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof SocketTimeoutException){

                    operationListener.onError("连接超时");
                }else if(t instanceof ConnectException){
                    operationListener.onError("连接失败");
                }else {

                    operationListener.onError("未知错误");

                }
            }
        });
    }

}
