package com.example.android.myhometownweather.sync;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.myhometownweather.Weather;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class WeatherAsyncTaskLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<Weather>> {
    URL urlToQuery;
    ArrayList<Weather> mWeathers;
    public WeatherAsyncTaskLoader(Context context, URL url) {
        super(context);
        urlToQuery=url;
    }

    @Override
    protected void onStartLoading() {
        if (mWeathers!=null){
            deliverResult(mWeathers);
        }else{
            forceLoad();
        }
    }

    @Override
    public ArrayList<Weather> loadInBackground() {
        if (urlToQuery==null){
            return null;
        }
        mWeathers = NetworkUtils.fetchDataByURL(urlToQuery);
        return mWeathers;
    }

    @Override
    public void deliverResult(ArrayList<Weather> data) {
        mWeathers = data;
        super.deliverResult(data);
    }
}
