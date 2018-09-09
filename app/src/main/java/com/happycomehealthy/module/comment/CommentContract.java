package com.happycomehealthy.module.comment;

import com.happycomehealthy.bean.Comment;
import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;

import java.util.List;

/**
 * Created by shixinshan on 2018/9/5.
 */

public class CommentContract {
    interface  ICommentPresenter extends BasePresenter {



    }
    interface ICommentView extends BaseView {

        /**
         * 显示客户的反馈信息
         * @param commentList
         */
        void showComment(List<Comment> commentList);

        /**
         * 返回上一个页面
         */
        void backLastPage();
    }
    interface  ICommentBiz extends BaseBiz {


        /**
         * 获取客户的反馈的评论
         * @param operationListener
         */
        void getCommentData(OperationListener operationListener);

        /**
         * 添加客户反馈的信息
         * @param comment
         * @param operationListener
         */
        void saveCommentData(String comment, OperationListener operationListener);
    }
}
