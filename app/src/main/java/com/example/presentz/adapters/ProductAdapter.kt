package com.example.presentz.adapters


import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.presentz.Fragments.CartFragment
import com.example.presentz.Fragments.HomeFragment
import com.example.presentz.R
import com.example.presentz.activities.CategoryActivity
import com.example.presentz.activities.MainActivity
import com.example.presentz.activities.ProductDetailActivity
import com.example.presentz.databinding.ItemProductBinding
import com.example.presentz.models.AddProductModel
import com.example.presentz.models.ProductDetails


class ProductAdapter(val context: Context,val list: ArrayList<AddProductModel>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    //private var products: ArrayList<ProductDetails>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
//        val layoutView:View=LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
//        return ProductViewHolder(layoutView)
        val binding=ItemProductBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product: ProductDetails = products[position]
//        Glide.with(context)
//            .load(product.image)
//            .into(holder.binding.productImage)
//        holder.binding.label.text = product.name
//        holder.binding.price.text ="PKR " + product.price
//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, ProductDetails::class.java)
//            intent.putExtra("name", product.getName())
//            intent.putExtra("image", product.getImage())
//            intent.putExtra("id", product.getId())
//            intent.putExtra("price", product.getPrice())
//            context.startActivity(intent)
        //}
        val data=list[position]
        Glide.with(context).load(data.productCoverImg).into(holder.binding.productImage)
        holder.binding.label.text=data.productName
        holder.binding.category.text=data.productCategory
        holder.binding.price.text=data.productMrp
//        holder.binding.addToCartButton.setOnClickListener {
//            val intent=Intent(context, CartFragment::class.java)
//            intent.putExtra("data",list[position].productId)
//            context.startActivity(intent)
//        }
        holder.itemView.setOnClickListener {
            val intent=Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

  class ProductViewHolder(val binding:ItemProductBinding):RecyclerView.ViewHolder(binding.root)
////        var binding: ItemProductBinding
////
////        init {
////            binding = ItemProductBinding.bind(itemView)
////        }
//        var productImage:ImageView=itemView.findViewById(R.id.productImage)
//        var productTitle:TextView=itemView.findViewById(R.id.label)
//        var productPrice:TextView=itemView.findViewById(R.id.price)
//    }

//    init {
//        this.products = products
//    }
}