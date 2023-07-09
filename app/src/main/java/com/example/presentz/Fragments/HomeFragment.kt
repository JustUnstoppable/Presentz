package com.example.presentz.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentz.R
import com.example.presentz.adapters.CategoryAdapter
import com.example.presentz.adapters.ProductAdapter
import com.example.presentz.databinding.FragmentHomeBinding
import com.example.presentz.databinding.ItemProductBinding
import com.example.presentz.models.AddProductModel
import com.example.presentz.models.CategoryModel
import com.example.presentz.models.ProductDetails
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var products: ArrayList<ProductDetails>
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        //binding=FragmentHomeBinding.inflate(layoutInflater)
//        val view=inflater.inflate(R.layout.fragment_home, container, false)
//        val recyclerView:RecyclerView=view.findViewById(R.id.productList)
//        recyclerView.setHasFixedSize(true)
        binding.productList.layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        val prefrence =requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        if(prefrence.getBoolean("isCart",false)){
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        getCategories()

        getProducts()
        addToCart()
        return binding.root
    }

    private fun addToCart() {
        val binding=ItemProductBinding.inflate(layoutInflater)
        binding.addToCartButton.setOnClickListener {

        }
    }

    private fun getProducts() {
        val list= ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data=doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.productList.adapter= ProductAdapter(requireContext(),list)
            }
    }

    private fun getCategories() {
        val list= ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data=doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecycler.adapter= CategoryAdapter(requireContext(),list)
            }
    }
//    private fun initProduct(){
//        products = ArrayList()
//        products.add(ProductDetails("Korean Loose short combo outwear","https://th.bing.com/th/id/OIP.OoPjZTmJKxUUNa1BvdcfVgHaHa?pid=ImgDet&rs=1","sale",20.0,1.0,2,1))
//        products.add(ProductDetails("Korean Loose short combo outwear","https://th.bing.com/th/id/OIP.OoPjZTmJKxUUNa1BvdcfVgHaHa?pid=ImgDet&rs=1","sale",20.0,1.0,2,1))
//        productAdapter = ProductAdapter(this, products)
//        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(activity)
//        binding.productList.layoutManager = layoutManager
//        binding.productList.adapter = productAdapter
//    }
}