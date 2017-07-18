package com.tuannp.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tuannp.weather.fragment.TodayWeatherFragment;
import com.tuannp.weather.fragment.WeeklyWeatherFragment;

/**
 * Created by Nguyễn Phương Tuấn on 18-Jul-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TodayWeatherFragment todayWeatherFragment = new TodayWeatherFragment();
                return todayWeatherFragment;
            case 1:
                WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();
                return weeklyWeatherFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}