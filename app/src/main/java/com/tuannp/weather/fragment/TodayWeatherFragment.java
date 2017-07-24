package com.tuannp.weather.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.tuannp.weather.databinding.FragmentTodayWeatherBinding;
import com.tuannp.weather.model.List;
import com.tuannp.weather.model.TodayWeatherResponse;
import com.tuannp.weather.model.WeeklyWeatherResponse;
import com.tuannp.weather.service.RetrofitInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyễn Phương Tuấn on 23-Jul-17.
 */

public class TodayWeatherFragment extends Fragment {

    private FragmentTodayWeatherBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false);
        setData();
        return binding.getRoot();
    }

    /**
     * function set data for today and tomorrow weather information
     */
    private void setData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);
        setTodayWeatherData(service);
        setTomorrowWeatherData(service);
    }

    /**
     * function to get and set data for today weather information
     * @param service
     */
    private void setTodayWeatherData(RetrofitInterface service) {
        //get date to display update time
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM hh:mm");
        final String currentDateTime = sdf.format(new Date());

        //query map to call weather api
        Map<String, String> todayWeatherQueryMap = new HashMap<>();
        todayWeatherQueryMap.put("q", MainActivity.location);
        todayWeatherQueryMap.put("type", "accurate");
        todayWeatherQueryMap.put("mode", "json");
        todayWeatherQueryMap.put("units", "metric");
        todayWeatherQueryMap.put("appid", "c08befbfcde1c0ea8fbec0586c2538b8");

        //make a call request to get weather info
        final Call<TodayWeatherResponse> weatherInfoCall = service.getWeatherInfo(todayWeatherQueryMap);
        weatherInfoCall.enqueue(new Callback<TodayWeatherResponse>() {
            @Override
            public void onResponse(Call<TodayWeatherResponse> call, Response<TodayWeatherResponse> response) {

                //if response success, update weather data to view
                if (response.isSuccessful() & response.body() != null) {
                    binding.itemTodayWeather.textViewName.setText(response.body().getName());
                    binding.itemTodayWeather.textViewCountry.setText(response.body().getSys().getCountry());
                    binding.itemTodayWeather.textViewWeatherName.setText(response.body().getWeather().get(0).getMain());
                    binding.itemTodayWeather.textViewWeatherDescription.setText(response.body().getWeather().get(0).getDescription());
                    binding.itemTodayWeather.textViewTemp.setText(response.body().getMain().getTemp()+"°C");
                    binding.itemTodayWeather.textViewTempMinMax.setText(response.body().getMain().getTempMin()+"°C ~ "+response.body().getMain().getTempMax()+"°C");
                    binding.itemTodayWeather.textViewHumidity.setText(Html.fromHtml("Humidity <b>" + response.body().getMain().getHumidity()+"</b>%"));
                    binding.itemTodayWeather.textViewWin.setText(Html.fromHtml("Win speed <b>" + response.body().getWind().getSpeed()+"</b> MPS"));
                    binding.itemTodayWeather.textViewPressure.setText(Html.fromHtml("Pressure <b>" + response.body().getMain().getPressure()+"</b> hPa"));
                    binding.itemTodayWeather.textViewLastUpdate.setText(Html.fromHtml("Updated: " + currentDateTime));

                    //display weather icon to imageView
                    Glide.with(getContext())
                            .load("http://openweathermap.org/img/w/"+ response.body().getWeather().get(0).getIcon() + ".png")
                            .into(binding.itemTodayWeather.imageViewIcon);
                    binding.itemTodayWeather.imageViewWeather.setImageResource(getImageResource(response.body().getWeather().get(0).getId()));
                }
                //if request fail, show error message in Snakebar
                else {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        Snackbar.make(getView(), "Error: " + jsonObject.getString("message"), Snackbar.LENGTH_SHORT).show();
                    }
                    catch (JSONException e) {}
                    catch (IOException ex) {}
                }
            }

            @Override
            public void onFailure(Call<TodayWeatherResponse> call, Throwable t) {
                Snackbar.make(getView(), "Failed to get Weather Info", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * function to get and set data for tomorrow weather information
     * @param service
     */
    private void setTomorrowWeatherData(RetrofitInterface service) {

        //query map to call weather api
        Map<String, String> weeklyWeatherQueryMap = new HashMap<>();
        weeklyWeatherQueryMap.put("q", MainActivity.location);
        weeklyWeatherQueryMap.put("type", "accurate");
        weeklyWeatherQueryMap.put("cnt", "8");
        weeklyWeatherQueryMap.put("units", "metric");
        weeklyWeatherQueryMap.put("appid", "c08befbfcde1c0ea8fbec0586c2538b8");

        //make a call request to get weather info
        final Call<WeeklyWeatherResponse> weeklyWeatherInfoCall = service.getWeeklyWeatherInfo(weeklyWeatherQueryMap);
        weeklyWeatherInfoCall.enqueue(new Callback<WeeklyWeatherResponse>() {
            @Override
            public void onResponse(Call<WeeklyWeatherResponse> call, Response<WeeklyWeatherResponse> response) {
                //if response success, update weather data to view
                if (response.isSuccessful() & response.body() != null) {
                    List dayWeather = response.body().getList().get(1);
                    binding.itemTomorrowWeather.textViewDate.setText("Tomorrow");
                    binding.itemTomorrowWeather.textViewTempDay.setText(Html.fromHtml("Day <b>"+ dayWeather.getTemp().getDay()+"</b>°C"));
                    binding.itemTomorrowWeather.textViewTempNight.setText(Html.fromHtml("Night <b>"+ dayWeather.getTemp().getNight()+"</b>°C"));
                    binding.itemTomorrowWeather.textViewHumidity.setText(Html.fromHtml("Humidity <b>"+ dayWeather.getHumidity()+"</b>%"));
                    binding.itemTomorrowWeather.textViewPressure.setText(Html.fromHtml("Pressure <b>"+ dayWeather.getPressure()+"</b> hPa"));
                    binding.itemTomorrowWeather.textViewDayWeather.setText(dayWeather.getWeather().get(0).getDescription()+"");

                    //display weather icon to imageView
                    Glide
                            .with(getContext())
                            .load("http://openweathermap.org/img/w/"+ dayWeather.getWeather().get(0).getIcon()+".png")
                            .into(binding.itemTomorrowWeather.imageViewIcon);

                    binding.itemTomorrowWeather.imageViewWeather.setImageResource(getImageResource(dayWeather.getWeather().get(0).getId()));
                }
            }
            //if request fail, show error message in Snakebar
            @Override
            public void onFailure(Call<WeeklyWeatherResponse> call, Throwable t) {

            }
        });
    }

    /**
     * refresh data when user choose Refresh button
     */
    public void refreshData() {
        setData();
    }


    /**
     * get image resource id base on weather condition
     * @param weatherId
     * @return
     */
    private int getImageResource(int weatherId) {
        if (weatherId < 300) {
            return R.drawable.image_2xx;
        }
        else if (weatherId < 500) {
            return R.drawable.image_3xx;
        }
        else if (weatherId < 600) {
            return R.drawable.image_5xx;
        }
        else if (weatherId < 700) {
            return R.drawable.image_6xx;
        }
        else if (weatherId < 800) {
            return R.drawable.image_7xx;
        }
        else if (weatherId < 801) {
            return R.drawable.image_800;
        }
        else if (weatherId < 900) {
            return R.drawable.image_8xx;
        }
        else if (weatherId < 910) {
            return R.drawable.image_90x;
        }
        else {
            return R.drawable.image_9xx;
        }
    }
}
