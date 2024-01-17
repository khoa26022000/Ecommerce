package com.example.login1.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login1.R
import com.example.login1.databinding.FragmentLoginBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Callback
import okhttp3.Response

class LoginFragment : Fragment() {
//    private val client = OkHttpClient()
    private val client = OkHttpClient()
    lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun post(url: String, json: String): String? {
        try {
            val client = OkHttpClient()
//        val mediaType = "application/json".toMediaType()
//        val body = json.toRequestBody(mediaType)
            val request = Request.Builder()
                .url(url)
//            .post(body)
                .build()
            client.newCall(request).execute().use { response ->
                return response.body?.string() ?: ""
            }
        } catch (e: Exception) {
            Log.d("KHOA LOG Error","$e")
            return "null"

        } finally {
            Log.d("KHOA LOG Error","e")
        }

    }

    fun bowlingJson(player1: String, player2: String): String {
        return """
        {
            "winCondition": "HIGH_SCORE",
            "name": "Bowling",
            "round": 4,
            "lastSaved": 1367702411696,
            "dateStarted": 1367702378785,
            "players": [
                {
                    "name": "$player1",
                    "history": listOf(10, 8, 6, 7, 8),
                    "color": -13388315,
                    "total": 39
                },
                {
                    "name": "$player2",
                    "history": listOf(6, 10, 5, 10, 10),
                    "color": -48060,
                    "total": 41
                }
            ]
        }
    """.trimIndent()
    }

//    fun main(args: Array<String>) {
//        val example = PostExample()
//        val json = example.bowlingJson("Jesse", "Jake")
//        val response = example.post("http:")
//        println(response)
//    }


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
            val isUsernameInValid = binding.inputemail.toString()
            val isPasswordInValid = binding.inputPass.toString()
           val valueEmail = binding.inputemail.text.toString()
            val valuePassword = binding.inputPass.text.toString()
            Log.d("KHOA LOG",valueEmail)
            val response = post("https://api.github.com/users/Evin1-/repos","")
            Log.d("KHOA LOG response","$response")
        }
    }
    
    
}