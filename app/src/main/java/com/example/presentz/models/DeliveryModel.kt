package com.example.presentz.models

import android.graphics.Bitmap
import java.util.*

data class DeliveryModel (
    val name:String,
    val ocassion:String,
    val date:String,
    val deliveryAddress:String,
    val city:String,
    val pincode:String,
    val card:Bitmap
    )