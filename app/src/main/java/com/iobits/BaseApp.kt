package com.iobits

import android.app.Application
import com.iobits.domain.utils.SharedPreferenceHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    // Hilt requires an Application class annotated with @HiltAndroidApp
    // to trigger code generation.
    // Make sure to register this class in the AndroidManifest.xml file.
    // <application
    //     android:name=".MyApplication"
    //     ... >

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceHelper.init(this)
    }
}