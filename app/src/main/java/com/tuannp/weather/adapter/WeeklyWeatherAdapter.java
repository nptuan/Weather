package com.tuannp.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuannp.weather.R;
import com.tuannp.weather.model.List;

import java.util.ArrayList;

/**
 * Created by Nguyễn Phương Tuấn on 23-Jul-17.
 */

public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.ViewHolder> {
    //list data to display to view
    private ArrayList<List> mDataset;
    private Context  context;
    String[] date;

    /**
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item
     * provide access to all the views for a data item in a view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewDate, textViewTempDay, textViewTempNight, textViewDayWeather, textViewHumidity, textViewPressure;
        public ImageView imageViewIcon;
        ImageView imageViewWeather;
        public ViewHolder(View v) {
            super(v);
            imageViewIcon = (ImageView) v.findViewById(R.id.imageViewIcon);
            textViewDate = (TextView) v.findViewById(R.id.textViewDate);
            textViewTempDay = (TextView) v.findViewById(R.id.textViewTempDay);
            textViewTempNight = (TextView) v.findViewById(R.id.textViewTempNight);
            textViewDayWeather = (TextView) v.findViewById(R.id.textViewDayWeather);
            textViewHumidity = (TextView) v.findViewById(R.id.textViewHumidity);
            textViewPressure = (TextView) v.findViewById(R.id.textViewPressure);
            imageViewWeather = (ImageView) v.findViewById(R.id.imageViewWeather);
        }
    }

    public WeeklyWeatherAdapter(ArrayList<List> myDataset, Context context, String[] date) {
        mDataset = myDataset;
        this.context = context;
        this.date = date;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WeeklyWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weekly_weather, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get element from list at this position
        //replace the contents of the view with that element
        //set data to view
        List dayWeather = mDataset.get(position);
        holder.textViewDate.setText((Integer.parseInt(date[0])+position+1)+"/"+date[1]);
        holder.textViewTempDay.setText(Html.fromHtml("Day <b>"+ dayWeather.getTemp().getDay()+"</b>°C"));
        holder.textViewTempNight.setText(Html.fromHtml("Night <b>"+ dayWeather.getTemp().getNight()+"</b>°C"));
        holder.textViewHumidity.setText(Html.fromHtml("Humidity <b>"+ dayWeather.getHumidity()+"</b>%"));
        holder.textViewPressure.setText(Html.fromHtml("Pressure <b>"+ dayWeather.getPressure()+"</b> hPa"));
        holder.textViewDayWeather.setText(dayWeather.getWeather().get(0).getDescription()+"");
        Glide
                .with(context)
                .load("http://openweathermap.org/img/w/"+ dayWeather.getWeather().get(0).getIcon()+".png")
                .into(holder.imageViewIcon);
        holder.imageViewWeather.setImageResource(getImageResource(dayWeather.getWeather().get(0).getId()));

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
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