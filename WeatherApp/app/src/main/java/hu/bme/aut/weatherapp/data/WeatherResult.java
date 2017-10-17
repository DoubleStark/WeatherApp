package hu.bme.aut.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResult
{
    @SerializedName("coord")
    @Expose
    public Coordinates coord;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("cod")
    @Expose
    public Double cod;

    @SerializedName("wind")
    @Expose
    public Wind wind;

    @SerializedName("visibility")
    @Expose
    public Double visibility;

    @SerializedName("base")
    @Expose
    public String base;

    @SerializedName("sys")
    @Expose
    public Sys sys;

    @SerializedName("dt")
    @Expose
    public Double dt;

    @SerializedName("main")
    @Expose
    public Main main;

    @SerializedName("weather")
    @Expose
    public List<Weather> weather;

    @SerializedName("clouds")
    @Expose
    public Clouds clouds;

    public WeatherResult(Coordinates coord, String name, Double cod, Wind wind, Double visibility, String base, Sys sys, Double dt, Main main, List<Weather> weather, Clouds clouds)
    {
        this.coord = coord;
        this.name = name;
        this.cod = cod;
        this.wind = wind;
        this.visibility = visibility;
        this.base = base;
        this.sys = sys;
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
    }

    public Clouds getClouds()
    {
        return clouds;
    }

    public void setClouds(Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Coordinates getCoord()
    {
        return coord;
    }

    public void setCoord(Coordinates coord)
    {
        this.coord = coord;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getCod()
    {
        return cod;
    }

    public void setCod(Double cod)
    {
        this.cod = cod;
    }

    public Wind getWind()
    {
        return wind;
    }

    public void setWind(Wind wind)
    {
        this.wind = wind;
    }

    public Double getVisibility()
    {
        return visibility;
    }

    public void setVisibility(Double visibility)
    {
        this.visibility = visibility;
    }

    public String getBase()
    {
        return base;
    }

    public void setBase(String base)
    {
        this.base = base;
    }

    public Sys getSys()
    {
        return sys;
    }

    public void setSys(Sys sys)
    {
        this.sys = sys;
    }

    public Double getDt()
    {
        return dt;
    }

    public void setDt(Double dt)
    {
        this.dt = dt;
    }

    public Main getMain()
    {
        return main;
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public List<Weather> getWeather()
    {
        return weather;
    }

    public void setWeather(List<Weather> weather)
    {
        this.weather = weather;
    }
}
