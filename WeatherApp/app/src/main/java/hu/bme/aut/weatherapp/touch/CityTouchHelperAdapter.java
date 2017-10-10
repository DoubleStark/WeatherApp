package hu.bme.aut.weatherapp.touch;

/**
 * Created by User on 09-Oct-17.
 */

public interface CityTouchHelperAdapter
{
    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);
}
