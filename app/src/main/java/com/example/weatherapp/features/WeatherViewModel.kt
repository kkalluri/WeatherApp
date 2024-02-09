package com.example.weatherapp.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.DataSource
import com.example.weatherapp.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class WeatherViewModel @Inject constructor(private val dataSource: DataSource) :
    ViewModel() {


    private val _city = MutableLiveData<String>()
    val city: LiveData<String> get() = _city

    fun updateCity(city: String) {
        _city.value = city
    }

    // hold the network result of weather
    private val _weatherResponse = MutableStateFlow<NetworkResult<WeatherResponse>>(
        NetworkResult.Loading(false)
    )
    val weatherResponse = _weatherResponse.asStateFlow()


    // Function to fetch weather information by city name
    fun getWeatherByCity(city: String) {
        viewModelScope.launch {
            dataSource.getWeatherByCity(city).collect { networkResult ->
                _weatherResponse.value = networkResult
            }
        }
    }
}

