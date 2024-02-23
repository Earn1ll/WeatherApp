package ru.earn1ll.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.earn1ll.weatherapp.databinding.ActivityMainBinding
import ru.earn1ll.weatherapp.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder,MainFragment.newInstance())
            .commit()
    }
}