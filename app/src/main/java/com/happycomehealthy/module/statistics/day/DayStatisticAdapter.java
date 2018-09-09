package com.happycomehealthy.module.statistics.day;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.elvishew.xlog.LogUtils;
import com.happycomehealthy.R;
import com.happycomehealthy.bean.DayGuest;
import com.happycomehealthy.utils.DateUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by shixinshan on 2018/8/23.
 */

public class DayStatisticAdapter extends CommonAdapter<DayGuest> {

    private String TAG = DayStatisticAdapter.class.getSimpleName();
    private ButtonInterface buttonInterface;
    private MenuSelectInterface menuSelectInterface;
    private MenuCancelInterface menuCancelInterface;

    public DayStatisticAdapter(Context context, int layoutId, List<DayGuest> datas) {
        super(context, layoutId, datas);
    }



    /**
     *按钮点击事件需要的方法
     */
    public void setButtonOnclick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }
    /**
     *选择控件点击事件需要的方法
     */
    public void setMenuSelectOnclick(MenuSelectInterface menuSelectedInterface){
        this.menuSelectInterface=menuSelectedInterface;
    }
    /**
     *取消控件点击事件需要的方法
     */
    public void setMenuCancelOnclick(MenuCancelInterface menuCancelInterface){
        this.menuCancelInterface = menuCancelInterface;
    }

    @Override
    protected void convert(ViewHolder holder, DayGuest guest, int position) {

        holder.setText(R.id.tv_guest_name,guest.getName());
        holder.setText(R.id.tv_person_id,guest.getPersonId());

        if(!TextUtils.isEmpty(guest.getSignDate())){
            String date = DateUtils.UTCStringtODefaulString2(guest.getSignDate());
            holder.setText(R.id.tv_sign_date,date);
        }else {
            holder.setText(R.id.tv_sign_date,"");
        }


    }

    /**
     * 按钮点击事件对应的接口
     */
    public interface ButtonInterface{
        void onclick(View view, int position);
    }
    /**
     *  取消点击事件对应的接口
     */
    public interface MenuSelectInterface{
        void onclick(View view, int position);
    }
    /**
     * 选择控件点击事件对应的接口
     */
    public interface MenuCancelInterface{
        void onclick(View view, int position);
    }


}