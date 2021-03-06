package com.iak.belajar.mysunshineindonesia;

import com.iak.belajar.mysunshineindonesia.model.Forecast;

import java.util.List;

/**
 * Created by Muchamat on 20/01/2018.
 */

public class WeatherEvent {
    private boolean success;
    private String message;
    private List<Forecast> forecastList;

    public WeatherEvent(boolean success, List<Forecast> forecastList) {
        this.success = success;
        this.forecastList = forecastList;
    }

    public WeatherEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
