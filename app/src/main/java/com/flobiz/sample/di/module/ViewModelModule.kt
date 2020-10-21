package com.flobiz.sample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flobiz.sample.di.util.ViewModelKey
import com.flobiz.sample.ui.MainViewModel
import com.flobiz.sample.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

}