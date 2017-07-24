package com.tuannp.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tuannp.weather.fragment.TodayWeatherFragment;
import com.tuannp.weather.fragment.WeeklyWeatherFragment;

/**
 * Created by Nguyễn Phương Tuấn on 23-Jul-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private TodayWeatherFragment todayWeatherFragment;
    private WeeklyWeatherFragment weeklyWeatherFragment;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        todayWeatherFragment = new TodayWeatherFragment();
        weeklyWeatherFragment = new WeeklyWeatherFragment();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return todayWeatherFragment;
            case 1:
                return weeklyWeatherFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void refreshData() {
        todayWeatherFragment.refreshData();
        weeklyWeatherFragment.refreshData();
    }
}