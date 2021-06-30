package com.sample.ecology.data.plant

import com.google.gson.annotations.SerializedName

 data class PlantArea(
    @SerializedName("result")
    var result: Plant
)

data class Plant(
    @SerializedName("count")
    var count: Int,
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("results")
    var results: ArrayList<PlantItem>,
    @SerializedName("sort")
    var sort: String
)

data class PlantItem(
    @SerializedName("F_AlsoKnown")
    var fAlsoKnown: String,
    @SerializedName("F_Brief")
    var fBrief: String,
    @SerializedName("F_CID")
    var fCID: String,
    @SerializedName("F_Code")
    var fCode: String,
    @SerializedName("F_Family")
    var fFamily: String,
    @SerializedName("F_Feature")
    var fFeature: String,
    @SerializedName("F_Function＆Application")
    var fFunction: String,
    @SerializedName("F_Genus")
    var fGenus: String,
    @SerializedName("F_Geo")
    var fGeo: String,
    @SerializedName("F_Keywords")
    var fKeywords: String,
    @SerializedName("F_Location")
    var fLocation: String,
    @SerializedName("F_Name_En")
    var fNameEn: String,
    @SerializedName("F_Name_Latin")
    var fNameLatin: String,
    @SerializedName("F_pdf01_ALT")
    var fPdf01ALT: String,
    @SerializedName("F_pdf01_URL")
    var fPdf01URL: String,
    @SerializedName("F_pdf02_ALT")
    var fPdf02ALT: String,
    @SerializedName("F_pdf02_URL")
    var fPdf02URL: String,
    @SerializedName("F_Pic01_ALT")
    var fPic01ALT: String,
    @SerializedName("F_Pic01_URL")
    var fPic01URL: String,
    @SerializedName("F_Pic02_ALT")
    var fPic02ALT: String,
    @SerializedName("F_Pic02_URL")
    var fPic02URL: String,
    @SerializedName("F_Pic03_ALT")
    var fPic03ALT: String,
    @SerializedName("F_Pic03_URL")
    var fPic03URL: String,
    @SerializedName("F_Pic04_ALT")
    var fPic04ALT: String,
    @SerializedName("F_Pic04_URL")
    var fPic04URL: String,
    @SerializedName("F_Summary")
    var fSummary: String,
    @SerializedName("F_Update")
    var fUpdate: String,
    @SerializedName("F_Vedio_URL")
    var fVedioURL: String,
    @SerializedName("F_Voice01_ALT")
    var fVoice01ALT: String,
    @SerializedName("F_Voice01_URL")
    var fVoice01URL: String,
    @SerializedName("F_Voice02_ALT")
    var fVoice02ALT: String,
    @SerializedName("F_Voice02_URL")
    var fVoice02URL: String,
    @SerializedName("F_Voice03_ALT")
    var fVoice03ALT: String,
    @SerializedName("F_Voice03_URL")
    var fVoice03URL: String,
    @SerializedName("_full_count")
    var fullCount: String,
    @SerializedName("_id")
    var id: Int,
    @SerializedName("rank")
    var rank: Double,
    @SerializedName("﻿F_Name_Ch")
    var fNameCh: String
)