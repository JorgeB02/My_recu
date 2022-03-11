package com.api.Peliculas.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.api.Peliculas.R
import com.api.Peliculas.data.model.ProductObjectItem
import com.api.Peliculas.core.NetworkManager
import com.api.Peliculas.data.database.ProductModel
import com.api.Peliculas.databinding.ProductDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class ProductDetailFragment : Fragment() {
    private var _binding: ProductDetailBinding? = null
    private val binding
        get() = _binding!!

    private val args: ProductDetailFragmentArgs by navArgs()

    private var stock: Int = 0
    private var desc: String? = null
    private var myId: String = ("fb36491d-7c21-40ef-9f67-a63237b5bbea");
    private var name: String = "Nombre"
    private var price: Float = 0.00F
    private var discountprice: Float = 0.00F
    private var available: Boolean = true
    private var imageURL: String = "nose"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
        arguments?.let {

            name = it.getString(name).toString()
            desc = it.getString(desc).toString()
            stock = it.getInt(stock.toString())
            price = it.getFloat(price.toString())
            discountprice = it.getFloat(discountprice.toString())
            desc = it.getString(desc).toString()
            available = it.getBoolean(available.toString())
            imageURL = it.getString(imageURL).toString()


        }
        binding.tvName.text = args.name
        binding.tvDesc.text = args.desc
        binding.tvStock.text = args.stock
        binding.tvPrice.text = args.price.toString()
        binding.tvPrecioDes.text = args.discountPrice.toString()
        binding.tvDisp.text = args.available.toString()
        Picasso.get()
            .load(args.imageURL)
            .resize(350,350)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imageView2)





        binding.btnDelete.setOnClickListener {
            //ir a list y pasar por parametro el id que va a borrar
            NetworkManager.service.deletePost(args.myId).enqueue(object :
                Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Elemento Eliminado", Toast.LENGTH_SHORT).show()
                        Log.e("Network", "Eliminacion Completa")
                        val generador = ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment()

                        findNavController().navigate(generador)

                    } else {
                        Log.e("Network", "error en la conexion on Response")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("Network", "error en la conexion",t)
                    Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
                }
            })
        }


        binding.btnBack.setOnClickListener {
            val generador = ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment()
            findNavController().navigate(generador)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun requestData() {

        NetworkManager.service.getProductDetailed(args.myId)?.enqueue(object : Callback<ProductObjectItem?> {

            override fun onFailure(call: Call<ProductObjectItem?>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esperábamos",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<ProductObjectItem?>,
                response: Response<ProductObjectItem?>
            ) {
                if (response.isSuccessful) {
                    Log.e("Retrofit", "Salió bien")
                } else {
                    Toast.makeText(context, "400", Toast.LENGTH_SHORT).show()
                }
                val productfav = ProductModel(
                response.body()?.stock.toString().toInt(),
                response.body()?.description.toString(),
                null,
                response.body()?.name.toString(),
                response.body()?.price.toString().toFloat(),
                response.body()?.discountPrice.toString().toFloat(),
                true,
                    response.body()?.imageURL.toString()
                )

                binding.btnfav.setOnClickListener {
                    val Comprobante = db.productDao().exists(binding.tvName.text.toString())
                    if(Comprobante == true){
                        Toast.makeText(context, "Ya en favoritos", Toast.LENGTH_SHORT).show()
                    }else{
                        db.productDao().add(productfav)
                        Toast.makeText(context, "Elemento añadido en Favoritos", Toast.LENGTH_SHORT).show()
                    }



                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





