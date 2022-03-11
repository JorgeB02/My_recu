package com.api.Peliculas.data.model

import com.google.gson.annotations.SerializedName

data class ProductObjectRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("stock")
    val pack: Int,
    @SerializedName("regularPrice")
    val price: Float,
    @SerializedName("discountPrice")
    val discountPrice: Float,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("imageUrl")
    val imageURL: String
    )