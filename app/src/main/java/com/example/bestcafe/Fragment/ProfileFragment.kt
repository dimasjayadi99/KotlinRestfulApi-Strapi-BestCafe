package com.example.bestcafe.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_profile, container, false)

        return view
    }

}