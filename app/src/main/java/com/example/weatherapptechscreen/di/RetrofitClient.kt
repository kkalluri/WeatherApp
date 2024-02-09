package com.example.weatherapptechscreen.di

import com.example.weatherapptechscreen.data.remote.WeatherAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 *
 * WeatherAppTechScreen
 * Created by venkatakalluri on 12/4/23.
 * Copyright © 2023 Kaiser Permanente. All rights reserved.
 */
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
