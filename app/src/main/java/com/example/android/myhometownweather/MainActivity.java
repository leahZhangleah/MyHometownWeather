package com.example.android.myhometownweather;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager mMainViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        TabLayout tabDots = (TabLayout) findViewById(R.id.tab_dots);
        tabDots.setupWithViewPager(mMainViewPager,true);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mMainViewPager.setAdapter(adapter);
    }
}
