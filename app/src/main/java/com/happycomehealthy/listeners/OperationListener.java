package com.happycomehealthy.listeners;

/**
 * Created by shixinshan on 2018/5/3.
 */

public interface OperationListener<T> {
    /**顺利执行某操作后*/
    void onDone(T obj);
    /**执行某操作失败后*/
    void onError(String errorMsg);
}
