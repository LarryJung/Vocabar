package com.dot2line.vocabar.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dot2line.vocabar.R
import com.facebook.shimmer.ShimmerFrameLayout

class SplashActivity : AppCompatActivity() {

    val SPLASH_TIME = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val shimmer: ShimmerFrameLayout = findViewById(R.id.shimmer) as ShimmerFrameLayout
        shimmer.angle = ShimmerFrameLayout.MaskAngle.CW_0
        shimmer.startShimmerAnimation()

        Handler().postDelayed({
            start()
        }, SPLASH_TIME)
    }

    fun start() {
        val intent = Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}