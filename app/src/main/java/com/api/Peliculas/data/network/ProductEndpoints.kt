package com.api.Peliculas.data.network
import com.api.Peliculas.data.model.ProductObjectItem
import com.api.Peliculas.data.model.ProductObjectRequest
import retrofit2.Call
import retrofit2.http.*

interface ProductEndpoints {
    @GET("/product/")
    fun getProducts(): Call<List<ProductObjectItem>>

    @GET("/product/{id}")
    fun getProductDetailed(@Path("id") id: String): Call<ProductObjectItem>?

    @POST("/product/")
    fun savePost(@Body post: ProductObjectRequest): Call<ProductObjectRequest>

  //  @PUT("{id}")
  //  fun savePut(@Path("id")id: Int,@Body post: ProductObjectItem): Call<ProductObjectItem>

    @DELETE("/product/{id}")
    fun deletePost(@Path("id") id: String): Call<Void>
}