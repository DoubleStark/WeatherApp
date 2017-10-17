package hu.bme.aut.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates
{
    @SerializedName("lon")
    @Expose
    public Double lon;

    @SerializedName("lat")
    @Expose
    public Double lat;

    public Coordinates(Double lon, Double lat)
    {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon()
    {
        return lon;
    }

    public void setLon(Double lon)
    {
        this.lon = lon;
    }

    public Double getLat()
    {
        return lat;
    }

    public void setLat(Double lat)
    {
        this.lat = lat;
    }
}
