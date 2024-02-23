package ru.earn1ll.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.earn1ll.weatherapp.fragments.adapters.WeatherModel

class MainViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}