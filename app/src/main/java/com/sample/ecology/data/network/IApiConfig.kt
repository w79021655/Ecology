package com.sample.ecology.data.network

import com.sample.ecology.data.animal.AnimalArea
import com.sample.ecology.data.plant.PlantArea
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiConfig {
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getAnimalAreaList(): Call<AnimalArea>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getPlantAreaList(@Query("q") q: String, @Query("limit") limit: Int, @Query("offset") offset: Int): Call<PlantArea>
}