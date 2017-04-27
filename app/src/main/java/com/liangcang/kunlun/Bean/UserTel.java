package com.liangcang.kunlun.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wuguojun on 16/9/24.
 */
public class UserTel implements Parcelable
{
    private String tag;
    private String name;
    private String tel;

    public UserTel()
    {
        super();
    }

    private UserTel(Parcel in)
    {
        tag = in.readString();
        name = in.readString();
        tel = in.readString();
    }

    public static final Creator<UserTel> CREATOR = new Creator<UserTel>()
    {
        @Override
        public UserTel createFromParcel(Parcel in)
        {
            return new UserTel(in);
        }

        @Override
        public UserTel[] newArray(int size)
        {
            return new UserTel[size];
        }
    };

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getTel()
    {
        return this.tel;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(tag);
        dest.writeString(name);
        dest.writeString(tel);
    }
}
