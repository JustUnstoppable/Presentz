package com.example.presentz.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentz.RoomDB.AppDatabase
import com.example.presentz.RoomDB.ProductModel
import com.example.presentz.activities.ProductDetailActivity
import com.example.presentz.databinding.LayoutCartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FinalCartAdapter(var context: Context,val list: List<ProductModel>):RecyclerView.Adapter<FinalCartAdapter.FinalCartViewHolder>(){
    inner class FinalCartViewHolder(val binding: LayoutCartItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalCartViewHolder {
        val binding= LayoutCartItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return FinalCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FinalCartViewHolder, position: Int) {
        Glide.with(context).load(list[position].productImage).into(holder.binding.imageView3)
        holder.binding.textView4.text=list[position].productName
        holder.binding.textView5.text=list[position].productMrp
        holder.itemView.setOnClickListener {
            val intent= Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }
//        val db= Firebase.firestore.collection("users")
//        val userId= FirebaseAuth.getInstance().currentUser!!.uid
//       val key=db.document(userId).collection("cart").document().id
//        val data= ProductModel(
//            list[position].productId,
//            list[position].productName ,
//            list[position].productImage,
//            list[position].productMrp
//        )
//
//        db.document(userId).collection("cart").document(key).update("Product$position",data).addOnSuccessListener {
//            Log.i("cart","Successfully")
//        }.addOnFailureListener {
//            Log.i("cart","Failed")
//        }
        val dao= AppDatabase.getInstance(context).productDao()
        holder.binding.button2.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO){
                dao.deleteProduct(ProductModel(list[position].productId,list[position].productName,list[position].productImage,list[position].productMrp))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}