package com.muco.myapplication3

import retrofit2.Response
import retrofit2.http.GET


interface BreweryApi {

    @GET("/breweries")
    suspend fun getBreweries(): Response<List<BreweryModel>>
}