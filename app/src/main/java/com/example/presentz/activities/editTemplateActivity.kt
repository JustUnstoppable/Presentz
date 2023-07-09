package com.example.presentz.activities

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.example.presentz.databinding.ActivityEditTemplateBinding


class editTemplateActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEditTemplateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEditTemplateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arr=intent.getStringArrayListExtra("arr")
        if(intent.getStringExtra("image")!=null){
            Glide.with(this).load(intent.getStringExtra("image")).into(binding.chosenImg)
            //binding.chosenImg.setImageBitmap(StringToBitMap(intent.getStringExtra("image")))
        }
        binding.saveBtn.setOnClickListener {

//            val intent=Intent(this,SurpriseDetailsActivity::class.java)
//            intent.putExtra("template",getScreenShotFromView(binding.template))
//            startActivity(intent)
            val bitImg= getScreenShotFromView(binding.cardView)
            Glide.with(this).load(bitImg).into(binding.imageTemplate)
            SurpriseDetailsActivity.myPic= bitImg
            val intent=Intent(this,SurpriseDetailsActivity::class.java)
            intent.putExtra("template","yes")
            intent.putStringArrayListExtra("product",arr)
            startActivity(intent)
        }
    }
    private fun getScreenShotFromView(v: View): Bitmap {
        // create a bitmap object
        var screenshot: Bitmap?=null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("Screenshot", "Failed to capture screenshot because:" + e.message)
        }
        // return the bitmap
        return screenshot!!
    }
    companion object {
        lateinit var chosenTemp: Bitmap
    }
    fun StringToBitMap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            Log.e("Bitmap", "Failed to capture screenshot because:" + e.message)
            null
        }
    }
}