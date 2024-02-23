package ru.earn1ll.weatherapp

data class DayItem(
    val city: String,
    val region: String,
    val country: String,
    val time: String,
    val condition: String,
    val imageUrl: String,
    val currentTemperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val hours: String
    )
