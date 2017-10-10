package hu.bme.aut.weatherapp.data;

/**
 * Created by User on 09-Oct-17.
 */

public class City
{
    private String name;

    public City()
    {}

    public City(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
