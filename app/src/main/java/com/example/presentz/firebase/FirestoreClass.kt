package com.example.presentz.firebase

import android.util.Log
import com.example.presentz.activities.SignInActivity
import com.example.presentz.activities.SignUpActivity
import com.example.presentz.models.User
import com.example.presentz.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private val mFireStore= FirebaseFirestore.getInstance()
    fun registerUser(activity: SignUpActivity, userInfo: User){
        //we have collections in collections we have document.
        mFireStore.collection(Constants.USERS)
            //create a new document for every single user
            .document(getCurrentUserId())
            // merges the userinfo
            .set(userInfo, SetOptions.merge())
            //if it works successfully then run following code
            .addOnSuccessListener {
                //sign up activity is passed as activity
                activity.userRegisteredSuccess()
            }.addOnFailureListener{
                    e->
                Log.e(activity.javaClass.simpleName,"Error")
            }
    }
    fun signInUser(activity: SignInActivity){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener {document->
                val loggedInUser=document.toObject(User::class.java)
                if(loggedInUser!=null)
                   activity.signInSuccess(loggedInUser)
            }.addOnFailureListener {
                e->
                Log.e(activity.javaClass.simpleName,"Error writing the email or password")
            }

    }
    fun getCurrentUserId(): String{
        var currentUser= FirebaseAuth.getInstance().currentUser
        var currentUserId=""
        if(currentUser!=null){
            currentUserId=currentUser.uid
        }
        return currentUserId
    }

}