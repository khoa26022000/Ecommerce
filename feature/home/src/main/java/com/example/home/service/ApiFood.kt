package com.example.home.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

internal interface ApiFood {
    @GET("/film")
    fun getDataFood() : Call<FoodModel>
}