package com.kluivert.kwota.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.quote_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.miFav->{

            }
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_nav)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}