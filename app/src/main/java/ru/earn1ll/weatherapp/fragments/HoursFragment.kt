package ru.earn1ll.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import ru.earn1ll.weatherapp.R
import ru.earn1ll.weatherapp.databinding.FragmentHoursBinding
import ru.earn1ll.weatherapp.databinding.FragmentMainBinding
import ru.earn1ll.weatherapp.fragments.adapters.WeatherAdapter
import ru.earn1ll.weatherapp.fragments.adapters.WeatherModel


class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(

            WeatherModel(
                "",
                "12:00",
                "Sunny",
                "25°C",
                "",
                "",
                "",
                ""
            ),
            WeatherModel(
                "",
                "13:00",
                "Sunny",
                "26°C",
                "",
                "",
                "",
                ""
            ),
            WeatherModel(
                "",
                "14:00",
                "Sunny",
                "28°C",
                "",
                "",
                "",
                ""
            )
        )
        adapter.submitList(list)
    }

    companion object {

        fun newInstance() = HoursFragment()
    }
}