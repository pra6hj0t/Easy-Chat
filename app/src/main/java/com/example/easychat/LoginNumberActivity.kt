package com.example.easychat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hbb20.CountryCodePicker

class LoginNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        setContentView(R.layout.activity_login_number)


        val counterCodePicker = findViewById<CountryCodePicker>(R.id.Login_countrycode)
        val userPhoneNumber = findViewById<EditText>(R.id.login_mobile_number)
        val btnSendOtp = findViewById<Button>(R.id.btn_send_otp)
        val progressBar = findViewById<ProgressBar>(R.id.login_progress_bar)

      progressBar.visibility = View.GONE

        counterCodePicker.registerCarrierNumberEditText(userPhoneNumber)

        btnSendOtp.setOnClickListener {
            if (!counterCodePicker.isValidFullNumber){
                userPhoneNumber.setError("Not Valid")
                return@setOnClickListener
            }

            val intent = Intent(this,LoginOtpScreen::class.java)
            intent.putExtra("number",counterCodePicker.fullNumber)
            startActivity(intent)
            finish()
        }

    }
}