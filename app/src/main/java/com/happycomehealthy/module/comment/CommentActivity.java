package com.happycomehealthy.module.comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.Comment;
import com.happycomehealthy.module.base.BaseActivity;
import com.happycomehealthy.module.base.BasePresenter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CommentActivity extends BaseActivity implements CommentContract.ICommentView{
    private String TAG = CommentActivity.class.getSimpleName();

    @BindView(R.id.rcv_comment)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_cancel_comment)
    TextView tv_cancel_comment;

    @BindView(R.id.tv_save_comment)
    TextView tv_save_comment;

    @BindView(R.id.edt_comment)
    EditText edt_comment;


    @BindView(R.id.ll_edit_content)
    LinearLayout ll_edit_content;

    private Unbinder unbinder;
    private ComentPresenter comentPresenter;
    private List<Comment> mData = new ArrayList<>();
    private CommentAdapter mAdapter;


    @Override
    public int loadLayout() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);

    }

    @Override
    public void initData() {

        mRecyclerView.setHasFixedSize(true);
        //设置布局管理器
//        linearLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CommentAdapter(this, R.layout.rcv_item_comment, mData);
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

        comentPresenter.getCommentData();
    }

    @Override
    public BasePresenter setPresenter() {
        comentPresenter = new ComentPresenter(this);
        return comentPresenter;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void showComment(List<Comment> commentList) {
        mData.clear();
        for (int i = 0;i<commentList.size();i++){
            Comment comment = commentList.get(i);
            mData.add(comment);

        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void backLastPage() {
        finish();
    }

    @OnClick(R.id.tv_cancel_comment)
    public void cancelComent(){
        finish();
    }
    @OnClick(R.id.tv_save_comment)
    public void saveComment(){

        String comment = edt_comment.getText().toString();
        if(!TextUtils.isEmpty(comment)){
            comentPresenter.addComment(comment);

        }
    }
    @OnClick(R.id.ll_edit_content)
    public void setOnclickEditext(){
        edt_comment.setFocusable(true);
        edt_comment.setFocusableInTouchMode(true);
        edt_comment.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);

        Log.w(TAG,"setOnclic");
    }
}
