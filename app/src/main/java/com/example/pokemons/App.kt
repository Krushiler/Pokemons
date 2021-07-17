package com.example.pokemons

import android.app.Application
import com.example.pokemons.di.dataModule
import com.example.pokemons.di.navigationModule
import com.example.pokemons.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(navigationModule, viewModelsModule, dataModule)
        }
    }
}