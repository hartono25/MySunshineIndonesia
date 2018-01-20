package com.iak.belajar.mysunshineindonesia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iak.belajar.mysunshineindonesia.R;
import com.iak.belajar.mysunshineindonesia.WeatherController;
import com.iak.belajar.mysunshineindonesia.WeatherEvent;
import com.iak.belajar.mysunshineindonesia.adapter.WeatherAdapter;
import com.iak.belajar.mysunshineindonesia.app;
import com.iak.belajar.mysunshineindonesia.model.Forecast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_today)
    TextView mainToday;
    @BindView(R.id.main_weather_image)
    ImageView mainWeatherImage;
    @BindView(R.id.main_weather_desc)
    TextView mainWeatherDesc;
    @BindView(R.id.main_weather_temp)
    TextView mainWeatherTemp;
    @BindView(R.id.main_weather_list)
    RecyclerView mainWeatherList;

    private WeatherAdapter WeatherAdapter;
    private EventBus eventBus = app.getInstance().getEventBus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventBus.register(this);

        initView();

        WeatherController controller = new WeatherController();
        controller.getWeatherList();

    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }

    private void initView(){
        ButterKnife.bind(this);

        mainWeatherList.setLayoutManager(new LinearLayoutManager(this));
        mainWeatherList.setHasFixedSize(true);

        WeatherAdapter = new WeatherAdapter();
        mainWeatherList.setAdapter(WeatherAdapter);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveWeatherList(WeatherEvent event){
        if(event.isSuccess()){
            List<Forecast> forecasts = event.getForecastList();
            Forecast todayForecast = forecasts.get(0);

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTimeInMillis(todayForecast.getForecastDate()*1000);
            calendar.getTime();
            String calendarStr = calendar.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (calendar.get(GregorianCalendar.MONTH)+1) + "-" + calendar.get(GregorianCalendar.YEAR);
            mainToday.setText(calendarStr);
            Glide.with(this).load(getWeatherImageUrl(todayForecast.getWeatherList().get(0).getWeatherIcon())).into(mainWeatherImage);
            mainWeatherDesc.setText(todayForecast.getWeatherList().get(0).getWeatherDesc());
            mainWeatherTemp.setText(todayForecast.getTemperature().getTempDay() + getString(R.string.degree));

            WeatherAdapter.setData(forecasts);
        }else{

        }
    }

    private String getWeatherImageUrl(String weatherIcon) {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.action_setting){
            Toast.makeText(this, "Ini Menu Setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}
