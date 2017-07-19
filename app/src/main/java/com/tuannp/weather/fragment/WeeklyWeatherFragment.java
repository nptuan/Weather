package com.tuannp.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tuannp.weather.R;
import com.tuannp.weather.model.TodayWeatherResponse;
import com.tuannp.weather.model.WeeklyWeatherResponse;
import com.tuannp.weather.service.RetrofitInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyễn Phương Tuấn on 18-Jul-17.
 */

public class WeeklyWeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weekly_weather, container, false);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", "Long-An");
        queryMap.put("type", "accurate");
        queryMap.put("cnt", "7");
        queryMap.put("units", "metric");
        queryMap.put("appid", "c08befbfcde1c0ea8fbec0586c2538b8");
        final Call<WeeklyWeatherResponse> call = service.getWeeklyWeatherInfo(queryMap);
        call.enqueue(new Callback<WeeklyWeatherResponse>() {
            @Override
            public void onResponse(Call<WeeklyWeatherResponse> call, Response<WeeklyWeatherResponse> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getCity().getName() + "\ntemp " + response.body().getList().get(0).getTemp().getDay() + "\nweather " + response.body().getList().get(0).getWeather().get(0).getDescription();
                    ((TextView)view.findViewById(R.id.textView)).setText(result);
                    Glide.with(getContext()).load("http://openweathermap.org/img/w/"+ response.body().getList().get(0).getWeather().get(0).getIcon() + ".png").into((ImageView) view.findViewById(R.id.imageView));
                }
            }

            @Override
            public void onFailure(Call<WeeklyWeatherResponse> call, Throwable t) {

            }
        });
        return view;
    }
}
