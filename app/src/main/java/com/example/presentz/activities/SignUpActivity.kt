package com.example.presentz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.presentz.R
import com.example.presentz.firebase.FirestoreClass
import com.example.presentz.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {
    var _tbSignUp: androidx.appcompat.widget.Toolbar?=null
    lateinit var _btn_sign_up: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        _tbSignUp = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_sign_up_activity)
        _btn_sign_up = findViewById<Button>(R.id.btn_sign_up)
        setUpActionBar()
        _btn_sign_up.setOnClickListener {
            registerUser()
        }

    }
    fun userRegisteredSuccess(){
        Toast.makeText(
            this,
            "you have successfully registered!",
            Toast.LENGTH_LONG
        ).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }
    private  fun setUpActionBar(){
        // Enabling Support for the Toolbar
        setSupportActionBar(_tbSignUp)
        val actionBar = supportActionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        _tbSignUp?.setNavigationOnClickListener { onBackPressed() }
    }
    private fun registerUser() {
        //trim is used to ignore white spaces
        val name: String = findViewById<EditText>(R.id.et_name).text.toString().trim { it <= ' ' }
        val email: String = findViewById<EditText>(R.id.et_email).text.toString().trim { it <= ' ' }
        val password: String =
            findViewById<EditText>(R.id.et_password).text.toString().trim { it <= ' ' }
        if (validateForm(name, email, password)) {
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    task ->
                // If the registration is successfully done
                if (task.isSuccessful) {
                    // Firebase registered user
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    // Registered Email
                    val registeredEmail = firebaseUser.email!!
                    val user= User(firebaseUser.uid,name,registeredEmail)
                    //need () with class in order to call its functions
                    FirestoreClass().registerUser(this,user)
                } else {
                    Toast.makeText(
                        this,
                        task.exception!!.message, Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }
    //To see if what data are entered or empty
    private fun validateForm(name: String, email: String, password:String): Boolean{
        return when {
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }
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