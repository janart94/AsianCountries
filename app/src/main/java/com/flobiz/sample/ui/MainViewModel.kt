package com.flobiz.sample.ui

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flobiz.sample.R
import com.flobiz.sample.data.CountryRepository
import com.flobiz.sample.data.model.Country
import com.flobiz.sample.constants.AppConstants.AD_DISABLED
import com.flobiz.sample.utils.NetworkUtils.isConnectedToNetwork
import com.flobiz.sample.utils.PreferenceHelper
import com.flobiz.sample.utils.PreferenceHelper.set
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val application: Application,
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val countriesLiveData: MutableLiveData<MutableList<Country>> = MutableLiveData()
    val placesLiveData: LiveData<MutableList<Country>> get() = countriesLiveData

    private val loadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = loadingLiveData
    private lateinit var countryList: MutableList<Country>

    fun getCountryList() {
        if (isConnectedToNetwork(application)) {
            loadingLiveData.value = true
            viewModelScope.launch(Dispatchers.Main) {
                countryList = countryRepository.getAllCountries()
                if (countryList.isNotEmpty())
                    countriesLiveData.value = countryList
                else
                    Toast.makeText(application, R.string.try_again, Toast.LENGTH_SHORT).show()
                loadingLiveData.value = false
            }
        } else
            Toast.makeText(application, R.string.not_connected, Toast.LENGTH_SHORT).show()
    }

    override fun onCleared() {
        super.onCleared()
        countriesLiveData.value?.clear()
    }

    fun deleteAdPosition(it: Int) {
        PreferenceHelper.getPref()[AD_DISABLED] = true
        countryList.remove(countryList[it])
        countriesLiveData.postValue(countryList)
    }
}
