package com.sample.ecology.ui.plant

import com.sample.ecology.data.plant.PlantArea
import com.sample.ecology.ui.IBaseView

interface IPlantAreaContract {
    interface IPlantAreaView: IBaseView {
        fun fetchPlantArea()

        fun onPlantAreaResult(plantArea: PlantArea?)

        fun onResponseFailure(throwable: Throwable?)
    }

    interface IPlantAreaPresenter {

        fun getPlantArea(q: String, limit: Int, offset: Int)

        fun onDestroy()
    }

    interface IPlantAreaRepository {

        interface OnFinishedListener {
            fun onFinished(plantArea: PlantArea?)

            fun onFailure(t: Throwable?)

            fun onServerError()
        }

        fun getPlantArea(q: String, limit: Int, offset: Int, onFinishedListener: OnFinishedListener)
    }
}