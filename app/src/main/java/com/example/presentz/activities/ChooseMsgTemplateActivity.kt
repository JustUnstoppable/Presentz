package com.example.presentz.activities

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentz.adapters.chooseTemplateAdapter
import com.example.presentz.databinding.ActivityChooseMsgTemplateBinding


class ChooseMsgTemplateActivity : AppCompatActivity() {
    private lateinit var templates: ArrayList<String>
    private lateinit var templateAdapter: chooseTemplateAdapter
    private lateinit var binding:ActivityChooseMsgTemplateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChooseMsgTemplateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProduct()

    }
    private fun initProduct(){
        templates = ArrayList()
        templates.add("https://wallpapercave.com/wp/wp3838081.jpg")
        templates.add("https://i.pinimg.com/originals/a0/03/02/a003024ed85f3490ec6244c43e22d572.jpg")
        templates.add("https://th.bing.com/th/id/R.f22a514ff28d2496eefd3127f192acda?rik=bdIkrExyXikgwQ&riu=http%3a%2f%2fwallpapercave.com%2fwp%2f0g6DlbQ.gif&ehk=rwqsOCYLavu%2fT0wXdxMz%2bYEVtMEJckJeiGiKTQAizgs%3d&risl=&pid=ImgRaw&r=0")
        val arr=intent.getStringArrayListExtra("arr")
        templateAdapter = chooseTemplateAdapter(this, templates, arr!!)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.templateRV.layoutManager = layoutManager
        binding.templateRV.adapter = templateAdapter
    }
}