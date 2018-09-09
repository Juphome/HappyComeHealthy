package com.happycomehealthy.module.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by shixinshan on 2017/10/31.
 */

public interface BasePresenter {
    //默认初始化
    void onStart();

    //Activity关闭把view对象置为空
    void onDetach();


    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();
}
