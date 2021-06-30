package com.sample.ecology.ui.plant

import com.sample.ecology.data.plant.PlantArea
import com.sample.ecology.data.plant.PlantAreaListRepository

class PlantAreaListPresenter(_view: IPlantAreaContract.IPlantAreaView) :
    IPlantAreaContract.IPlantAreaPresenter,
    IPlantAreaContract.IPlantAreaRepository.OnFinishedListener {

    private var view: IPlantAreaContract.IPlantAreaView? = null
    private var repository: PlantAreaListRepository? = null

    init {
        this.view = _view
    }

    override fun getPlantArea(q: String, limit: Int, offset: Int) {
        if (view != null) {
            view!!.showProgress()
        }

        repository = PlantAreaListRepository()
        repository!!.getPlantArea(q, limit, offset, this)
    }

    override fun onDestroy() {
        view = null
        repository = null
    }

    override fun onFinished(plantArea: PlantArea?) {
        if (view != null) {
            view!!.onPlantAreaResult(plantArea)
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