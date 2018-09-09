package com.happycomehealthy.module.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.happycomehealthy.widget.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by shixinshan on 2017/10/18.
 */

public abstract class BaseFragment< P extends BasePresenter> extends Fragment implements BaseView{
    /**
     * Log tag
     */
    protected static String TAG_LOG = null;
    protected P presenter;
    private DialogLoading dialogLoading;
    /**
     * activity context of fragment
     */
    protected Context mContext;
    protected Activity mActivity;
    //    protected Subscription mSubscription;


    private Unbinder mUnBinder;
    public View mView;


    //    @Inject
//    DataManager mDataManager;


    @Override
    public void onAttach(Context context) {
        //set a context from current activity
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set Timber as log util
        TAG_LOG = this.getClass().getSimpleName();
//        Timber.tag(TAG_LOG);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(getLayoutId(), container, false);
        mUnBinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setPresenter();
        iniView();
        initData();
    }

    protected abstract P setPresenter();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //设置状态栏透明
//        setStatusBarColor();
        super.onViewCreated(view, savedInstanceState);
        //bind The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.

        //init views events here so we can use ButterKnife


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mSubscriptions.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//ButterKnife unbind
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
    }



    /**
     * Every fragment has to inflate a layout in the onCreateView method.
     * We have added this method to avoid duplicate all the inflate code in every fragment.
     * You only have to return the layout to inflate in this method when extends AbsBaseFragment.
     */
    protected abstract int getLayoutId();



    /**
     * override this method to use Dagger2 which support for Dependency Injection
     * <p>
     * using dagger2 in base class：https://github.com/google/dagger/issues/73
     */
//    protected void initDagger2(ActivityComponent activityComponent) {
//        activityComponent.inject(this);
//    }


    protected abstract void initData();

    protected abstract void iniView();






    @Override
    public void hideLoadingDialog() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
        }
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (dialogLoading == null) {
            dialogLoading = new DialogLoading(mActivity);
        }
        dialogLoading.setDialogLabel(msg);
        dialogLoading.show();
    }




}