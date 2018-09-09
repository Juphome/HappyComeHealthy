package com.happycomehealthy.module.edit;

import com.happycomehealthy.listeners.OperationListener;
import com.happycomehealthy.module.base.BaseBiz;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.base.BaseView;

/**
 * Created by shixinshan on 2018/5/3.
 */

public class EditContact {

    interface  IEditPresenter extends BasePresenter{

    }
    interface IEditView extends BaseView{
        /**
         * 显示用户ID
         * @param user_id
         */
        void showUserId(String user_id);
    }
    interface  IEditBiz extends BaseBiz{


        /**
         * 上传用户数据
         * @param user_id
         * @param user_name
         * @param user_age
         * @param sex
         * @param user_phone_number
         * @param address
         * @param introducer
         * @param medical_history
         * @param operationListener
         */
        void upLoadUserData(String user_id, String user_name, String user_age,
                            String sex, String user_phone_number, String address,
                            String introducer, String medical_history, OperationListener operationListener);

        /**
         * 获取用户ID
         * @param operationListener
         */
        void getPersonId(OperationListener operationListener);
    }
}
