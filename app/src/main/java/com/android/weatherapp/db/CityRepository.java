package com.android.weatherapp.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.android.weatherapp.db.dao.CityDao;
import com.android.weatherapp.db.models.City;

import java.util.List;

import javax.inject.Inject;

public class CityRepository {

    private CityDao cityDao;

    @Inject
    public CityRepository(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public LiveData<List<City>> getCities() {
        return cityDao.getAllCities();
    }

    public void addCity(City city) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                cityDao.insert(city);
            }
        }.start();
    }

    public void updateCity(City city) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                cityDao.update(city);
            }
        }.start();
    }
}
