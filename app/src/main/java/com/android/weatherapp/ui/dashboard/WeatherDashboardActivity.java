package com.android.weatherapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.weatherapp.R;
import com.android.weatherapp.databinding.ActivityWeatherDashboardBinding;
import com.android.weatherapp.db.models.City;
import com.android.weatherapp.ui.settings.SettingsActivity;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherDashboardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    private ActivityWeatherDashboardBinding binding;
    private WeatherDashboardViewModel viewModel;

    private CitySpinnerAdapter citySpinnerAdapter;

    private City selectedCity;
    private String selectedCityName;
    private String selectedSeasonName;
    private String selectedCityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

        viewModel = new ViewModelProvider(this).get(WeatherDashboardViewModel.class);

        viewModel.allCitiesLiveData.observe(this, cities -> {
            citySpinnerAdapter.clear();
            citySpinnerAdapter.addAll(cities);

            //
            if (!cities.isEmpty()) {
                binding.cityTypeTxt.setText(cities.get(0).cityType);
                binding.citiesSpinner.setSelection(0);
            }
        });
    }

    private void initViews() {
        citySpinnerAdapter = new CitySpinnerAdapter(this, new ArrayList<>());
        binding.citiesSpinner.setAdapter(citySpinnerAdapter);

        binding.citiesSpinner.setOnItemSelectedListener(this);
        binding.seasonsSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (binding.citiesSpinner.getSelectedItem() == null) {
            return;
        }
        selectedCity = (City) binding.citiesSpinner.getSelectedItem();
        binding.cityTypeTxt.setText(selectedCity.cityType);

        selectedSeasonName = (String) binding.seasonsSpinner.getSelectedItem();

        String temperature = viewModel.getAverageTemp(selectedCity, selectedSeasonName);
        binding.tempValueTxt.setText(temperature + "°C");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}