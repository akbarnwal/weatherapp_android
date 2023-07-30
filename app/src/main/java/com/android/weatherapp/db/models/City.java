package com.android.weatherapp.db.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

import kotlin.jvm.Volatile;

@Entity(tableName = "cities")
public class City implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long cityId;
    public String name;
    public String cityType; // large, small, medium
    public int tempJan;
    public int tempFeb;
    public int tempMar;
    public int tempApr;
    public int tempMay;
    public int tempJun;
    public int tempJul;
    public int tempAug;
    public int tempSep;
    public int tempOct;
    public int tempNov;
    public int tempDec;

    public City(String name, String cityType) {
        this.name = name;
        this.cityType = cityType;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("City[name=%s]", this.name);
    }
}
