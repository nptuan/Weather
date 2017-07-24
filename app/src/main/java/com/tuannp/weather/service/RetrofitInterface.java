package com.tuannp.weather.service;

import com.tuannp.weather.model.TodayWeatherResponse;
import com.tuannp.weather.model.WeeklyWeatherResponse;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Nguyễn Phương Tuấn on 23-Jul-17.
 */

public interface RetrofitInterface {
    @GET("/data/2.5/weather")
    Call<TodayWeatherResponse> getWeatherInfo(@QueryMap Map<String, String> options);

    @GET("/data/2.5/forecast/daily")
    Call<WeeklyWeatherResponse> getWeeklyWeatherInfo(@QueryMap Map<String, String> options);
}
