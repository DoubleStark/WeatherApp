package hu.bme.aut.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Sys
{
    @SerializedName("id")
    @Expose
    public Double id;

    @SerializedName("type")
    @Expose
    public Double type;

    @SerializedName("message")
    @Expose
    public Double message;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("sunrise")
    @Expose
    public Double sunrise;

    @SerializedName("sunset")
    @Expose
    public Double sunset;

    public Sys(Double id, Double type, Double message, String country, Double sunrise, Double sunset)
    {
        this.id = id;
        this.type = type;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Double getId()
    {
        return id;
    }

    public void setId(Double id)
    {
        this.id = id;
    }

    public Double getType()
    {
        return type;
    }

    public void setType(Double type)
    {
        this.type = type;
    }

    public Double getMessage()
    {
        return message;
    }

    public void setMessage(Double message)
    {
        this.message = message;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Double getSunrise()
    {
        return sunrise;
    }

    public void setSunrise(Double sunrise)
    {
        this.sunrise = sunrise;
    }

    public Double getSunset()
    {
        return sunset;
    }

    public void setSunset(Double sunset)
    {
        this.sunset = sunset;
    }

}
