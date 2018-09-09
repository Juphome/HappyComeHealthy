package com.happycomehealthy.net.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 客户
 * Created by shixinshan on 2018/5/2.
 * 实现Parcelable接口需要实现两个方法
 describeContents方法。内容接口描述，默认返回0就可以;
 writeToParcel方法。将传递的数据打包到Parcel容器中。
 除了要实现这两个方法还必须创建一个Parcelable.Creator接口的实例，用于读取Parcel容器中的数据


 android activity之间传递对象（Serializable 和 Parcelable）区别

 性能：

 1.在使用内存的时候，Parcelable 类比Serializable性能高，所以推荐使用Parcelable类。

 2.Serializable在序列化的时候会产生大量的临时变量，从而引起频繁的GC。

 3.Parcelable不能使用在要将数据存储在磁盘上的情况，因为Parcelable不能很好的保证数据的持续性在外界有变化的情况下。

 尽管Serializable效率低点， 也不提倡用，但在这种情况下，还是建议你用Serializable 。

 Android中，Activity和Fragment之间传递对象，可以通过将对象序列化并存入Bundle或者Intent中进行传递，也可以将对象转化为JSON字符串，进行传递。

 序列化对象可以使用Java的Serializable的接口、Parcelable接口。转化成JSON字符串，可以使用Gson等库。


 */

public class Guest implements Parcelable {

    private String personId ;
    private String name ;
    private String sex ;
    private String age ;
    private String phoneNumber ;
    private String address ;
    private String introducer ;
    private String medical_history;
    private boolean signed;//已经签到

    public Guest() {

    }

    public Guest(Parcel in) {
        personId = in.readString();
        name = in.readString();
        sex = in.readString();
        age = in.readString();
        phoneNumber = in.readString();
        address = in.readString();
        introducer = in.readString();
        medical_history = in.readString();
        signed = in.readByte() != 0;
    }

    public static final Creator<Guest> CREATOR = new Creator<Guest>() {
        @Override
        public Guest createFromParcel(Parcel in) {
            return new Guest(in);
        }

        @Override
        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(personId);
        parcel.writeString(name);
        parcel.writeString(sex);
        parcel.writeString(age);
        parcel.writeString(phoneNumber);
        parcel.writeString(address);
        parcel.writeString(introducer);
        parcel.writeString(medical_history);
        parcel.writeByte((byte) (signed ? 1 : 0));
    }
}
