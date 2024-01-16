package com.example.onboarding1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.onboarding1.R
import com.example.onboarding1.databinding.FragmentSplashThreeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SplashThreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashThreeFragment : Fragment() {
    lateinit var binding: FragmentSplashThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashThreeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnnext.setOnClickListener{
            val uri = "ecommerce-app://ecommerce/login/"
            val deeplink = NavDeepLinkRequest.Builder.fromUri(uri.toUri()).build()
            findNavController().navigate(deeplink)
//            findNavController().navigate(com.example.login1.R.id.nav_login)

        }
    }

}