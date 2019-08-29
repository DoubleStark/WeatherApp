package hu.bme.aut.weatherapp.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hu.bme.aut.weatherapp.MainActivity;
import hu.bme.aut.weatherapp.data.City;
import hu.bme.aut.weatherapp.R;
import hu.bme.aut.weatherapp.touch.CityTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> implements CityTouchHelperAdapter {
    private List<City> citiesList;

    private Context context;

    public CitiesAdapter(Context context) {
        this.context = context;

        citiesList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCity.setText(citiesList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).openWeatherActivity(holder.getAdapterPosition(),
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

        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCity;
        private TextView tvCity;

        public ViewHolder(View itemView) {
            super(itemView);

            ivCity = itemView.findViewById(R.id.ivCity);
            tvCity = itemView.findViewById(R.id.tvCity);
        }
    }
}
