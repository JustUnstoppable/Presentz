package com.example.presentz.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentz.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FinalCheckout : AppCompatActivity() {
    var counter:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_checkout)


        val userMap= mapOf<String,String>("hi" to "hello")

//
        val editor = getPreferences(MODE_PRIVATE).edit()
        var defaultVal = getPreferences(MODE_PRIVATE).getInt("count_key", counter)
        Toast.makeText(this, "$defaultVal", Toast.LENGTH_SHORT).show()
        defaultVal += 1
        getPreferences(MODE_PRIVATE).edit().putInt("count_key", defaultVal).apply()
        counter = getPreferences(MODE_PRIVATE).getInt("count_key", counter)
        Toast.makeText(this, " count $counter", Toast.LENGTH_SHORT).show()
       val db = Firebase.firestore.collection("users")
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val key = db.document(userId).collection("cart").document().id
        val arr = intent.getStringArrayListExtra("products")
        val d = intent.getSerializableExtra("details")
//        db.document(userId).collection("order").document(key).set(arr!!)
//            .addOnSuccessListener { Toast.makeText(this, "done", Toast.LENGTH_SHORT).show() }
//            .addOnFailureListener {  Toast.makeText(this, " not done", Toast.LENGTH_SHORT).show()}
        //Toast.makeText(this, intent.getStringArrayListExtra("products").toString(), Toast.LENGTH_SHORT).show()
        db.document(userId).collection("order").document("$defaultVal").set(userMap)

        db.document(userId).collection("order").document("$defaultVal")
            .update("details", d, "productList", arr).addOnSuccessListener {
                Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
            Log.i("cart", it.localizedMessage!!.toString())
        }
    }

}