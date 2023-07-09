package com.example.presentz.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.example.presentz.databinding.ActivitySurpriseDetailsBinding
import java.io.Serializable


class SurpriseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurpriseDetailsBinding
    lateinit var arr:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(com.example.presentz.R.layout.activity_surprise_details)
        binding= ActivitySurpriseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent.getStringArrayListExtra("product")!=null){
            arr= intent.getStringArrayListExtra("product")!!
        }



        //val arr= intent.getStringArrayListExtra("product")
       // Toast.makeText(this, "do ${arr.toString()}", Toast.LENGTH_SHORT).show()
        binding.imageView.setOnClickListener {
            val intent=Intent(this,ChooseMsgTemplateActivity::class.java)
            intent.putStringArrayListExtra("arr",arr)
            startActivity(intent)
        }
        if(intent.getStringExtra("template")!=null){

            binding.imageView.setImageBitmap(myPic)
        }
//        val dao= AppDatabase.getInstance(this).productDao()
//        dao.getAllProducts().observe(this){
//            binding.productsAddedRecycler.adapter= FinalCartAdapter(this,it)
//        }
        binding.proceedBtn.setOnClickListener {
            val name=binding.whom.text.toString()
            val ocassion=binding.ocassion.text.toString()
            val date=binding.Date.text.toString()
            val deliveryAddress=binding.deliveryAddress.text.toString()
            val city=binding.City.text.toString()
            val pincode=binding.Pincode.text.toString()
            val card= binding.imageView.toString()
            val map=storeDetails(name,ocassion,date,deliveryAddress,city,pincode,card)
            val b=Bundle()
            b.putStringArrayList("products",arr)
            b.putSerializable("details",map as Serializable)
            Toast.makeText(this, "Surprise ${arr.toString()}", Toast.LENGTH_SHORT).show()
            val i=Intent(this,FinalCheckout::class.java)
            i.putExtras(b)

            //i.putStringArrayListExtra("productList",intent.getStringArrayExtra("productList"))
            startActivity(i)
        }
    }

    private fun storeDetails(name: String, ocassion: String, date: String, deliveryAddress: String, city: String, pincode: String, img: String):HashMap<String,String>{
        val map= hashMapOf<String,String>()
        map["whom"]=name
        map["ocassion"]=ocassion
        map["date"]=date
        map["deliveryAddress"]=deliveryAddress
        map["city"]=city
        map["pincode"]=pincode
        map["card"]=img
        return  map
//        val db= Firebase.firestore.collection("users")
//        val userId= FirebaseAuth.getInstance().currentUser!!.uid
//        db.document(userId).collection("order").document().set(map).addOnSuccessListener {
//            Log.i("map","Successfully")
//        }.addOnFailureListener {
//            Log.i("map","Not  Successfull")
//        }
    }

    //    private fun getProductDetails(proId: String?) {
//        Firebase.firestore.collection("products")
//            .document(proId!!).get().addOnSuccessListener {
//                //data will be retrieved in form of list
//                val list=it.get("productImages") as ArrayList<String>
//                val name=it.getString("productName")
//                val productMrp=it.getString("productMRP")
//                val productDesc=it.getString("productDescription")
//                binding.titleDetail.text =name
//                binding.priceDetail.text=productMrp
//                binding.descriptionDetail.text=productDesc
//                val slideList=ArrayList<SlideModel>()
//                for(data in list){
//                    slideList.add(SlideModel(data, ScaleTypes.CENTER_CROP))
//                }
//                cartAction(proId,name,productMrp,it.getString("productCoverImg"))
//
//
//                binding.imageSlider.setImageList(slideList)
//
//            }.addOnFailureListener {
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//
//    }
    companion object {
        lateinit var myPic: Bitmap
    }
}