package com.happycomehealthy.net;

import android.util.Log;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Post请求添加Json类型参数的header配置
 * creator: ZZF
 * careate date: 2017/10/10  09:15.
 */

public class HeaderInterceptor implements Interceptor {

    private String TAG = HeaderInterceptor.class.getSimpleName();
//    String userInfo1 = "{\"userName\":\"admin\",
//            \"userId\":\"0d42dd2b492649d392fb42ba1ecccafa\",
//            \"orgId\":null,
//            \"orgName\":null,
//            \"roleCode\":null,
//            \"deptCode\":\"001\",
//            \"locationCode\":\"001\",
//            \"organizeCode\":\"001\",
//            \"deptCodeName\":null,
//            \"locationCodeName\":null,
//            \"organizeCodeName\":null,
//            \"jgbm\":\"999000\",
//            \"jgmc\":\"%E6%99%BA%E6%85%A7%E9%80%9A%E5%AE%9D\",
//            \"dataRight\":null,
//            \"extendProperty\":"{\"menuId\":\"6000289039bc11e5b42db8ee65235a71\",
//            \"userType\":\"0\",
//            \"smtShzzMc\":\"\",
//            \"smtShzzdm\":\"001\",
//            \"smtXzzzMc\":\"\",
//            \"tableName\":null,
//            \"smtDlwzdm\":\"001\",
//            \"smtXzzzdm\":\"001\",
//            \"ryId\":\"null\",
//            \"smtTpCode\":\"index\",
//            \"smtDlwzMc\":\"\",
//            \"organImg\":\"null\",
//            \"realName\":\"admin\",
//            \"unitId\":\"fkTree\",
//            \"smtShzzFullMc\":\"\"
//        }"
//    }";
//        String userInfo =
//
//        "{'userName':'admin', 'userId':'0d42dd2b492649d392fb42ba1ecccafa', 'orgId':null, '地理':39, " +
//                "'object':{'aaa':'11c11','bbb':'2222','cccc':'3333'}}]";
    @Override
    public Response intercept(Chain chain) throws IOException {

//
//        UserInfo user = new UserInfo();
//        user.setUserName("admin");
//        user.setUserId("0d42dd2b492649d392fb42ba1ecccafa");
//        user.setOrgId(null);
//        user.setOrgName(null);
//        user.setRoleCode(null);
//        user.setDeptCode("001");
//        user.setOrganizeCode("001");
//        user.setDeptCode(null);
//        user.setLocationCodeName(null);
//        user.setOrganizeCodeName(null);
//        user.setJgbm("999000");
//        user.setJgmc("%E6%99%BA%E6%85%A7%E9%80%9A%E5%AE%9D");
//        user.setDataRight(null);
//        Map<String,String> extendProperty = new HashMap<>();
//        extendProperty.put("menuId", "6000289039bc11e5b42db8ee65235a71");
//        extendProperty.put("userType", "0");
//        extendProperty.put("smtShzzMc", "");
//        extendProperty.put("userType", "0");
//        extendProperty.put("smtShzzdm", "001");
//        extendProperty.put("smtXzzzMc", "");
//        extendProperty.put("tableName", "null");
//        extendProperty.put("smtDlwzdm", "001");
//        extendProperty.put("smtXzzzdm", "001");
//        extendProperty.put("ryId", "null");
//        extendProperty.put("smtTpCode", "index");
//        extendProperty.put("smtDlwzMc", "");
//        extendProperty.put("organImg", "null");
//        extendProperty.put("realName", "admin");
//        extendProperty.put("unitId", "fkTree");
//        extendProperty.put("smtShzzFullMc", "");
//
//        user.setExtendProperty(extendProperty);
//        String userJsonStr = new Gson().toJson(user);
//        String userInfoStr = null;//用来作为header的

        try {
//            userInfoStr = URLEncoder.encode(userJsonStr,"UTF-8");
            String userInfoStr = URLEncoder.encode("","UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Request request = chain.request()
                .newBuilder()
                .addHeader("userInfo",null)
                .build();
        Log.i(TAG, "request headers:" + request.headers().toString());
        return chain.proceed(request);
    }
}
