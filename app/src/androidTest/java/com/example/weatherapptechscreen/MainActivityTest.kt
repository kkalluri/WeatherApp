package com.example.weatherapptechscreen

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.weatherapp.MainActivity
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.data.model.Main
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.features.LoadingBar
import com.example.weatherapp.features.SearchScreen
import com.example.weatherapp.features.WeatherInfo
import com.example.weatherapp.features.WeatherViewModel
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

/**
 *
 * WeatherAppTechScreen
 * Created by venkatakalluri on 2/1/24.
 * Copyright © 2024 Kaiser Permanente. All rights reserved.
 */


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

/*

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var activity: MainActivity

    @Before
    fun init() {
        hiltRule.inject()
        composeTestRule.activityRule.scenario.onActivity {
            activity = it
        }
    }
*/

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createAndroidComposeRule<ComponentActivity>()


    @Test
    fun testWeatherInfo() {
        // Mock the WeatherResponse data
        val mockWeatherResponse = WeatherResponse(
            main = Main(temp = 25.0, pressure = 1015, humidity = 60),
            weather = listOf(Weather(id = 801, main = "Clear", description = "Sunny", icon = "01d")),
            name = "Berlin"
        )


            // Set up the WeatherInfo composable with the mocked data
            composeTestRule.setContent {
                WeatherInfo(mockWeatherResponse)
            }

        // Perform assertions for WeatherInfo
        composeTestRule.onNodeWithText("City Name: Berlin").assertIsDisplayed()
        composeTestRule.onNodeWithText("Temperature: 25.0 °C").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description: Sunny").assertIsDisplayed()
    }

    @Test
    fun testLoadingBar() {
        // Now, continue with your Compose test for LoadingBar
        composeTestRule.setContent {
            LoadingBar()
        }

        // Verify that the loading bar is displayed
        composeTestRule.onNodeWithTag("LinearProgressIndicator").assertIsDisplayed()
    }
}
