package hu.bme.aut.weatherapp.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import hu.bme.aut.weatherapp.MainActivity;
import hu.bme.aut.weatherapp.data.City;
import hu.bme.aut.weatherapp.R;
import hu.bme.aut.weatherapp.touch.CityTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> implements CityTouchHelperAdapter
{
    private List<City> citiesList;

    private Context context;

    public CitiesAdapter(Context context)
    {
        this.context = context;

       citiesList = new ArrayList<City>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.tvCity.setText(citiesList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((MainActivity)context).openWeatherActivity(holder.getAdapterPosition(),
                        citiesList.get(holder.getAdapterPosition()).getName()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }


    public void addCity(City city) {
        citiesList.add(0, city);
        notifyItemInserted(0);
    }

    @Override
    public void onItemDismiss(int position) {
        citiesList.remove(position);

        // refreshes the whole list
        //notifyDataSetChanged();
        // refreshes just the relevant part that has been deleted
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        /*todoList.add(toPosition, todoList.get(fromPosition));
        todoList.remove(fromPosition);*/

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(citiesList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(citiesList, i, i - 1);
            }
        }


        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivCity;
        private TextView tvCity;

        public ViewHolder(View itemView)
        {
            super(itemView);

            ivCity = (ImageView) itemView.findViewById(R.id.ivCity);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
        }
    }
}
