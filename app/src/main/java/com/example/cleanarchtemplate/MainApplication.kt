package com.example.cleanarchtemplate

import android.app.Application
import com.example.cleanarchtemplate.di.localModule
import com.example.cleanarchtemplate.di.networkModule
import com.example.cleanarchtemplate.di.repositoryModule
import com.example.cleanarchtemplate.di.viewModelModule
import com.example.cleanarchtemplate.di.mainViewModelModule
import com.example.cleanarchtemplate.di.cartViewModelModule
import com.example.cleanarchtemplate.di.profileViewModelModule
import com.example.cleanarchtemplate.di.detailViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule, localModule, repositoryModule, viewModelModule, mainViewModelModule, cartViewModelModule, profileViewModelModule, detailViewModelModule)
        }
    }
}
