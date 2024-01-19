package com.example.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.service.ApiFood
import com.example.home.service.FoodModel
import com.example.home.service.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun re() {
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataFood()
        re()
    }

    private fun loadDataFood() {

        val foodApi = RetrofitHelper.getInstance().create(ApiFood::class.java)
        GlobalScope.launch {
            foodApi.getDataFood().enqueue(object : Callback<FoodModel> {
                override fun onResponse(
                    call: Call<FoodModel>,
                    response: Response<FoodModel>
                ) {
                    if (response.isSuccessful) {
//                        val uri = "ecommerce-app://ecommerce/home/"
//                        val deeplink = NavDeepLinkRequest.Builder.fromUri(uri.toUri()).build()
//                        findNavController().navigate(deeplink)
                        val data = response.body()
                        Log.d("KHOA LOG response", "$data")
                        Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(context, "Login failure", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<FoodModel>, t: Throwable) {
                    Toast.makeText(context, "Login failure", Toast.LENGTH_LONG).show()
                    // handle the failure
                }
            })
        }
    }

}