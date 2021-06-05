package com.example.passthetime.ui.beforehome

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.passthetime.R


class GetStarted : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_started, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAnimation(view)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnGetStarted).setOnClickListener {
            navController!!.navigate(R.id.action_getStarted_to_loginFragment)
        }

    }

    private fun setAnimation(view: View) {
        val top = AnimationUtils.loadAnimation(view.context, R.anim.slide_left_right)
        view.findViewById<ImageView>(R.id.goldenWideImage).animation = top
        val left = AnimationUtils.loadAnimation(view.context, R.anim.slide_right_left)
        view.findViewById<Button>(R.id.btnGetStarted).animation = left

    }

}