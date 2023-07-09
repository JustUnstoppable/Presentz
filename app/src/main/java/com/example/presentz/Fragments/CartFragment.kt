package com.example.presentz.Fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentz.R
import com.example.presentz.RoomDB.AppDatabase
import com.example.presentz.activities.SurpriseDetailsActivity
import com.example.presentz.adapters.CartAdapter
import com.example.presentz.databinding.FragmentCartBinding
import com.example.presentz.databinding.FragmentHomeBinding

class CartFragment : Fragment() {
   lateinit var binding:FragmentCartBinding
   lateinit var productList: ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCartBinding.inflate(layoutInflater)
        //binding=FragmentHomeBinding.inflate(layoutInflater)
//        val view=inflater.inflate(R.layout.fragment_home, container, false)
//        val recyclerView:RecyclerView=view.findViewById(R.id.productList)
//        recyclerView.setHasFixedSize(true)
//        binding.cartRecyclerView.layoutManager=
//            GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
        val prefrence =requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor=prefrence.edit()
        editor.putBoolean("isCart",false)
        editor.apply()
        productList= ArrayList()
        //get data from database
        val dao=AppDatabase.getInstance(requireContext()).productDao()
        dao.getAllProducts().observe(requireActivity()) {
            binding.cartRecyclerView.adapter = CartAdapter(requireContext(), it)
            productList.clear()
            for (item in it) {
                productList.add(item.productId)
               // Toast.makeText(requireContext(), "Added ${item.productId}", Toast.LENGTH_SHORT).show()

            }
            binding.orderButton.setOnClickListener {
                val b=Bundle()
                b.putStringArrayList("product",productList)
                val intent=Intent(getActivity(),SurpriseDetailsActivity::class.java)
                //Toast.makeText(requireContext(), "First "+productList.toString(), Toast.LENGTH_SHORT).show()
                intent.putExtras(b)
                startActivity(intent)
            }

        }
        return binding.root
    }

    private fun getItem(stringExtra: Any) {

    }


}