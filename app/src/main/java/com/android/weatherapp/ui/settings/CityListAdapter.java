package com.android.weatherapp.ui.settings;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.weatherapp.R;
import com.android.weatherapp.db.models.City;
import com.android.weatherapp.ui.citydetail.CityDetailActivity;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private List<City> cities;

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        public TextView cityNameTv;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTv = itemView.findViewById(R.id.name_txt);
            itemView.findViewById(R.id.arrow_txt).setVisibility(View.VISIBLE);
        }

        public void bind(City city) {
            cityNameTv.setText(city.name);
            itemView.setOnClickListener(v -> launchCityDetail(city));
        }

        private void launchCityDetail(City city) {
            Intent intent = new Intent(itemView.getContext(), CityDetailActivity.class);
            intent.putExtra(CityDetailActivity.BUNDLE_CITY, city);
            itemView.getContext().startActivity(intent);
        }
    }
}
