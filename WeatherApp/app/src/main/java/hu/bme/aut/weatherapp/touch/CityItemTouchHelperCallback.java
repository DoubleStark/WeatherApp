package hu.bme.aut.weatherapp.touch;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;


public class CityItemTouchHelperCallback extends ItemTouchHelper.Callback
{
    private CityTouchHelperAdapter cityTouchHelperAdapter;

    public CityItemTouchHelperCallback(CityTouchHelperAdapter cityTouchHelperAdapter)
    {
        this.cityTouchHelperAdapter = cityTouchHelperAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
            return true;
        }

    @Override
    public boolean isItemViewSwipeEnabled() {
            return true;
        }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        cityTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        cityTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
