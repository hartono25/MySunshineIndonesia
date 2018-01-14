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

import com.iak.belajar.mysunshineindonesia.R;
import com.iak.belajar.mysunshineindonesia.adapter.WeatherAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_today) TextView mainToday;
    @BindView(R.id.main_weather_image) ImageView mainWeatherImage;
    @BindView(R.id.main_weather_desc) TextView mainWeatherDesc;
    @BindView(R.id.main_weather_temp) TextView mainWeatherTemp;
    @BindView(R.id.main_weather_list) RecyclerView mainWeatherList;
    private com.iak.belajar.mysunshineindonesia.adapter.WeatherAdapter WeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView(){
        ButterKnife.bind(this);

        /*mainToday           =   (TextView)findViewById(R.id.main_today);
        mainWeatherImage    =   (ImageView)findViewById(R.id.main_weather_image);
        mainWeatherDesc     =   (TextView)findViewById(R.id.main_weather_desc);
        mainWeatherTemp     =   (TextView)findViewById(R.id.main_weather_temp);
        mainWeatherList     =   (RecyclerView)findViewById(R.id.main_weather_list);*/

        mainWeatherList.setLayoutManager(new LinearLayoutManager(this));
        mainWeatherList.setHasFixedSize(true);

        WeatherAdapter = new WeatherAdapter();
        mainWeatherList.setAdapter(WeatherAdapter);

        mainToday.setText("Minggu");
        mainWeatherImage.setImageResource(R.mipmap.ic_launcher_round);
        mainWeatherDesc.setText("Cuaca Berawan");
        mainWeatherTemp.setText("100" + getString(R.string.degree));

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
