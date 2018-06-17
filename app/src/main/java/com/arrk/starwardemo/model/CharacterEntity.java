package com.arrk.starwardemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.arrk.starwardemo.util.DateTimeUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import javax.inject.Inject;

public class CharacterEntity implements Parcelable
{
    @SerializedName("created")
    @Expose
    public Date created;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("mass")
    @Expose
    public String mass;

    @Inject
    public CharacterEntity()
    {

    }

    protected CharacterEntity(Parcel in)
    {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        created = new Date(in.readLong());
    }

    public static final Creator<CharacterEntity> CREATOR = new Creator<CharacterEntity>()
    {
        @Override
        public CharacterEntity createFromParcel(Parcel in)
        {
            return new CharacterEntity(in);
        }

        @Override
        public CharacterEntity[] newArray(int size)
        {
            return new CharacterEntity[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeLong(created.getTime());
    }

    public String getDateAndTime()
    {
        if (created == null)
            return "unknown";

        return DateTimeUtil.getDateTimeFormatted(created);
    }


    public String getHeightInMeter()
    {
        try
        {
            return String.valueOf(Float.valueOf(height) / 100);
        } catch (NumberFormatException e)
        {
            return "unknown";
        }

    }
}
