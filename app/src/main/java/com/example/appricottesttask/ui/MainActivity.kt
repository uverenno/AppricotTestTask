package com.example.appricottesttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.appricottesttask.R
import com.example.appricottesttask.ui.navigation.Navigate

class MainActivity : AppCompatActivity(),Navigate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
    }

    override fun navigate(id: Int, bundle: Bundle) {
        findNavController(R.id.fragment_container).navigate(id,bundle)
    }
}