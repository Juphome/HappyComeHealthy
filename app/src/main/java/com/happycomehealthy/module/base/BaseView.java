package com.happycomehealthy.module.base;

/**
 * Created by shixinshan on 2017/10/31.
 */

public interface BaseView {
    /**
     * 显示dialog
     */
    void showLoadingDialog(String msg);

    /**
     * 取消dialog
     */
    void hideLoadingDialog();

    void showToast(String msg);

//    Context getContext();
}
