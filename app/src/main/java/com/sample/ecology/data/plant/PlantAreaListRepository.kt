package com.sample.ecology.data.plant

import com.sample.ecology.data.network.IApiConfig
import com.sample.ecology.data.network.RetrofitClient
import com.sample.ecology.ui.plant.IPlantAreaContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantAreaListRepository: IPlantAreaContract.IPlantAreaRepository {
    private val apiService: IApiConfig = RetrofitClient.instance.api

    override fun getPlantArea(
        q: String,
        limit: Int,
        offset: Int,
        onFinishedListener: IPlantAreaContract.IPlantAreaRepository.OnFinishedListener
    ) {
        val task = apiService.getPlantAreaList(q, limit, offset)

        task.enqueue(object : Callback<PlantArea> {
            override fun onResponse(call: Call<PlantArea>, response: Response<PlantArea>) {
                if (response.isSuccessful) {
                    onFinishedListener.onFinished(response.body())
                } else {
                    onFinishedListener.onServerError()
                }
            }

            override fun onFailure(call: Call<PlantArea>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}