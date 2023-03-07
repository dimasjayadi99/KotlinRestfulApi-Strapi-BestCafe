package com.example.bestcafe.Fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.bestcafe.Activity.LoginActivity
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.R

class ProfileFragment : Fragment() {

    lateinit var tv_username : TextView
    lateinit var tv_email : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_profile, container, false)

        tv_username = view.findViewById(R.id.tv_username)
        tv_email = view.findViewById(R.id.tv_email)

        val dataUser = SharedPrefManager.getInstance(view.context).user

        tv_username.text = dataUser.username
        tv_email.text = dataUser.email

        val cd_logout = view.findViewById<CardView>(R.id.cd_logout)
        cd_logout.setOnClickListener{
            //logout action
            logoutAccount(view)
        }

        return view
    }

    private fun logoutAccount(view: View?) {
        if (view != null) {
            val alertDialog = AlertDialog.Builder(view.context)
            alertDialog.setTitle("Logout Account")
            alertDialog.setMessage("Anda yakin ingin keluar dari aplikasi?")
            alertDialog.setPositiveButton("Ya") { dialogInterface: DialogInterface, _: Int ->
                SharedPrefManager.getInstance(view.context).clear()
                val intent = Intent(view.context,LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                Toast.makeText(view.context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
            alertDialog.setNegativeButton("Tidak") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            alertDialog.create().show()
        }
    }

}