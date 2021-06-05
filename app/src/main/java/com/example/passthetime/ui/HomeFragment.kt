package com.example.passthetime.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.example.passthetime.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val l11: LottieAnimationView = view.findViewById(R.id.animation_view11)
        val l12: LottieAnimationView = view.findViewById(R.id.animation_view12)
        val l13: LottieAnimationView = view.findViewById(R.id.animation_view13)

        l11.setOnClickListener {
            l11.repeatCount = 3
            l11.setAnimation(R.raw.xbox)
            CoroutineScope(Dispatchers.IO).launch {
                delay(2900)
                CoroutineScope(Dispatchers.Main).launch {
                    l11.pauseAnimation()

                }
            }
        }

        l12.setOnClickListener {
            l12.repeatCount = 3
            l12.setAnimation(R.raw.obox)
            CoroutineScope(Dispatchers.IO).launch {
                delay(2900)
                CoroutineScope(Dispatchers.Main).launch {
                    l12.pauseAnimation()

                }
            }
        }

        l13.setOnClickListener {
            l13.repeatCount = 3
            l13.setAnimation(R.raw.xbox)
            CoroutineScope(Dispatchers.IO).launch {
                delay(2900)
                CoroutineScope(Dispatchers.Main).launch {
                    l13.pauseAnimation()

                }
            }
        }

    }

}