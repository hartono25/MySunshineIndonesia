package com.iak.belajar.mysunshineindonesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Muchamat on 20/01/2018.
 */

public class WeatherResponse {
    @SerializedName("list")
    private List<Forecast> forecastList;

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
