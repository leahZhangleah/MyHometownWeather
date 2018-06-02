package com.example.android.myhometownweather;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WeatherFragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    ArrayList<Weather> mWeathers;
    Typeface weatherFont;
    public static WeatherFragment newInstance(ArrayList<Weather> weathers){
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("weathers",weathers);
        weatherFragment.setArguments(args);
        return weatherFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeathers = getArguments()!=null? getArguments().<Weather>getParcelableArrayList("weathers"):null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View weather = inflater.inflate(R.layout.weather_fragment,container,false);
        TextView mLocationTv, mTemperatureTv, mMainDescriptionTv,mFunnyCommentTv,mWeatherIconV;
        mLocationTv = (TextView) weather.findViewById(R.id.location_tv);
        mTemperatureTv = (TextView) weather.findViewById(R.id.temperature_tv);
        mMainDescriptionTv = (TextView)weather.findViewById(R.id.main_description_tv);
        mFunnyCommentTv =(TextView) weather.findViewById(R.id.funny_comments);
        mWeatherIconV = (TextView) weather.findViewById(R.id.weather_icon);
        //todo: set data for different views based on the data return http request and json reading
        if (mWeathers!=null){
            Weather currentWeather = mWeathers.get(0);
            mLocationTv.setText(currentWeather.getmCity()+","+currentWeather.getmCountry());
            mTemperatureTv.setText(String.valueOf(currentWeather.getmTemperature()));
            mMainDescriptionTv.setText(currentWeather.getmDescription());
            //weatherFont =Typeface.createFromAsset(getContext().getAssets(),"fonts/weathericons-regualr-webfont.ttf");
            //mWeatherIconV.setTypeface(weatherFont);
            //todo: don't know if this is correct
            mWeatherIconV.setText(currentWeather.getmIcon());
        }
        //weather.setOnClickListener(this);
        return weather;
    }

    @Override
    public void onClick(View v) {
        //todo:set intent to open a detail activity shared for weather days
    }
}
