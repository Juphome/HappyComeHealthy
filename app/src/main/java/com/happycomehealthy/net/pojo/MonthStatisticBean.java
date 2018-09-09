package com.happycomehealthy.net.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 月统计
 * Created by shixinshan on 2018/8/13.
 */

public class MonthStatisticBean implements Parcelable{


    /**
     * date : 2018-08-01
     * count : 0
     */

    private String date;
    private int count;

    protected MonthStatisticBean(Parcel in) {
        date = in.readString();
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MonthStatisticBean> CREATOR = new Creator<MonthStatisticBean>() {
        @Override
        public MonthStatisticBean createFromParcel(Parcel in) {
            return new MonthStatisticBean(in);
        }

        @Override
        public MonthStatisticBean[] newArray(int size) {
            return new MonthStatisticBean[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
