package com.example.cardiacrecord;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserRVModal implements Parcelable {
   private String userName ;
    private  String userDesc;
    private  String usersys ;
    private  String userdio ;
    private  String userheart;
    private String userId ;
    private String userdate;
    private String usertime;


    public UserRVModal(){

    }

    protected UserRVModal(Parcel in) {
        userName = in.readString();
        userDesc = in.readString();
        usersys = in.readString();
        userdio = in.readString();
        userheart = in.readString();
        userId = in.readString();
        userdate = in.readString();
        usertime = in.readString();
    }

    public static final Creator<UserRVModal> CREATOR = new Creator<UserRVModal>() {
        @Override
        public UserRVModal createFromParcel(Parcel in) {
            return new UserRVModal(in);
        }

        @Override
        public UserRVModal[] newArray(int size) {
            return new UserRVModal[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUsersys() {
        return usersys;
    }

    public void setUsersys(String usersys) {
        this.usersys = usersys;
    }

    public String getUserdio() {
        return userdio;
    }

    public void setUserdio(String userdio) {
        this.userdio = userdio;
    }

    public String getUserheart() {
        return userheart;
    }

    public void setUserheart(String userheart) {
        this.userheart = userheart;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserdate() {
        return userdate;
    }

    public void setUserdate(String userdate) {
        this.userdate = userdate;
    }

    public String getUsertime() {
        return usertime;
    }

    public void setUsertime(String usertime) {
        this.usertime = usertime;
    }

    public UserRVModal(String userName, String userDesc, String usersys, String userdio, String userheart, String userId, String userdate, String usertime) {
        this.userName = userName;
        this.userDesc = userDesc;
        this.usersys = usersys;
        this.userdio = userdio;
        this.userheart = userheart;
        this.userId = userId;
        this.userdate = userdate;
        this.usertime = usertime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userDesc);
        dest.writeString(usersys);
        dest.writeString(userdio);
        dest.writeString(userheart);
        dest.writeString(userId);
        dest.writeString(userdate);
        dest.writeString(usertime);
    }
}
