package com.example.bestcafe.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.bestcafe.Activity.WelcomeActivity
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.R

class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_booking,container,false)

        return view
    }

}