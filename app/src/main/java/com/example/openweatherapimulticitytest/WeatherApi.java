package com.example.openweatherapimulticitytest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("group")
    Call<cityList> getUserCitiesQuery(@Query("id") String citiesID,
                                      @Query("appid") String apiKey,
                                      @Query("units") String unitMeasure);
}
