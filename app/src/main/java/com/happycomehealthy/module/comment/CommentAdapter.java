package com.happycomehealthy.module.comment;

import android.content.Context;
import android.view.View;

import com.happycomehealthy.R;
import com.happycomehealthy.bean.Comment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by shixinshan on 2018/9/5.
 */

public class CommentAdapter extends CommonAdapter<Comment> {

    private String TAG = CommentAdapter.class.getSimpleName();
    private ButtonInterface buttonInterface;


    public CommentAdapter(Context context, int layoutId, List<Comment> datas) {
        super(context, layoutId, datas);
    }



    /**
     *按钮点击事件需要的方法
     */
    public void setButtonOnclick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }


    @Override
    protected void convert(ViewHolder holder, Comment comment, int position) {

        holder.setText(R.id.tv_comment_content,comment.getContent());
        holder.setText(R.id.tv_comment_date,comment.getUpdateTime());



    }

    /**
     * 按钮点击事件对应的接口
     */
    public interface ButtonInterface{
        void onclick(View view, int position);
    }

}
