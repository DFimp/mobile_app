package com.example.myapplication

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация анимации для солнца
        val sun: ImageView = findViewById(R.id.sun)
        val sunriseAnimation = AnimationUtils.loadAnimation(this, R.anim.sunrise)
        sun.startAnimation(sunriseAnimation)

        // Инициализация анимации для Колобка
        val kolobok: ImageView = findViewById(R.id.kolobok)
        val moveRightAnimation = AnimationUtils.loadAnimation(this, R.anim.move_right)
        kolobok.startAnimation(moveRightAnimation)
    }
}

