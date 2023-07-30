package com.android.weatherapp.ui.dashboard;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.weatherapp.MiscUtil;
import com.android.weatherapp.db.CityRepository;
import com.android.weatherapp.db.models.City;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherDashboardViewModel extends ViewModel {

    public LiveData<List<City>> allCitiesLiveData;

    @Inject
    public WeatherDashboardViewModel(CityRepository cityRepository) {
        allCitiesLiveData = cityRepository.getCities();
    }

    public String getAverageTemp(City selectedCity, String seasonName) {
        int avgTemp = 0;
        MiscUtil.Season season = MiscUtil.Season.fromString(seasonName);

        switch (season) {
            case SEASON_SPRING:
                int tempMar = selectedCity.tempMar;
                int tempApr = selectedCity.tempApr;
                int tempMay = selectedCity.tempMay;
                avgTemp = (tempMar + tempApr + tempMay) / 3;
                break;

            case SEASON_SUMMER:
                int tempJun = selectedCity.tempJun;
                int tempJul = selectedCity.tempJul;
                int tempAug = selectedCity.tempAug;
                avgTemp = (tempJun + tempJul + tempAug) / 3;
                break;

            case SEASON_AUTUMN:
                int tempSep = selectedCity.tempSep;
                int tempOct = selectedCity.tempOct;
                int tempNov = selectedCity.tempNov;
                avgTemp = (tempSep + tempOct + tempNov) / 3;
                break;

            case SEASON_WINTER:
                int tempDec = selectedCity.tempDec;
                int tempJan = selectedCity.tempJan;
                int tempFeb = selectedCity.tempFeb;
                avgTemp = (tempDec + tempJan + tempFeb) / 3;
                break;
            default:
                System.out.println("Unknown season!");
        }
        return String.valueOf(avgTemp);
    }
}
