package com.example.finalproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.thedogapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val dogBreedsService = retrofit.create(ApiService::class.java)

interface ApiService {
    @Headers("x-api-key: live_OLTnWu8TAJsdF069wfIMLtzTtFj6mDve4hG7KtYQzbm2egS36QgxahoZEYjv6yCm")
    @GET("breeds")
    suspend fun getBreeds(): List<Breed>

    //add get breed by id
    @Headers("x-api-key: live_OLTnWu8TAJsdF069wfIMLtzTtFj6mDve4hG7KtYQzbm2egS36QgxahoZEYjv6yCm")
    @GET("breeds/{id}")
    suspend fun getBreedById(@Path("id") id: String): Breed

    @Headers("x-api-key: live_OLTnWu8TAJsdF069wfIMLtzTtFj6mDve4hG7KtYQzbm2egS36QgxahoZEYjv6yCm")
    @GET("images/{id}")
    suspend fun getImageById(@Path("id") id: String): Image
}