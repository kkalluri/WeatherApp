package com.example.weatherapp.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.NetworkResult

@Composable
fun WeatherInfo(viewModel: WeatherViewModel, fetchWeatherDataByCity: (String) -> Unit) {

    val enteredCity by viewModel.city.observeAsState()
    val weatherResponseResult = viewModel.weatherResponse.collectAsState().value


    // Trigger data fetching when the entered city
    LaunchedEffect(enteredCity) {
        println("LaunchedEffect triggered enteredCity with value: ${viewModel.city.value}")
        viewModel.city.value?.let { selectedItem ->
            if(!selectedItem.isNullOrBlank()) {
                fetchWeatherDataByCity(selectedItem)
            }
        }
    }

    when (weatherResponseResult) {
        is NetworkResult.Loading -> {
            if((weatherResponseResult as NetworkResult.Loading).isLoading) {
                LoadingBar()
            }
        }

        is NetworkResult.Success -> {
            var response = (weatherResponseResult as NetworkResult.Success<WeatherResponse>).data
            Column(
                Modifier
                    .padding(20.dp)
            ) {
                WeatherInfo(response)
            }
        }

        is NetworkResult.Failure -> {
            var errorMessage =
                (weatherResponseResult as NetworkResult.Failure<WeatherResponse>).errorMessage
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(10.dp))
        }
    }
}


@Composable
fun WeatherInfo(weatherData: WeatherResponse?) {
    if (weatherData != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Display weather information, e.g., temperature, description
            Text("City Name: ${weatherData.name}")
            Text("Temperature: ${weatherData.main.temp} °C")
            Text("Description: ${weatherData.weather.firstOrNull()?.description}")
        }
    }
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .width(50.dp)
                .height(4.dp).testTag("LinearProgressIndicator")
        )
    }
}
