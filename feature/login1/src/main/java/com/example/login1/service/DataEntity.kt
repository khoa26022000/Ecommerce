package com.example.login1.service

import com.google.gson.annotations.SerializedName

data class DataEntity(
    @SerializedName("name")
    val fullName: String
)
