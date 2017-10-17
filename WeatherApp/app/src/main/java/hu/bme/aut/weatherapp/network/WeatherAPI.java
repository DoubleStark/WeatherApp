package hu.bme.aut.weatherapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import hu.bme.aut.weatherapp.data.WeatherResult;

public interface WeatherAPI
{
    @GET("data/2.5/weather")
    Call<WeatherResult> getWeather(@Query("q") String name, @Query("units") String units, @Query("appid") String appid);
}
