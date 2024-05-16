package com.example.easychat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginOtpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_otp_screen)


        val phoneNumber = intent.getStringExtra("number")
        Toast.makeText(this,phoneNumber,Toast.LENGTH_SHORT).show()
    }
}