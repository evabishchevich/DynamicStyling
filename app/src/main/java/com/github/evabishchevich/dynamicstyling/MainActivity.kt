package com.github.evabishchevich.dynamicstyling

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), CustomViewsInflater(delegate))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnChangeTheme).setOnClickListener {
            val newTheme = when (ThemeManager.theme) {
                ThemeManager.Theme.DARK -> ThemeManager.Theme.LIGHT
                ThemeManager.Theme.LIGHT -> ThemeManager.Theme.DARK
            }
            ThemeManager.theme = newTheme
        }

        findViewById<View>(R.id.ivCoffee).setOnClickListener {
            Log.d("XXX", "Coffee clicked")
        }
    }
}
