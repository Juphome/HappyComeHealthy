package com.happycomehealthy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class PreferenceUtils {
    private static PreferenceUtils util;
    private static SharedPreferences sp;
    private PreferenceUtils(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次，建议在Application中初始化
     *
     * @param context 上下文对象
     * @param name    SharedPreferences Name
     */
    public static void getInstance(Context context, String name) {
        if (util == null) {
            util = new PreferenceUtils(context, name);
        }
    }
    /**
     * 清空应用中的文件存储
     */
    public static void reset() {
        sp.edit().clear().commit();
    }

    /**
     * 获取String文件信息
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getString( String key, String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * 存储String类型的数据到文件当中
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取int文件信息
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt( String key, int defValue) {
        return sp.getInt(key, defValue);
    }


    /**
     * 存储int类型数据到文件当中
     * @param key
     * @param value
     */
    public static void putInt( String key, int value) {
        sp.edit().putInt(key, value).commit();
    }
    /**
     * 存储long类型数据到文件当中
     * @param key
     * @param value
     */
    public static void putLong( String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    public static void putBoolean(String key, Boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }
}
