package de.syntaxinstitut.confetti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            createConfettiEffect(it)
        }
    }
}

class ConfettiView(context: Context) : View(context) {
    init {
        // Set a random color for each confetti piece
        setBackgroundColor(Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
    }
}

fun createConfettiEffect(view: View) {

    val container = view.parent as ViewGroup

    // Create and add a number of confetti views to the container
    for (i in 1..50) {
        val confetti = ConfettiView(view.context)

        container.addView(confetti, ViewGroup.LayoutParams(10, 10))

        // Set the starting position
        confetti.translationX = view.x + (view.width/2)
        confetti.translationY = view.y + (view.height/2)


        // Define the range for translation and rotation
        val rangeX = -500..500
        val rangeY = -500..500
        val rangeRotation = 0..360

        // Generate random values within the defined ranges
        val randomTranslationX = Random.nextInt(rangeX.start, rangeX.endInclusive).toFloat()
        val randomTranslationY = Random.nextInt(rangeY.start, rangeY.endInclusive).toFloat()
        val randomRotation = Random.nextInt(rangeRotation.start, rangeRotation.endInclusive).toFloat()


        // Animate the confetti
        confetti.animate()
            .translationXBy(randomTranslationX)
            .translationYBy(randomTranslationY)
            .rotationBy(randomRotation)
            .setDuration(1000) // duration in milliseconds, adjust as needed
            .withEndAction {
                container.removeView(confetti)
            }
            .start()
    }
}

