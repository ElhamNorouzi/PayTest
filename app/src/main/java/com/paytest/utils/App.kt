package com.paytest.utils

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.paytest.di.AppModule
import com.paytest.di.NetModule
import com.paytest.di.component.AppComponents
import com.paytest.di.component.DaggerAppComponents


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
        // Setup Dagger2
        appComponents = initDaggerComponent()

    }

    companion object {
        lateinit var appComponents: AppComponents
    }

    // Setup Dagger2
    private fun initDaggerComponent(): AppComponents {
        appComponents = DaggerAppComponents.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .build()
        return appComponents
    }
}