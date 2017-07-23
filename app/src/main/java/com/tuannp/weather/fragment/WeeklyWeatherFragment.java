package com.tuannp.weather.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tuannp.weather.MainActivity;
import com.tuannp.weather.R;
import com.tuannp.weather.adapter.WeeklyWeatherAdapter;
import com.tuannp.weather.databinding.FragmentWeeklyWeatherBinding;
import com.tuannp.weather.model.List;
import com.tuannp.weather.model.TodayWeatherResponse;
import com.tuannp.weather.model.WeeklyWeatherResponse;
import com.tuannp.weather.service.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    FragmentWeeklyWeatherBinding binding;
    WeeklyWeatherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weekly_weather, container, false);
        setData();
        return binding.getRoot();
    }

    private void setData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        setWeeklyWeatherData(service);
    }

    private void setWeeklyWeatherData(RetrofitInterface service) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        String currentDateTime= sdf.format(new Date());
        final String[] date = currentDateTime.split("-");
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", MainActivity.location);
        queryMap.put("type", "accurate");
        queryMap.put("cnt", "8");
        queryMap.put("units", "metric");
        queryMap.put("appid", "c08befbfcde1c0ea8fbec0586c2538b8");

        final Call<WeeklyWeatherResponse> call = service.getWeeklyWeatherInfo(queryMap);
        call.enqueue(new Callback<WeeklyWeatherResponse>() {
            @Override
            public void onResponse(Call<WeeklyWeatherResponse> call, Response<WeeklyWeatherResponse> response) {
                if (response.isSuccessful() & response.body() != null) {

                    ((AppCompatActivity)getActivity()).getSupportActionBar()
                            .setTitle(getResources().getString(R.string.app_name) + " " + response.body().getCity().getName());

                    ArrayList<List> listWeatherData = response.body().getList();
                    listWeatherData.remove(0);
                    adapter = new WeeklyWeatherAdapter(listWeatherData, getContext(), date);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    binding.recyclerViewWeekly.setLayoutManager(mLayoutManager);
                    binding.recyclerViewWeekly.setItemAnimator(new DefaultItemAnimator());
                    binding.recyclerViewWeekly.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<WeeklyWeatherResponse> call, Throwable t) {

            }
        });
    }

    public void refreshData() {
        setData();
    }
}
