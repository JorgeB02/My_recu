package com.api.Peliculas.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.api.Peliculas.data.model.ProductObjectItem
import com.api.Peliculas.core.NetworkManager
import com.api.Peliculas.databinding.ProductListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

// TODO: Rename parameter arguments, choose names that match

class ProductListFragment : Fragment() {
    private lateinit var tempArrayList: ArrayList<ProductObjectItem>


    private var _binding: ProductListBinding? = null
    private val binding
        get() = _binding!!


    private val adapter = ProductAdapter {
        val parametroBusqueda = MutableLiveData<String>()


        val generador =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                it.stock.toString(),
                it.description,
                it.id,
                it.name,
                it.price.toFloat(),
                it.discountPrice.toFloat(),
                it.available,
                it.imageURL


            )
        findNavController().navigate(generador)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ProductListBinding.inflate(inflater, container, false)
        return binding.root

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        binding.rvProduct.adapter = adapter
        binding.btnAdd.setOnClickListener {
            val generador =
                ProductListFragmentDirections.actionProductListFragmentToProductAddFragment(
                )
            findNavController().navigate(generador)
        }
        requestData()

        binding.btnBuscar.setOnClickListener {

            val filteredList: MutableList<ProductObjectItem> = mutableListOf()
            val filtro = binding.Search.text.toString()
            for(i in adapter.currentList){
                if (i.name.lowercase().contains(filtro.lowercase())){
                    filteredList.add(i)
                    adapter.submitList(filteredList)
                    adapter.notifyDataSetChanged()
                }
            }
            binding.progressBar.visibility = View.INVISIBLE

        }
        binding.ReloadData.setOnClickListener {
            requestData()
        }

    }

    private fun requestData() {
        binding.progressBar.visibility = View.VISIBLE

        NetworkManager.service.getProducts().enqueue(object : Callback<List<ProductObjectItem>> {

            override fun onFailure(call: Call<List<ProductObjectItem>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esperábamos",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<List<ProductObjectItem>>,
                response: Response<List<ProductObjectItem>>
            ) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                    Log.e("Retrofit", "Salió bien")
                } else {
                    Toast.makeText(context, "400", Toast.LENGTH_SHORT).show()
                }
            }

        })
        binding.progressBar.visibility = View.INVISIBLE

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

private fun NavController.navigate(generador: Unit) {

}

private fun Any.actionProductListFragmentToProductDetailFragment(
    stock: String,
    desc: String,
    myId: String,
    name: String,
    price: Float,
    dicountPrice: Float,
    available: Boolean,
    imageURL: String
) {

}
