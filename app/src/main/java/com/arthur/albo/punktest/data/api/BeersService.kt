package com.arthur.albo.punktest.data.api

import com.arthur.albo.punktest.data.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeersService {
    @GET("beers")
    suspend fun getBeers(@Query("page")page : Int, @Query("per_page")perPage : Int): Response<List<Beer>>

    @GET("beers/{id}")
    suspend fun getBeer(@Path("id") id: Int): Response<Beer>
}