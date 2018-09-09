package com.happycomehealthy.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.happycomehealthy.widget.DialogLoading;


/**
 * Activity基类
 * Created by shixinshan on 2017/10/7.
 */

public abstract class BaseActivity< P extends BasePresenter> extends AppCompatActivity implements BaseView{
    private DialogLoading dialogLoading;
    protected P presenter;
    public Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(loadLayout());
        setPresenter();
        initView(savedInstanceState);
//        iniView();
        initData();
        setContext(this);
    }

    public abstract int loadLayout();


    public abstract void initView(Bundle savedInstanceState);
    //    public abstract void iniView();
    public abstract void initData();
    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P setPresenter();




    private void setContext(Context context){
        this.context = context;
    }
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐藏加载提示
     */
    @Override
    public void hideLoadingDialog() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
        }
    }

    /**
     * 显示含有加载信息的提示
     * @param msg
     */
    @Override
    public void showLoadingDialog(String msg) {
        if (dialogLoading == null) {
            dialogLoading = new DialogLoading(this);
        }
        dialogLoading.setDialogLabel(msg);
        dialogLoading.show();
    }
}
