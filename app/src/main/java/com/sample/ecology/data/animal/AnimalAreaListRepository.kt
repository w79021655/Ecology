package com.sample.ecology.data.animal

import com.sample.ecology.Config
import com.sample.ecology.data.network.IApiConfig
import com.sample.ecology.data.network.RetrofitClient
import com.sample.ecology.ui.animal.IAnimalAreaContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalAreaListRepository: IAnimalAreaContract.IAnimalAreaRepository {
    private val apiService: IApiConfig = RetrofitClient.instance.api

    override fun getAnimalArea(onFinishedListener: IAnimalAreaContract.IAnimalAreaRepository.OnFinishedListener) {
        val task = apiService.getAnimalAreaList()

        task.enqueue(object : Callback<AnimalArea> {
            override fun onResponse(call: Call<AnimalArea>, response: Response<AnimalArea>) {
                if (response.isSuccessful) {
                    onFinishedListener.onFinished(response.body())
                } else {
                    onFinishedListener.onServerError()
                }
            }

            override fun onFailure(call: Call<AnimalArea>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}