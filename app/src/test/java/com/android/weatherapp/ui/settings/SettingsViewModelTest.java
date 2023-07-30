package com.android.weatherapp.ui.settings;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.weatherapp.db.CityRepository;
import com.android.weatherapp.db.models.City;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SettingsViewModelTest {

    @Mock
    private CityRepository mockCityRepository;

    private SettingsViewModel settingsViewModel;

    @Before
    public void setup() {
        when(mockCityRepository.getCities()).thenReturn(new MutableLiveData<>(getTestCities()));
        settingsViewModel = new SettingsViewModel(mockCityRepository);
    }

    @Test
    public void testAllCitiesLiveDataNotNull() {
        assertNotNull(settingsViewModel.allCitiesLiveData);
    }

    private List<City> getTestCities() {
        // Provide some dummy test cities for the test
        List<City> cities = new ArrayList<>();
        City city1 = new City("City1", "Large");
        city1.tempJan = 20;
        city1.tempFeb = 40;
        city1.tempMar = 30;
        city1.tempApr = 20;
        city1.tempMay = 34;
        city1.tempJun = 30;
        city1.tempJul = 20;
        city1.tempAug = 45;
        city1.tempSep = 33;
        city1.tempOct = 25;
        city1.tempNov = 43;
        city1.tempDec = 38;

        cities.add(city1);
        City city2 = new City("City2", "Small");
        cities.add(city2);
        // Add more cities if needed
        return cities;
    }

}