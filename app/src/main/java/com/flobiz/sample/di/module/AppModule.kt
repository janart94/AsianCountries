package com.flobiz.sample.di.module


import com.flobiz.sample.data.CountryRepository
import com.flobiz.sample.data.rest.CountriesApiClient
import com.flobiz.sample.data.rest.CountriesApiService
import com.flobiz.sample.constants.ApiConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule {

    @Singleton
    @Provides
    fun providesRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCountryApiService(retrofit: Retrofit): CountriesApiService {
        return retrofit.create(CountriesApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesCountryApiClient(countriesApiService: CountriesApiService): CountriesApiClient {
        return CountriesApiClient(countriesApiService)
    }

    @Singleton
    @Provides
    fun providesCountryRepository(countriesApiClient: CountriesApiClient): CountryRepository {
        return CountryRepository(countriesApiClient)
    }
}
