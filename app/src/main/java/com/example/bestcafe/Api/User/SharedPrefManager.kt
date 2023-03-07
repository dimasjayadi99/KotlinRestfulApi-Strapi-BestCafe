package com.example.bestcafe.Api.User

import android.annotation.SuppressLint
import android.content.Context
import com.example.bestcafe.Response.ResponseUser
import com.example.bestcafe.Response.User

class SharedPrefManager private constructor(private val mCtx : Context){

    val isLoggedIn : Boolean
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt("id", -1) != -1
    }

    val user : UserModel
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return UserModel(
            sharedPreferences.getInt("id", -1),
            sharedPreferences.getString("email",null),
            sharedPreferences.getString("username",null),
            sharedPreferences.getString("telp",null),
            sharedPreferences.getString("alamat",null),
            sharedPreferences.getString("jwt",null)
        )
    }

    fun saveUser(user: ResponseUser?) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user?.user?.id!!)
        editor.putString("email", user?.user.email!!)
        editor.putString("username", user?.user.username!!)
        editor.putString("telp", user.user.telp)
        editor.putString("alamat", user.user.alamat)
        editor.putString("jwt", user?.jwt)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }


}