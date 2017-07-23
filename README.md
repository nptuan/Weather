# Weather
A Weather App use OpenWeatherMap API and Retrofit 2

# Screen 
Main screen contains a TabLayout with 2 fragments: DAILY and WEEKLY. Use can swipe to change between 2 fragments.

DAILY fragment: weather infomation for today and tomorrow.

WEEKLY fragment: weather infomation for the next 7 days.

ActionBar Refresh Button and Location Button:

  +Refresh Button: call API and refresh weather data.

  +Location Button: set specify a location, then call API with new location and update weather data.


# OpenWeatherMap API
This application use OpenWeatherMap API to get weather infomation by using Retrofit to call request and handle response.

OpenWeatherMap API:

  +API to get current weather infomation: http://api.openweathermap.org/data/2.5/weather?units=metric&mode=json&type=accurate&appid=myAppId&q=cityName

  +API to get 16 day/daily weather forecast: http://api.openweathermap.org/data/2.5/forecast/daily?units=metric&type=accurate&cnt=8&appid=myAppId&q=cityName
  
For more infomation: https://openweathermap.org/api

# Retrofit:

Request to get curreent weather: 
  
@GET("/data/2.5/weather")
  
Call<TodayWeatherResponse> getWeatherInfo(@QueryMap Map<String, String> options);
  
Request to get weekly weather:
  
@GET("/data/2.5/forecast/daily")

Call<WeeklyWeatherResponse> getWeeklyWeatherInfo(@QueryMap Map<String, String> options);

# WorkFlow

In the first time user install this app, it will show weather for Tokyo.

User can set current location by choose Location Button in ActionBar, after that, the new location will be stored in SharePreferences for the next time and app will update weather data for new location. 

User can update the newest weather infomation by choose Refresh Button in ActionBar, app will get the newest data from OpenWeather and update weather data.

When user device does not have internet connection, a SnakeBar message will show when user set new location.

If user type the incorrect location and OpenWeather can not determine, a SnakeBar messega will show when user set new location.

When user change location or choose Refresh, app will make a call request to OpenWeather API to get the newest data, then update data when the response is received.
