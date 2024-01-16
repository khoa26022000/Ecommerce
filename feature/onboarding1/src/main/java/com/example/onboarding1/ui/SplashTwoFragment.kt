package com.example.onboarding1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onboarding1.R
import com.example.onboarding1.databinding.FragmentSplashTwoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SplashTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashTwoFragment : Fragment() {
    lateinit var binding: FragmentSplashTwoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashTwoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnnext.setOnClickListener{
            findNavController().navigate(R.id.splashThreeFragment)
        }
    }
}