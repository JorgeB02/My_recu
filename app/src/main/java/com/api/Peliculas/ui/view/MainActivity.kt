package com.api.Peliculas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.api.Peliculas.R
import com.api.Peliculas.data.database.ProductDb
import com.api.Peliculas.databinding.ActivityMainBinding
import com.api.Peliculas.data.model.ProductObjectItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tempArrayList: ArrayList<ProductObjectItem>
    lateinit var db: ProductDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getSupportActionBar()?.hide()


        db = Room.databaseBuilder(applicationContext, ProductDb::class.java, "products")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

    }

}

val Fragment.db: ProductDb
    get() = (requireActivity() as MainActivity).db
