package com.api.Peliculas.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.api.Peliculas.core.NetworkManager
import com.api.Peliculas.data.model.ProductObjectRequest
import com.api.Peliculas.databinding.ProductAddBinding
import kotlinx.android.synthetic.main.product_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAddFragment : Fragment() {
    private var _binding: ProductAddBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = ProductAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAgregar.setOnClickListener {
            postProduct()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    var ValorIntStock: Int = 0
    var ValorIntPrice: Float = 0.00F
    var ValorIntPriceD: Float = 0.00F
    var ValorBool: Boolean = true

    private fun postProduct() {

        getNumericValuePrice()
        getNumericValuePriceD()

        getNumericValueStock()

        getBooleanValueAv()


        NetworkManager.service.savePost(
            ProductObjectRequest(

                etName.text.toString(),
                etDesc.text.toString(),
                ValorIntStock,
                ValorIntPrice,
                ValorIntPriceD,
                ValorBool,
                etimage.text.toString()


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


