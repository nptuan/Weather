package com.tuannp.weather.model;

import java.util.ArrayList;

/**
 * Created by Nguyễn Phương Tuấn on 23-Jul-17.
 */

public class List
{
    private int dt;

    public int getDt() { return this.dt; }

    public void setDt(int dt) { this.dt = dt; }

    private Temp temp;

    public Temp getTemp() { return this.temp; }

    public void setTemp(Temp temp) { this.temp = temp; }

    private double pressure;

    public double getPressure() { return this.pressure; }

    public void setPressure(double pressure) { this.pressure = pressure; }

    private int humidity;

    public int getHumidity() { return this.humidity; }

    public void setHumidity(int humidity) { this.humidity = humidity; }

    private ArrayList<Weather> weather;

    public ArrayList<Weather> getWeather() { return this.weather; }

    public void setWeather(ArrayList<Weather> weather) { this.weather = weather; }

    private double speed;

    public double getSpeed() { return this.speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    private int deg;

    public int getDeg() { return this.deg; }

    public void setDeg(int deg) { this.deg = deg; }

    private int clouds;

    public int getClouds() { return this.clouds; }

    public void setClouds(int clouds) { this.clouds = clouds; }

    private Double rain;

    public Double getRain() { return this.rain; }

    public void setRain(Double rain) { this.rain = rain; }
}
