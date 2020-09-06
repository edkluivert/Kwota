package com.kluivert.kwota.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.kluivert.kwota.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment_nav))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_nav)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}