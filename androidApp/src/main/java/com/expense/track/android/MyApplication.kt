package com.expense.track.android

import android.app.Application
import com.expense.track.initializeDatabase

class MyApplication:Application() {

    companion object{
        var instance:MyApplication?= null
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        initializeDatabase(this)
    }
}