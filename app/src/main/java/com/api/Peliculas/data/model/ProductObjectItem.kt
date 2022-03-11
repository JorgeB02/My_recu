package com.api.Peliculas.data.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductObjectItem(
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("regularPrice")
    val price: Float,
    @SerializedName("discountPrice")
    val discountPrice: Float,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("imageUrl")
    val imageURL: String
    )