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
import com.tuannp.weather.service.RetrofitInterface;

import org.json.JSONObject;

import java.io.IOException;
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

public class TodayWeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_today_weather, container, false);
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
        queryMap.put("mode", "json");
        queryMap.put("units", "metric");
        queryMap.put("appid", "c08befbfcde1c0ea8fbec0586c2538b8");
        final Call<TodayWeatherResponse> call = service.getWeatherInfo(queryMap);
        call.enqueue(new Callback<TodayWeatherResponse>() {
            @Override
            public void onResponse(Call<TodayWeatherResponse> call, Response<TodayWeatherResponse> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getName() + "\ntemp " + response.body().getMain().getTemp() + "\nweather " + response.body().getWeather().get(0).getDescription();
                    ((TextView)view.findViewById(R.id.textView)).setText(result);
                    Glide.with(getContext()).load("http://openweathermap.org/img/w/"+ response.body().getWeather().get(0).getIcon() + ".png").into((ImageView) view.findViewById(R.id.imageView));
                }
            }

            @Override
            public void onFailure(Call<TodayWeatherResponse> call, Throwable t) {

            }
        });


        return view;
    }
}
