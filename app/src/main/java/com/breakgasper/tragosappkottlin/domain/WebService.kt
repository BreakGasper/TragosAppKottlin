package com.breakgasper.tragosappkottlin.domain


import com.breakgasper.tragosappkottlin.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getTragooByName(@Query("s") tragoName:String): DrinkList
}