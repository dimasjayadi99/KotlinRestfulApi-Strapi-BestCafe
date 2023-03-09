package com.example.bestcafe.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcafe.Adapter.AdapterPromo
import com.example.bestcafe.Adapter.AdapterRecommended
import com.example.bestcafe.Api.Food.ApiConfigFood
import com.example.bestcafe.Api.User.ApiConfigUser
import com.example.bestcafe.Api.User.SharedPrefManager
import com.example.bestcafe.Api.User.UserModel
import com.example.bestcafe.R
import com.example.bestcafe.Response.ResponseFoods
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var rv_promo : RecyclerView
    lateinit var rv_recommended : RecyclerView
    lateinit var tv_username : TextView

    val sharedPrefManager = SharedPrefManager

    var id_user : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_home,container,false)

        tv_username = view.findViewById(R.id.tv_username)
        rv_promo = view.findViewById(R.id.rv_promo)
        rv_recommended = view.findViewById(R.id.rv_recommended)

        id_user = sharedPrefManager.getInstance(view.context).user.id

        getUsername(view)
        getPromoList(view)
        getRecommendedList(view)

        return view
    }

    private fun getUsername(view: View?) {
        ApiConfigUser.getService().getDataUser(id_user).enqueue(object : Callback<List<UserModel>>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val responseBody = response.body()
                        val username = responseBody?.get(0)?.username
                        tv_username.text = "Hi, $username"
                    }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Toast.makeText(view?.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getRecommendedList(view: View?) {
        ApiConfigFood.getService().getRecommended().enqueue(object : Callback<ResponseFoods>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseFoods>, response: Response<ResponseFoods>) {
                if (response.body() != null){
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        val responseList = responseBody?.data
                        val adapterList = AdapterRecommended(responseList)
                        rv_recommended.apply {
                            layoutManager = GridLayoutManager(view?.context,2)
                            setHasFixedSize(true)
                            adapterList.notifyDataSetChanged()
                            adapter = adapterList
                        }
                    }else{
                        Toast.makeText(view?.context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(view?.context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseFoods>, t: Throwable) {
                Toast.makeText(view?.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getPromoList(view: View?) {
        ApiConfigFood.getService().getPromo().enqueue(object : Callback<ResponseFoods>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseFoods>, response: Response<ResponseFoods>) {
                if (response.body() != null){
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        val responseList = responseBody?.data
                        val adapterList = AdapterPromo(responseList)
                        rv_promo.apply {
                            layoutManager = LinearLayoutManager(view?.context,LinearLayoutManager.HORIZONTAL,false)
                            setHasFixedSize(true)
                            adapterList.notifyDataSetChanged()
                            adapter = adapterList
                        }
                    }else{
                        Toast.makeText(view?.context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(view?.context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseFoods>, t: Throwable) {
                Toast.makeText(view?.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

}