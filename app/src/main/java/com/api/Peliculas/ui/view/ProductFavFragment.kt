package com.api.Peliculas.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.api.Peliculas.R
import com.api.Peliculas.data.model.ProductObjectItem
import com.api.Peliculas.databinding.FavProductBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fav_product.*

class ProductFavFragment : Fragment() {


    private var _binding: FavProductBinding? = null
    private val binding
        get() = _binding!!


    private val adapter = ProductAdapter {
        db.productDao().deleteProd(it.name)
        //val action = ProductFavFragmentDirections.actionProductFavFragment2ToProductDetailFragment(
        //  it.id
        //)
        //findNavController().navigate(action)
        refreshProducts()
        checkEmpty()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavProductBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkEmpty()
        binding.recyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerview.adapter = adapter
        refreshProducts()
    }


    private fun checkEmpty() {
        val mCursor: MutableList<ProductObjectItem> = db.productDao().findProducts()

        if (mCursor.size == 0) {
            binding.lblAviso.text = "No añadio nada a favoritos"
            imageView3.setImageResource(R.drawable.circulorojo)

        } else {
            binding.lblAviso.text = "Pulse en cualquier elemento para eliminarlo"
            imageView3.setImageResource(R.drawable.circuloverde)
        }
    }

    private fun refreshProducts() {
        val products = db.productDao().findProducts()
        adapter.submitList(products)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}