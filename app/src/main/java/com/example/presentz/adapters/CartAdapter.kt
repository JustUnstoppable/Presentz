package com.example.presentz.adapters

import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentz.RoomDB.AppDatabase
import com.example.presentz.RoomDB.ProductModel
import com.example.presentz.activities.ProductDetailActivity
import com.example.presentz.databinding.LayoutCartItemBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartAdapter (var context: Context,val list: List<ProductModel>):RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    inner class CartViewHolder(val binding:LayoutCartItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding=LayoutCartItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Glide.with(context).load(list[position].productImage).into(holder.binding.imageView3)
        holder.binding.textView4.text=list[position].productName
        holder.binding.textView5.text=list[position].productMrp
        holder.itemView.setOnClickListener {
            val intent= Intent(context,ProductDetailActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }

        val dao=AppDatabase.getInstance(context).productDao()
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
