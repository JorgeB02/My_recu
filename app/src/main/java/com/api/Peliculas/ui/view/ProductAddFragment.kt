package com.api.Peliculas.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.BuildConfig
import com.api.Peliculas.core.NetworkManager
import com.api.Peliculas.data.model.ProductObjectItem
import com.api.Peliculas.data.model.ProductObjectRequest
import com.api.Peliculas.databinding.ProductAddBinding
import kotlinx.android.synthetic.main.product_add.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAddFragment : Fragment() {
    private var _binding: ProductAddBinding? = null
    private val binding
        get() = _binding!!


    val isLoading: Boolean = false
    val loginSuccess: Boolean = false
    val isError: Boolean = false
    val errorMessage: String? = null
    val repositories: List<ProductObjectItem>? = null

    var ValorIntStock: Int = 0
    var ValorIntPrice: Float = 0.00F
    var ValorIntPriceD: Float = 0.00F
    var ValorBool: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {

        }
        binding.btnAgregar.setOnClickListener {

            getNumericValuePrice()
            getNumericValuePriceD()
            getNumericValueStock()
            getBooleanValueAv()

            val name = etName.text.toString();
            val desc = etDesc.text.toString();
            val stock = ValorIntStock;
            val price = ValorIntPrice;
            val priceD = ValorIntPriceD;
            val avai = ValorBool;
            val ima = etimage.text.toString()



            postProduct(name, desc, stock, price, priceD, avai, ima)

            val action =
                ProductAddFragmentDirections.actionProductAddFragmentToProductListFragment()
            findNavController().navigate(action)
        }

        binding.btnBack.setOnClickListener {
            val action =
                ProductAddFragmentDirections.actionProductAddFragmentToProductListFragment()
            findNavController().navigate(action)
        }
    }

    fun postProduct(
        name: String,
        desc: String,
        stock: Int,
        price: Float,
        priceD: Float,
        avai: Boolean,
        ima: String
    ) {
        NetworkManager.service.savePost(
            ProductObjectRequest(

                name,
                desc,
                stock,
                price,
                priceD,
                avai,
                ima,


                )
        ).enqueue(object :
            Callback<ProductObjectRequest> {
            override fun onResponse(
                call: Call<ProductObjectRequest>,
                response: Response<ProductObjectRequest>
            ) {
                if (response.isSuccessful) {
                    //getMs
                    Toast.makeText(context, "Pasa por el post", Toast.LENGTH_SHORT).show()
                    Log.e("Network", "post hecho con éxito")
                } else {
                    Log.e("Network", "error en la conexion on Response")
                }
            }

            override fun onFailure(call: Call<ProductObjectRequest>, t: Throwable) {
                Log.e("Network", "error en la conexion", t)
                Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToHome() {
        showError("Navigate To Home")
    }

    private fun showLoading() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    private fun showError(message: String?) {
        Toast.makeText(requireContext(), message ?: "Unkown error", Toast.LENGTH_SHORT).show()
    }

    private fun hideLoading() {
        binding.progressBar2.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderUiState(state: LoginUiState) {
        if (state.isLoading) {
            showLoading()
        } else {
            hideLoading()
        }

        if (state.isError) {
            showError(state.errorMessage)
        }

        if (state.loginSuccess) {
            navigateToHome()
        }

        if (state.repositories != null) {
            Log.d("UDF", "repo: ${state.repositories}")
            showError("Repositories")
        }
    }


    private fun getBooleanValueAv() {
        val sacarBool: String = etavailable.text.toString()
        ValorBool = sacarBool.toBoolean()
    }

    private fun getNumericValueStock() {
        val sacarIntStock: String = etStock.text.toString()
        ValorIntStock = sacarIntStock.toInt()
    }

    fun getNumericValuePrice() {
        val sacarIntPrice: String = etPrice.getText().toString()
        ValorIntPrice = sacarIntPrice.toFloat()
    }

    fun getNumericValuePriceD() {
        val sacarIntPriceD: String = etDescuento.getText().toString()
        ValorIntPriceD = sacarIntPriceD.toFloat()


    }
}
