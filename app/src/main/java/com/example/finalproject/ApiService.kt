package com.example.finalproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl("https://dogapi.dog/api/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val dogBreedsService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("breeds")
    suspend fun getBreeds(): BreedsResponse

    //add get breed by id
    @GET("breeds/{id}")
    suspend fun getBreedById(@Path("id") id: String): BreedByIdResponse

    //get group by id
//    @GET("groups/{id}")
//    suspend fun getGroupById(id: String): Group
}