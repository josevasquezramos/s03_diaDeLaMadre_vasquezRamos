package com.example.s3vasquezramos

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvMensaje: TextView
    private lateinit var btnHeart: Button
    private lateinit var btnAfter: Button
    private lateinit var heartContainer: FrameLayout
    private var isMessageVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMensaje = findViewById(R.id.tvMensaje)
        btnHeart = findViewById(R.id.btnHeart)
        btnAfter = findViewById(R.id.btnAfter)
        heartContainer = findViewById(R.id.heartContainer)

        btnHeart.setOnClickListener {
            if (!isMessageVisible) {
                btnHeart.visibility = View.GONE
                fadeIn(tvMensaje)
                btnAfter.visibility = View.VISIBLE
                isMessageVisible = true
            }
        }

        btnAfter.setOnClickListener {
            // Mostrar varios corazones flotando en posiciones aleatorias
            for (i in 0 until 10) { // Cambiar el número para mostrar más corazones
                showHeartAnimation()
            }
        }
    }

    private fun fadeIn(view: View) {
        view.visibility = View.VISIBLE
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 500
            fillAfter = true
        }
        view.startAnimation(fadeIn)
    }

    private fun showHeartAnimation() {
        val heart = ImageView(this)
        heart.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.heart))

        // Asignar un tamaño fijo
        val size = 100
        val layoutParams = FrameLayout.LayoutParams(size, size)

        // Generar una posición aleatoria en el contenedor
        layoutParams.leftMargin = (0..heartContainer.width - size).random()
        layoutParams.topMargin = 0  // Empieza desde la parte superior

        heart.layoutParams = layoutParams

        heartContainer.addView(heart)

        // Animación de movimiento: los corazones caen hacia abajo
        val anim = TranslateAnimation(0f, 0f, 0f, heartContainer.height.toFloat()).apply {
            duration = (3000..5000).random().toLong() // Duración aleatoria para variación
            fillAfter = true
        }

        heart.startAnimation(anim)

        // Opcional: quitar el corazón del contenedor después de la animación
        heart.postDelayed({
            heartContainer.removeView(heart)
        }, 5000)
    }
}