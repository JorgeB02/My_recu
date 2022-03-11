package com.api.Peliculas.data.database

import androidx.room.*
import com.api.Peliculas.data.model.ProductObjectItem

@Dao
interface ProductDAO {
 @Query("SELECT * FROM Product ORDER BY Product.name")
 fun findProducts(): MutableList<ProductObjectItem>
 @Query("SELECT EXISTS (SELECT 1 FROM Product WHERE Product.name = :namef)")
 abstract fun exists(namef: String): Boolean
 @Query("SELECT * FROM Product WHERE Product.id = :productId LIMIT 1")
 fun findProductById(productId: Int):ProductModel
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun add(product: ProductModel)
 @Delete
 fun delete(product: ProductModel)
 @Query("DELETE FROM Product WHERE Product.name = :productName")
 fun deleteProd(productName: String)
 //@Query("SELECT COUNT(id) FROM Product")
 //fun checkIfEmpty()
}