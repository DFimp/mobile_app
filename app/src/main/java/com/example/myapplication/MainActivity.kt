package com.example.myapplication

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kolobok: ImageView = findViewById(R.id.kolobok)
        val house: ImageView = findViewById(R.id.house)

        // Получаем координаты домика для расчета пути
        house.post {
            val houseX = house.x
            val kolobokX = kolobok.x

            // Настройка анимации движения Колобка к домику
            val animation = TranslateAnimation(
                0f, houseX - kolobokX, 0f, 0f
            ).apply {
                duration = 3000  // Длительность анимации
                repeatCount = Animation.INFINITE  // Зацикливание
                repeatMode = Animation.RESTART
            }

            kolobok.startAnimation(animation)
        }
    }
}
