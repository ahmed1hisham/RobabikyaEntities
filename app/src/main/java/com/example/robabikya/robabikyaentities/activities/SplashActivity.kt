package com.example.robabikya.robabikyaentities.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.robabikya.robabikyaentities.R
import com.example.robabikya.robabikyaentities.firebase.FirebaseUtils


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (FirebaseUtils.firebaseAuth.currentUser != null) {
            startActivity(Intent(this, HomeScreen::class.java))

        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
