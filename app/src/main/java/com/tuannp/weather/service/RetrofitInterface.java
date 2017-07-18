package com.tuannp.weather.service;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Nguyễn Phương Tuấn on 18-Jul-17.
 */

public interface RetrofitInterface {
    @GET("/data/2.5/forecast")
    Call<JSONObject> getWeatherInfo(@QueryMap Map<String, String> options);
}
