package ru.earn1ll.weatherapp.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject
import ru.earn1ll.weatherapp.MainViewModel
import ru.earn1ll.weatherapp.databinding.FragmentMainBinding
import ru.earn1ll.weatherapp.fragments.adapters.VpAdapter
import ru.earn1ll.weatherapp.fragments.adapters.WeatherAdapter
import ru.earn1ll.weatherapp.fragments.adapters.WeatherModel
import com.android.volley.toolbox.StringRequest as StringRequest1

const val API_KEY = "36fd04a64c4241798fd112721242302"

class MainFragment : Fragment() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding
    private val model: MainViewModel by activityViewModels()

    private val flist = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance()
    )
    private val tlist = listOf(
        "Hours",
        "Days"

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        updateCurrentCard()
        requestWeatherData("Brest")
    }

    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, flist)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = tlist[pos]
        }.attach()
    }

    private fun updateCurrentCard() = with(binding){
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            val maxMinTemperature = "${it.maxTemperature}°C / ${it.minTemperature}°C"
            val temperature = "${it.currentTemperature} °C"

            tvData.text = it.time
            tvCity.text = it.city
            tvRegion.text = it.region
            tvCountry.text = it.country
            tvCurrentTemp.text = temperature
            tvCondition.text = it.condition
            tvMaxMin.text = maxMinTemperature
            Picasso.get().load("https:" +it.imageUrl).into(imWeather)
        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "3" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parsWeatherData(result)
            },
            { error ->
                Log.d("myLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    private fun parsWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list =parseDays(mainObject)
        parsCurrentWeatherData(mainObject,list[0])
    }

    private fun parseDays(mainObject: JSONObject):List<WeatherModel> {
        val list = ArrayList<WeatherModel>()
        val daysArray = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
        val name = mainObject.getJSONObject("location").getString("name")
        val region = mainObject.getJSONObject("location").getString("region")
        val country = mainObject.getJSONObject("location").getString("country")

        for (i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                day.getJSONArray("hour").toString(),
                region,
                country
            )
            list.add(item)
        }
        return list
    }

    private fun parsCurrentWeatherData(mainObject: JSONObject,weatherItem:WeatherModel) {
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            weatherItem.maxTemperature,
            weatherItem.minTemperature,
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            weatherItem.hours,
            mainObject.getJSONObject("location").getString("region"),
            mainObject.getJSONObject("location").getString("country"),
        )
        model.liveDataCurrent.value = item

    }


    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}