package com.example.appricottesttask

import android.app.Application
import android.content.Context
import com.example.appricottesttask.di.AppComponent
import com.example.appricottesttask.di.DaggerAppComponent

class AppricotApp : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}
val Context.appComponent: AppComponent
    get() = when (this) {
        is AppricotApp -> appComponent
        else -> applicationContext.appComponent
    }