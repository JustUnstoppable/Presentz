package com.example.presentz.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentz.activities.editTemplateActivity
import com.example.presentz.databinding.SingleTemplateBinding
import com.google.protobuf.LazyStringArrayList


class chooseTemplateAdapter(var context: Context, val list:ArrayList<String>
                            , private val arr:ArrayList<String>):RecyclerView.Adapter<chooseTemplateAdapter.TemplateViewHolder>() {
    inner class TemplateViewHolder(val binding:SingleTemplateBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
       val binding=SingleTemplateBinding.inflate(LayoutInflater.from(context),parent,false)
        return TemplateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        Glide.with(context).load(list[position]).into(holder.binding.imageView2)
        holder.binding.editButton.setOnClickListener {
          val i= Intent(context,editTemplateActivity::class.java)
            i.putExtra("image",list[position])
            i.putStringArrayListExtra("arr",arr)
           context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}