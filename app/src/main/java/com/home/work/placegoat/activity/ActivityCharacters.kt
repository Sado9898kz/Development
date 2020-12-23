package com.home.work.placegoat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home.work.placegoat.fragment.FragmentCharacters
import com.home.work.placegoat.R

class ActivityCharacters : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, FragmentCharacters())
            .disallowAddToBackStack()
            .commit()
    }
}