package com.flobiz.sample.data.model

import com.flobiz.sample.constants.ApiConstants.CAPITAL
import com.flobiz.sample.constants.ApiConstants.FLAG
import com.flobiz.sample.constants.ApiConstants.NAME
import com.flobiz.sample.constants.ApiConstants.POPULATION
import com.flobiz.sample.constants.ApiConstants.TYPE
import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName(POPULATION) val population: Long,
    @SerializedName(FLAG) val flag: String,
    @SerializedName(NAME) val name: String,
    @SerializedName(CAPITAL) val capital: String,
    @SerializedName(TYPE) val cardType: String?
)