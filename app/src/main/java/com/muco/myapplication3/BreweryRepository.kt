package com.muco.myapplication3

import javax.inject.Inject

class BreweryRepository @Inject constructor(
    private val breweryApi: BreweryApi
) {
    suspend fun getBreweries() = breweryApi.getBreweries()
}