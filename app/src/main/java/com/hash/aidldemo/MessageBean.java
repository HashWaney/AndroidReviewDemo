package com.hash.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HashWaney on 2019/2/23.
 */

public class MessageBean implements Parcelable {
    private String content;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageBean(String content, int level) {
        this.content = content;
        this.level = level;
    }

    public void readFromParcel(Parcel in) {
        this.content = in.readString();
        this.level = in.readInt();
    }

    protected MessageBean(Parcel in) {
        this.content = in.readString();
        this.level = in.readInt();
    }

    public MessageBean() {
    }

    public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel in) {
            return new MessageBean(in);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeInt(this.level);
    }
}
