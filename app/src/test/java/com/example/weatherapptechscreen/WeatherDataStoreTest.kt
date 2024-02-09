package com.example.weatherapptechscreen

/**
 *
 * WeatherAppTechScreen
 * Created by venkatakalluri on 11/29/23.
 * Copyright © 2023 Kaiser Permanente. All rights reserved.
 */
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.weatherapptechscreen.data.local.WeatherDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherDataStoreTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Use the custom TestCoroutineRule
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var weatherDataStore: WeatherDataStore

    @Before
    fun setUp() {
        weatherDataStore = WeatherDataStore(ApplicationProvider.getApplicationContext(), testCoroutineRule.testCoroutineDispatcher)
    }


    @Test
    fun `saveLastSearchedCity should store the correct city name`() = testCoroutineRule.runBlockingTest {
        // Arrange
        val cityName = "New York"

        // Act
        weatherDataStore.saveLastSearchedCity(cityName)

        // Assert
        val storedCity = weatherDataStore.getLastSearchedCity()
        assertEquals(cityName, storedCity)
    }

    @Test
    fun `getLastSearchedCity should return default city when not set`() = testCoroutineRule.runBlockingTest {
        // Act
        val storedCity = weatherDataStore.getLastSearchedCity()

        // Assert
        assertEquals("", storedCity)
    }

    @Test
    fun `getLastSearchedCity should return the correct city after saving`() = testCoroutineRule.runBlockingTest {
        // Arrange
        val cityName = "Los Angeles"
        weatherDataStore.saveLastSearchedCity(cityName)

        // Act
        val storedCity = weatherDataStore.getLastSearchedCity()

        // Assert
        assertEquals(cityName, storedCity)
    }
}

