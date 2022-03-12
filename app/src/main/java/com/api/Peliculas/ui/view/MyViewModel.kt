package com.api.Peliculas.ui.view

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.Peliculas.core.NetworkManager
import com.api.Peliculas.data.model.ProductObjectItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    private val users: MutableLiveData<List<ProductObjectItem>> by lazy {
        MutableLiveData<List<ProductObjectItem>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<ProductObjectItem>> {
        return users
    }

    private fun loadUsers() {
        NetworkManager.service.getProducts().enqueue(object : Callback<List<ProductObjectItem>> {

            override fun onFailure(call: Call<List<ProductObjectItem>>, t: Throwable) {

                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<List<ProductObjectItem>>,
                response: Response<List<ProductObjectItem>>
            ) {
                if (response.isSuccessful) {

                } else {
                }
            }

        })    }
}