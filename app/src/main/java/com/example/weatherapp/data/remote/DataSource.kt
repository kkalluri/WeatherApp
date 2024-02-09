package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class DataSource @Inject constructor(
    private val weatherAPI: WeatherAPI
) {
     fun getWeatherByCity(city: String):Flow<NetworkResult<WeatherResponse>> = flow {
        try {
            emit(NetworkResult.Loading(true))

            val response = weatherAPI.getWeatherByCity(city, WEATHER_API_KEY)
            emit(NetworkResult.Success(response))
        } catch (e: Exception) {
            // Handle the network error and emit a Failure
            emit(NetworkResult.Failure("Failed to fetch weather"))
        }
    }.flowOn(Dispatchers.IO)

    companion object{
       val WEATHER_API_KEY = "8728c2d223a14f48783efb7da1cb5d2d"
    }

}
