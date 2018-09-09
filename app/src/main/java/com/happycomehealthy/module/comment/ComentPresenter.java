package com.happycomehealthy.module.comment;

import com.happycomehealthy.bean.CacheData;
import com.happycomehealthy.bean.Comment;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.listeners.OperationListener;

import java.util.List;
import java.util.Objects;

import butterknife.OnClick;

/**
 * Created by shixinshan on 2018/9/5.
 */

public class ComentPresenter implements CommentContract.ICommentPresenter{


    private  CommentBiz ibz;
    private  CommentContract.ICommentView iview;

    public ComentPresenter(CommentContract.ICommentView commentActivity) {
        this.iview = commentActivity;
        this.ibz = new CommentBiz();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    public void getCommentData() {
        ibz.getCommentData(new OperationListener<List<Comment>>(){
            @Override
            public void onDone(List<Comment> commentList) {
                if(commentList != null){
                    iview.showComment(commentList);

                }
            }

            @Override
            public void onError(String errorMsg) {
                iview.showToast(errorMsg);
                iview.hideLoadingDialog();
            }
        });
    }

    /**
     * add comment
     * @param comment
     */
    public void addComment(String comment) {
        ibz.saveCommentData(comment,new OperationListener(){
            @Override
            public void onDone(Object commentList) {
                iview.backLastPage();
            }

            @Override
            public void onError(String errorMsg) {
                iview.showToast(errorMsg);
                iview.hideLoadingDialog();
            }
        });
    }
}
