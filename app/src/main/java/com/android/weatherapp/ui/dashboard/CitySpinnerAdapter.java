package com.android.weatherapp.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.weatherapp.R;
import com.android.weatherapp.db.models.City;

import java.util.List;

public class CitySpinnerAdapter extends ArrayAdapter<City> {

    private final LayoutInflater inflater;

    public CitySpinnerAdapter(Context context, List<City> cityList) {
        super(context, 0, cityList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_city, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.name_txt);

        // Get the City object for the current position
        City city = getItem(position);

        // Set the city name in the TextView
        if (city != null) {
            tvName.setText(city.name);
        }

        return convertView;
    }
}

