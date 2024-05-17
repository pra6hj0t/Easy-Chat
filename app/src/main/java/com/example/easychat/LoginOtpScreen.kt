package com.example.easychat

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class LoginOtpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_login_otp_screen)

            val progressBar = findViewById<ProgressBar>(R.id.otp_page_progress_bar)
        progressBar.visibility = View.GONE

        val phoneNumber = intent.getStringExtra("number")
        Toast.makeText(this,phoneNumber,Toast.LENGTH_SHORT).show()
//        val a: Map<String, String> = mutableMapOf()
//        FirebaseFirestore.getInstance().collection("Test").add(a)
    }
}