package com.example.presentz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.presentz.R
import com.example.presentz.activities.SignInActivity
import com.example.presentz.activities.SignUpActivity

class IntroActivity : BaseActivity() {
    lateinit var _btnSignUpIntro: Button
    lateinit var _btnSignInIntro: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        _btnSignInIntro = findViewById<Button>(R.id.btn_sign_in_intro)
        _btnSignInIntro.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }
        _btnSignUpIntro = findViewById<Button>(R.id.btn_sign_up_intro)
        // Add a click event for Sign Up button and launch the Sign Up Screen.
        _btnSignUpIntro.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}