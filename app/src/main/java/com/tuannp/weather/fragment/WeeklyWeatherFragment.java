package com.tuannp.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuannp.weather.R;

/**
 * Created by Nguyễn Phương Tuấn on 18-Jul-17.
 */

public class WeeklyWeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly_weather, container, false);
    }
}
