package com.iak.belajar.mysunshineindonesia;

import android.util.Log;

import com.iak.belajar.mysunshineindonesia.model.Forecast;
import com.iak.belajar.mysunshineindonesia.model.WeatherResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muchamat on 20/01/2018.
 */

public class WeatherController {

    private static final String TAG = "WeatherController";
    private EventBus eventBus = app.getInstance().getEventBus();

    public void getWeatherList(){
        Call<WeatherResponse> dailyForecast = app.getInstance().getWeatherApi().getDailyForecast("Jakarta","json","metric",10,WeatherApi.API_KEY);
        dailyForecast.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d(TAG, "onResponse: Success");
                Log.d(TAG, "onResponse: JSON = " + app.getInstance().getGson().toJson(response.body()));
                List<Forecast> forecastList = response.body().getForecastList();
                WeatherEvent weatherEvent = new WeatherEvent(true, forecastList);
                eventBus.post(weatherEvent);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed");
                Log.e(TAG, "onFailure: Message = " + t.getMessage());
                WeatherEvent weatherEvent = new WeatherEvent(false, t.getMessage());
                eventBus.post(weatherEvent);
            }
        });
    }
}
