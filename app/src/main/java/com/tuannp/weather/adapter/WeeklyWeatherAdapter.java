package com.tuannp.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuannp.weather.R;
import com.tuannp.weather.model.List;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by Nguyễn Phương Tuấn on 20-Jul-17.
 */

public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.ViewHolder> {
    private ArrayList<List> mDataset;
    private Context  context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewDate, textViewTempDay, textViewTempNight, textViewDayWeather;
        public ImageView imageViewIcon;
        public ViewHolder(View v) {
            super(v);
            imageViewIcon = (ImageView) v.findViewById(R.id.imageViewIcon);
            textViewDate = (TextView) v.findViewById(R.id.textViewDate);
            textViewTempDay = (TextView) v.findViewById(R.id.textViewTempDay);
            textViewTempNight = (TextView) v.findViewById(R.id.textViewTempNight);
            textViewDayWeather = (TextView) v.findViewById(R.id.textViewDayWeather);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WeeklyWeatherAdapter(ArrayList<List> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WeeklyWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_weather, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewDate.setText((position+1)+"");
        holder.textViewTempDay.setText(mDataset.get(position).getTemp().getDay()+"°C");
        holder.textViewTempNight.setText(mDataset.get(position).getTemp().getNight()+"°C");
        holder.textViewDayWeather.setText(mDataset.get(position).getWeather().get(0).getDescription()+"");

        Glide
                .with(context)
                .load("http://openweathermap.org/img/w/"+ mDataset.get(position).getWeather().get(0).getIcon()+".png")
                .into(holder.imageViewIcon);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}