package com.android.weatherapp.ui.citydetail;

import static com.android.weatherapp.MiscUtil.CITY_TYPES;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.weatherapp.databinding.ActivityCityDetailBinding;
import com.android.weatherapp.db.models.City;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CityDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String BUNDLE_CITY = "CITY";

    private String selectedCityType;

    private City city;

    private ActivityCityDetailBinding binding;
    private CityDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CityDetailViewModel.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            city = getIntent().getSerializableExtra(BUNDLE_CITY, City.class);
        } else {
            city = (City) getIntent().getSerializableExtra(BUNDLE_CITY);
        }

        initViews();
    }

    private void initViews() {
        // Enable the back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setCityData(city);

        binding.addCityBtn.setOnClickListener(v -> {
            if (city == null) {
                validateAndInsert();
            } else {
                validateAndUpdate();
            }

            //Finishing current activity
            finish();
        });

        binding.cityTypeSpinner.setOnItemSelectedListener(this);
    }

    private boolean validate() {
        //TODO: Need to add validation conditions here
        return true;
    }

    private void validateAndInsert() {
        if (validate()) {
            City city = createCityFromUi();
            if (city != null) {
                viewModel.addCity(city);
                Toast.makeText(this, "Inserted successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void validateAndUpdate() {
        if (validate()) {
            City city = createCityFromUi();
            if (city != null) {
                city.cityId = this.city.cityId;
                viewModel.updateCity(city);
                Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private City createCityFromUi() {
        String cityName = binding.cityNameEdt.getText().toString().trim();
        if (cityName.isEmpty()) {
            Toast.makeText(this, "City name can't be empty..", Toast.LENGTH_SHORT).show();
            return null;
        }
        City city = new City(cityName, selectedCityType);
        city.tempJan = getTempIntVal(binding.tempJanEdt.getText().toString());
        city.tempFeb = getTempIntVal(binding.tempFebEdt.getText().toString());
        city.tempMar = getTempIntVal(binding.tempMarEdt.getText().toString());
        city.tempApr = getTempIntVal(binding.tempAprEdt.getText().toString());
        city.tempMay = getTempIntVal(binding.tempMayEdt.getText().toString());
        city.tempJun = getTempIntVal(binding.tempJunEdt.getText().toString());
        city.tempJul = getTempIntVal(binding.tempJulyEdt.getText().toString());
        city.tempAug = getTempIntVal(binding.tempAugEdt.getText().toString());
        city.tempSep = getTempIntVal(binding.tempSepEdt.getText().toString());
        city.tempOct = getTempIntVal(binding.tempOctEdt.getText().toString());
        city.tempNov = getTempIntVal(binding.tempNovEdt.getText().toString());
        city.tempDec = getTempIntVal(binding.tempDecEdt.getText().toString());
        return city;
    }

    private int getTempIntVal(String monthTempVal) {
        if (monthTempVal.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(monthTempVal);
    }

    private void setCityData(City city) {
        if (city == null) return;

        binding.cityNameEdt.setText(city.name);
        setSelectedCityType(city.cityType);

        binding.tempJanEdt.setText(String.valueOf(city.tempJan));
        binding.tempFebEdt.setText(String.valueOf(city.tempFeb));
        binding.tempMarEdt.setText(String.valueOf(city.tempMar));
        binding.tempAprEdt.setText(String.valueOf(city.tempApr));
        binding.tempMayEdt.setText(String.valueOf(city.tempMay));
        binding.tempJunEdt.setText(String.valueOf(city.tempJun));
        binding.tempJulyEdt.setText(String.valueOf(city.tempJul));
        binding.tempAugEdt.setText(String.valueOf(city.tempAug));
        binding.tempSepEdt.setText(String.valueOf(city.tempSep));
        binding.tempOctEdt.setText(String.valueOf(city.tempOct));
        binding.tempNovEdt.setText(String.valueOf(city.tempNov));
        binding.tempDecEdt.setText(String.valueOf(city.tempDec));
    }

    private void setSelectedCityType(String cityType) {
        int position = 0;
        for (int i = 0; i < CITY_TYPES.length; i++) {
            if (CITY_TYPES[i].equals(cityType)) {
                selectedCityType = cityType;
                position = i;
                break;
            }
        }
        binding.cityTypeSpinner.setSelection(position);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Perform the back button action here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCityType = binding.cityTypeSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}