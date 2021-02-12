package com.example.openweatherapimulticitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherAPI = retrofit.create(WeatherApi.class);

        String userCities = "524901,703448,2643743";
        String apiKey = ""; //PUT API KEY HERE
        String unitsMeasure = "imperial";

        Call<cityList> call = weatherAPI.getUserCitiesQuery(userCities,apiKey,unitsMeasure); //give IDs here

        call.enqueue(new Callback<cityList>() {
            @Override
            public void onResponse(Call<cityList> call, Response<cityList> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                cityList cities = response.body();
                List<singleCity> temp = cities.getList();

                for (singleCity city: temp){
                    String content = "";
                    content += "ID: " + city.getCityID() + "\n";
                    content += "Name: " + city.getCityName() + "\n";
                    content += "Temp: " + city.getMainTemperture() + "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<cityList> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }
}