package com.flobiz.sample.di.component

import android.app.Application
import com.flobiz.sample.di.module.AppModule
import com.flobiz.sample.di.module.ViewModelModule
import com.flobiz.sample.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
