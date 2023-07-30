package com.android.weatherapp.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.weatherapp.db.CityRepository;
import com.android.weatherapp.db.models.City;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsViewModel extends ViewModel {

    public LiveData<List<City>> allCitiesLiveData;

    private final CityRepository cityRepository;

    @Inject
    public SettingsViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        allCitiesLiveData = cityRepository.getCities();
    }
}
