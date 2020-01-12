package com.example.udemdyprj2

import android.app.Application
import android.content.Context
import com.example.udemdyprj2.db.AppDatabase

class RxApp : Application() {

    companion object{
        lateinit var INSTANCE: Context
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE=applicationContext
    }
}