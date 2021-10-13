package com.foster.whichdornobareyou

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface

class MyApplication : Application() {
    companion object {
        lateinit var myResources : Resources
    }

    override fun onCreate() {
        super.onCreate()
        myResources = resources
    }

    val Context.MyApplication: MyApplication
        get() = applicationContext as MyApplication
}