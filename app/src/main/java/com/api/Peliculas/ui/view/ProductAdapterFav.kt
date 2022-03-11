package com.api.Peliculas.ui.view
/*

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.api.Peliculas.R
import com.api.Peliculas.data.model.ProductObjectItem
import com.api.Peliculas.databinding.ProductItemBinding
import com.api.Peliculas.databinding.ProductItemFavBinding
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductAdapterFav(private val onProductClicked: (ProductObjectItem) -> Unit) :
    ListAdapter<ProductObjectItem, ProductAdapter.ViewHolder>(ProductItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Producto = getItem(position)

        Picasso.get()
            .load(Producto.imageURL)
            .resize(350, 350)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imageView)

        holder.binding.tvItemName.text = Producto.name

        val priceDescuento = (Producto.price - Producto.discountPrice)

        val df = DecimalFormat("####.##")
        df.roundingMode = RoundingMode.CEILING


        holder.binding.tvItemPrice.text = df.format(priceDescuento).toString()



        holder.binding.tvItemStock.text = Producto.stock.toString()


        if (Producto.stock <= 4) {
            holder.binding.textAviso.setVisibility(View.VISIBLE)
        } else {
            holder.binding.textAviso.setVisibility(View.INVISIBLE)
        }


        holder.binding.root.setOnClickListener {
            onProductClicked(Producto)
        }

        /*holder.binding.button.setOnClickListener{

        }*/
    }


    inner class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

}


class ProductItemCallback : DiffUtil.ItemCallback<ProductObjectItem>() {
    override fun areItemsTheSame(oldItem: ProductObjectItem, newItem: ProductObjectItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ProductObjectItem,
        newItem: ProductObjectItem
    ): Boolean = oldItem.id == newItem.id

}
*/
