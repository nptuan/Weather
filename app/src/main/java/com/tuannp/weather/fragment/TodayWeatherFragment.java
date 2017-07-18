package com.tuannp.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tuannp.weather.R;
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

    String body = null;
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
        queryMap.put("id", "524901");
//        queryMap.put("type", "accurate");
//        queryMap.put("mode", "json");
//        queryMap.put("units", "metric");
        queryMap.put("appid", "c08befbfcde1c0ea8fbec0586c2538b8");
        final Call<JSONObject> call = service.getWeatherInfo(queryMap);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    body = call.execute().body().toString();
                }
                catch (IOException e) {

                }
                getActivity().runOnUiThread(new Runnable() {
                    public void run(){
                        ((TextView)view.findViewById(R.id.textView)).setText(body);
                    }
                });

            }
        }).start();

//        call.enqueue(new Callback<JSONObject>() {
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                if (response.isSuccessful()) {
//                    ((TextView)view.findViewById(R.id.textView)).setText(response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//
//            }
//        });


        return view;
    }
}
