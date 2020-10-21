package com.flobiz.sample.data.rest

import com.flobiz.sample.data.model.Country
import com.flobiz.sample.constants.ApiConstants.ASIA_REGION
import com.flobiz.sample.constants.ApiConstants.FIELDS
import com.flobiz.sample.constants.ApiConstants.REQUIRED_FIELD
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApiService {
    @GET(ASIA_REGION)
    suspend fun getAllCountries(@Query(FIELDS) fields: String = REQUIRED_FIELD): ArrayList<Country>
}
