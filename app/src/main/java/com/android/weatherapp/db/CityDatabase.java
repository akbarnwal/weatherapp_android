package com.android.weatherapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.weatherapp.db.dao.CityDao;
import com.android.weatherapp.db.models.City;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class CityDatabase extends RoomDatabase {
    public abstract CityDao cityDao();

    public static final String DATABASE_NAME = "city_database";
}
