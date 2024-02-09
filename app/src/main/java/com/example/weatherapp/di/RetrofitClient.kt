package com.example.weatherapp.di

import com.example.weatherapp.data.remote.WeatherAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {


    val instance: WeatherAPI by lazy {

      var okHttpClient =  OkHttpClient.Builder().build()


      val retrofit =  Retrofit.Builder()
            .baseUrl(WeatherAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(WeatherAPI::class.java)

    }


}
