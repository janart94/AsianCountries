package com.flobiz.sample.base

import android.app.Application
import com.flobiz.sample.constants.AppConstants.APP_NAME
import com.flobiz.sample.di.component.AppComponent
import com.flobiz.sample.di.component.DaggerAppComponent
import com.flobiz.sample.utils.PreferenceHelper

class BaseApplication : Application() {

    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppInjector(): AppComponent = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
        PreferenceHelper.setPref(this, APP_NAME)
    }

}
