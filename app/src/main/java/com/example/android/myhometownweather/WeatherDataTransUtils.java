package com.example.android.myhometownweather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherDataTransUtils {
    private WeatherDataTransUtils(){
    }
    public static String transformDate(long dateToTransform){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        Date date = new Date(dateToTransform);
        String dayOfWeek = formatter.format(date);
        return dayOfWeek;
    }

}
