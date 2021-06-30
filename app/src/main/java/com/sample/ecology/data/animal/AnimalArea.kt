package com.sample.ecology.data.animal
import com.google.gson.annotations.SerializedName


data class AnimalArea(
    @SerializedName("result")
    var result: Result
)

data class Result(
    @SerializedName("count")
    var count: Int,
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("results")
    var results: ArrayList<AnimalAreaItem>,
    @SerializedName("sort")
    var sort: String
)

data class AnimalAreaItem(
    @SerializedName("E_Category")
    var eCategory: String,
    @SerializedName("E_Geo")
    var eGeo: String,
    @SerializedName("E_Info")
    var eInfo: String,
    @SerializedName("E_Memo")
    var eMemo: String,
    @SerializedName("E_Name")
    var eName: String,
    @SerializedName("E_no")
    var eNo: String,
    @SerializedName("E_Pic_URL")
    var ePicURL: String,
    @SerializedName("E_URL")
    var eURL: String,
    @SerializedName("_id")
    var id: Int
)