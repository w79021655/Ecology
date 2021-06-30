package com.sample.ecology.ui.animal

import com.sample.ecology.data.animal.AnimalArea
import com.sample.ecology.ui.IBaseView

interface IAnimalAreaContract {
    interface IAnimalAreaView: IBaseView {
        fun fetchAnimalArea()

        fun onAnimalAreaResult(animalArea: AnimalArea?)

        fun onResponseFailure(throwable: Throwable?)
    }

    interface IAnimalAreaPresenter {

        fun getAnimalArea()

        fun onDestroy()
    }

    interface IAnimalAreaRepository {

        interface OnFinishedListener {
            fun onFinished(animalArea: AnimalArea?)

            fun onFailure(t: Throwable?)

            fun onServerError()
        }

        fun getAnimalArea(onFinishedListener: OnFinishedListener)
    }
}