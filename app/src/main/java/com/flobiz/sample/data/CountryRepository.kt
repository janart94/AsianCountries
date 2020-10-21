package com.flobiz.sample.data

import com.flobiz.sample.data.model.Country
import com.flobiz.sample.data.rest.CountriesApiClient
import com.flobiz.sample.constants.AppConstants
import com.flobiz.sample.constants.AppConstants.AD
import com.flobiz.sample.constants.AppConstants.AD_IMAGE_URL
import com.flobiz.sample.utils.PreferenceHelper
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countriesApiClient: CountriesApiClient) {

    suspend fun getAllCountries(): MutableList<Country> {
        val countriesList = countriesApiClient.getCountries()
        if (!PreferenceHelper.getPref().getBoolean(AppConstants.AD_DISABLED, false)) {
            countriesList.add(Country(0, AD_IMAGE_URL, "", "", AD))
        }
        return countriesList
    }

}