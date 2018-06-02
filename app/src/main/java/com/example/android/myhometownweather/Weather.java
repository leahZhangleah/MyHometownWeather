package com.example.android.myhometownweather;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Weather implements Parcelable {
    private String mDescription, mCountry, mCity;
    private int mHumidity,mIconId;
    private double mPressure,mTemperature, mWindSpeed,mMinTemp,mMaxTemp;
    private long mDate;

    public Weather(String description, int iconId, String country, String city, int humidity,
                   double pressure, double temperature, double windSpeed, double minTemp, double maxTemp, long date) {
        mDescription= description;
        mIconId=iconId;
        mCountry=country;
        mCity=city;
        mHumidity=humidity;
        mPressure=pressure;
        mTemperature=temperature;
        mWindSpeed=windSpeed;
        mMinTemp=minTemp;
        mMaxTemp=maxTemp;
        mDate=date;
        Log.i("Weather","the long constructor is called");
    }

    public String getmDescription() {
        return mDescription;
    }

    public int getmIconId() {
        return mIconId;
    }

    public String getmCountry() {
        return mCountry;
    }

    public String getmCity() {
        return mCity;
    }

    public int getmHumidity() {
        return mHumidity;
    }

    public double getmPressure() {
        return mPressure;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public double getmWindSpeed() {
        return mWindSpeed;
    }

    public double getmMinTemp() {
        return mMinTemp;
    }

    public double getmMaxTemp() {
        return mMaxTemp;
    }

    public long getmDate() {
        return mDate;
    }

    private Weather(Parcel source){
        mDescription = source.readString();
        mIconId = source.readInt();
        mCountry = source.readString();
        mCity = source.readString();
        mHumidity = source.readInt();
        mPressure = source.readDouble();
        mTemperature = source.readDouble();
        mWindSpeed = source.readDouble();
        mMinTemp = source.readDouble();
        mMaxTemp = source.readDouble();
        mDate = source.readLong();
        Log.i("Weather","the short constructor is called");
    }
    @Override
    public int describeContents() {
        Log.i("Weather","describe contents is called");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDescription);
        dest.writeInt(mIconId);
        dest.writeString(mCountry);
        dest.writeString(mCity);
        dest.writeInt(mHumidity);
        dest.writeDouble(mPressure);
        dest.writeDouble(mTemperature);
        dest.writeDouble(mWindSpeed);
        dest.writeDouble(mMinTemp);
        dest.writeDouble(mMaxTemp);
        dest.writeLong(mDate);
        Log.i("Weather","write to parcel is called");
    }
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>(){

        @Override
        public Weather createFromParcel(Parcel source) {
            Log.i("Weather","create from parcel is called");
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            Log.i("Weather","new array is called");
            return new Weather[size];
        }
    };
}
