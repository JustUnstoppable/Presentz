package com.example.presentz.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.example.presentz.R
import com.example.presentz.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInActivity : BaseActivity() {
    var _tbSignIn: androidx.appcompat.widget.Toolbar? = null
    private lateinit var _btn_signin: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        auth= Firebase.auth
        _tbSignIn = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_sign_in_activity)
        _btn_signin = findViewById<Button>(R.id.btn_sign_in)
        _btn_signin.setOnClickListener{
            signInRegisteredUser()
        }
        setupActionBar()
    }
    fun signInSuccess(user:User){
        hideProgressDialog()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
    private fun setupActionBar(){
        setSupportActionBar(_tbSignIn)

        val actionBar = supportActionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        _tbSignIn?.setNavigationOnClickListener{onBackPressed()}
    }
    private fun signInRegisteredUser() {
        val email: String =
            findViewById<EditText>(R.id.et_email_signin).text.toString().trim { it <= ' ' }
        val password: String =
            findViewById<EditText>(R.id.et_password_signin).text.toString().trim { it <= ' '}
        if(validateForm(email,password)){
            showProgressDialog("please wait")
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Registration failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }

    }
    private fun validateForm(email: String, password:String): Boolean{
        return when {
            TextUtils.isEmpty(email) ->{
                showErrorSnackBar("Please enter a email")
                false
            }
            TextUtils.isEmpty(password) ->{
                showErrorSnackBar("Please enter a password")
                false
            }else -> {
                true
            }
        }
    }
}