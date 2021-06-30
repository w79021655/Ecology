package com.sample.ecology.ui.animal

import com.sample.ecology.data.animal.AnimalArea
import com.sample.ecology.data.animal.AnimalAreaListRepository

class AnimalAreaListPresenter(_view: IAnimalAreaContract.IAnimalAreaView) :
    IAnimalAreaContract.IAnimalAreaPresenter,
    IAnimalAreaContract.IAnimalAreaRepository.OnFinishedListener {

    private var view: IAnimalAreaContract.IAnimalAreaView? = null
    private var repository: AnimalAreaListRepository? = null

    init {
        this.view = _view
    }

    override fun getAnimalArea() {
        if (view != null) {
            view!!.showProgress()
        }

        repository = AnimalAreaListRepository()
        AnimalAreaListRepository().getAnimalArea(this)
    }

    override fun onDestroy() {
        view = null
        repository = null
    }

    override fun onFinished(animalArea: AnimalArea?) {
        if (view != null) {
            view!!.onAnimalAreaResult(animalArea)
            view!!.hideProgress()
        }
    }

    override fun onFailure(t: Throwable?) {
        if (view != null) {
            view!!.onResponseFailure(t)
            view!!.hideProgress()
        }
    }

    override fun onServerError() {
        if (view != null) {
            view!!.onServerError()
            view!!.hideProgress()
        }
    }
}