package com.example.android.myhometownweather;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import java.util.ArrayList;

public class ForecastFragmentAdapter extends RecyclerView.Adapter<ForecastFragmentAdapter.ForecastViewHolder>{
    Typeface weatherFont;
    Context mContext;
    ArrayList<Weather> mForecasts;
    public ForecastFragmentAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView rootView = (CardView) LayoutInflater.from(mContext).inflate(R.layout.forecast_item_view,parent,false);
        ForecastViewHolder holder = new ForecastViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Weather forecast = mForecasts.get(position);
        if (forecast!= null){
            long date = forecast.getmDate();
            String dayOfWeek = WeatherDataTransUtils.transformDate(date);
            holder.mDateTv.setText(dayOfWeek);
            holder.mMinTempTv.setText(String.valueOf(forecast.getmMinTemp()));
            holder.mMaxTempTv.setText(String.valueOf(forecast.getmMaxTemp()));
            //weatherFont =Typeface.createFromAsset(mContext.getAssets(),"fonts/weathericons-regualr-webfont.ttf");
            //holder.mForecastWeatherIcon.setTypeface(weatherFont);
            //todo: don't know if this is correct
            holder.mForecastWeatherIcon.setText(forecast.getmIcon());
        }
    }

    @Override
    public int getItemCount() {
        if (mForecasts!=null){
            return mForecasts.size();
        }
        return 0;
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView mDateTv, mMinTempTv, mMaxTempTv,mForecastWeatherIcon;
        public ForecastViewHolder(View itemView) {
            super(itemView);
            mDateTv = itemView.findViewById(R.id.date_tv);
            mMinTempTv = itemView.findViewById(R.id.min_temp_tv);
            mMaxTempTv = itemView.findViewById(R.id.max_temp_tv);
            mForecastWeatherIcon = itemView.findViewById(R.id.forecast_weather_icon);
        }
    }

    public void setmForecasts(ArrayList<Weather> mForecasts) {
        this.mForecasts = mForecasts;
    }
}
