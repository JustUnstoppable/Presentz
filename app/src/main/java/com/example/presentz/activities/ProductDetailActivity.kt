package com.example.presentz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.presentz.Fragments.CartFragment
import com.example.presentz.R
import com.example.presentz.RoomDB.AppDatabase
import com.example.presentz.RoomDB.ProductDAO
import com.example.presentz.RoomDB.ProductModel
import com.example.presentz.databinding.ActivityProductDetailBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProductDetails(intent.getStringExtra("id"))
    }

    private fun getProductDetails(proId: String?) {
        Firebase.firestore.collection("products")
            .document(proId!!).get().addOnSuccessListener {
                //data will be retrieved in form of list
                val list=it.get("productImages") as ArrayList<String>
                val name=it.getString("productName")
                val productMrp=it.getString("productMRP")
                val productDesc=it.getString("productDescription")
                 binding.titleDetail.text =name
                binding.priceDetail.text=productMrp
                binding.descriptionDetail.text=productDesc
                val slideList=ArrayList<SlideModel>()
                for(data in list){
                    slideList.add(SlideModel(data,ScaleTypes.CENTER_CROP))
                }
                cartAction(proId,name,productMrp,it.getString("productCoverImg"))


                binding.imageSlider.setImageList(slideList)

            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

    }

    private fun cartAction(proId: String, name: String?, productMrp: String?, coverImg: String?) {
        val productDao=AppDatabase.getInstance(this).productDao()
        if(productDao.isExist(proId)!=null){
           binding.button.text="Go to Cart"
        }else{
            binding.button.text="Add to Cart"

        }
        binding.button.setOnClickListener {
            if(productDao.isExist(proId)!=null){
                openCart()
            }else{
                addToCart(productDao,proId,name,productMrp,coverImg)
            }
        }
    }

    private fun openCart() {
       val prefrence =this.getSharedPreferences("info", MODE_PRIVATE)
        val editor=prefrence.edit()
        editor.putBoolean("isCart",true)
        editor.apply()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun addToCart(productDao: ProductDAO, proId: String, name: String?, productMrp: String?, coverImg: String?) {
         val data=ProductModel(proId,name,coverImg,productMrp)
         lifecycleScope.launch(Dispatchers.IO) {
              productDao.insertProduct(data)
             binding.button.text="Go to Cart"
         }
    }
}