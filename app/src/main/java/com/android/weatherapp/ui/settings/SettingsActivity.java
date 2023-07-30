package com.android.weatherapp.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.weatherapp.databinding.ActivitySettingsBinding;
import com.android.weatherapp.db.models.City;
import com.android.weatherapp.ui.citydetail.CityDetailActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = SettingsActivity.class.getSimpleName();

    private ActivitySettingsBinding binding;
    private SettingsViewModel settingsViewModel;

    private CityListAdapter cityListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        settingsViewModel.allCitiesLiveData.observe(this, cities -> {
            Log.i(TAG, "onChanged: " + cities);
            cityListAdapter.setCities(cities);
        });
    }

    private void initViews() {
        // Enable the back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cityListAdapter = new CityListAdapter();
        binding.citiesRv.setAdapter(cityListAdapter);

        binding.addCityBtn.setOnClickListener(v -> launchCityDetail(null));
    }

    private void launchCityDetail(City city) {
        Intent intent = new Intent(this, CityDetailActivity.class);
        intent.putExtra(CityDetailActivity.BUNDLE_CITY, city);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Perform the back button action here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
