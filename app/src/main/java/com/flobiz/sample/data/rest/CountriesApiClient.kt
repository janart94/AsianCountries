package com.flobiz.sample.data.rest

import com.flobiz.sample.data.model.Country
import javax.inject.Inject

class CountriesApiClient @Inject constructor(private val countriesApiService: CountriesApiService) {
    suspend fun getCountries(): MutableList<Country> = countriesApiService.getAllCountries()
}