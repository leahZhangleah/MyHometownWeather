package com.example.android.myhometownweather.sync;

import android.net.Uri;
import android.util.Log;

import com.example.android.myhometownweather.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkUtils {

    private NetworkUtils(){}
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String FORECAST_BASE_URL = "http://worksample-api.herokuapp.com/forecast/daily";
    private static final String QUERY_PARAM = "q";
    private static final String API_PARAM = "key";
    private static final String API_KEY = "62fc4256-8f8c-11e5-8994-feff819cdc9f";

    public static URL buildUrl(String locationQuery) {
        Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, locationQuery)
                .appendQueryParameter(API_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    public static ArrayList<Weather> fetchDataByURL(URL url){
        String jsonResponse = "";
        try{
            jsonResponse = getResponseFromHttpUrl(url);
        }catch (IOException e){
            //todo: set error msg
        }
        ArrayList<Weather> weathersArrayList = extractDataFromString(jsonResponse);
        return weathersArrayList;
    }

    private static String getResponseFromHttpUrl(URL url)throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            }else{
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }

    private static ArrayList<Weather> extractDataFromString(String jsonResponse){
        if (jsonResponse == null){
            Log.i(TAG,"the jsonresponse is null");
            return null;
        }
        ArrayList<Weather> weatherArrayList = new ArrayList<>();
        try{
            JSONObject object = new JSONObject(jsonResponse);
            if (object.has("cod")){
                int errorCode =object.getInt("cod");
                Log.i("NetworkUtils","error code is: "+errorCode);
                switch (errorCode){
                    case HttpURLConnection.HTTP_OK:
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        return null;
                        default:return null;
                }
            }
            JSONObject city = object.getJSONObject("city");
            String cityName = city.getString("name");
            Log.i("NetworkUtils","the city is: "+cityName);
            String country = city.getString("country");
            Log.i("NetworkUtils","the country is: "+country);
            JSONArray lists = object.getJSONArray("list");
            for (int i=0; i<lists.length();i++){
                JSONObject list = (JSONObject) lists.get(i);
                long date = list.getLong("dt");
                Log.i("NetworkUtils","the date is: "+date);
                double pressure = list.getDouble("pressure");
                Log.i("NetworkUtils","the pressure is: "+pressure);
                int humidity = list.getInt("humidity");
                Log.i("NetworkUtils","the humidity is: "+humidity);
                double speed = list.getDouble("speed");
                Log.i("NetworkUtils","the speed is: "+speed);
                JSONObject temp = list.getJSONObject("temp");
                double dayTemp = temp.getDouble("day");
                Log.i("NetworkUtils","the day temp is: "+dayTemp);
                double min = temp.getDouble("min");
                Log.i("NetworkUtils","the min is: "+min);
                double max = temp.getDouble("max");
                Log.i("NetworkUtils","the max is: "+max);
                JSONArray weatherArray = list.getJSONArray("weather");
                JSONObject weatherItem = (JSONObject) weatherArray.get(0);
                int id = weatherItem.getInt("id");
                Log.i("NetworkUtils","the id is: "+id);
                String mainDescription = weatherItem.getString("main");
                Log.i("NetworkUtils","the description is: "+mainDescription);
                String icon = weatherItem.getString("icon");
                Log.i("NetworkUtils","the icon is: "+icon);
                Weather weatherArrayListItem = new Weather(mainDescription,icon,country,cityName,humidity,pressure,dayTemp,speed,min,max,date);
                weatherArrayList.add(weatherArrayListItem);
            }
        }catch (JSONException e){
            //todo:
        }
        return weatherArrayList;
    }


}
