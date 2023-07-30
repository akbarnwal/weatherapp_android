package com.android.weatherapp.ui.citydetail;

import androidx.lifecycle.ViewModel;

import com.android.weatherapp.db.CityRepository;
import com.android.weatherapp.db.models.City;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CityDetailViewModel extends ViewModel {

    private final CityRepository cityRepository;

    @Inject
    public CityDetailViewModel(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(City city) {
        cityRepository.addCity(city);
    }

    public void updateCity(City city) {
        cityRepository.updateCity(city);
    }
}
