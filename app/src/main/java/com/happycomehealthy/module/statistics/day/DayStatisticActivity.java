package com.happycomehealthy.module.statistics.day;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.bean.DayGuest;
import com.happycomehealthy.module.base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DayStatisticActivity extends BaseActivity {


    private Unbinder unbinder;
    @BindView(R.id.rcv_guest)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_signed)
    Button btn_signed;
    @BindView(R.id.btn_not_signed)
    Button btn_not_signed;
    private LinearLayoutManager linearLayoutManager;
    private List<DayGuest> mData = new ArrayList<>();
    private DayStatisticAdapter mAdapter;
    private LoadMoreWrapper mLoadMoreWraxpper;
    private DayStatisticPresenter dayStatisticPresenter;

    @Override
    public int loadLayout() {
        return R.layout.activity_day_statistic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        List<DayGuest> daySignedGuestList = CacheData.getInstance().getDaySignedGuestList();
        for (DayGuest dayGuest:daySignedGuestList){
            mData.add(dayGuest);
        }

        mRecyclerView.setHasFixedSize(true);
        //设置布局管理器
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new DayStatisticAdapter(this, R.layout.rcv_item_day_guest, mData);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {


            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });



//        initHeaderAndFooter();

//        mLoadMoreWraxpper = new LoadMoreWrapper(mAdapter);
//        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
//        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                Log.i(Tag,"onLoadMoreRequested");
//
//
//            }
//        });

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public DayStatisticPresenter setPresenter() {
        dayStatisticPresenter = new DayStatisticPresenter();
        return dayStatisticPresenter;
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        if (presenter != null) {
            presenter.onDestroy();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 显示签到的客户
     */
    @OnClick(R.id.btn_signed)
    public void showignedGuest(){
        btn_signed.setBackgroundColor(Color.parseColor("#7CFC00"));
        btn_not_signed.setBackgroundColor(Color.parseColor("#ffffff"));
        mData.clear();
        List<DayGuest> daySignedGuestList = CacheData.getInstance().getDaySignedGuestList();
        for (DayGuest dayGuest:daySignedGuestList){
            mData.add(dayGuest);
        }
        mAdapter.notifyDataSetChanged();

    }
    /**
     * 显示没有签到的客户
     */
    @OnClick(R.id.btn_not_signed)
    public void showNotSignedGuest(){
        btn_signed.setBackgroundColor(Color.parseColor("#ffffff"));
        btn_not_signed.setBackgroundColor(Color.parseColor("#7CFC00"));
        mData.clear();
        List<DayGuest> dayNotSignedGuestList = CacheData.getInstance().getDayNotSignedGuestList();
        for (DayGuest dayGuest:dayNotSignedGuestList){
            mData.add(dayGuest);
        }
        mAdapter.notifyDataSetChanged();
    }

}
