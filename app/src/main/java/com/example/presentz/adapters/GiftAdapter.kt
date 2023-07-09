package com.example.presentz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentz.RoomDB.ProductModel
import com.example.presentz.databinding.GiftListBinding

class GiftAdapter(var context:Context,val list:List<ProductModel>):RecyclerView.Adapter<GiftAdapter.GiftViewHolder>() {
    inner class GiftViewHolder(val binding:GiftListBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
        val binding=LayoutProd
    }

    override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

}