package com.cleansample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cleansample.api_android.location.contract.LocationApiContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var locationApiContract: LocationApiContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
//            val locations = locationApiContract.getLocations(10.0F, 20.0F)
//            sampleToast(locations)
        }
    }

    private fun sampleToast(locations: List<String>) {
        Toast.makeText(this@MainActivity, locations.toString(), Toast.LENGTH_LONG).show()
    }

}
