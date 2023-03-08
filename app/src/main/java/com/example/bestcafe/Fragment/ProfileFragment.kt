package com.example.bestcafe.Fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.bestcafe.Activity.EditProfileActivity
import com.example.bestcafe.Activity.LoginActivity
import com.example.bestcafe.Api.User.ApiConfigUser
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.Api.User.UserModel
import com.example.bestcafe.R
import com.example.bestcafe.Response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    lateinit var tv_username : TextView
    lateinit var tv_email : TextView
    lateinit var tv_edit : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_profile, container, false)

        val cd_logout = view.findViewById<CardView>(R.id.cd_logout)
        cd_logout.setOnClickListener{
            //logout action
            logoutAccount(view)
        }

        return view
    }

    private fun getDataUser(id: Int) {
        ApiConfigUser.getService().getDataUser(id).enqueue(object : Callback<List<UserModel>>{
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val responseBody = response.body()
                        val email = responseBody?.get(0)?.email
                        val username = responseBody?.get(0)?.username

                        tv_username.text = username
                        tv_email.text = email
                    }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {

            }

        })
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

    override fun onResume() {
        super.onResume()
        tv_username = requireView().findViewById(R.id.tv_username)
        tv_email = requireView().findViewById(R.id.tv_email)
        tv_edit = requireView().findViewById(R.id.tv_edit)
        tv_edit.setOnClickListener{
            val intent = Intent(it.context,EditProfileActivity::class.java)
            startActivity(intent)
        }

        val dataUser = SharedPrefManager.getInstance(requireView().context).user

        val id = dataUser.id

        getDataUser(id)
    }

}