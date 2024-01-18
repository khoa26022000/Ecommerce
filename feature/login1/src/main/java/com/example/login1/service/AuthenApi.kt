package com.example.login1.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenApi {
    @POST("user/sign-in")
    @JvmSuppressWildcards
    fun loginAction (@Body body: Map<String, Any>) : Call<AuthenModel>

    @POST("user/sign-up")
    @JvmSuppressWildcards
    fun registerAction (@Body body: Map<String, Any>) : Call<AuthenModel>
}