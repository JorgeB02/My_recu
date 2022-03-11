package com.api.Peliculas.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductModel(
    @ColumnInfo(name = "stock")
    val stock: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val regularPrice: Float,
    @ColumnInfo(name = "discountPrice")
    val discountPrice: Float,
    @ColumnInfo(name = "available")
    val available: Boolean,
    @ColumnInfo(name = "imageURL")
    val imageUrl: String






)