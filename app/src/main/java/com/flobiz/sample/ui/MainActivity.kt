package com.flobiz.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flobiz.sample.R
import com.flobiz.sample.base.BaseApplication.Companion.getAppInjector
import com.flobiz.sample.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAppInjector().inject(this)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )

        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val adapter = CountryListAdapter()

        binding.mainViewModel = mainViewModel
        binding.placesRecyclerView.adapter = adapter
        binding.lifecycleOwner = this
        adapter.onAdItemClick = {
            mainViewModel.deleteAdPosition(it)
        }

        mainViewModel.placesLiveData.observe(
            this,
            {
                it?.let {
                    adapter.submitList(it)
                }
            })

        mainViewModel.getCountryList()
    }
}
