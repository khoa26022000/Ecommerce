package com.example.login1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.login1.R
import com.example.login1.databinding.FragmentRegisterBinding
import com.example.login1.service.AuthenApi
import com.example.login1.service.AuthenModel
import com.example.login1.service.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.register.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        val valueName = binding.inputfullname.text.toString()
        val valueEmail = binding.inputemail.text.toString()
        val valuePassword = binding.inputPass.text.toString()
        val valuePasswordConfirm = binding.inputPassConfirm.text.toString()
        val isUsernameInValid = valueName.isEmpty()
        val isEmailInValid = valueEmail.isEmpty()
        val isPasswordInValid = valuePassword.isEmpty()
        val isPasswordConfirm = valuePasswordConfirm.isEmpty()

        if (isPasswordInValid || isUsernameInValid || isEmailInValid || isPasswordConfirm) {
            Toast.makeText(context, "UserName or PassWord no empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (valuePassword == valuePasswordConfirm) {
            Toast.makeText(context, "PassWord is not same", Toast.LENGTH_SHORT).show()
            return
        }

        val body = mapOf(
            "userName" to valueEmail,
            "passWord" to valuePassword
        )
        val authenApi = RetrofitHelper.getInstance().create(AuthenApi::class.java)
        GlobalScope.launch {
            authenApi.registerAction(body).enqueue(object : Callback<AuthenModel> {
                override fun onResponse(
                    call: Call<AuthenModel>,
                    response: Response<AuthenModel>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Register success", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(context, "Register failure", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<AuthenModel>, t: Throwable) {
                    Toast.makeText(context, "Register failure", Toast.LENGTH_LONG).show()
                    // handle the failure
                }
            })
        }
    }


}