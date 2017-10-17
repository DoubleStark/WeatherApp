package hu.bme.aut.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind
{
    @SerializedName("speed")
    @Expose
    public Double speed;

    @SerializedName("deg")
    @Expose
    public Double deg;

    public Wind(Double speed, Double deg)
    {
        this.speed = speed;
        this.deg = deg;
    }

    public Double getSpeed()
    {
        return speed;
    }

    public void setSpeed(Double speed)
    {
        this.speed = speed;
    }

    public Double getDeg()
    {
        return deg;
    }

    public void setDeg(Double deg)
    {
        this.deg = deg;
    }
}
