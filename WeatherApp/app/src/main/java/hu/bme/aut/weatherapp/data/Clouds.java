package hu.bme.aut.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 12-Oct-17.
 */

public class Clouds
{
    @SerializedName("all")
    @Expose
    public Double all;

    public Clouds(Double all)
    {
        this.all = all;
    }

    public Double getAll()
    {
        return all;
    }

    public void setAll(Double all)
    {
        this.all = all;
    }
}
