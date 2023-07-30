package com.android.weatherapp.ui.dashboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import androidx.lifecycle.MutableLiveData;

import com.android.weatherapp.db.CityRepository;
import com.android.weatherapp.db.models.City;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WeatherDashboardViewModelTest {

    private WeatherDashboardViewModel weatherDashboardViewModel;

    @Before
    public void setup() {
        // Setup the ViewModel with a mock repository if needed.
        CityRepository mockCityRepository = Mockito.mock(CityRepository.class);
        when(mockCityRepository.getCities()).thenReturn(new MutableLiveData<>(getTestCities()));
        weatherDashboardViewModel = new WeatherDashboardViewModel(mockCityRepository);
    }

    @Test
    public void testGetAverageTempForSpring() {
        City selectedCity = new City("City1", "Small");
        selectedCity.tempMar = 10;
        selectedCity.tempApr = 20;
        selectedCity.tempMay = 30;
        String seasonName = "Spring";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("20", result); // Expected average temperature for SPRING is 25
    }

    @Test
    public void testGetAverageTempForSpringNotEqual() {
        City selectedCity = new City("City1", "Small");
        selectedCity.tempMar = 40;
        selectedCity.tempApr = 20;
        selectedCity.tempMay = 30;
        String seasonName = "Spring";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertNotEquals("20", result); // Expected average temperature for SPRING is 25
    }

    @Test
    public void testGetAverageTempForSpringWithDefaultValueZero() {
        City selectedCity = new City("City1", "Small");
        String seasonName = "Spring";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("0", result); // Expected average temperature for SPRING is 25
    }

    @Test
    public void testGetAverageTempForSummer() {
        City selectedCity = new City("City2", "Medium");
        selectedCity.tempJun = 20;
        selectedCity.tempJul = 25;
        selectedCity.tempAug = 45;
        String seasonName = "Summer";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("30", result); // Expected average temperature for SUMMER is 37
    }

    @Test
    public void testGetAverageTempForSummerWithDefaultValueZero() {
        City selectedCity = new City("City2", "Medium");
        String seasonName = "Summer";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("0", result); // Expected average temperature for SUMMER is 37
    }

    @Test
    public void testGetAverageTempForAutumn() {
        City selectedCity = new City("City2", "Medium");
        selectedCity.tempSep = 35;
        selectedCity.tempOct = 25;
        selectedCity.tempNov = 45;
        String seasonName = "Autumn";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("35", result); // Expected average temperature for SUMMER is 37
    }

    @Test
    public void testGetAverageTempForAutumnWithDefaultValueZero() {
        City selectedCity = new City("City2", "Medium");
        String seasonName = "Autumn";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("0", result); // Expected average temperature for SUMMER is 37
    }

    @Test
    public void testGetAverageTempForWinter() {
        City selectedCity = new City("City2", "Medium");
        selectedCity.tempDec = 30;
        selectedCity.tempJan = 20;
        selectedCity.tempFeb = 40;
        String seasonName = "Winter";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("30", result); // Expected average temperature for SUMMER is 37
    }

    @Test
    public void testGetAverageTempForWinterWithDefaultValueZero() {
        City selectedCity = new City("City2", "Medium");
        String seasonName = "Winter";
        String result = weatherDashboardViewModel.getAverageTemp(selectedCity, seasonName);
        assertEquals("0", result); // Expected average temperature for SUMMER is 37
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
    // Add more test cases for other seasons if needed.
}