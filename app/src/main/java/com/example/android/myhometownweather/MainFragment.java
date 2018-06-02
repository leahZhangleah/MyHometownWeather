package com.example.android.myhometownweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.myhometownweather.sync.NetworkUtils;
import com.example.android.myhometownweather.sync.WeatherAsyncTaskLoader;

import java.net.URL;
import java.util.ArrayList;

public class MainFragment extends Fragment{
    ArrayList<Weather> weathers;
    private final int LOADER_VERSION = 1;
    private LoaderManager.LoaderCallbacks<ArrayList<Weather>> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<ArrayList<Weather>>() {
        @NonNull
        @Override
        public Loader<ArrayList<Weather>> onCreateLoader(int id, @Nullable Bundle args) {
            //todo:get current location
            if (id == LOADER_VERSION){
                String currentLocation = "Ningbo";
                URL urlToQuery = NetworkUtils.buildUrl(currentLocation);
                Log.i("MainFragment","the query url is:"+urlToQuery);
                return new WeatherAsyncTaskLoader(getContext(),urlToQuery);
            }
            return null;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<ArrayList<Weather>> loader, ArrayList<Weather> data) {
            if (data != null){
                //weathers = data;
                Log.i("MainFragment","onloadfinished is called");
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction wTransaction = manager.beginTransaction();
                FragmentTransaction fTransaction = manager.beginTransaction();
                //WeatherFragment weatherFragment = new WeatherFragment();
                //ForecastFragment forecastFragment = new ForecastFragment();
                Log.i("MainFragment","onviewcreated is called");
                WeatherFragment weatherFragment = WeatherFragment.newInstance(data);
                ForecastFragment forecastFragment = ForecastFragment.newInstance(data);
                wTransaction.replace(R.id.weather_fragment_container,weatherFragment).commit();
                fTransaction.replace(R.id.forecast_fragment_container,forecastFragment).commit();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<ArrayList<Weather>> loader) {
            weathers=null;
        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_VERSION,null,mLoaderCallbacks);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment,container,false);
        Log.i("MainFragment","oncreateview is called");
        return rootView;
    }

}
