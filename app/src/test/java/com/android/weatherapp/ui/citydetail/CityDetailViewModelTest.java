package com.android.weatherapp.ui.citydetail;

import static org.mockito.Mockito.verify;
import com.android.weatherapp.db.CityRepository;
import com.android.weatherapp.db.models.City;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class CityDetailViewModelTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private CityRepository mockCityRepository;

    private CityDetailViewModel cityDetailViewModel;

    @Before
    public void setup() {
        cityDetailViewModel = new CityDetailViewModel(mockCityRepository);
    }

    @Test
    public void testAddCity() {
        City cityToAdd = new City("Test City", "Small");
        cityDetailViewModel.addCity(cityToAdd);
        verify(mockCityRepository).addCity(cityToAdd);
    }

    @Test
    public void testUpdateCity() {
        City cityToUpdate = new City("Test City", "Small");
        cityDetailViewModel.updateCity(cityToUpdate);
        verify(mockCityRepository).updateCity(cityToUpdate);
    }
}