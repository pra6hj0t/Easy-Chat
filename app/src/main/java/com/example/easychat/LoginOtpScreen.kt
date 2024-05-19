package com.example.easychat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit

class LoginOtpScreen : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var otpEditText: EditText
    lateinit var login_next_btn : Button
    lateinit var progressBar : ProgressBar
    lateinit var resend_otp_btn : TextView
    lateinit var verficationCode :String
    lateinit var ResendingToken : PhoneAuthProvider.ForceResendingToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_login_otp_screen)

        mAuth = FirebaseAuth.getInstance()

         otpEditText = findViewById<EditText>(R.id.login_otp)
         login_next_btn = findViewById<Button>(R.id.btn_login_next)
         progressBar = findViewById<ProgressBar>(R.id.otp_page_progress_bar)
         resend_otp_btn = findViewById<TextView>(R.id.resend_otp_textView)



      val phoneNumber = intent.getStringExtra("number")

        if (phoneNumber != null) {
            sendOtp(phoneNumber,false)
        }


        login_next_btn.setOnClickListener {

            Toast.makeText(this@LoginOtpScreen,"We will Work on next part Later....",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginOtpScreen,ComingSoon::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun sendOtp(phoneNumber: String, isResend :Boolean){
        setInProgress(true)
//        PhoneAuthOptions.newBuilder(mAuth)
            val builder = PhoneAuthOptions.Builder(mAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    signIn(p0)
                    setInProgress(false)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Toast.makeText(this@LoginOtpScreen,"OTP Verification Failed",Toast.LENGTH_SHORT).show()
                    setInProgress(false)
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)

                    verficationCode = p0
                    ResendingToken = p1
                    Toast.makeText(this@LoginOtpScreen,"OTP Sent Successfully",Toast.LENGTH_SHORT).show()
                    setInProgress(false)
                }

            })

        if (isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(ResendingToken).build())
        }else
        {
            PhoneAuthProvider.verifyPhoneNumber(builder.build())
        }

    }


    fun setInProgress(inProgress : Boolean){
        if (inProgress){
            progressBar.visibility = View.VISIBLE
            login_next_btn.visibility = View.GONE
        }
        else{
            progressBar.visibility = View.GONE
            login_next_btn.visibility = View.VISIBLE
        }
    }

    fun signIn(p0 : PhoneAuthCredential){
        //login And go to next Activity
    }
}