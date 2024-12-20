package com.expense.track.android

import android.app.Application
import com.expense.track.initializeDatabase

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDatabase(this)
    }
}