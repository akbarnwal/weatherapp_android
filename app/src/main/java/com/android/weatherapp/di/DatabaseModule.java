package com.android.weatherapp.di;

import static com.android.weatherapp.db.CityDatabase.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.android.weatherapp.db.CityDatabase;
import com.android.weatherapp.db.dao.CityDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public CityDatabase provideCityDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, CityDatabase.class, DATABASE_NAME).build();
    }

    @Provides
    public CityDao provideCityDao(CityDatabase database) {
        return database.cityDao();
    }
}
