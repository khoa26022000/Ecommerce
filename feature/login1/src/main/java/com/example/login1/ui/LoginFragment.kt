package com.example.login1.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login1.R
import com.example.login1.databinding.FragmentLoginBinding
import com.example.login1.service.AuthenApi
import com.example.login1.service.AuthenModel
import com.example.login1.service.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import  retrofit2.Callback
import  retrofit2.Response

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signin.setOnClickListener {
            doLogin()
        }
        binding.res.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

    private fun doLogin() {
        val valueEmail = binding.inputemail.text.toString()
        val valuePassword = binding.inputPass.text.toString()
        val isUsernameInValid = valueEmail.isEmpty()
        val isPasswordInValid = valuePassword.isEmpty()
        if (isPasswordInValid || isUsernameInValid) {
            Toast.makeText(context, "UserName or PassWord no empty", Toast.LENGTH_SHORT).show()
            return
        }
        val body = mapOf(
            "userName" to valueEmail,
            "passWord" to valuePassword
        )
        val authenApi = RetrofitHelper.getInstance().create(AuthenApi::class.java)
        GlobalScope.launch {
            authenApi.loginAction(body).enqueue(object : Callback<AuthenModel> {
                override fun onResponse(
                    call: Call<AuthenModel>,
                    response: Response<AuthenModel>
                ) {
                    if (response.isSuccessful) {
                        val uri = "ecommerce-app://ecommerce/home/"
                        val NavOptions = NavOptions.Builder().setPopUpTo(R.id.nav_login,true).setLaunchSingleTop(true).build()
                        val deeplink = NavDeepLinkRequest.Builder.fromUri(uri.toUri()).build()
                        findNavController().navigate(deeplink, NavOptions)
                        Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(context, "Login failure", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<AuthenModel>, t: Throwable) {
                    Toast.makeText(context, "Login failure", Toast.LENGTH_LONG).show()
                    // handle the failure
                }
            })
        }
    }
}