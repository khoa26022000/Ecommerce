package com.example.login1.service

data class AuthenModel(
    val isSuccess: Boolean,
    val message: String,
    val token: String
)