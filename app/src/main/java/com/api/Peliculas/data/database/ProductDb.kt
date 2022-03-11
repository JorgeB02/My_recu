package com.api.Peliculas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductModel::class], version = 1)
abstract class ProductDb: RoomDatabase() {
   abstract fun productDao(): ProductDAO
}